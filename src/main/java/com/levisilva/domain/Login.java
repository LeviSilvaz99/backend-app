package com.levisilva.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "o campo login é obrigatorio")
    private String login;

    @NotEmpty(message = "o campo senha é obrigatorio")
    private String senha;

    private boolean admin;

}
