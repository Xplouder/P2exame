package bonus;

import outros.Posicao;
import paineis.PainelBonus;

/**
 * O Bonus Ampulheta extende de BonusImediato e � uma classe que � respons�vel
 * por incrementar mais 10 valores ao n�vel da vida.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
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