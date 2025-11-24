package com.wateralsie.blog.article;

import com.wateralsie.blog.common.dto.BlogResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BlogResponse<List<Article>> getAllArticles() {
        return BlogResponse.of(
                "글 목록 조회 성공",
                articleService.getAllArticles()
        );
    }

    @GetMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public BlogResponse<Article> getArticleById(@PathVariable Long articleId) {
        return BlogResponse.of(
                "글 조회 성공",
                articleService.getArticleById(articleId)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogResponse<Article> postArticle(@RequestBody WriteArticleRequest request) {
        return BlogResponse.of(
                "글 작성 성공",
                articleService.createArticle(request.toDomain())
        );
    }

    @PatchMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public BlogResponse<Article> patchArticle(@PathVariable Long articleId, @RequestBody WriteArticleRequest request) {
        return BlogResponse.of(
                "글 수정 성공",
                articleService.updateArticle(articleId, request.toDomain())
        );
    }

    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.OK)
    public BlogResponse<Void> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return BlogResponse.of("글 삭제 성공");
    }
}