package elementos;

import outros.Corrente;
import celulas.Bloco;

public class ElementoBonus extends ElementoEspecial {

	public ElementoBonus(String nomeImagem, Bloco bloco,
			Corrente correnteSimples) {
		super(nomeImagem, bloco, correnteSimples);
	}

	/**
	 * O m�todo � definido aqui e n�o em cada classe espec�fica pois � comum a
	 * todos os Elementos bonus. Todos enviamos o seu nome e o respetivo tamanho
	 * (size) de incremento, informa��o que vai ser re-encaminhada para o bloco,
	 * que por sua vez reencaminha para o painelPrincipal, que por sua vez
	 * reencaminha para o jogo, que por sua vez reencaminha para o painelBonus e
	 * este, por seu turno, incrementa o respectivo bonus.
	 */
	public void incrementarNivel(int tamanhoGrupo) {
		bloco.incrementarNivelBonus(getClass().getSimpleName(),
				tamanhoGrupo);
	}
}
