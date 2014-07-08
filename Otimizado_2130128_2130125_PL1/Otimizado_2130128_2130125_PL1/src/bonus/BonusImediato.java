package bonus;

import outros.Posicao;
import paineis.PainelBonus;

/**
 * O Bonus Imediato serve para distinguir os bonus Ampulheta e Trovão, já que
 * estes têm ambos um executar diferente dos demais. O Bonus Imediato é logo
 * executado, isto é, logo que esteja selecionado um desses dois Bonus, pelo que
 * não precisa de receber nenhum bloco como origem onde atuar, como os demais
 * blocos que herdam de BlocoNormal.
 *
 * @authors Daniel Pinto e Diogo Simão
 */
public class BonusImediato extends Bonus {

	public BonusImediato(PainelBonus painelBonus, String nomeImagem,
			int nivelMaximo, Posicao posicao, String som) {
		super(painelBonus, nomeImagem, nivelMaximo, posicao, som);
	}

	public void executarBonusImediato() {
		reporNivelZero();
	}
	
	@Override
	protected void executarSelecao() {
		executarBonusImediato();	
	}
}
