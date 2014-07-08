package elementos;

import outros.Corrente;
import celulas.Bloco;


public class Trovao extends ElementoBonus {

	public Trovao(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/trovao", bloco, correnteSimples);
	}
}
