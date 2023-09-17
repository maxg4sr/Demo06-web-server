package tw.hp.demo06.web.server.JPA;


import org.springframework.data.jpa.repository.JpaRepository;
import tw.hp.demo06.web.server.pojo.entity.ResumeEntity;

public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {
}
