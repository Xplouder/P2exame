package elementos;

import outros.Corrente;
import celulas.Bloco;


public class Folha extends ElementoComCorrente {

	public Folha(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/folha", bloco, correnteSimples);
	}

}
