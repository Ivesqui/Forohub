package ivesqui.forohub.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ivesqui.forohub.api.domain.topics.*;
import ivesqui.forohub.api.repository.TopicRepository;
import ivesqui.forohub.api.models.Topic;
import ivesqui.forohub.api.models.User;
import ivesqui.forohub.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    //Create Topic
    @PostMapping
    public ResponseEntity<DtoTopicResponse> addTopic(@RequestBody @Valid DtoCreateTopic dtoCreateTopic,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        User user = userRepository.findById(dtoCreateTopic.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));


        Topic topic = new Topic(dtoCreateTopic, user);
        topicRepository.save(topic);

        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        DtoTopicResponse dtoTopicResponse = new DtoTopicResponse(topic);
        return ResponseEntity.created(url).body(dtoTopicResponse);
    }

    // get Topic by Id.

    @GetMapping("/{id}")
    public ResponseEntity<DtoTopicResponse> getTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);

        DtoTopicResponse topicData = new DtoTopicResponse(topic);
        return ResponseEntity.ok(topicData);
    }


    //List all and sort by title.

    @GetMapping
    public ResponseEntity<Page<DtoListTopic>> listTopic(@PageableDefault(size = 10,
            page = 0,
            sort = "title",
            direction = Sort.Direction.ASC) Pageable pagination){
        return ResponseEntity.ok(topicRepository.findAll(pagination).map(DtoListTopic::new));
    }

    //list all active topics, sort by title, sort direction ASC

    @GetMapping("/active")
    public ResponseEntity<Page<DtoListTopic>> listActiveTopics(@PageableDefault(size=10,
            page = 0,
            sort = "title",
            direction = Sort.Direction.ASC)Pageable pagination){
        return ResponseEntity.ok(topicRepository.findByActiveTrue(pagination).map(DtoListTopic::new));
    }

    // list all inactive topics, sort by title, sort direction ASC.

    @GetMapping("/inactive")
    public ResponseEntity<Page<DtoListTopic>> listInactiveTopics(@PageableDefault(
            size = 10,
            page = 0,
            sort = "title",
            direction = Sort.Direction.ASC)Pageable pagination){
        return ResponseEntity.ok(topicRepository.findByActiveFalse(pagination).map(DtoListTopic::new));
    }


    //Update topic
    @PutMapping
    @Transactional
    public ResponseEntity updateTopic(@RequestBody @Valid DtoUpdateTopic dtoUpdateTopic){
        Topic topic = topicRepository.getReferenceById(dtoUpdateTopic.id());
        topic.Update(dtoUpdateTopic);
        return ResponseEntity.ok(new DtoTopicResponse(topic));
     }

    // delete topic (logic delete)

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topic.deactivateTopic();
        return ResponseEntity.noContent().build();
    }

    // activate hidden topic (Logic update)

    @PutMapping("/act/{id}")
    @Transactional
    public ResponseEntity activateTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topic.activateTopic();
        return ResponseEntity.noContent().build();
    }

    // Change Topic Status
    @PutMapping("/{id}/status")
    @Transactional
    public ResponseEntity changeTopicStatus(@PathVariable Long id, @RequestBody TopicStatus newStatus) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.changeStatus(newStatus);
        return ResponseEntity.ok().build();
    }



}
