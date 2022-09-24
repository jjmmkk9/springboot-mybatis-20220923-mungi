package com.boot.mybatis20220923mungi.controller.api;

import com.boot.mybatis20220923mungi.domain.News;
import com.boot.mybatis20220923mungi.dto.CMRespDto;
import com.boot.mybatis20220923mungi.dto.NewsWriteReqDto;
import com.boot.mybatis20220923mungi.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api") //이건 먼데
@RestController
@RequiredArgsConstructor
public class NewsController {


    private final NewsRepository newsRepository; // <- ioc에서 자동으로 인터페이스 implements된 news.xml 연결해줌

    @PostMapping("/news")
    public ResponseEntity<?> write(NewsWriteReqDto newsWriteReqDto){
        log.info("{}", newsWriteReqDto); //dto -> news객체에 넣기 -> repository -> xml -> db!!

        News news = newsWriteReqDto.toEntity(); //dto에 있는 정보들이 news객체 만드는데 사용됨 -> news객체 담음
        news.setNews_writer("조문기");

        newsRepository.save(news);



        return ResponseEntity.ok(new CMRespDto<>(1, "새 news 작성 성공",null));
    }



}
