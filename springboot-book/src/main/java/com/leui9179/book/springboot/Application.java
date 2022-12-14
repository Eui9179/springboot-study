package com.leui9179.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
// 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정됨
// @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트 최상단에 위치해야한다.
public class Application { // Application 클래스는 앞으로 만들 프로젝트의 메인 클래스가 된다.
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS(web application server)를 실행
    }
}
