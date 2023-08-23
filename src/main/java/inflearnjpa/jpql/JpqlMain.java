package inflearnjpa.jpql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpqlMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member = new Member();
      member.setUsername("member1");
      em.persist(member);

//      // TypeQuery, Query
//      TypedQuery<Member> query1 =
//          em.createQuery("SELECT m FROM Member m", Member.class);
//      Query query2 =
//          em.createQuery("SELECT m.username, m.age from Member m"); // String과 int라서. 다르다.

      // 결과 조회 API

//      //이렇게 하면 반환이 타입 쿼리의 제네릭으로 들어간다.
//      TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//      List<Member> memberList = query.getResultList(); // 컬렉션이 반환 될 거야를 알려줌
//
//      for (Member member1 : memberList) { //출력하기
//        System.out.println("member1 = " + member1);
//      }

      /*
      // 값이 무조건 하나라면, 단일 객체 반환
      TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 10", Member.class);
      Member singleResult = query.getSingleResult();
      System.out.println("singleResult = " + singleResult);
       */
      //이거를
//      TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :username", Member.class);
//      query.setParameter("username", "member1");
//      Member singleResult = query.getSingleResult();
//      System.out.println("singleResult = " + singleResult.getUsername());

//      //위의 것을 더 세련되게 체인으로 설곟란다.


      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }
    emf.close();
  }

}
