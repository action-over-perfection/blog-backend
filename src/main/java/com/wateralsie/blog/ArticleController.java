package com.wateralsie.blog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public Iterable<ArticleEntity> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{articleId}")
    public ArticleEntity getArticleById(@PathVariable Long articleId) {
        return articleService.getArticleById(articleId);
    }

    @PostMapping
    public ResponseEntity<String> postArticle(@RequestBody Article article) {
        articleService.createArticle(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<String> patchArticle(@PathVariable Long articleId, @RequestBody Article article) {
        articleService.updateArticle(articleId, article);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}