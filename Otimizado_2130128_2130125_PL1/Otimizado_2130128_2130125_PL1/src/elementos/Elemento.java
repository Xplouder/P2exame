package elementos;

import identificadores.IdentificadorSabePosicao;
import interfaces.Atualizavel;
import interfaces.Iteravel;
import interfaces.SaberPosicaoIgual;
import interfaces.Vizinhanca;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import outros.Posicao;
import celulas.Bloco;
import paineis.PainelPrincipal;
import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

/**
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public abstract class Elemento extends IdentificadorSabePosicao implements
		Iteravel, Atualizavel, Vizinhanca, SaberPosicaoIgual {

	protected Bloco bloco;
	protected static final Posicao[] DELTAS = { new Posicao(0, 1),
			new Posicao(-1, 1), new Posicao(1, 1) };
	protected static final Integer[] posicoes = { 1, 2 };
	protected static List<Integer> listaPosicoes = Arrays.asList(posicoes);

	private boolean aCair = false;

	public Elemento(String nomeImagem, Bloco bloco) {
		super(nomeImagem + ".png");
		this.bloco = bloco;
	}

	@Override
	public CellRepresentation getCellRepresentation() {
		return new SingleImageCellRepresentation(nomeImagem);
	}

	public Bloco getBloco() {
		return bloco;
	}

	@Override
	public Posicao getPosicao() {
		return bloco.getPosicao();
	}

	public PainelPrincipal getPainelPrincipal() {
		return bloco.getPainel();
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	@Override
	public void iterar() {
		Posicao baixo = getPosicao().posicaoDelta(DELTAS[0]);
		aCair = false;
		if (podeCair(baixo))
			mover(baixo);
		else if (vizinhosNaoIteramBaixo()) {
			Collections.shuffle(listaPosicoes);
			for (Integer i : listaPosicoes) {
				Posicao diagonal = getPosicao().posicaoDelta(DELTAS[i]);
				if (podeCair(diagonal))
					mover(diagonal);
			}
		}
	}

	public boolean podeCair(Posicao posicao) {
		return bloco.isBlocoSemElemento(posicao);
	}

	public boolean estaCair() {

		return aCair;
	}

	@Override
	public boolean vizinhosNaoIteramBaixo() {
		return bloco.vizinhosNaoIteramBaixo();
	}

	public boolean saoElementosIguais(Elemento elemento) {
		return !(estaCair() || elemento.estaCair())
				&& nomeImagem.equals(elemento.nomeImagem);
	}

	@Override
	public boolean isPosicaoIgual(Posicao comparacao) {
		return getPosicao().isPosicaoIgual(comparacao);
	}

	@Override
	public void atualizar(int x, int y) {
		bloco.atualizar(x, y);
	}

	public void mover(Posicao destino) {
		bloco.moverElemento(destino, this);
		aCair = true;
	}

	public abstract void explodirParcial();

	public void explodirTotal() {
		bloco.destruir(true);
	}

	public abstract void explodirGrupo();

	public boolean aindaEstaNoPainel() {
		// TODO Auto-generated method stub
		return bloco.aindaContemEsteElemento(this);
	}
}