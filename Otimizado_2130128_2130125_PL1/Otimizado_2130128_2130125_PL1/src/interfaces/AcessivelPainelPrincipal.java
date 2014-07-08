package interfaces;

import java.util.LinkedList;

import celulas.Bloco;

/**
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 *
 */
public interface AcessivelPainelPrincipal extends Acessivel {
	int getIteracao();
	LinkedList<Bloco> getListaBlocos();
	boolean isConcluido();
	void limparGrelha();
}
