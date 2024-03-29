package br.com.dbc.chronosapi.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UAdminUpdateDTO {
    @NotNull
    @NotBlank
    @Schema(description = "Nome do usuário")
    private String nome;

    @NotEmpty
    @Schema(description = "Cargos do usuário")
    private Set<CargoCreateDTO> cargos;
}
