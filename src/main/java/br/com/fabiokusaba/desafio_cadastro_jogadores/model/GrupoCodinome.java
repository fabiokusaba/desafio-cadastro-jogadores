package br.com.fabiokusaba.desafio_cadastro_jogadores.model;

// Strategy Pattern -> o codinome define algo único, Liga da Justiça ou Vingadores.
// Vamos ter dentro desse enum um construtor para VINGADORES e LIGA_DA_JUSTICA e esse construtor dependendo da
// base de codinomes ele vai receber um nome que vai ser exibido e uma fonte de dados.
// A razão por estar colocando isso aqui é que quando for verificar o grupo codinome escolhido já vou abstrair
// que existe um lugar onde posso buscar essa informação.
// Quando for buscar essa informação vou simplesmente fazer uma busca de codinome.
public enum GrupoCodinome {
    VINGADORES("Vingadores", 
    "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json"),
    LIGA_DA_JUSTICA("Liga da Justiça", 
    "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml");

    GrupoCodinome(String nome, String uri) {
        this.nome = nome;
        this.uri = uri;
    }

    private final String nome; // Label que vai aparecer no sistema
    private final String uri; // Endereço de onde vou buscar os codinomes daquele grupo

    public String getNome() {
        return nome;
    }

    public String getUri() {
        return uri;
    }
}
