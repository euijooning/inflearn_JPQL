package inflearnjpa.jpql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpqlMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // 팀 먼저 저장
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member member = new Member();
      member.setUsername("member1");
      member.setAge(10);
      member.setType(MemberType.ADMIN); // 여기 추가
      em.persist(member);

      member.setTeam(team); // 연관관계 편의 메서드 만들러 Member 다녀옴.

      em.flush();
      em.clear(); // 비움

//      String query  = "select m.username, 'HELLO', TRUE from Member m "
//          + "where m.type = inflearnjpa.jpql.MemberType.USER";
//      List<Object[]> result = em.createQuery(query)
//              .getResultList();
      String query  = "select m.username, 'HELLO', TRUE from Member m "
          + "where m.type = :userType";
      List<Object[]> result = em.createQuery(query)
          .setParameter("userType", MemberType.ADMIN)
          .getResultList();

      for (Object[] objects : result) {
        System.out.println("objects = " + objects[0]);
        System.out.println("objects = " + objects[1]);
        System.out.println("objects = " + objects[2]);
      }

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
