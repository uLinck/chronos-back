package br.com.dbc.chronosapi.service;

import br.com.dbc.chronosapi.dto.usuario.LoginDTO;
import br.com.dbc.chronosapi.entity.classes.UsuarioEntity;
import br.com.dbc.chronosapi.exceptions.RegraDeNegocioException;
import br.com.dbc.chronosapi.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final ObjectMapper objectMapper;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public Integer getIdLoggedUser() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    public LoginDTO getLoggedUser() {
        Optional<UsuarioEntity> userLogged = findById(getIdLoggedUser());
        return objectMapper.convertValue(userLogged, LoginDTO.class);
    }

    public String updatePassword(String senha) throws RegraDeNegocioException {
        LoginDTO login = getLoggedUser();
        UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(login.getEmail());
        if(usuarioEntity != null ) {
            usuarioEntity.setSenha(passwordEncoder.encode(senha));
            usuarioRepository.save(usuarioEntity);
            return "Senha atualizada com sucesso!";
        }
        throw new RegraDeNegocioException("Usuario não encontrado!");
    }

    public String sendRecoverPasswordEmail(String email) throws RegraDeNegocioException {
        UsuarioEntity usuario = usuarioRepository.findByEmail(email);

//        String token = tokenService.getToken(usuario, true);
        emailService.sendEmailRecuperacaoSenha(usuario.getEmail(), usuario.getSenha());

        return "Verifique seu email para trocar a senha.";
    }

    public Optional<UsuarioEntity> findByEmailAndSenha(String login, String senha) {
        return usuarioRepository.findByEmailAndSenha(login, senha);
    }

    public Optional<UsuarioEntity> findById(Integer idLoginUsuario) {
        return usuarioRepository.findById(idLoginUsuario);
    }

}
