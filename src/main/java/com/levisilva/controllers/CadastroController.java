package com.levisilva.controllers;

import com.levisilva.domain.Cadastro;
import com.levisilva.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired //vai injetar a depencia pra eu conseguir acessar o elemento repository
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Cadastro> listarTodos(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public Cadastro cadastrarPessoa(@RequestBody @Valid Cadastro cadastro){
        return pessoaRepository.save(cadastro);
    }

    @PutMapping("/{codigo}")
    public Cadastro atualizar(@PathVariable ("codigo") Long codigo, @RequestBody @Valid Cadastro cadastro){
        return pessoaRepository.findById(codigo).map(
                record -> {
                    record.setCpf(cadastro.getCpf());
                    record.setEmail(cadastro.getEmail());
                    record.setNome(cadastro.getNome());
                    Cadastro cadastroAtualizada = pessoaRepository.save(cadastro);
                    return pessoaRepository.save(record);
        }).orElse(null);
    }

    @GetMapping("{codigo}")
    public Cadastro buscarPeloId(@PathVariable Long codigo){
        return pessoaRepository.findById(codigo).orElse(null);
    }

    @DeleteMapping("api/pessoas/{codigo}")
    public void deletaProduto(@RequestBody Cadastro cadastro){
        pessoaRepository.delete(cadastro);
    }
}
