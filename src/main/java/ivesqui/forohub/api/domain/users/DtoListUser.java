package ivesqui.forohub.api.domain.users;
import ivesqui.forohub.api.models.Role;
import ivesqui.forohub.api.models.User;

import java.util.List;
import java.util.stream.Collectors;

public record DtoListUser(Long id, String username, String email, String password, List<String> roles) {

    public DtoListUser(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles().stream().map(Role::getRole).collect(Collectors.toList()));
    }
}