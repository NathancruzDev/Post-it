package JavaLearn.demo.model;

import JavaLearn.demo.DTO.TaskIdDTO;
import JavaLearn.demo.DTO.TaskModelDTO;
import JavaLearn.demo.DTO.TaskPutDTO;
import JavaLearn.demo.enumMod.TipoTarefa;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
* entity e uma notacao do jakarta e o table e da prorpia tabela do bd
* e os enumerated
* */
@Entity(name = "tasks")
@Table(name = "tasks")
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    int numeroVariavel;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tipo de tarefa é obrigatório")
    TipoTarefa tipoTarefa;

    @NotBlank
    String tarefa;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userModel;

    public TaskModel() {
    }

    public TaskModel(TaskModelDTO taskModelDTO){
        this.tarefa= taskModelDTO.tarefa();
        this.tipoTarefa=taskModelDTO.tipoTarefa();
    }

    public TaskModel(@Valid TaskIdDTO taskIdDTO) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroVariavel() {
        return numeroVariavel;
    }

    public void setNumeroVariavel(int numeroVariavel) {
        this.numeroVariavel = numeroVariavel;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
    public TipoTarefa getTipoTarefa(){
        return tipoTarefa;
    }
    public void setTipoTarefa(TipoTarefa tipoTarefa){
        this.tipoTarefa=tipoTarefa;
    }

    public void atualizarDadosTarefa(@Valid TaskPutDTO task) {
        if(task.tipoTarefa() != null){
            this.tipoTarefa=task.tipoTarefa();;
        }
        if(task.tarefa() != null){
            this.tarefa=task.tarefa();
        }

    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
