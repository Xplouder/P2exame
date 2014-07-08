package bonus;

import java.util.LinkedList;

import outros.Posicao;
import paineis.PainelBonus;
import celulas.Bloco;

public class BonusPesoPesado extends BonusDestruidorParcial {

	public BonusPesoPesado(PainelBonus painelBonus) {
		super(painelBonus, "/imagens_bonus/bonus_pesopesado_", 10, new Posicao(
				7, 0), "/sons/hammer.wav");
	}

	@Override
	protected LinkedList<Bloco> getBlocosInfluenciados(Bloco blocoOrigem) {
		LinkedList<Bloco> influenciaveis = new LinkedList<>();
		influenciaveis.add(blocoOrigem);
		return influenciaveis;
	}

}
