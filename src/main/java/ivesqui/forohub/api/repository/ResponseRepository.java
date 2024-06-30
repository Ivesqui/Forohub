package ivesqui.forohub.api.repository;

import ivesqui.forohub.api.models.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
