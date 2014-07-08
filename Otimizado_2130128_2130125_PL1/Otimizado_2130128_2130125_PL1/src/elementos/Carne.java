package elementos;

import outros.Corrente;
import celulas.Bloco;


public class Carne extends ElementoComCorrente {

	public Carne(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/carne", bloco, correnteSimples);
	}

}