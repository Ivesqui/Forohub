package ivesqui.forohub.api.domain.users;

import java.util.List;

public record DtoUserResponse(Long id, String username, String email, String password, List<String> roles) {
}
