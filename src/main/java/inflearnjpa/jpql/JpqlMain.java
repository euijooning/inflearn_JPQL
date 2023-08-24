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
      Team teamA = new Team();
      teamA.setName("팀A");
      em.persist(teamA);

      Team teamB = new Team();
      teamB.setName("팀B");
      em.persist(teamB);

      Member member1 = new Member();
      member1.setUsername("회원1");
      member1.setTeam(teamA);
      em.persist(member1);

      Member member2 = new Member();
      member2.setUsername("회원2");
      member2.setTeam(teamA);
      em.persist(member2);

      Member member3 = new Member();
      member3.setUsername("회원3");
      member3.setTeam(teamB);
      em.persist(member3);


      em.flush();
      em.clear(); // 비움


//      String query = "select m from Member m";
      String query = "select m from Member m join fetch m.team";
      List<Member> result = em.createQuery(query, Member.class)
          .getResultList();

      for (Member member : result) {
        System.out.println("member = " + member + ", " + member.getTeam().getName());
        //회원1, 팀A(SQL)
        //회원2, 팀A(1차캐시 => 쿼리 없이 바로가져옴)

        //회원3 ,팀B(1차캐시에 없으므로 새로운 쿼리, SQL)
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
