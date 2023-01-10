package com.example.basicjpa;

import com.example.basicjpa.entity.UserEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

@SpringBootApplication
public class BasicJpaApplication {

	public static void main(String[] args) {
		// EntityManagerFactory 는 EntityManager 를 생성하기 위한 팩토리 인터페이스 persistence 단위별로 팩토리 생성
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
				"basicjpa"); // persistence.xml 파일에 기입한 이름을 적어줘야 함

		System.out.println("BasicJpaApplication.main 1");

		// EntityManager 객체를 생성R
		// EntityManager 는 Persistence Context 와 Entity 를 관리
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		System.out.println("BasicJpaApplication.main 2");

		// EntityManager 에서 트랜잭션을 가져와 관리하기 위한 객체 생성
		EntityTransaction entityTransaction = entityManager.getTransaction();

		System.out.println("BasicJpaApplication.main 3");

		try {
			// 트랜잭션을 시작해야 DB를 조작할 수 있음
			entityTransaction.begin();

			System.out.println("BasicJpaApplication.main 4");

			// 저장하고자 하는 엔티티 객체를 생성
			UserEntity userEntity = new UserEntity("haeun@naver.com", "parkhaeun", LocalDateTime.now(), LocalDateTime.now());

			System.out.println("BasicJpaApplication.main 5");

			// UserEntity 객체를 Persistence Context 에 추가
			entityManager.persist(userEntity);

			System.out.println("BasicJpaApplication.main 6");

			// 실재 DB 에 반영한다
			entityTransaction.commit();

			System.out.println("BasicJpaApplication.main 7");

		}catch (Exception e){
			e.printStackTrace();

			// 예외가 발생했을 경우 트랜잭션 롤백 진행
			entityTransaction.rollback();
		}finally {
			// 엔티티 매니져 종료 JDBC 에서 connection 종료하는 것과 동일한 기능으로 보면 됨.
			entityManager.close();

			System.out.println("BasicJpaApplication.main 8");
		}

		// 팩토리 종료. 커넥션 풀 자원을 반환한다.
		entityManagerFactory.close();

		System.out.println("BasicJpaApplication.main 9");
	}

}
