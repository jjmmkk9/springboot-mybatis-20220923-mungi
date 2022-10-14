package com.boot.mybatis20220923mungi.controller.api;

import com.boot.mybatis20220923mungi.domain.News;
import com.boot.mybatis20220923mungi.domain.NewsFile;
import com.boot.mybatis20220923mungi.dto.CMRespDto;
import com.boot.mybatis20220923mungi.dto.NewsReadRespDto;
import com.boot.mybatis20220923mungi.dto.NewsWriteReqDto;
import com.boot.mybatis20220923mungi.dto.NewsWriteResponseDto;
import com.boot.mybatis20220923mungi.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.AbstractDocument;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsController {

    /*
    newsController가 생성될 때 변수에 Value의 값이 주입된다.
    autoWired 같은 기능 autoWired는 객체를 주입하고 value는 변수에 값을 주입함.
    ${file.path}는 표현식으로 yml의 경로 설정을 그대로 가지고 온 것, 이렇게 경로를 잡아주면 하나하나 다시 설정할 필요 없이
    yml만 바꿔주어서 모든 파일에서 경로를 관리할 수 있다.
     */
    @Value("${file.path}")
    private String filePath;
    private final NewsRepository newsRepository;

    @PostMapping("/news")
    public ResponseEntity<?> writeNews(NewsWriteReqDto newsWriteReqDto){
        log.info("{}", newsWriteReqDto);
        List<NewsFile> newsFileList = null;

        MultipartFile firstFile = newsWriteReqDto.getFiles().get(0);
        String firstFileName = firstFile.getOriginalFilename();
        if(!firstFileName.isBlank()){
            log.info("파일 입출력을 합니다.");
            newsFileList = new ArrayList<NewsFile>();

            for(MultipartFile file : newsWriteReqDto.getFiles()){
                String originFileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();

                String extension = originFileName.substring(originFileName.lastIndexOf("."));
                String tempFileName = uuid + extension;
                Path uploadPath = Paths.get(filePath + "news/" + tempFileName);

                File f = new File(filePath + "news");

                //경로에 실제 파일 없으면 디렉토리를 만들어라?
                if(!f.exists()){
                    f.mkdirs();
                }
                //file 의 bytes 를 가져와서 write 하겠다ㅣ
                try {
                    Files.write(uploadPath, file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                NewsFile newsFile = NewsFile
                        .builder()
                        .file_origin_name(originFileName)
                        .file_temp_name(tempFileName)
                        .build();
                newsFileList.add(newsFile);
            }
        }
        News news = newsWriteReqDto.toEntity("chomungi");
        int result =  newsRepository.save(news);//insert news_mst

        if(result == 0){ //건수 0건이면 서버문제잖아
            return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "작성 실패", news));
        }
        if(newsFileList != null){                       //newsFileList 에 값이 있는지 체크
            for(NewsFile newsFile : newsFileList){      //있으면 newsFile 꺼내서
                newsFile.setNews_id(news.getNews_id()); //파일마다 set news id
            }
            result = newsRepository.saveFiles(newsFileList);//insert news_file
            if(result != newsFileList.size()){
                return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "파일 업로드 실패", newsFileList));

            }
        }
        NewsWriteResponseDto newsWriteResponseDto = news.toNewsWriteRespDto(newsFileList);
        return ResponseEntity.ok(new CMRespDto<>(1, "새 news 작성 성공", news));
    }


    @GetMapping("/news/{newsId}")
    public ResponseEntity<?> read(@PathVariable int newsId){

        log.info("{}", newsId);

        News news = newsRepository.getNews(newsId);
        log.info("{}", news);



        NewsReadRespDto newsReadRespDto = news.toNewsReadRespDto();

        return ResponseEntity.ok(new CMRespDto<>(1, "게시글 불러오기 성공", newsReadRespDto));
    }


}
