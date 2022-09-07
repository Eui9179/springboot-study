package com.leui9179.book.springboot.web;

import com.leui9179.book.springboot.service.posts.PostsService;
import com.leui9179.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // postsService 인스턴스 생성
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        // @RequestBody: 넘어온 json 데이터를 PostsSaveRequestDto 객체로 맵핑
        return postsService.save(requestDto);
    }
}
