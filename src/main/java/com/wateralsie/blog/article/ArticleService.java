package com.wateralsie.blog.article;

import com.wateralsie.blog.common.exception.BlogException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(ArticleEntity::toDomain)
                .toList();
    }

    public Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(
                        () -> new BlogException(HttpStatus.NOT_FOUND.value(), "해당 id를 가진 글이 존재하지 않습니다 : " + articleId))
                .toDomain();
    }

    public Article createArticle(Article article) {
        return articleRepository.save(new ArticleEntity(article)).toDomain();
    }

    public Article updateArticle(Long articleId, Article article) {
        ArticleEntity originalArticle = articleRepository.findById(articleId)
                .orElseThrow(
                        () -> new BlogException(HttpStatus.NOT_FOUND.value(), "해당 id를 가진 글이 존재하지 않습니다 : " + articleId));
        originalArticle.update(article);
        return articleRepository.save(originalArticle).toDomain();
    }

    public void deleteArticle(Long articleId) {
        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(
                        () -> new BlogException(HttpStatus.NOT_FOUND.value(), "해당 id를 가진 글이 존재하지 않습니다 : " + articleId));
        articleRepository.delete(article);
    }
}
