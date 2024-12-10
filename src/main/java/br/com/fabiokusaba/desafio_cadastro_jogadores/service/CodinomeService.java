package br.com.fabiokusaba.desafio_cadastro_jogadores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fabiokusaba.desafio_cadastro_jogadores.model.GrupoCodinome;

@Service
public class CodinomeService {

    private final CodinomeRepositoryFactory codinomesRepositoryFactory;

    public CodinomeService(CodinomeRepositoryFactory codinomesRepositoryFactory) {
        this.codinomesRepositoryFactory = codinomesRepositoryFactory;
    }

    public String gerarCodinome(GrupoCodinome grupoCodinome, List<String> codinomesEmUso) throws Exception {
        var codinomesDisponiveis = listarCodinomesDisponiveis(grupoCodinome, codinomesEmUso);
        
        if (codinomesDisponiveis.isEmpty()) {
            throw new Exception("Não há codinomes disponíveis para o grupo " + grupoCodinome.getNome());
        }
        
        var codinomeSorteado = sortearCodinome(codinomesDisponiveis);
        
        return codinomeSorteado;
    }
                      
    private List<String> listarCodinomesDisponiveis(GrupoCodinome grupoCodinome, List<String> codinomesEmUso) throws Exception {
        var codinomes = buscarCodinomes(grupoCodinome);
        
        var codinomesDisponiveis = codinomes
            .stream()
            .filter(codinome -> !codinomesEmUso.contains(codinome))
            .toList();

        return codinomesDisponiveis;
    }
        
    private List<String> buscarCodinomes(GrupoCodinome grupoCodinome) throws Exception {
        // Componente Repository -> sempre que precisamos buscar alguma informação em uma fonte de dados
        // o ideal é não colocar essa busca implementada num serviço, o ideal é usarmos um repositório.
        // Repositório não é só para buscar dados em banco, ele também é usado para buscar dados em serviços
        // HTTP.
        // Padrão Factory -> não estou especificando se o repositório é da Liga da Justiça ou dos Vingadores
        // porque isso é um detalhe que não pode estar explícito aqui na minha lógica, por isso utilizamos
        // esse padrão para isolar este detalhe.
        // Desta forma, vamos criar um repositório baseado no grupoCodinome que for passado.
        var codinomeRepository = codinomesRepositoryFactory.create(grupoCodinome);

        return codinomeRepository.buscarCodinomes();
    }

    private String sortearCodinome(List<String> codinomesDisponiveis) {
        return codinomesDisponiveis
            .get((int) (Math.random() * codinomesDisponiveis.size()));
    }
}
