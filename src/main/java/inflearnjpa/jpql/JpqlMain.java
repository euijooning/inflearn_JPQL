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
      em.persist(member);

      member.setTeam(team); // 연관관계 편의 메서드 만들러 Member 다녀옴.

      em.flush();
      em.clear(); // 비움

      String query  = "select m from Member m, Team t where m.username = t.name";
      List<Member> result = em.createQuery(query, Member.class)
          .getResultList();

      System.out.println("result.size = " + result.size());

      for (Member member1 : result) {
        System.out.println("member1 = " + member1);
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
