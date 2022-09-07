package com.leui9179.book.springboot.web.dto;

import com.leui9179.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* Dto: 클라이언트에서 넘어온 데이터 맵핑
* domain.posts.Posts: 데이터 베이스 맞닿은 클래스
* */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    // flask 의 Schema 에 해당
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
