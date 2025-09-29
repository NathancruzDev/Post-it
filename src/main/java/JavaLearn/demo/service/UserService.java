package JavaLearn.demo.service;

import JavaLearn.demo.DTO.CadastroUserDTO;
import JavaLearn.demo.DTO.UserDTO;
import JavaLearn.demo.Exceptions.RegraDeNegocioException;
import JavaLearn.demo.model.UserModel;
import JavaLearn.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserDTO criarUser(@Valid UserDTO userDTO){
        UserModel user=new UserModel(userDTO);
        userRepository.save(user);
        return userDTO;
    }
    @Transactional
    public CadastroUserDTO userCadastrarNaPagina(@Valid CadastroUserDTO cadastroUserDTO){
        UserModel user=new UserModel(cadastroUserDTO);
        userRepository.save(user);
        return cadastroUserDTO;
    }

    public ArrayList<UserDTO> pegarUsuarios(){
        List<UserModel> user=userRepository.findAll();
            if(user.isEmpty()){
                throw new RuntimeException("Nenhum usuário existe no sistema");
            }
            else {
                return new ArrayList<>(user.stream().
                        map(users -> new UserDTO
                                (users.getId(), users.getEmail(),
                                        users.getPassword(), users.getUsername())).collect(Collectors.toList())
                        );
            }
    }
    public UserDTO pegarUsuarioEspecificoPrivado(@RequestParam Long id){
        Optional<UserModel> user=userRepository.findById(id);
        if(user.isEmpty()){
            //lançar exception
             throw new RegraDeNegocioException("Nenhum usuário presente");
        }
        UserModel userModel=user.get();
        UserDTO userConvert=new UserDTO(userModel.getId(), userModel.getEmail(), null, userModel.getUsername());
        return userConvert;
    }

    public UserModel atualizarUsuario(@RequestParam UserDTO userDTO){
        var user=userRepository.getReferenceById(userDTO.id());
        user.atualizarDadosUser(userDTO);
        return userRepository.save(user);
    }
}
