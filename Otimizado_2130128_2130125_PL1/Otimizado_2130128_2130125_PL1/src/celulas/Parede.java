package celulas;

import outros.Posicao;
import paineis.PainelPrincipal;
import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

/**
 * 
 * A Parede � uma classe que serve de obst�culo, isto �, � uma for�a que impede
 * que naquela posi��o da grelha do PainelPrincipal haja elementos.
 * 
 * Assim sendo, a parede representa graficamente os limites m�ximos da grelha do
 * jogo, de modo que o jogador n�o os pode usar para jogar com os elementos.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
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
