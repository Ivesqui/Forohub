package ivesqui.forohub.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ivesqui.forohub.api.domain.users.DtoCreateUser;
import ivesqui.forohub.api.domain.users.DtoListUser;
import ivesqui.forohub.api.domain.users.DtoUpdateUser;
import ivesqui.forohub.api.domain.users.DtoUserResponse;
import ivesqui.forohub.api.models.Role;
import ivesqui.forohub.api.repository.RoleRepository;
import ivesqui.forohub.api.repository.UserRepository;
import ivesqui.forohub.api.models.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoUserResponse> registerUser(@RequestBody @Valid DtoCreateUser dtoCreateUser,
                                                        UriComponentsBuilder uriComponentsBuilder) {

        String encodedPassword = passwordEncoder.encode(dtoCreateUser.password());

        // Assign role USER by default if no roles are provided
        List<Role> roles;
        if (dtoCreateUser.roles() == null || dtoCreateUser.roles().isEmpty()) {
            Role userRole = roleRepository.findByRole("USER")
                    .orElseThrow(() -> new EntityNotFoundException("Role USER not found"));
            roles = new ArrayList<>();
            roles.add(userRole);
        } else {
            roles = dtoCreateUser.roles();
        }

        User user = new User(dtoCreateUser.username(), dtoCreateUser.email(), encodedPassword, roles);

        user = userRepository.save(user);

        DtoUserResponse dtoUserResponse = new DtoUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                encodedPassword,
                user.getRoles().stream().map(Role::getRole).collect(Collectors.toList())
        );

        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(url).body(dtoUserResponse);
    }


    // list Users

    @GetMapping
    public List<DtoListUser> listUsers(){
        return userRepository.findAll().stream().map(DtoListUser::new).toList();
    }

        //get User by Id

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoUserResponse> getUser(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        var userData = new DtoUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(Role::getRole).collect(Collectors.toList()));
        return ResponseEntity.ok(userData);
    }

    // list active users

    @GetMapping("/active")
    public ResponseEntity<Page<DtoListUser>> listActiveUsers(@PageableDefault(size=10)Pageable pagination){
        return ResponseEntity.ok(userRepository.findByActiveTrue(pagination).map(DtoListUser::new));
    }

    // list inactive users

    @GetMapping("/inactive")
    public ResponseEntity<Page<DtoListUser>> listInactiveUsers(@PageableDefault(size = 10)Pageable pagination){
        return ResponseEntity.ok(userRepository.findByActiveFalse(pagination).map(DtoListUser::new));
    }

    // update users data

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody @Valid DtoUpdateUser dtoUpdateUser){
        User user = userRepository.getReferenceById(dtoUpdateUser.id());
        user.Update(dtoUpdateUser);
        return ResponseEntity.ok(new DtoUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(Role::getRole).collect(Collectors.toList())));
    }

    // delete users (logic delete)

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        user.deactivateUser();
        return ResponseEntity.noContent().build();
    }

    // activate users (logic update)

    @PutMapping("/act/{id}")
    @Transactional
    public ResponseEntity activateUser(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));;
        user.activateUser();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/adm/{id}")
    @Transactional
    public ResponseEntity<?> grantAdmin(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));
        Role adminRole = roleRepository.findByRole("ADMIN").orElseThrow(() -> new EntityNotFoundException("Role ADMIN not found"));
        if (!user.getRoles().contains(adminRole)) {
            user.getRoles().add(adminRole);
            userRepository.save(user);
        }
        return ResponseEntity.ok("Usuario actualizado a ADMIN");
    }

    @DeleteMapping("/adm/{id}")
    @Transactional
    public ResponseEntity<?> revokeAdmin(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));
        Role adminRole = roleRepository.findByRole("ADMIN").orElseThrow(() -> new EntityNotFoundException("Role ADMIN not found"));
        user.getRoles().removeIf(role -> role.equals(adminRole));
        userRepository.save(user);
        return ResponseEntity.ok("Rol de ADMIN revocado");
    }
}
