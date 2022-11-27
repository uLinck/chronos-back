package br.com.dbc.chronosapi.controller.classes;

import br.com.dbc.chronosapi.controller.interfaces.IUsuarioController;
import br.com.dbc.chronosapi.dto.PageDTO;
import br.com.dbc.chronosapi.dto.usuario.UsuarioCreateDTO;
import br.com.dbc.chronosapi.dto.usuario.UsuarioDTO;
import br.com.dbc.chronosapi.dto.usuario.UsuarioUpdateDTO;
import br.com.dbc.chronosapi.exceptions.RegraDeNegocioException;
import br.com.dbc.chronosapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController implements IUsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<PageDTO<UsuarioDTO>> list(Integer pagina, Integer tamanho) {
        return new ResponseEntity<>(usuarioService.list(pagina, tamanho), HttpStatus.OK);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioCreateDTO usuario,
                                             @RequestParam MultipartFile imagem) throws RegraDeNegocioException, IOException {
        log.info("Cadastrando usuário...");
        UsuarioDTO usuarioDTO = usuarioService.create(usuario, imagem);
        log.info("Usuário cadastrado com sucesso!");

        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

        @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable("idUsuario") Integer idUsuario,
                                             @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) throws RegraDeNegocioException {
        log.info("Atualizando usuário....");
        UsuarioDTO usuarioDTO = usuarioService.update(idUsuario, usuarioUpdateDTO);
        log.info("Usuário atualizado com sucesso!");

        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable("idUsuario") Integer idUsuario) throws RegraDeNegocioException {
        log.info("Deletando usuário...");
        usuarioService.delete(idUsuario);
        log.info("Usuário deletado com sucesso!");

        return ResponseEntity.noContent().build();
    }
}