package com.leui9179.book.springboot.service.posts;

import com.leui9179.book.springboot.domain.posts.Posts;
import com.leui9179.book.springboot.domain.posts.PostsRepository;
import com.leui9179.book.springboot.web.dto.PostsListResponseDto;
import com.leui9179.book.springboot.web.dto.PostsResponseDto;
import com.leui9179.book.springboot.web.dto.PostsSaveRequestDto;
import com.leui9179.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)// = .map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(posts);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
