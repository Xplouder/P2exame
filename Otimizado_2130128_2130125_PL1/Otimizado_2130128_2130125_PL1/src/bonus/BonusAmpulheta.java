package bonus;

import outros.Posicao;
import paineis.PainelBonus;

/**
 * O Bonus Ampulheta extende de BonusImediato e é uma classe que é responsável
 * por incrementar mais 10 valores ao nível da vida.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public class BonusAmpulheta extends BonusImediato {

	public BonusAmpulheta(PainelBonus painelBonus) {
		super(painelBonus, "/imagens_bonus/bonus_ampulheta_", 7,
				new Posicao(3, 0), "/sons/life.wav");
	}

	@Override
	public void executarBonusImediato() {
		getPainel().incrementarNivelVida(10);
		super.executarBonusImediato();
	}
}