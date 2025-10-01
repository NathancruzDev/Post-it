package JavaLearn.demo.model;

import JavaLearn.demo.DTO.CadastroUserDTO;
import JavaLearn.demo.DTO.TaskModelDTO;
import JavaLearn.demo.DTO.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity(name="users")
@Table(name="users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique = true ,length = 366)
    String email;
    @Column(nullable = false, length = 50)
    String password;
    @NotBlank
    String username;

    String confirmPassword;

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskModel> tasks = new ArrayList<>();

    public UserModel() {
    }
    public UserModel(Long id,String email, String password,String username){
        this.id=id;
        this.email=email;
        this.password=password;
        this.username=username;
    }
    public UserModel(Long id,String email, String password,String username,String confirmPassword){
        this.id=id;
        this.email=email;
        this.password=password;
        this.username=username;
        this.confirmPassword=confirmPassword;
    }

    public UserModel(@Valid UserDTO userDTO) {
    }

    public UserModel(@Valid CadastroUserDTO cadastroUserDTO) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void atualizarDadosUser(@Valid UserDTO userDTO){
        if(userDTO.email() != null){
            this.email= userDTO.email();
        }
        if(userDTO.password() != null ){
            this.password=userDTO.password();
        }

    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
