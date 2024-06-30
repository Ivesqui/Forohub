package ivesqui.forohub.api.domain.topics;

import ivesqui.forohub.api.models.Topic;

public record DtoTopicResponse(
        long id,
        String title,
        String content,
        String category,
        String status,
        String author) {
    public DtoTopicResponse(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getTopic_content(),
                topic.getCategories().toString(),
                topic.getStatus().name(),
                topic.getUser().getUsername());
    }
}
