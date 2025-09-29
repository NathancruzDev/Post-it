package JavaLearn.demo.controller;


import JavaLearn.demo.DTO.TaskIdDTO;
import JavaLearn.demo.DTO.TaskPutDTO;
import JavaLearn.demo.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("usuariosfinal")
public class TaskHtmxController {

    @Autowired
    private TaskService taskService;


    @GetMapping("/homepage")
    public String index() {
        return "tarefas/index";
    }

    @GetMapping("/tarefasTemp")
    public String tarefasTemp() {
        return "tarefas/tarefas";
    }

    @GetMapping("/tarefas")
    @ResponseBody
    public String pegarTarefaHtmx(Model model) {
        List<TaskIdDTO> tarefas = taskService.mostrarTarefasSemId();
        model.addAttribute("tarefas", tarefas);
        return "tarefas/fragments :: lista-tarefas";
    }

    @GetMapping("/tarefaUnica/{id}")
    @ResponseBody
    public String pegarTarefaPorIdHtmx(@PathVariable Long id, Model model) {
        TaskIdDTO taskIdDTO = taskService.tarefaEspecifica(id);
        model.addAttribute("tarefa", taskIdDTO);
        return "tarefas/fragments :: tarefa-unica";
    }

    @PostMapping("/postarTarefa")
    @Transactional
    @ResponseBody
    public  String postarTarefa(@RequestParam @ModelAttribute TaskIdDTO taskIdDTO,Model model){
        TaskIdDTO tarefa=taskService.criarTarefa(taskIdDTO);
        model.addAttribute("tarefa",tarefa);
        return "tarefas/fragments :: post-tarefa";

    }
    @DeleteMapping("/{id}")
    @ResponseBody
    @Transactional
    public String deletarTarefaPorHtmx(@PathVariable Long id){
        taskService.deletarTarefa(id);
        return "";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String atualizarTarefaPorHtmx(@PathVariable Long id,@ModelAttribute TaskPutDTO taskPutDTO,Model model){
        if (!id.equals(taskPutDTO.id())) {
            throw new RuntimeException("ID da URL não corresponde ao ID do formulário");
        }
        var tarefaAtualizada=taskService.atualizarTarefaHtmx(taskPutDTO);
        model.addAttribute("tarefa",tarefaAtualizada);


        return "tarefas/fragments :: item-tarefa";
    }


}
