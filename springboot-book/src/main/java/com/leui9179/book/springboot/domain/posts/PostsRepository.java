package com.leui9179.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
// 보통 ibatis, MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자이다. JPA 에선 Repository 라고 부른다.
public interface PostsRepository extends JpaRepository<Posts, Long> { // <Entity 클래스, PK 타입>

}
