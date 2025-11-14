package com.wateralsie.blog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    private final AtomicLong counter = new AtomicLong();
    private static final List<Article> ARTICLE_LIST = List.of(
            new Article(1, "title1", "content1"),
            new Article(2, "title2", "content2"),
            new Article(3, "title3", "content3"),
            new Article(4, "title4", "content4"),
            new Article(5, "title5", "content5")
    );

    @GetMapping("/articles")
    public List<Article> getArticles() {
        return new ArrayList<>(ARTICLE_LIST);
    }

    @GetMapping("/article")
    public Article getArticleById(@RequestParam(defaultValue = "1") long id) {
        return ARTICLE_LIST.getFirst();
    }
}