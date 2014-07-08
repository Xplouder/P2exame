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
 * A Classe Bonus é uma super-classe da qual todos os bonus (quer normais quer
 * imediatos) herdam. Tudo o que é comum em todos bonus, está aqui implementado.
 * Todos os bonus têm um som, que reproduzem quando se dá a explosão. Todos os
 * bonus conhecem o painelBonus.
 * 
 * O Bonus também tem um nível e um nivelMaximo, e delega tudo o que tem a haver
 * com esses atributos através do NiveladorBonu, que é responsável pela gestão
 * do nível do Bonus e de dar informações respeitantes ao nível.
 * 
 * @authors Daniel Pinto e Diogo Simão
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
