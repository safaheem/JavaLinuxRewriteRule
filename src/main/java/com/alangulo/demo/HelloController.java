package com.alangulo.demo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
	

	 
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @GetMapping(value = "/home")
    public String home() {
        //initializeModel(model, token);

        return "home";
    }
    
 
    
}
