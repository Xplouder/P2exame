package elementos;

import outros.Corrente;
import celulas.Bloco;


public class Madeira extends ElementoComCorrente {

	public Madeira(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/madeira", bloco, correnteSimples);
	}

}
