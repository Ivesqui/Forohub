package ivesqui.forohub.api.domain.responses;

import ivesqui.forohub.api.models.Response;

public record DtoResponse(
        long id,
        String responseContent,
        String topicTitle,
        String author) {
    public DtoResponse(Response response) {
        this(
                response.getId(),
                response.getResponse_content(),
                response.getTopic().getTitle(),
                response.getUser().getUsername());
    }
}
