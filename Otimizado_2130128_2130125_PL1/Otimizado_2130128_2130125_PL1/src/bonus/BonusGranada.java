package bonus;

import java.util.LinkedList;

import celulas.Bloco;
import outros.Posicao;
import paineis.PainelBonus;

/**
 * O BonusGranada é um bonus que extende de BonusNormal e que destroi de forma
 * total todos os blocos que estão definidos num losango de tamanho igual a 3.
 * 
 * É possivel visualizar a área do losango a ser destruída através da sombra
 * aplicada sobre esses blocos.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public class BonusGranada extends BonusDestruidorTotal {

	public BonusGranada(PainelBonus painelBonus) {
		super(painelBonus, "/imagens_bonus/bonus_granada_", 9,
				new Posicao(5, 0), "/sons/grenade.wav");
	}

	@Override
	public LinkedList<Bloco> getBlocosInfluenciados(Bloco blocoOrigem) {
		LinkedList<Bloco> influenciaveis=new LinkedList<>();
	
		int x0 = blocoOrigem.getPosicao().getX();
		int y0 = blocoOrigem.getPosicao().getY();
		int tamanho = 3;

		for (int x = -tamanho; x <= tamanho; x++) {
			for (int y = -tamanho; y <= tamanho; y++) {
				if (Math.abs(y) <= Math.abs(Math.abs(x) - tamanho)) {
					Bloco bloco = getPainel().getBloco(
							new Posicao(x + x0, y + y0));
					if (bloco != null) {
						influenciaveis.add(bloco);
					}
				}
			}
		}
		return influenciaveis;
	}
}