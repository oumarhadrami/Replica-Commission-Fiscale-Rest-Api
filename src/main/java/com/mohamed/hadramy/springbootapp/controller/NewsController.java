package com.mohamed.hadramy.springbootapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mohamed.hadramy.springbootapp.model.News;
import com.mohamed.hadramy.springbootapp.repository.NewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news_api/v1/")
public class NewsController {

    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/news")
    public ResponseEntity<Map<String, Object>> getAllNewsPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {

        try {
            List<News> news = new ArrayList<News>();
            Pageable firstPageWithEightItems = PageRequest.of(page, size);
            Page<News> newsPage = newsRepository.findAllByOrderByIdDesc(firstPageWithEightItems);
            news = newsPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("news", "vuy");
            response.put("news", news);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable("id") Long id){
        Optional<News> newsItem = newsRepository.findById(id);
        if (newsItem.isPresent())  return new ResponseEntity<>(newsItem.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
