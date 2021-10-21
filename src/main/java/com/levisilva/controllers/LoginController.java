package com.levisilva.controllers;

import com.levisilva.domain.Login;
import com.levisilva.dto.CredenciaisDTO;
import com.levisilva.dto.TokenDTO;
import com.levisilva.exception.SenhaInvalidaException;
import com.levisilva.security.jwt.JwtService;
import com.levisilva.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Login salvar(@RequestBody @Valid Login login){
        String senhaCriptografada = passwordEncoder.encode(login.getSenha());
        login.setSenha((senhaCriptografada));
        return usuarioService.salvar(login);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Login login = Login.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(login);
            String token = jwtService.gerarToken(login);
            return new TokenDTO(login.getLogin(), token);
        }catch (UsernameNotFoundException | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}

