package com.wateralsie.blog;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public Iterable<ArticleEntity> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/{articleId}")
    public ArticleEntity getArticleById(@PathVariable Long articleId) {
        return articleRepository.findById(articleId)
            .orElseThrow(() -> new BlogException(HttpStatus.NOT_FOUND.value(), "해당 id를 가진 글이 존재하지 않습니다 : " + articleId));
    }

    @PostMapping
    public ResponseEntity<String> postArticle(@RequestBody Article article) {
        ArticleEntity newArticle = ArticleEntity.from(article);
        articleRepository.save(newArticle);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<String> patchArticle(@PathVariable Long articleId, @RequestBody Article article) {
        ArticleEntity originalArticle = articleRepository.findById(articleId)
            .orElseThrow(() -> new BlogException(HttpStatus.NOT_FOUND.value(), "해당 id를 가진 글이 존재하지 않습니다 : " + articleId));

        if (!Objects.equals(originalArticle.getTitle(), article.title())) {
            originalArticle.setTitle(article.title());
        }
        if (!Objects.equals(originalArticle.getContent(), article.content())) {
            originalArticle.setContent(article.content());
        }
        articleRepository.save(originalArticle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long articleId) {
        ArticleEntity article = articleRepository.findById(articleId)
            .orElseThrow(() -> new BlogException(HttpStatus.NOT_FOUND.value(), "해당 id를 가진 글이 존재하지 않습니다 : " + articleId));
        articleRepository.delete(article);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}