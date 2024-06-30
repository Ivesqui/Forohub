package ivesqui.forohub.api.models;

import ivesqui.forohub.api.domain.responses.DtoCreateResponse;
import ivesqui.forohub.api.domain.responses.DtoUpdateResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "responses")
@Entity(name = "Response")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="response_content")
    private String response_content;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    private boolean active;


    public Response(DtoCreateResponse dtoCreateResponse, Topic topic, User user){
        this.response_content = dtoCreateResponse.content();
        this.active = true;
        this.topic = topic;
        this.user = user;
    }


    public void Update(DtoUpdateResponse dtoUpdateResponse) {
        if(dtoUpdateResponse.content() != null){
            this.response_content = dtoUpdateResponse.content();
        }

    }

    public void deactivateResponse() {
        this.active = false;
    }

    public void activateResponse() {
        this.active = true;
    }
}
