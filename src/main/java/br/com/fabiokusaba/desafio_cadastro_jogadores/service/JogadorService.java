package br.com.fabiokusaba.desafio_cadastro_jogadores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fabiokusaba.desafio_cadastro_jogadores.model.GrupoCodinome;
import br.com.fabiokusaba.desafio_cadastro_jogadores.model.Jogador;
import br.com.fabiokusaba.desafio_cadastro_jogadores.repository.JogadorRepository;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final CodinomeService codinomeService;

    public JogadorService(JogadorRepository jogadorRepository, CodinomeService codinomeService) {
        this.jogadorRepository = jogadorRepository;
        this.codinomeService = codinomeService;
    }

    public Jogador registrarJogador(Jogador jogador) throws Exception {
        var codinomesEmUso = listarCodinomesEmUso(jogador.grupoCodinome());
        var novoCodinome = codinomeService.gerarCodinome(jogador.grupoCodinome(), codinomesEmUso);

        var novoJogador = new Jogador(
            jogador.nome(), 
            jogador.email(), 
            jogador.telefone(), 
            novoCodinome, 
            jogador.grupoCodinome()
        );

        return jogadorRepository.salvar(novoJogador);
    }

    public List<Jogador> listarJogadores() {
        return jogadorRepository.listarJogadores();
    }

    private List<String> listarCodinomesEmUso(GrupoCodinome grupoCodinome) {
        return jogadorRepository.listarCodinomesPorGrupo(grupoCodinome);
    }
}
