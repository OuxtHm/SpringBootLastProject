package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

/*NO         NOT NULL NUMBER       
CNO                 NUMBER       
TYPE                NUMBER       
ID                  VARCHAR2(20) 
NAME       NOT NULL VARCHAR2(51) 
SEX                 VARCHAR2(6)  
MAG        NOT NULL CLOB         
REGDATE             DATE         
GROUP_ID            NUMBER       
GROUP_STEP          NUMBER       
GROUP_TAB           NUMBER       
ROOT                NUMBER       
DEPT                NUMBER


1. Spring 기반 => 애플리케이션을 빠르고 쉽게 개발을 위한 프레임 워크
	= 설정 최소화
	= 빠른 실행(내장 => 톰캣서버) = CI / CD
	= 독립적 실행
	---------------------------------------------
	** SpringFramework 		Spring-Boot
		외부 tomcat 사용		 내장 Tomcat
		시작 : 복합			 속도 빠르다
	---------------------------------------------
		src/main/java
			| => 자바 클래스
		src/main/resource
			| => image / css / js
		=> ThymeLeaf 중심 : 보조 : JSP
		=> 전자정부 프레임워크 : Spring 5 => SpringFramwork 기반
	---------------------------------------------
		SpringFramework : SML + Annotation
		Spring-Boot : Annotation
		1. 구동
			@SpringBootApplication => main
		2. 메모리 할당
			@Component / @Repository / @Service / @Controller / @RestController
		3. DI : 객체 주입
			@Autowired => @RequiredArgsConstructor
							=> 생성자 만들고 생성자에서 주입
		4. 웹 구동
			@GetMapping / @PostMapping / @RequestMapping
			=> 값을 받는 경우
				@RequestParam
				@ModelAttribute => VO단위
				@RequestBody => JSON을 객체 단위로 변경
				@PathVariable
				 ------------- React
				 				| MySQL / JPA / PathVariable
				 				------------------------------- JWT 인증
		5. MVC 구조
			User ===== Controller ===== Service ===== Mapper ===== DB
													  ------- Repository
		6. XML => yml : 들여쓰기 잘하기
				  ------------------- spring-boot 설정
				  ------------------- ci/cd script : deploy.yml
				  Git Action / docker / docker-compose
				  ---------------------------------------------
				  jenkins : 모니터링
		 ------------------------------------------------------------
		7. ORM
			= MyBatis
				=> CRUD / 동적 쿼리
			= JPA
				=> CURD (SQL, 메소드 규칙)
		-----------------------------------------------------------------
		Security
			=> Session / Cookie
							| JWT
			: 인증 / 인가
		WebSocket
			=> SockJS / Stomp
			
		FileUpLoad / FileDownLoad
		-------------------------------------------
		Test : Junit => 단위테스트
		-------------------------------------------
		기타 : Spring AI / Kafka / Task(batch)
		-------------------------------------------
		Front-End
			= Jquer : Ajax
			= VueJS : Pinia
			-----------------------
			= React : tanStack-Query
			= NodeJS / TypeScript
			----------------------------- HTML / CSS / JavaScript
	
		Docker / Docker-Compose
		-------------------------------------
		애플리케이션과 실행 환경을 저장 => 필요하게 실행하는 플랫폼
		------------------------ Image화 하여 저장
								--------------- 컨테이너
		1. 명령어
			docker images -a : 이미지 목록
			docker ps -a : 컨테이너 목록
			docker run : 실행
			docker stop / rm => 컨테이너 제어
			docker rmi : 이미지 삭제
		=> Docker-Compose : 여러개의 컨테이너를 한번에 관리 도구
			명령어
				docker-compose up
				docker-compose down
		=> 실시간 처리
			Git Action
			----------
			확인 : JDK / 인증
			docker build
			docker push
*/
@Data
public class CommonsCommentVO {
	private int no, cno, type, group_id, group_step, group_tab, root, dept;
	private String id, name, sex, msg, dbday;
	private Date regdate;
}
