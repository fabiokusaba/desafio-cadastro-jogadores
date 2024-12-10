package br.com.fabiokusaba.desafio_cadastro_jogadores.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fabiokusaba.desafio_cadastro_jogadores.model.Jogador;
import br.com.fabiokusaba.desafio_cadastro_jogadores.service.JogadorService;

@Controller
@RequestMapping("cadastro-jogador")
public class CadastroJogadorController {

    private final JogadorService jogadorService;

    public CadastroJogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping
    public String cadastrarJogador(@ModelAttribute Jogador jogador) {
        try {
            jogadorService.registrarJogador(jogador);
            return "redirect:/cadastro-jogador";
        } catch (Exception e) {
            return "redirect:/cadastro-jogador";
        }        
    }
}
