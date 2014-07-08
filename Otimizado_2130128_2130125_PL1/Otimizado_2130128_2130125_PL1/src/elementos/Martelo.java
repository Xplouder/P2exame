package elementos;

import outros.Corrente;
import celulas.Bloco;


public class Martelo extends ElementoBonus {

	public Martelo(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/martelo", bloco, correnteSimples);
	}
}
