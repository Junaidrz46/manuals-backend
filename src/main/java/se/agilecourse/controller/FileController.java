package se.agilecourse.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
import se.agilecourse.services.CategoryServices;
import se.agilecourse.services.FileStorageService;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CategoryServices categoryServices;

    @PostMapping("/uploadFile")
    public Material uploadFile(@RequestParam("ProductId") String productId,
                               @RequestParam("file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/file/downloadFile/")
                .path(fileName)
                .toUriString();
        Material material = new Material();
        material.setFileName(fileName);
        material.setFileDownloadUri(fileDownloadUri);
        material.setFileType(file.getContentType());
        material.setSize(file.getSize());

        return categoryServices.saveMaterialByProduct(material,productId);
    }

    @PostMapping("/uploadProfileImage")
    public Material uploadProfileImage(@RequestParam("ProductId") String productId,
                               @RequestParam("file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/file/downloadFile/")
                .path(fileName)
                .toUriString();
        Material material = new Material();
        material.setFileName(fileName);
        material.setFileDownloadUri(fileDownloadUri);
        material.setFileType(file.getContentType());
        material.setSize(file.getSize());

        Material savedMaterial = categoryServices.saveMaterialByProduct(material,productId);

        return  categoryServices.saveMaterialAsProfileImage(productId,material);

    }

    @PostMapping("/uploadMultipleFiles")
    public List<Material> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("ProductId") String productId) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(productId,file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}