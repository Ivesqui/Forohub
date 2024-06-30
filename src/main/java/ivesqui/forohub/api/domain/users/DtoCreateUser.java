package ivesqui.forohub.api.domain.users;

import ivesqui.forohub.api.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

import java.util.List;


public record DtoCreateUser(
        @NotBlank
        String username,
        @NotBlank(message = "{email.required}")
        @Email(message = "{email.invalid}")
        String email,
        @Pattern(regexp = "\\d{4,6}", message = "The password cannot be more than 6 characters.")
        @NotBlank
        String password,
        List<Role> roles) {
}
