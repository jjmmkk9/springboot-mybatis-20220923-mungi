package com.boot.mybatis20220923mungi.dto;

import com.boot.mybatis20220923mungi.domain.NewsFile;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class NewsWriteResponseDto {
    private int id;
    private String title;
    private String writer;
    private String broadcastingName;
    private String content;

    private List<NewsFile> newsFileList;


}
