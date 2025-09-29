package JavaLearn.demo.DTO;

import JavaLearn.demo.enumMod.TipoTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskPutDTO(
        Long id,
        TipoTarefa tipoTarefa,
        @NotBlank
        String tarefa
) {

}
