package com.boot.mybatis20220923mungi.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Slf4j
@RequestMapping("/download")
public class DownloadController {

    @Value("${file.path}")
    private String filePath;

    /*
    다운로드 기본 세팅
     */
    @GetMapping("/news") // /download/news
    public ResponseEntity<?> download(@RequestParam String originFileName, @RequestParam String tempFileName ) throws IOException {

    //MIME 로 컨텐츠 타입 가져오기
        Path path = Paths.get(filePath + "news/" + tempFileName);
        //MIME 형식으로 바꿔줌 (경로파일)  => image/png (원래는 application/json 이런거 )
        String contentType = Files.probeContentType(path);
        log.info("ContentType: {}", contentType);


    //HttpHeader 에 타입 설정해주기
        HttpHeaders headers = new HttpHeaders();
        ContentDisposition contentDisposition =
                ContentDisposition
                .builder("attachment")
                .filename(originFileName, StandardCharsets.UTF_8)
                .build();
        headers.setContentDisposition(contentDisposition);
                                                    //해당 (path)에 들어있는 것을 inputStream 객체로 java 에 입력  ==> input
        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
