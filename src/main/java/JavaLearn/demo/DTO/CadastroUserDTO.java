package JavaLearn.demo.DTO;

public record CadastroUserDTO(
        String email,
        String password,
        String username,
        String confirmPassword
) {
}
