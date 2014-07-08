package elementos;

import outros.Corrente;
import celulas.Bloco;


public class Raio extends ElementoBonus {

	public Raio(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/raio", bloco, correnteSimples);
	}
}
