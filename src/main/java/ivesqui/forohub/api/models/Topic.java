package ivesqui.forohub.api.models;
import ivesqui.forohub.api.domain.topics.Categories;
import ivesqui.forohub.api.domain.topics.DtoCreateTopic;
import ivesqui.forohub.api.domain.topics.DtoUpdateTopic;
import ivesqui.forohub.api.domain.topics.TopicStatus;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Categories categories;

    private String topic_content;

    private boolean active;

    @Enumerated(EnumType.ORDINAL)
    private TopicStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Topic(DtoCreateTopic dtoCreateTopic, User user) {
        this.active = true;
        this.title = dtoCreateTopic.title();
        this.categories = dtoCreateTopic.categories();
        this.topic_content = dtoCreateTopic.content();
        this.user = user;
        this.status = TopicStatus.ACTIVO;
    }

    public void Update(DtoUpdateTopic dtoUpdateTopic) {

        if(dtoUpdateTopic.title() != null) {
            this.title = dtoUpdateTopic.title();
        }

        if (dtoUpdateTopic.content() != null) {
            this.topic_content = dtoUpdateTopic.content();
        }

        if(dtoUpdateTopic.categories() != null) {try {
            this.categories = Categories.valueOf(dtoUpdateTopic.categories().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + dtoUpdateTopic.categories());
        }}

    }

    public void deactivateTopic() {
        this.active = false;
    }

    public void activateTopic() {
        this.active = true;
    }

    public void changeStatus(TopicStatus newStatus) {
        this.status = newStatus;
    }
}
