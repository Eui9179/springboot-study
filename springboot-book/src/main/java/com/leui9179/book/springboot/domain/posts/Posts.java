package com.leui9179.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 생성
@Entity // 테이블과 링크될 클래스(카멜케이스를 언더스코어네이밍으로 매칭 시킴)
// Entity 클래스에서는 절대 Setter 메소드를 만들지 않음. 값을 변경할 일이 있다면 명확한 목적의 메소드를 추가한다.
public class Posts { // Posts 클래스는 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity 클래스라고 부른다.

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 스프링부트 2.0에서는 옵션을 추가해야 auto_increment 가 된다.
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    /*
    * Builder 패턴
    * Example.builder().a(a).b(b).build();
    * */
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
