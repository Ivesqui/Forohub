package ivesqui.forohub.api.domain.topics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoUpdateTopic(
        @NotNull Long id,
        @NotBlank(message = "The new title cannot be empty.")
        String title,
        @NotNull(message = "Category cannot be empty.")
        String categories,
        @NotBlank (message = "The content cannot be empty or exceed 2000 characters.")
        String content) {
}
