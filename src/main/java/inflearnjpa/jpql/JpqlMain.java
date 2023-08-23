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
      Member member = new Member();
      member.setUsername("member1");
      member.setAge(10);
      em.persist(member);

      em.flush();
      em.clear(); // 비움

//      // 엔티티 프로젝션
//      List<Member> result = em.createQuery("select m from Member m", Member.class)
//              .getResultList();
//      // 여기서 m이 엔티티인데, 그럼 엔티티들이 반환된다.
//      // 얘는 영속성 컨텍스트에서 관리되는가?
//
//      // 정답은 바뀌면 관리가 되고, 안 바뀌면 관리가 안 된다.
//      Member findMember = result.get(0);
//      findMember.setAge(20); // 여기서는 바귐

//      // 2. 엔티티 프로젝션 - 팀
//      List<Team> result = em.createQuery("select t from Member m join m.team t", Team.class)
//              .getResultList();

//      // 3. 임베디드 타입 프로젝션
//      List<Address> result = em.createQuery("select o.address from Order o", Address.class)
//          .getResultList();

//      // 4. 스칼라 타입 프로젝션
//      em.createQuery("select m.username, m.age from Member m")
//          .getResultList();

//      // 1) Query 타입으로 조회
//      List resultList = em.createQuery("select m.username, m.age from Member m")
//          .getResultList();
//
//      Object o = resultList.get(0);
//      Object[] result = (Object[]) o;
//      System.out.println("username : " + result[0]);
//      System.out.println("age : " + result[1]);

//      //2) Object[] 타입으로 조회
//      List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m")
//          .getResultList();
//
//      Object[] result = resultList.get(0);
//      System.out.println("username : " + result[0]);
//      System.out.println("age : " + result[1]);

      // 3. new 명령어로 조회
      List<MemberDto> result = em.createQuery(
              "select new inflearnjpa.jpql.MemberDto(m.username, m.age) from Member m", MemberDto.class)
          .getResultList();// 풀네임 다 적어줘야 한다.

      MemberDto dto = result.get(0);
      System.out.println("memberDto = " + dto.getUsername());
      System.out.println("memberDto = " + dto.getAge());

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
