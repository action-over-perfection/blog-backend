package com.wateralsie.blog;

import lombok.Builder;

@Builder
public record Article(Long id, String title, String content) {
}
