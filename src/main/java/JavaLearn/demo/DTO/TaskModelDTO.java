package JavaLearn.demo.DTO;

import JavaLearn.demo.enumMod.TipoTarefa;
import JavaLearn.demo.model.TaskModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TaskModelDTO(
        @NotNull(message = "Tipo de tarefa é obrigatório")
        TipoTarefa tipoTarefa,
        @NotBlank(message = "Descrição da tarefa é obrigatória")
        String tarefa
) {

    public void addAttribute(String tarefas, List<TaskIdDTO> tarefas1) {

    }
}
