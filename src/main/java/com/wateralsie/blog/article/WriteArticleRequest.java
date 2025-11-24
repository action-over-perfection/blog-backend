package com.wateralsie.blog.article;

public record WriteArticleRequest(String title, String content) {
    public Article toDomain() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
