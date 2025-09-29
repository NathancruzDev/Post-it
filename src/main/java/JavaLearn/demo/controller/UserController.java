package JavaLearn.demo.controller;

import JavaLearn.demo.DTO.UserDTO;
import JavaLearn.demo.repository.UserRepository;
import JavaLearn.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(name="User")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDTO> criarUsuario(@RequestParam UserDTO userDTO, UriComponentsBuilder uriComponentsBuilder){

       UserDTO userCriado=userService.criarUser(userDTO);
        var uri=uriComponentsBuilder.path("user/{id}").buildAndExpand(userCriado.id()).toUri();
        return ResponseEntity.created(uri).body(userCriado);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> pegarUsuarios(@RequestParam UserDTO userDTO) {
        List<UserDTO> list=userService.pegarUsuarios();
            return ResponseEntity.ok(list);
    }

}
