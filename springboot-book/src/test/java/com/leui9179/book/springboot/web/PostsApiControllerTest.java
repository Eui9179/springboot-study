package com.leui9179.book.springboot.web;


import com.leui9179.book.springboot.domain.posts.Posts;
import com.leui9179.book.springboot.domain.posts.PostsRepository;
import com.leui9179.book.springboot.web.dto.PostsResponseDto;
import com.leui9179.book.springboot.web.dto.PostsSaveRequestDto;
import com.leui9179.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록하기() throws Exception {
        //given
        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto
                .builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void Posts_업데이트하기() throws Exception {
        //given
        String newTitle = "new title";
        String newContent = "new content";

        // 데이터 베이스에 직접 저장
        Posts savedPosts = postsRepository.save(Posts
                .builder()
                .title("title")
                .content("content")
                .author("leui9179")
                .build());

        Long updateId = savedPosts.getId();

        //update dto 생성
        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto
                .builder()
                .title(newTitle)
                .content(newContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(postsUpdateRequestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, Long.class
        );

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(newTitle);
        assertThat(all.get(0).getContent()).isEqualTo(newContent);
    }

    @Test
    public void Posts_가져오기() {
        Posts posts = postsRepository.save(Posts
                .builder()
                .title("title")
                .content("content")
                .author("Leui9179")
                .build()
        );

        Long postsId = posts.getId();

        String url = "http://localhost:" + port + "/api/v1/posts/" + postsId;

        ResponseEntity<PostsResponseDto> responseEntity = restTemplate.getForEntity(url, PostsResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getTitle()).isEqualTo("title");

    }
}
