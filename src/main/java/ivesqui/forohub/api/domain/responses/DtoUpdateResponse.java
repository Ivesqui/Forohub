package ivesqui.forohub.api.domain.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoUpdateResponse(
        @NotNull(message = "The response ID is required")
        Long id,
        @NotBlank(message = "The response must not be empty")
        String content) {
}
