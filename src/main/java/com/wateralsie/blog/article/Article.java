package com.wateralsie.blog.article;

import lombok.Builder;

@Builder
public record Article(Long id, String title, String content) {
}
