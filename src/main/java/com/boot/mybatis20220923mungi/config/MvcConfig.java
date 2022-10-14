package com.boot.mybatis20220923mungi.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


//설정해주는 객체는 어노테이션 Configuration 해준다.
//@Configuration
//public class MvcConfig implements WebMvcConfigurer {
//
//    /*
//    파일 패스로 image 불러오기
//     */
//    @Value("${file.path}")
//    private String filePath;
//
//
//    /*
//    전체적인 mvc 통신에 대해 세팅해주기
//    registry = 설정값
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//        registry.addResourceHandler("/image/**")
//                .addResourceLocations("file:///" + filePath)
//                //경로를 한시간 동안 캐시 유지
//                .setCachePeriod(60 * 60)
//                //캐시 데이터랑 연결시켜주겠다.
//                .resourceChain(true)
///*익명 메소드*/   .addResolver(new PathResourceResolver(){
//                    @Override
//                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
//
//                        //경로중에 한글이 있으면 한글 path 를 한글로 encoding해주는 역할
//                        resourcePath = URLDecoder.decode(resourcePath, StandardCharsets.UTF_8);
//                        return super.getResource(resourcePath, location);
//                    }
//                });
//    }
//}
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///" + filePath)
                .setCachePeriod(60 * 60)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        resourcePath = URLDecoder.decode(resourcePath, StandardCharsets.UTF_8);
                        return super.getResource(resourcePath, location);
                    }
                });
    }

}
