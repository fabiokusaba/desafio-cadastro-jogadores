package br.com.fabiokusaba.desafio_cadastro_jogadores;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fabiokusaba.desafio_cadastro_jogadores.model.GrupoCodinome;
import br.com.fabiokusaba.desafio_cadastro_jogadores.model.Jogador;

@SpringBootTest
@AutoConfigureMockMvc
class DesafioCadastroJogadoresIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void cadastrarListarJogadorSucesso() throws Exception {

		var jogador = new Jogador("test", "test@test.com", "123456", null, GrupoCodinome.VINGADORES);

		mockMvc
			.perform(post("/cadastro-jogador")
				.param("nome", jogador.nome())
				.param("email", jogador.email())
				.param("telefone", jogador.telefone())
				.param("grupoCodinome", jogador.grupoCodinome().name()))
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/listagem-jogadores"));

		mockMvc
			.perform(get("/listagem-jogadores"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("listagem_jogadores"))
			.andExpect(model().attribute("jogadores", hasSize(1)))
			.andExpect(model().attribute("jogadores", contains(allOf(
				hasToString(containsString(jogador.nome())),
				hasToString(constainsString(jogador.email())),
				hasToString(constainsString(jogador.telefone())),
				hasToString(containsString(jogador.grupoCodinome().name()))
			))));
	}

}
