package se.agilecourse.model;

import org.springframework.web.multipart.MultipartFile;

public class WrapperUploadFile {
    MultipartFile file;
    String ProductId;

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    MultipartFile[] files;
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }
}
