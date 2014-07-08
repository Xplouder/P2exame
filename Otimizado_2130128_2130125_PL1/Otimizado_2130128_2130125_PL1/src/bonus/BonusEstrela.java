package bonus;

import java.util.LinkedList;

import celulas.Bloco;
import elementos.Elemento;
import outros.Posicao;
import paineis.PainelBonus;

/**
 * O BonusEstrela é um BonusNormal que tem um método que faz explodir totalmente
 * todos os blocos que possuem o mesmo elemento que é passado como argumento
 * para o método executarBonusNormal.
 * 
 * É possivel reconhecer os elementos que vão ser destruídos através da sombra
 * que é aplicada sobre o bloco que tem o elemento a ser explodido.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public class BonusEstrela extends BonusDestruidorTotal {

	public BonusEstrela(PainelBonus painelBonus) {
		super(painelBonus, "/imagens_bonus/bonus_estrela_", 11,
				new Posicao(6, 0), "/sons/explosion.wav");
	}

	@Override
	public LinkedList<Bloco> getBlocosInfluenciados(Bloco blocoOrigem) {
		LinkedList<Bloco> influenciaveis=new LinkedList<>();
	
		Elemento elemento = blocoOrigem.getElemento();
		if (elemento != null) {
			for (Bloco bloco : painel.getListaBlocos()) {
				Elemento aComparar = bloco.getElemento();
				if (aComparar != null && elemento.saoElementosIguais(aComparar)) {
					influenciaveis.add(bloco);
				}
			}
		}
		return influenciaveis;
	}
}