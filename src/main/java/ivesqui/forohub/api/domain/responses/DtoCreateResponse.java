package ivesqui.forohub.api.domain.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCreateResponse(
        @NotBlank(message = "The response must not be empty")
        String content,
        @NotNull(message = "The topic ID must not be null")
        Long topicId,
        @NotNull(message = "The user ID must not be null.")
        Long userId
) {

}
