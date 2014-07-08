package elementos;

import outros.Corrente;
import celulas.Bloco;

public class Balde extends ElementoEspecial {

	public Balde(Bloco bloco, Corrente correnteSimples) {
		super("/imagens_elementos/balde", bloco, correnteSimples);
	}

	@Override
	public void incrementarNivel(int tamanhoGrupo) {
		bloco.incrementarNivelVida(tamanhoGrupo - 2);
	}
}
