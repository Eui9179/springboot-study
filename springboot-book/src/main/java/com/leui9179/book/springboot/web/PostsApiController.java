package com.leui9179.book.springboot.web;

import com.leui9179.book.springboot.service.posts.PostsService;
import com.leui9179.book.springboot.web.dto.PostsResponseDto;
import com.leui9179.book.springboot.web.dto.PostsSaveRequestDto;
import com.leui9179.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // postsService 인스턴스 생성
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        // @RequestBody: 넘어온 json 데이터를 PostsSaveRequestDto 객체로 맵핑
        return postsService.save(requestDto);
    }

    @GetMapping("api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }


}
