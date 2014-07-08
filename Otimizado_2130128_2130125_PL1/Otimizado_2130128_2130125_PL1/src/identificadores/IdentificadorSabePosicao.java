package identificadores;

import outros.Posicao;

/**
 * 
 * IdentificadorSabePosicao � uma superClasse que tem um m�todo abstrato
 * getPosicao(); Este m�todo � diferente no Elemento, pois como este n�o sabe a
 * posi��o, vai pergunt�-la ao bloco. Ach�mos por bem fazer uma hierarquia ao
 * inv�s de uma interface pois todos os que tinham getPosicao, pertenciam �
 * mesma hierarquia.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 *
 */
public abstract class IdentificadorSabePosicao extends Identificador {

	public IdentificadorSabePosicao(String nomeImagem) {
		super(nomeImagem);
	}

	public abstract Posicao getPosicao();
}
