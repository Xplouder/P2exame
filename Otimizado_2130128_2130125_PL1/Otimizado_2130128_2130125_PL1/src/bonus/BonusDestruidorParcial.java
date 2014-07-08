package bonus;

import outros.Posicao;
import paineis.PainelBonus;
import celulas.Bloco;

public abstract class BonusDestruidorParcial extends BonusNormal {

	public BonusDestruidorParcial(PainelBonus painelBonus, String nomeImagem,
			int nivelMaximo, Posicao posicao, String som) {
		super(painelBonus, nomeImagem, nivelMaximo, posicao, som);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destruir(Bloco bloco) {
		painel.destruicaoParcial(bloco.getElemento());
	}

}
