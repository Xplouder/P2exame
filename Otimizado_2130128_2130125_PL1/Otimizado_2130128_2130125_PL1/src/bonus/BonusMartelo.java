package bonus;

import java.util.LinkedList;

import celulas.Bloco;
import outros.Posicao;
import paineis.PainelBonus;

/**
 * 
 * O BonusMartelo é definido como sendo um BonusNormal que vai atuar sobre um
 * bloco, destruíndo-o parcialmente, isto é, diminuindo/retirando a corrente ao
 * elemento, ou, caso não tenha corrente, destruir o elemento e decrementar um
 * nível à resistência do bloco.
 * 
 * @authors Daniel Pinto e Diogo Simão.
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
