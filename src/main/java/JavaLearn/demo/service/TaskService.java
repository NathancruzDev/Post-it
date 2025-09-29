package JavaLearn.demo.service;
import JavaLearn.demo.DTO.TaskIdDTO;
import JavaLearn.demo.DTO.TaskPutDTO;
import JavaLearn.demo.Exceptions.RegraDeNegocioException;
import JavaLearn.demo.model.TaskModel;
import JavaLearn.demo.repository.TasksRepository;
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
public class TaskService {
    @Autowired
    TasksRepository tasksRepository;

    public TaskIdDTO criarTarefa(@Valid TaskIdDTO taskIdDTO) {
        TaskModel taskModel = new TaskModel(taskIdDTO);

        if (tasksRepository.existsByTarefa(taskModel.getTarefa())) {
            throw new RegraDeNegocioException("Está tarefa já existe: " + taskModel.getTarefa());
        }

        tasksRepository.save(taskModel);
        return taskIdDTO;
    }

    public List<TaskIdDTO> mostrarTarefas() {
        List<TaskModel> tasks = tasksRepository.findAll();
        if (tasks.isEmpty()) {
            throw new RegraDeNegocioException("Não tem nenhum cadastro");
        } else {
            //A escolha do map e para cada elemento do fluxo seja transofrmado em outro tipo de objeto
            //neste caso transformando da classe model em classe de padrao Dto.
            return new ArrayList<>(tasks.stream().
                    map(task -> new TaskIdDTO( task.getId(),task.getTipoTarefa(), task.getTarefa())).
                    collect(Collectors.toList()));
        }
    }
    public List<TaskIdDTO> mostrarTarefasSemId() {
        List<TaskModel> tasks = tasksRepository.findAll();
        if (tasks.isEmpty()) {
            throw new RegraDeNegocioException("Não tem nenhum cadastro");
        } else {
            return new ArrayList<>(tasks.stream().
                    map(task -> new TaskIdDTO( null,task.getTipoTarefa(), task.getTarefa())).
                    collect(Collectors.toList()));
        }
    }


    public TaskIdDTO tarefaEspecifica(@RequestParam Long id){
        Optional<TaskModel> task=tasksRepository.findById(id);
        if (!task.isPresent()){
            throw new RegraDeNegocioException("Este id não existe:"+ id.toString());
        }
        if(task.isEmpty()){
            throw new RegraDeNegocioException("O id está vazio");
        }
        TaskModel taskModel=task.get();
        TaskIdDTO taskConvert=new TaskIdDTO(taskModel.getId(), taskModel.getTipoTarefa(),taskModel.getTarefa());
        return taskConvert;

    }
    @Transactional
    public void atualizarTarefa(TaskPutDTO task){
        var tarefa=tasksRepository.getReferenceById(task.id());
         tarefa.atualizarDadosTarefa(task);
         tasksRepository.save(tarefa);
    }

    @Transactional
    public TaskPutDTO atualizarTarefaHtmx(TaskPutDTO task){
        var tarefa = tasksRepository.findById(task.id())
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.atualizarDadosTarefa(task);

        if(tarefa.getId()== null) throw new RuntimeException("Atividade precisa de id");

        if(tarefa.getTarefa().isBlank()) throw new RuntimeException("Atividade precisa ser valida");
        return new TaskPutDTO(
                tarefa.getId(),tarefa.getTipoTarefa(), tarefa.getTarefa()
        );


    }

    @Transactional
    public void deletarTarefa(Long id){
        if(tasksRepository.existsById(id)){
            tasksRepository.deleteById(id);
        }
       else throw new RuntimeException("para a tarefa ser deletada ela precisa de um identificador.");
    }
}
