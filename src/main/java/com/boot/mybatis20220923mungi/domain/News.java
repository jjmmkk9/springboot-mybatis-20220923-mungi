package com.boot.mybatis20220923mungi.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/*
뉴스 객체가 바로 db에 들어감
 */
@Builder
@Data
public class News {
    private int news_id;

    private String news_title;
    private String news_writer;
    private String news_broadcasting;
    private String news_content;

    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
