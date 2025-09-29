package JavaLearn.demo.controller;

import JavaLearn.demo.DTO.TaskIdDTO;
import JavaLearn.demo.DTO.TaskPutDTO;
import JavaLearn.demo.repository.TasksRepository;
import JavaLearn.demo.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("homepage")
public class TaskController {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private TaskService taskService;

    @PostMapping("/teste")
    public void retorno(){
        System.out.printf("Teste");
    }

    /*@PostMapping("/tarefa")
    public void postarTarefa(@RequestBody TaskModel taskModel){
        System.out.println(taskModel);
        boolean tarefaVerdadeira=taskModel.getTarefa().isEmpty();
        if(tarefaVerdadeira){
            tasksRepository.save(new TaskModel());
        }

    }*/
    @PostMapping("tarefa/postada")
    @Transactional
    public ResponseEntity<TaskIdDTO> testePostarTarefa(@RequestBody TaskIdDTO taskIdDTO, UriComponentsBuilder uriComponentsBuilder){
        TaskIdDTO tarefaCriada = taskService.criarTarefa(taskIdDTO);
        var uri = uriComponentsBuilder.path("tarefa/postada/{id}")
                .buildAndExpand(tarefaCriada.id())
                .toUri();
        return ResponseEntity.created(uri).body(tarefaCriada);
    }

    @GetMapping("all/tarefas")
    public ResponseEntity<List<TaskIdDTO>> pegarTarefa(){
        List<TaskIdDTO> list=taskService.mostrarTarefas();
            return ResponseEntity.ok(list);
    }

    @GetMapping("pegarTarefaEspecifica")
    public ResponseEntity<TaskIdDTO> pegarTarefaEspecifica(@RequestParam Long id){
        TaskIdDTO task=taskService.tarefaEspecifica(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("atualizarTarefa")
    @Transactional
    public ResponseEntity<Void> atualizarTarefaEspecifica(@RequestParam TaskPutDTO task){
        taskService.atualizarTarefa(task);
            return ResponseEntity.ok().build();

    }

}
