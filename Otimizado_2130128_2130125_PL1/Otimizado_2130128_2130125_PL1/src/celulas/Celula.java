package celulas;

import outros.Posicao;
import identificadores.IdentificadorComPainel;
import paineis.PainelPrincipal;

/**
 * A C�lula � uma super classe abstrata da qual herdam o Bloco e a Parede. Esta
 * super classe � importante na medida em que o PainelPrincipal, na sua grelha,
 * tanto pode ter paredes como blocos, como tal, a grelha do Painel passa a ser
 * uma grelha de C�lulas, reunindo as duas classes da hierarquia abaixo.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 * 
 */
public abstract class Celula extends IdentificadorComPainel<PainelPrincipal> {

	public Celula(String nomeImagem, PainelPrincipal painelPrincipal,
			Posicao posicao) {
		super(nomeImagem, painelPrincipal, posicao);
	}
}
