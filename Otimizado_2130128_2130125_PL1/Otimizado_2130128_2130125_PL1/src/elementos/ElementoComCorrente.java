package elementos;

import outros.Corrente;
import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.OverlayCellRepresentation;
import celulas.Bloco;

/**
 * ElementoComCorrente é uma super classe que extende de elemento e que tem uma
 * corrente. Esta corrente, por sua vez tem dois níveis, que vão impedir, caso
 * ainda haja corrente no elemento, de que o elemento seja explodido. Ao invés,
 * quando é se forma um grupo ou se destroi parcialmente um elemento, o nível da
 * corrente é decrementado. Apenas quando o nível da corrente é 1, então o
 * elemento perde a corrente.
 * 
 * O método explodirParcial é implementado aqui, pois é comum a todos os elementos.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public class ElementoComCorrente extends Elemento {

	protected Corrente corrente;

	public ElementoComCorrente(String nomeImagem, Bloco bloco, Corrente corrente) {
		super(nomeImagem, bloco);
		this.corrente = corrente;
	}

	@Override
	public CellRepresentation getCellRepresentation() {
		return (temCorrente() ? new OverlayCellRepresentation(
				super.getCellRepresentation(), corrente.getCellRepresentation())
				: super.getCellRepresentation());
	}

	public boolean temCorrente() {
		return (corrente != null ? true : false);
	}

	public void atualizarCorrente() {
		if (corrente.isNivelMaximo())
			corrente.decrementarNivel();
		else
			corrente = null;
		atualizar(getPosicao().getX(), getPosicao().getY());
	}

	public void explodirParcial() {
		if (temCorrente())
			atualizarCorrente();
		else
			bloco.destruir(false);
	}

	public void setCorrente(Corrente corrente) {
		this.corrente = corrente;
	}

	public int getNivel() {
		return corrente.getNivel();
	}
	

	@Override
	public void explodirGrupo() {
		explodirParcial();
	}
}
