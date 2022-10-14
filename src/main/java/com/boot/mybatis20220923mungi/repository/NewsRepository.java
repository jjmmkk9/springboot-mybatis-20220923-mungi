package com.boot.mybatis20220923mungi.repository;

import com.boot.mybatis20220923mungi.domain.News;
import com.boot.mybatis20220923mungi.domain.NewsFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper  //news.xml이 implements 클래스라고 생각하면됨 그래서 xml이 자동으로 ioc에 등록됨
public interface NewsRepository {
    public int save(News news);
    public int saveFiles(List<NewsFile> newsFileList);
    public News getNews(int news_id);
}
