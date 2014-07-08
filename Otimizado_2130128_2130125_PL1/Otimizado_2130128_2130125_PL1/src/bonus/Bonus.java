package bonus;

import delegacoes.NiveladorBonus;
import identificadores.IdentificadorComPainel;
import interfaces.Atualizavel;
import interfaces.Incrementavel;
import interfaces.ReporNivel;
import outros.Posicao;
import paineis.PainelBonus;
import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.MediaLoader;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

/**
 * A Classe Bonus � uma super-classe da qual todos os bonus (quer normais quer
 * imediatos) herdam. Tudo o que � comum em todos bonus, est� aqui implementado.
 * Todos os bonus t�m um som, que reproduzem quando se d� a explos�o. Todos os
 * bonus conhecem o painelBonus.
 * 
 * O Bonus tamb�m tem um n�vel e um nivelMaximo, e delega tudo o que tem a haver
 * com esses atributos atrav�s do NiveladorBonu, que � respons�vel pela gest�o
 * do n�vel do Bonus e de dar informa��es respeitantes ao n�vel.
 * 
 * @authors Daniel Pinto e Diogo Sim�o
 *
 */
public abstract class Bonus extends IdentificadorComPainel<PainelBonus> implements
		Atualizavel, Incrementavel, ReporNivel {

	protected String som;
	protected NiveladorBonus niveladorBonus;

	public Bonus(PainelBonus painelBonus, String nomeImagem, int nivelMaximo,
			Posicao posicao, String som) {
		super(nomeImagem, painelBonus, posicao);
		niveladorBonus = new NiveladorBonus(nivelMaximo);
		this.som = som;
	}

	public boolean isNivelMaximo() {
		return niveladorBonus.isNivelMaximo();
	}

	public void inverterSelecionado() {
		niveladorBonus.inverterSelecionado();
		atualizar(posicao.getX(), posicao.getY());
	}

	@Override
	public void reporNivelZero() {
		niveladorBonus.reporNivelZero();
		MediaLoader.getClip(som).start();
		painel.deselecionarBonus();
		atualizar(posicao.getX(), posicao.getY());
	}

	public String getNome() {
		return getClass().getSimpleName();
	}

	@Override
	public void atualizar(int x, int y) {
		painel.atualizar(x, y);
	}

	@Override
	public CellRepresentation getCellRepresentation() {
		return new SingleImageCellRepresentation(nomeImagem + niveladorBonus.getNivel() + ".png");
	}

	@Override
	public void incrementarNivel(int incremento) {
		niveladorBonus.incrementarNivel(incremento);
		atualizar(posicao.getX(), posicao.getY());
	}

	public void selecionar() {
		if (niveladorBonus.isNivelMaximo())
			executarSelecao();
	}
	
	protected abstract void executarSelecao();
}
