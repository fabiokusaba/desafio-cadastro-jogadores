package br.com.fabiokusaba.desafio_cadastro_jogadores.repository;

import java.util.List;

import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.fabiokusaba.desafio_cadastro_jogadores.model.GrupoCodinome;
import br.com.fabiokusaba.desafio_cadastro_jogadores.web.LigaDaJusticaDTO;

public class LigaDaJusticaRepository implements CodinomeRepository {

    @Override
    public List<String> buscarCodinomes() throws Exception {
        var codinomes = RestClient
            .builder()
            .baseUrl(GrupoCodinome.LIGA_DA_JUSTICA.getUri())
            .build()
            .get()
            .retrieve()
            .body(String.class);

        var xmlMapper = new XmlMapper();
        var ligaDaJustica = xmlMapper.readValue(codinomes, LigaDaJusticaDTO.class);
        
        return ligaDaJustica.getCodinomes();
    }

}
