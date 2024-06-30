package ivesqui.forohub.api.domain.users;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DtoUpdateUser(
        @NotNull Long id,
        String username,
        String email,
        String password,
        Boolean active,
        List<String> roles) {
}
