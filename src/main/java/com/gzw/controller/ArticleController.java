package com.gzw.controller;

import com.alibaba.fastjson.JSON;
import com.gzw.domain.Article;
import com.gzw.domain.ResultInfo;
import com.gzw.enums.ResultCode;
import com.gzw.service.ArticleService;
import com.gzw.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by gujian on 2017/6/23.
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @GetMapping(value = "/{nickName}")
//    public String getArticleByAuthor(@PathVariable("nickName") String nickName){
//
//        return JSON.toJSONString(articleService.getArticleByAuthor(nickName));
//    }

    @PostMapping(value = "/do_write")
    public String writeArticle(Article article, HttpSession session) {

        return JSON.toJSONString(articleService.writeArticle(article, session));
    }
}
