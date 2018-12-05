package se.agilecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Controller
public class EndpointController {
    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public EndpointController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @GetMapping("/endpoints")
    public String show(Model model) {
        //model.addAttribute("name", "Junaid Raza");
        model.addAttribute("handlerMethods", this.handlerMapping.getHandlerMethods());


        Map<RequestMappingInfo, HandlerMethod> requestMap = this.handlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : requestMap.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        //ModelAndView mv = new ModelAndView("")
        return "endpoints";
    }
}