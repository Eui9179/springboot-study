package com.leui9179.book.springboot.service.posts;

import com.leui9179.book.springboot.domain.posts.PostsRepository;
import com.leui9179.book.springboot.web.dto.PostsSaveRequestDto;
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
}
