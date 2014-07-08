package elementos;

import outros.Corrente;
import celulas.Bloco;


public class Anel extends ElementoComCorrente {

	public Anel(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/anel", bloco, correnteSimples);
	}
}
