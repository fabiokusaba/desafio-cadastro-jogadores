package br.com.fabiokusaba.desafio_cadastro_jogadores.model;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
public record Jogador(
    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    String telefone,

    String codinome,

    @NotNull
    GrupoCodinome grupoCodinome
) {

}
