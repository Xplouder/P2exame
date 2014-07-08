package elementos;

import outros.Corrente;
import celulas.Bloco;

public class PesoPesado extends ElementoBonus {

	public PesoPesado(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/pesopesado", bloco, correnteSimples);
	}

}