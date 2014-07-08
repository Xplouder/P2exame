package identificadores;

import outros.Posicao;

/**
 * 
 * IdentificadorSabePosicao é uma superClasse que tem um método abstrato
 * getPosicao(); Este método é diferente no Elemento, pois como este não sabe a
 * posição, vai perguntá-la ao bloco. Achámos por bem fazer uma hierarquia ao
 * invés de uma interface pois todos os que tinham getPosicao, pertenciam à
 * mesma hierarquia.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public abstract class IdentificadorSabePosicao extends Identificador {

	public IdentificadorSabePosicao(String nomeImagem) {
		super(nomeImagem);
	}

	public abstract Posicao getPosicao();
}
