package ivesqui.forohub.api.repository;
import ivesqui.forohub.api.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByActiveTrue(Pageable pagination);

    Page<User> findByActiveFalse(Pageable pagination);

    UserDetails findByUsername(String username);


}
