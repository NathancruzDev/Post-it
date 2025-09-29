package JavaLearn.demo.DTO;

import JavaLearn.demo.enumMod.TipoTarefa;
import JavaLearn.demo.model.TaskModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record TaskIdDTO(

        Long id,
        @NotNull(message = "Tipo de tarefa é obrigatório")
        TipoTarefa tipoTarefa,
        @NotBlank(message = "Descrição da tarefa é obrigatória")
        String tarefa

) {

    public void addAttribute(String tarefa, TaskIdDTO taskIdDTO) {
    }
}
