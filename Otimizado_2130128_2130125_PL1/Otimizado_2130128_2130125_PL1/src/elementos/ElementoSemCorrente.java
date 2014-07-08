package elementos;

import celulas.Bloco;

/**
 * ElementoSemCorrente é uma marker class para distinguir a caveira (elemento
 * sem corrente) dos demais elementos, os quais possuem todos corrente.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public abstract class ElementoSemCorrente extends Elemento {

	public ElementoSemCorrente(String nomeImagem, Bloco bloco) {
		super(nomeImagem, bloco);
	}
}
