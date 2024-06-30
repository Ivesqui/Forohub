package ivesqui.forohub.api.domain.responses;

import ivesqui.forohub.api.models.Response;


public record DtoListResponse(
        Long id,
        String content,
        String topicTitle,
        String author) {

    public DtoListResponse(Response response) {
        this(
                response.getId(),
                response.getResponse_content(),
                response.getTopic().getTitle(),
                response.getUser().getUsername());
    }

}
