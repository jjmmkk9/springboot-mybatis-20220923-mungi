package com.boot.mybatis20220923mungi.domain;

import com.boot.mybatis20220923mungi.dto.NewsReadRespDto;
import com.boot.mybatis20220923mungi.dto.NewsWriteResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
뉴스 객체가 바로 db에 들어감
domain의 클래스는 변수명과 db열 이름이 같아야한다.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//셀렉트할 때 생성해서 달아줘야해서 no, allargsConstructor달아주는거래
public class News {
    private int news_id;

    private String news_title;
    private String news_writer;
    private String news_broadcasting;
    private String news_content;

    //newsfile을 리스트르 받아줘야
    private List<NewsFile> news_file;

    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public NewsReadRespDto toNewsReadRespDto(){
        return NewsReadRespDto.builder()
                .id(news_id)
                .title(news_title)
                .writer(news_writer)
                .broadcastingName(news_broadcasting)
                .content(news_content)
                .createDate(create_date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")))
                .fileList(news_file)
                .build();
    }

    public NewsWriteResponseDto toNewsWriteRespDto(List<NewsFile> newsFileList){
        return NewsWriteResponseDto.builder()
                .id(news_id)
                .title(news_title)
                .writer(news_writer)
                .broadcastingName(news_broadcasting)
                .content(news_content)
                .newsFileList(newsFileList) //파일리스트를 컨트롤러에서 만들어서 response에 같이 넣어줌
                .build();
    }

}
