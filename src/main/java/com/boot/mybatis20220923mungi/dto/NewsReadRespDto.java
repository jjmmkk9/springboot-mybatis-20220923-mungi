package com.boot.mybatis20220923mungi.dto;


import com.boot.mybatis20220923mungi.domain.NewsFile;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class NewsReadRespDto {

    private int id;
    private String title;
    private String writer;
    private String broadcastingName;
    private String content;

    //다운로드할때 file 이름 눌러서 다운 할 수 있어야 합니다 -> 파일 이름 보내주기 위해 생성 해줍니다.
    private List<NewsFile> fileList;

    private String createDate;
    private String updateDate;



}
