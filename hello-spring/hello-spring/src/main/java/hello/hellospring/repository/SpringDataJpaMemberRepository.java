package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스 받을때는 extends
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
// SpringDataJpa가 JpaRepository를 받고 있으면, 구현체를 자동으로 만들어준다.
    @Override
    Optional<Member> findByName(String name);
}
