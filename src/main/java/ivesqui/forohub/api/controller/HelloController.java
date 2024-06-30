package ivesqui.forohub.api.controller;

//This class is just my Dev signature :v

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
@SecurityRequirement(name = "bearer-key")
public class HelloController {

    @GetMapping
    public void HolaMundo(){
        System.out.println("Hello World!");
        System.out.println("another one b, Chris Ivesqui! :D");

    }
}

