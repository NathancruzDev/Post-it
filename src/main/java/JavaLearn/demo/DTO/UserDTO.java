package JavaLearn.demo.DTO;

import jakarta.validation.Valid;

public record UserDTO(
        Long id,
        String email,
        String password,
        String username
) {

}
