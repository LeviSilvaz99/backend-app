package com.levisilva.resources;

import com.levisilva.domain.Pessoa;
import com.levisilva.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Pessoa cadastrarPessoa(@RequestBody @Valid Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/{codigo}")
    public Pessoa atualizar(@PathVariable ("codigo") Long codigo, @RequestBody @Valid Pessoa pessoa){
        return pessoaRepository.findById(codigo).map(
                record -> {
                    record.setCpf(pessoa.getCpf());
                    record.setEmail(pessoa.getEmail());
                    record.setNome(pessoa.getNome());
                    Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);
                    return pessoaRepository.save(record);
        }).orElse(null);
    }

    @GetMapping("/pessoas/{codigo}")
    public Pessoa buscarPeloId(@PathVariable Long codigo){
        return pessoaRepository.findById(codigo).orElse(null);
    }

    @DeleteMapping("/pessoas/{codigo}")
    public void deletaProduto(@RequestBody Pessoa pessoa){
        pessoaRepository.delete(pessoa);
    }
}
