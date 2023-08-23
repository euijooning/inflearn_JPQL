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

//      String query =
//          "select " +
//              "case when m.age <= 10 then '학생요금' " +
//              "     when m.age >= 60 then '경로요금' " +
//              "     else '일반요금' " +
//              "end " +
//          "from Member m";
      String query =
          "select " + "case t.name "
              + "when 'TeamA' then '인센티브 110%' "
              + "when 'TeamB' then '인센티브 120%' "
              + "else '인센티브 105%' "
              + "end "
              + "from Team t";

      List<String> result = em.createQuery(query, String.class)
          .getResultList();
      for (String s : result) {
        System.out.println("s = " + s);
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
