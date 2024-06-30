package ivesqui.forohub.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ivesqui.forohub.api.domain.responses.DtoCreateResponse;
import ivesqui.forohub.api.domain.responses.DtoListResponse;
import ivesqui.forohub.api.domain.responses.DtoResponse;
import ivesqui.forohub.api.domain.responses.DtoUpdateResponse;
import ivesqui.forohub.api.repository.ResponseRepository;
import ivesqui.forohub.api.models.Response;
import ivesqui.forohub.api.models.Topic;
import ivesqui.forohub.api.repository.TopicRepository;
import ivesqui.forohub.api.models.User;
import ivesqui.forohub.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/responses")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ResponseRepository responseRepository;

    // Add Response

    @PostMapping
    public ResponseEntity<DtoResponse> addResponse(@RequestBody @Valid DtoCreateResponse dtoCreateResponse,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        User user = userRepository.findById(dtoCreateResponse.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Topic topic = topicRepository.findById(dtoCreateResponse.topicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        Response response = new Response(dtoCreateResponse, topic, user);
        responseRepository.save(response);

        URI url = uriComponentsBuilder.path("/responses/{id}").buildAndExpand(response.getId()).toUri();

        DtoResponse dtoResponse = new DtoResponse(response);
        return ResponseEntity.created(url).body(dtoResponse);
    }


    // get response by id

    @GetMapping("/{id}")
    public ResponseEntity<DtoResponse> getResponse(@PathVariable Long id){
        Response response = responseRepository.getReferenceById(id);

        DtoResponse responseData = new DtoResponse(response);
        return ResponseEntity.ok(responseData);
    }


    // list all responses

    @GetMapping
    public ResponseEntity<Page<DtoListResponse>> listResponses(@PageableDefault(
            size = 10,
            page = 0,
            direction = Sort.Direction.ASC) Pageable pagination){
        return ResponseEntity.ok(responseRepository.findAll(pagination).map(DtoListResponse::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateResponse(@RequestBody @Valid DtoUpdateResponse dtoUpdateResponse){
        Response response = responseRepository.getReferenceById(dtoUpdateResponse.id());
        response.Update(dtoUpdateResponse);
        return ResponseEntity.ok(new DtoResponse(response));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteResponse(@PathVariable Long id){
        Response response = responseRepository.getReferenceById(id);
        response.deactivateResponse();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/act/{id}")
    @Transactional
    public ResponseEntity activateResponse(@PathVariable Long id){
        Response response = responseRepository.getReferenceById(id);
        response.activateResponse();
        return ResponseEntity.noContent().build();
    }


}
