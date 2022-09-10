package com.leui9179.book.springboot.service.posts;

import com.leui9179.book.springboot.domain.posts.Posts;
import com.leui9179.book.springboot.domain.posts.PostsRepository;
import com.leui9179.book.springboot.web.dto.PostsResponseDto;
import com.leui9179.book.springboot.web.dto.PostsSaveRequestDto;
import com.leui9179.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    // flask 의 Model 의 메서드 부분
    private final PostsRepository postsRepository;

    @Transactional
    // @Transactional: 메소드가 호출 될 경우, PlatformTransactionManager 를 사용하여 트랜잭션을 시작하고,
    // 정상 여부에 따라 Commit, Rollback 한다
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(posts);
    }
}
