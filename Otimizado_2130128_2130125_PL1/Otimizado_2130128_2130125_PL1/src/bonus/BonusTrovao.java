package bonus;

import java.util.LinkedList;

import celulas.Bloco;
import outros.Posicao;
import paineis.PainelBonus;

/**
 * BonusTrovão estende de BonusImediato e tem como função escolher quaisqer
 * entre 5 a 15 elementos a destruir aleatoriamente.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public class BonusTrovao extends BonusImediato {

	public BonusTrovao(PainelBonus painelBonus) {
		super(painelBonus, "/imagens_bonus/bonus_trovao_", 8,
				new Posicao(4, 0), "/sons/lightning.wav");
	}

	@Override
	public void executarBonusImediato() {
		trovoada(0, 5 + getPainel().nextInt(11), getPainel().getListaBlocos());
		super.executarBonusImediato();
	}

	public void trovoada(int i, int condicaoParagem, LinkedList<Bloco> blocos) {
		if (i == condicaoParagem)
			return;
		Bloco bloco;
		do {
			bloco = blocos.get(getPainel().nextInt(blocos.size()));
		} while (bloco.getElemento() == null);
		bloco.setElemento(null);
		trovoada(i + 1, condicaoParagem, blocos);
	}
}
