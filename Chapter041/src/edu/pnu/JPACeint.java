package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.sql.Update;

import edu.pnu.domain.Board;

public class JPACeint {
	public static void main(String[] args) {

		// 엔티티메니저를 만들어주는
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter041");

		EntityManager em = emf.createEntityManager();

		inserData(em);
		Update(em);
		deleteData(em);

//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//
//		Board b = new Board();
//		b.setTitle("Title");
//		b.setWriter("Writer");
//		b.setContent("Content");
//		b.setCreateDate(new Date());
//		b.setCnt(0L);
//
//		em.persist(b);
//
//		tx.commit();

		em.close();
		emf.close();

	}

	private static void deleteData(EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		Board b = em.find(Board.class, 5L); // 업데이트 전에는 먼져 검색을 해야한다.

		try {
			tx.begin();
			em.remove(b);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

	}

	private static void Update(EntityManager em) {

		EntityTransaction tx = em.getTransaction();
		Board b = em.find(Board.class, 2L); // 업데이트 전에는 먼져 검색을 해야한다.

		b.setTitle("나는 새로운 타이틀이다");

		try {
			tx.begin();

			em.persist(b);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

	}

	private static void inserData(EntityManager em) {

		int n = 10;

		EntityTransaction tx = em.getTransaction();

		for (int i = 0; i < n; i++) {
			try {
				tx.begin();

				Board b = new Board();
				b.setTitle("Title" + i);
				b.setWriter("Writer" + i);
				b.setContent("Content" + i);
				b.setCreateDate(new Date());
				b.setCnt(0L);

				em.persist(b);

				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			}
		}
	}
}
