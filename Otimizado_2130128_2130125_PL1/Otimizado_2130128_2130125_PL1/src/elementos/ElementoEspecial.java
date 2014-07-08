package elementos;

import interfaces.Incrementador;
import outros.Corrente;
import celulas.Bloco;

/**
 * O ElementoEspecial é um elemento que tem a capacidade de incrementar, por
 * isso implementa a interface 'Incrementador'. De ElementoEspecial herda o Balde,
 * que tem o poder de incrementar o nível da vida e os ElementosBonus, que têm o
 * poder de incrementar o seu bonus respetivo.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public abstract class ElementoEspecial extends ElementoComCorrente implements
		Incrementador {

	public ElementoEspecial(String nomeImagem, Bloco bloco, Corrente corrente) {
		super(nomeImagem, bloco, corrente);
	}
}
