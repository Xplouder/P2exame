package elementos;

import outros.Corrente;
import celulas.Bloco;


public class Estrela extends ElementoBonus {

	public Estrela(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/estrela", bloco, correnteSimples);
	}
}