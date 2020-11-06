package com.ys.appler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
@Import({FileConfig.class})
@ComponentScan(basePackages = {"com.ys.appler"})
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**",
                                      "/summernoteImage/**",
                                          "/img/**",
                                            "/css/**",
                                           "/js/**")
                .addResourceLocations("file:c:" + File.separator + "temp/",
                        "file:///C:/summernote_image/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }
}