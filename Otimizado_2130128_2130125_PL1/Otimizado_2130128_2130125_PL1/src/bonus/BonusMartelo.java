package bonus;

import java.util.LinkedList;

import celulas.Bloco;
import outros.Posicao;
import paineis.PainelBonus;

/**
 * 
 * O BonusMartelo � definido como sendo um BonusNormal que vai atuar sobre um
 * bloco, destru�ndo-o parcialmente, isto �, diminuindo/retirando a corrente ao
 * elemento, ou, caso n�o tenha corrente, destruir o elemento e decrementar um
 * n�vel � resist�ncia do bloco.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 * 
 */
public class BonusMartelo extends BonusDestruidorParcial {

	public BonusMartelo(PainelBonus painelBonus) {
		super(painelBonus, "/imagens_bonus/bonus_martelo_", 4,
				new Posicao(0, 0), "/sons/hammer.wav");
	}

	@Override
	public LinkedList<Bloco> getBlocosInfluenciados(Bloco blocoOrigem) {
		LinkedList<Bloco> influenciaveis=new LinkedList<>();
		influenciaveis.add(blocoOrigem);
		return influenciaveis;
	}
}
