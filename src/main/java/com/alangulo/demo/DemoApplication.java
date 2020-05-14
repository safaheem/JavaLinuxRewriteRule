package com.alangulo.demo;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
	
	
	@Component
	public class CoderURLRewriteFilter extends UrlRewriteFilter {
	 
	    private static final String CONFIG_LOCATION = "classpath:/urlrewrite.xml";
	 
	    @Value(CONFIG_LOCATION)
	    private Resource resource;
	 
	    @Override
	    protected void loadUrlRewriter(FilterConfig filterConfig) throws ServletException {
	        try {
	            Conf conf = new Conf(filterConfig.getServletContext(), resource.getInputStream(), resource.getFilename(), "");
	        checkConf(conf);
	        } catch (IOException ex) {
	            throw new ServletException("Unable to load URL-rewrite configuration file from " + CONFIG_LOCATION, ex);
	        }
	    }
	}


}
