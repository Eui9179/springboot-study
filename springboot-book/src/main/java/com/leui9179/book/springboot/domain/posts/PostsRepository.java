package com.leui9179.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 보통 ibatis, MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자이다. JPA 에선 Repository 라고 부른다.
public interface PostsRepository extends JpaRepository<Posts, Long> { // <Entity 클래스, PK 타입>
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    // SpringDataJpa에서 제공되지 않는 메소드는 위처럼 쿼리로 작성 가능하다.
    List<Posts> findAllDesc();
}
