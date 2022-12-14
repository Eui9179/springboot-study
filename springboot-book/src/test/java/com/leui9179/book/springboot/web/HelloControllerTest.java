package com.leui9179.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
@RunWith: 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴.
즉, 스프링부트 테스트와 JUnit 사이에 연결자 역할을 함.
*/
@RunWith(SpringRunner.class)

// @WebMvcTest: 여러 스프링 테스트 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired // @Autowired: 스프링이 관리하는 빈(Bean)을 주입받는다.

    /*MockMvc: 웹 api 테스트할 때 사용
     스프링 MVC 테스트의 시작점, 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.*/
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증, 200 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증, Controller에서 "hello"가 리턴 되었는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name) // api  테스트할 때 사용될 요청 파라미터를 설정한다(String 만 가능)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                //jsonPath: JSON 응답값을 필드별로 검증할 수 있는 메소드이다.
                //$를 기준으로 필드명을 명시한다.
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
