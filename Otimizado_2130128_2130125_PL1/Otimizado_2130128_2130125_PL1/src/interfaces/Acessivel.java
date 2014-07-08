package interfaces;

import celulas.Bloco;
import elementos.Elemento;
import outros.Posicao;

/**
 *
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public interface Acessivel extends IncrementadorNivelBonus, IncrementadorNivelVida {
	void adicionarListaBlocosComSombra (Bloco bloco);
	int getMaxX();
	int getMaxY();
	int nextInt(int numero);
	void destruicaoParcial(Elemento elemento);
	void destruicaoTotal(Bloco bloco);
	Bloco getBloco (Posicao posicao);
}