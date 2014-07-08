package bonus;

import java.util.LinkedList;

import celulas.Bloco;
import outros.Posicao;
import paineis.PainelBonus;

/**
 * 
 * O BonusBomba é um bonus que extende de BonusNormal e atua com uma
 * explosaoParcial, destruindo apenas os blocos que têm elementos e decremeta um
 * valor à respectiva resistência do bloco.
 * 
 * Este bonus destroi 9 blocos que formam um quadrado, sendo visivel pela sombra
 * que ele aplica sobre os respectivos bonus.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public class BonusBomba extends BonusDestruidorParcial {

	public BonusBomba(PainelBonus painelBonus) {
		super(painelBonus, "/imagens_bonus/bonus_bomba_", 5,
				new Posicao(1, 0), "/sons/explosion.wav");
	}

	@Override
	public LinkedList<Bloco> getBlocosInfluenciados(Bloco blocoOrigem) {
		LinkedList<Bloco> influenciaveis=new LinkedList<>();
		
		int x0 = blocoOrigem.getPosicao().getX() - 1;
		int y0 = blocoOrigem.getPosicao().getY() - 1;
		
		
		for (int x = x0; x <= x0 + 2; x++) {
			for (int y = y0; y <= y0 + 2; y++) {
				Bloco bloco = getPainel().getBloco(new Posicao(x, y));
				if (bloco != null) {
					influenciaveis.add(bloco);
				}
			}
		}
		return influenciaveis;
	}
}