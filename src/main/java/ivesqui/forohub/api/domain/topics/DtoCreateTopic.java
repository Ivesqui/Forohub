package ivesqui.forohub.api.domain.topics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCreateTopic(
        @NotBlank(message = "El título no puede estar vacío")
        String title,
        @NotNull(message = "La categoría no puede ser nula")
        Categories categories,
        @NotBlank(message = "El contenido del topico no puede estar vacío")
        String content,
        @NotNull(message = "El usuario no puede ser nulo")
        Long userId) {
}
