package celulas;

import delegacoes.NiveladorBloco;
import outros.Posicao;
import interfaces.Atualizavel;
import interfaces.IncrementadorNivelBonus;
import interfaces.IncrementadorNivelVida;
import interfaces.SaberNivel;
import interfaces.Verificavel;
import interfaces.Vizinhanca;
import interfaces.SaberAdjacente;
import elementos.Elemento;
import paineis.PainelPrincipal;
import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.OverlayCellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

/**
 * 
 * O Bloco � uma classe que estende de C�lula e que est� associada a uma grelha
 * de c�lulas no painelPrincipal. Ele pode ter um elemento e pode estar a
 * sombreado, caso esteja selecionado um bonus sobre o bloco.
 * 
 * O bloco � um delegante que possui um delegado chamado niveladorBonus, o qual
 * cont�m os atributos n�vel e nivelMaximo (inteiros), e todos os m�todos que
 * usam destes atributos, isto �, todos os m�todos dos atributos n�vel e
 * nivelMaximo foram delegados ao niveladorBloco.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 * 
 */
public class Bloco extends Celula implements Atualizavel, Verificavel,
		Vizinhanca, SaberAdjacente, IncrementadorNivelVida, IncrementadorNivelBonus, SaberNivel {

	private NiveladorBloco niveladorBloco;
	private Elemento elemento;
	private boolean sombra;

	private static final SingleImageCellRepresentation SOMBRA = new SingleImageCellRepresentation(
			"/efeitos/shadow.png");

	public Bloco(Posicao posicao, PainelPrincipal painelPrincipal, int nivel) {
		super("/imagens_celulas/blocoNivel", painelPrincipal, posicao);
		niveladorBloco = new NiveladorBloco(nivel, 2);
		sombra = false;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
		if (elemento != null)
			elemento.setBloco(this);
		atualizar(posicao.getX(), posicao.getY());
	}

	public void decrementarNivel(boolean putNivel0) {
		if (!putNivel0)
			niveladorBloco.decrementarNivel();
		else
			niveladorBloco.reporNivelZero();
		atualizar(posicao.getX(), posicao.getY());
	}

	public void incrementaNivel() {
		niveladorBloco.incrementarNivel(1);
		atualizar(posicao.getX(), posicao.getY());
	}

	@Override
	public int getNivel() {
		return niveladorBloco.getNivel();
	}

	@Override
	public CellRepresentation getCellRepresentation() {
		CellRepresentation representacaoGrafica = new SingleImageCellRepresentation(
				nomeImagem + niveladorBloco.getNivel() + ".png");
		if (elemento != null)
			representacaoGrafica = new OverlayCellRepresentation(
					representacaoGrafica, elemento.getCellRepresentation());
		if (sombra)
			representacaoGrafica = new OverlayCellRepresentation(
					representacaoGrafica, SOMBRA);
		return representacaoGrafica;
	}

	@Override
	public void atualizar(int x, int y) {
		painel.atualizar(x, y);
	}

	@Override
	public boolean vizinhosNaoIteramBaixo() {
		return painel.vizinhosNaoIteramBaixo(posicao);
	}

	@Override
	public boolean isBlocoSemElemento(Posicao posicao) {
		return painel.isBlocoSemElemento(posicao);
	}

	@Override
	public boolean isAdjacente(Posicao comparacao) {
		return posicao.isAdjacente(comparacao);
	}

	public void moverElemento(Posicao destino, Elemento elemento) {
		painel.moverElemento(this, destino, elemento);
	}

	public void setSombra(boolean sombra) {
		this.sombra = sombra;
		atualizar(posicao.getX(), posicao.getY());
	}

	public void destruir(boolean putNivel0) {
		setElemento(null);
		decrementarNivel(putNivel0);
	}

	@Override
	public void incrementarNivelBonus(String chave, int incremento) {
		painel.incrementarNivelBonus(chave, incremento);
	}

	@Override
	public void incrementarNivelVida(int numeroDeElementos) {
		painel.incrementarNivelVida(numeroDeElementos);
	}

	public boolean aindaContemEsteElemento(Elemento elemento) {
		// TODO Auto-generated method stub
		return this.elemento == elemento;
	}
}
