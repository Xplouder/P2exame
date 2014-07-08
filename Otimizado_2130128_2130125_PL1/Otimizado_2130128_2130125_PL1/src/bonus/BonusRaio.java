package bonus;

import java.util.LinkedList;

import celulas.Bloco;
import outros.Posicao;
import paineis.PainelBonus;

/**
 * O BonusRaio é uma BonusNormal que destroi de forma total a linha e a coluna
 * que contém o bloco enviado por argumento na função executarBonusNormal.
 * 
 * É possível ver a linha e a coluna a ser afetada através da aplicação de
 * sombra nesses blocos.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 */
public class BonusRaio extends BonusDestruidorTotal {

	public BonusRaio(PainelBonus painelBonus) {
		super(painelBonus, "/imagens_bonus/bonus_raio_", 6,
				new Posicao(2, 0), "/sons/lightning.wav");
	}

	@Override
	public LinkedList<Bloco> getBlocosInfluenciados(Bloco blocoOrigem) {
		LinkedList<Bloco> influenciaveis=new LinkedList<>();
	
		for (int x = 0; x < painel.getMaxX(); x++) {
			Bloco b=painel.getBloco(new Posicao(x, blocoOrigem.getPosicao().getY()));
			if (b!=null)
				influenciaveis.add(b);
		}

		for (int y = 0; y < painel.getMaxY(); y++) {
			Bloco b=painel.getBloco(new Posicao(blocoOrigem.getPosicao().getX(), y));
			if (b!=null)
				influenciaveis.add(b);
		}

		return influenciaveis;
	}
}