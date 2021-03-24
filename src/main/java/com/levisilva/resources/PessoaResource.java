package com.levisilva.resources;

import com.levisilva.domain.Pessoa;
import com.levisilva.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired //vai injetar a depencia pra eu conseguir acessar o elemento repository
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listarTodos(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public Pessoa cadastrarPessoa(@RequestBody  Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/{codigo}")
    public Pessoa atualizar(@PathVariable ("codigo") Long codigo, @RequestBody Pessoa pessoa){
        return pessoaRepository.findById(codigo).map(
                record -> {
                    record.setCpf(pessoa.getCpf());
                    record.setDataNascimento(pessoa.getDataNascimento());
                    record.setEmail(pessoa.getEmail());
                    record.setIdade(pessoa.getIdade());
                    record.setNome(pessoa.getNome());
                    record.setTelefone(pessoa.getTelefone());
                    Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);
                    return pessoaRepository.save(record);
        }).orElse(null);
    }

    @GetMapping("/{codigo}")
    public Pessoa buscarPeloId(@PathVariable Long codigo){
        return pessoaRepository.findById(codigo).orElse(null);
    }
}
