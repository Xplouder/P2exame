package bonus;

import outros.Posicao;
import paineis.PainelBonus;
import celulas.Bloco;

public abstract class BonusDestruidorTotal extends BonusNormal {

	public BonusDestruidorTotal(PainelBonus painelBonus, String nomeImagem,
			int nivelMaximo, Posicao posicao, String som) {
		super(painelBonus, nomeImagem, nivelMaximo, posicao, som);
	}

	@Override
	public void destruir(Bloco bloco) {
		painel.destruicaoTotal(bloco);
	}

}
