package celulas;

import outros.Posicao;
import paineis.PainelPrincipal;
import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

/**
 * 
 * A Parede é uma classe que serve de obstáculo, isto é, é uma força que impede
 * que naquela posição da grelha do PainelPrincipal haja elementos.
 * 
 * Assim sendo, a parede representa graficamente os limites máximos da grelha do
 * jogo, de modo que o jogador não os pode usar para jogar com os elementos.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public class Parede extends Celula {

	public Parede(PainelPrincipal painelPrincipal, Posicao posicao) {
		super("/imagens_celulas/parede_borderless.png", painelPrincipal,
				posicao);
	}

	@Override
	public CellRepresentation getCellRepresentation() {
		return new SingleImageCellRepresentation(nomeImagem);
	}
}
