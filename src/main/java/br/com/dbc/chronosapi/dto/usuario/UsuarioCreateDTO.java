package br.com.dbc.chronosapi.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class UsuarioCreateDTO {
    @NotNull
    @NotBlank
    @Schema(description = "Nome do usuário")
    private String nome;
    @NotNull
    @Email
    @Schema(description = "Email do usuário")
    private String email;
    @NotNull
    @NotEmpty
    @Schema(description = "Lista de cargos do usuário")
    private List<String> cargos;
}
