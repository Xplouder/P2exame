package elementos;

import outros.Corrente;
import celulas.Bloco;

public class Camarao extends ElementoComCorrente {

	public Camarao(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/camarao", bloco, correnteSimples);
	}

}
