package com.boot.mybatis20220923mungi.dto;

import com.boot.mybatis20220923mungi.domain.News;
import lombok.Data;
import org.apache.tomcat.jni.Multicast;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Data
public class NewsWriteReqDto {
    private String title;
    private String broadcastingName;
    private String writer;
    private String content;
    private List<MultipartFile> files;


    public News toEntity(String writer){

        return News.builder()
                .news_title(title)
                .news_writer(writer)
                .news_broadcasting(broadcastingName)
                .news_content(content)
                .build();
    }
}
