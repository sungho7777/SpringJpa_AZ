package com.example.entitymanagerfactory;

import com.example.entitymanagerfactory.entity.UserEntity;
import com.example.entitymanagerfactory.factory.CEntityManagerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;


@SpringBootApplication
public class EntityManagerFactoryApplication {

	public static void main(String[] args) {

		CEntityManagerFactory.initialization();

		EntityManager entityManager = CEntityManagerFactory.createEntityManaget();

		// EntityManager 에서 트랜젝션을 가져와 관리하기 위한 객체 생성
		EntityTransaction entityTransaction = entityManager.getTransaction();

		try {
			// 트랜잭션을 시작해야 DB를 조작할 수 있음
			entityTransaction.begin();

			System.out.println("EntityManagerFactoryApplication.main 4");

			// 저장하고자 하는 엔티티 객체를 생성
			UserEntity userEntity = new UserEntity("juju@naver.com", "juju", LocalDateTime.now(), LocalDateTime.now());

			System.out.println("EntityManagerFactoryApplication.main 5");

			// UserEntity 객체를 Persistence Context 에 추가
			entityManager.persist(userEntity);

			System.out.println("EntityManagerFactoryApplication.main 6");

			// 실재 DB 에 반영한다
			entityTransaction.commit();

			System.out.println("EntityManagerFactoryApplication.main 7");

		}catch (Exception e){
			e.printStackTrace();

			// 예외가 발생했을 경우 트랜잭션 롤백 진행
			entityTransaction.rollback();
		}finally {
			// 엔티티 매니져 종료 JDBC 에서 connection 종료하는 것과 동일한 기능으로 보면 됨.
			entityManager.close();

			System.out.println("EntityManagerFactoryApplication.main 8");
		}

		// 팩토리 종료. 커넥션 풀 자원을 반환한다.
		entityManager.close();

		System.out.println("EntityManagerFactoryApplication.main 9");
	}

}
