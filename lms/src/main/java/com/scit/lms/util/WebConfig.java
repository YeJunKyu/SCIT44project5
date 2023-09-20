package com.scit.lms.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${memberUpload.path}")
    private String memberUploadPath;
    @Value("${memberphoto.path}")
    private String memberphotoPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/memberphoto/**")
                .addResourceLocations("file:///c:/lms/uploadFile/memberphoto/");
        //registry.addResourceHandler(memberUploadPath)
         //       .addResourceLocations(memberphotoPath);
    }
}
