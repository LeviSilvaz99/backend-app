package com.levisilva.service;

import com.levisilva.domain.Login;
import com.levisilva.exception.SenhaInvalidaException;
import com.levisilva.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Faz o carregamento de usuários através de uma base de dados
//carrega o usuario atraves do seu login
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Login salvar(Login login){
        return repository.save(login);
    }

    public UserDetails autenticar (Login login){
        UserDetails user = loadUserByUsername(login.getLogin());
        boolean senhasBatem = encoder.matches(login.getSenha(), user.getPassword());
        if(senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("user não encontrado"));

        String[] roles = login.isAdmin() ?
                new String[]{"ADMIN","USER"} : new String[]{"USER"};
        return User
                .builder()
                .username(login.getLogin())
                .password(login.getSenha())
                .roles(roles)
                .build();
    }
}
