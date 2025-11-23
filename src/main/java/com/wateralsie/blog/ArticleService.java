package com.wateralsie.blog;

import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Iterable<ArticleEntity> getAllArticles() {
        return articleRepository.findAll();
    }

    public ArticleEntity getArticleById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(
                        () -> new BlogException(HttpStatus.NOT_FOUND.value(), "해당 id를 가진 글이 존재하지 않습니다 : " + articleId));
    }

    public ArticleEntity createArticle(Article article) {
        ArticleEntity newArticle = ArticleEntity.from(article);
        return articleRepository.save(newArticle);
    }

    public ArticleEntity updateArticle(Long articleId, Article article) {
        ArticleEntity originalArticle = articleRepository.findById(articleId)
                .orElseThrow(
                        () -> new BlogException(HttpStatus.NOT_FOUND.value(), "해당 id를 가진 글이 존재하지 않습니다 : " + articleId));

        if (!Objects.equals(originalArticle.getTitle(), article.title())) {
            originalArticle.setTitle(article.title());
        }
        if (!Objects.equals(originalArticle.getContent(), article.content())) {
            originalArticle.setContent(article.content());
        }
        return articleRepository.save(originalArticle);
    }

    public void deleteArticle(Long articleId) {
        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(
                        () -> new BlogException(HttpStatus.NOT_FOUND.value(), "해당 id를 가진 글이 존재하지 않습니다 : " + articleId));
        articleRepository.delete(article);
    }
}
