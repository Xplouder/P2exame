package elementos;

import celulas.Bloco;

/**
 * ElementoSemCorrente � uma marker class para distinguir a caveira (elemento
 * sem corrente) dos demais elementos, os quais possuem todos corrente.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 *
 */
public abstract class ElementoSemCorrente extends Elemento {

	public ElementoSemCorrente(String nomeImagem, Bloco bloco) {
		super(nomeImagem, bloco);
	}
}
