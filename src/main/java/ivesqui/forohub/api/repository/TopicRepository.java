package ivesqui.forohub.api.repository;

import ivesqui.forohub.api.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByActiveTrue(Pageable pagination);
    Page<Topic> findByActiveFalse(Pageable pagination);
}
