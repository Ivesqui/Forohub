package ivesqui.forohub.api.domain.topics;

import ivesqui.forohub.api.models.Topic;

public record DtoListTopic(Long id,
                           String title,
                           String categories,
                           String content,
                           String status,
                           String author) {

    public DtoListTopic(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getCategories().toString(),
                topic.getTopic_content(),
                topic.getStatus().name(),
                topic.getUser().getUsername());
    }

}
