package interfaces;

import bonus.BonusNormal;
import celulas.Bloco;

/**
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public interface AcessivelPainelBonus extends Acessivel {
	void incrementarNivelBonus(String chave, int numeroDeElementos);
	BonusNormal getBonusSelecionado();
	void executarBonusSelecionado(Bloco bloco);
	void deselecionarBonus();
	String getNomeBonusSelecionado();
}
