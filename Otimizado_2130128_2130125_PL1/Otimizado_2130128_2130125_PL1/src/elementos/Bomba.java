package elementos;

import outros.Corrente;
import celulas.Bloco;

public class Bomba extends ElementoBonus {

	public Bomba(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/bomba", bloco, correnteSimples);
	}
}
