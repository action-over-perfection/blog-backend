package com.wateralsie.blog.article;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    public ArticleEntity(Article article) {
        this.id = article.id();
        this.title = article.title();
        this.content = article.content();
    }

    public Article toDomain() {
        return Article.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }

    public ArticleEntity update(Article article) {
        this.title = article.title();
        this.content = article.content();
        return this;
    }
}
