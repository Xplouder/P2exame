package elementos;

import interfaces.Incrementador;
import outros.Corrente;
import celulas.Bloco;

/**
 * O ElementoEspecial � um elemento que tem a capacidade de incrementar, por
 * isso implementa a interface 'Incrementador'. De ElementoEspecial herda o Balde,
 * que tem o poder de incrementar o n�vel da vida e os ElementosBonus, que t�m o
 * poder de incrementar o seu bonus respetivo.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 *
 */
public abstract class ElementoEspecial extends ElementoComCorrente implements
		Incrementador {

	public ElementoEspecial(String nomeImagem, Bloco bloco, Corrente corrente) {
		super(nomeImagem, bloco, corrente);
	}
}
