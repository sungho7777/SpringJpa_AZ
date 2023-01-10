package com.example.entitymanagerfactory.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CEntityManagerFactory {

	private static EntityManagerFactory entityManagerFactory;

	/*
		EntityManagerFactory 는 EntityManager 를 생성하기 위한 팩토리 인터페이스로 persistence 단위별로 팩토리를 생성
	*/
	public static void initialization(){
		entityManagerFactory = Persistence.createEntityManagerFactory("entitymanagerfactory");
	}

	/*
		EntityManager 객체를 생성
		EntityManager 는 Persistence Context 와 Entity 를 관리

		@return EntityManager 객체
	 */
	public static EntityManager createEntityManaget(){
		return entityManagerFactory.createEntityManager();
	}

	/*
		EntityManagerFactory 객체 종료
	 */
	public static void close(){
		entityManagerFactory.close();
	}
}
