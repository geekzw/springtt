package com.gzw.service;

import com.alibaba.fastjson.JSON;
import com.gzw.domain.Article;
import com.gzw.domain.ResultInfo;
import com.gzw.domain.User;
import com.gzw.enums.ResultCode;
import com.gzw.mappers.ArticleMapper;
import com.gzw.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by gujian on 2017/6/23.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public ResultInfo getArticleByAuthor(String nickName){
        ResultInfo resultInfo;
        List<Article> list = articleMapper.findByAuthor(nickName);
        if(list == null || list.size()<=0){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.RESULT_EMPTY);
        }else{
            resultInfo =  ResultInfo.getSuccessWithInfo(ResultCode.RESULT_SUCCESS,list);
        }
        return resultInfo;
    }

    public ResultInfo writeArticle(Article article, HttpSession session){
        ResultInfo resultInfo;
        if(StringUtil.isEmpty(article.getTitle())){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.TITLE_EMPTY);
        }else if(StringUtil.isEmpty(article.getContent())){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.CONENT_EMPTY);
        }else{
            User user = (User) session.getAttribute("user");
            article.setUsername(user.getUsername());
            articleMapper.insert(article);
            resultInfo = ResultInfo.getSuccessInfo(ResultCode.RESULT_SUCCESS);
        }
        return resultInfo;
    }
}
