package paineis;

import interfaces.AcessivelPainelBonus;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import outros.Jogo;
import outros.Posicao;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;
import bonus.Bonus;
import bonus.BonusAmpulheta;
import bonus.BonusBomba;
import bonus.BonusEstrela;
import bonus.BonusGranada;
import bonus.BonusMartelo;
import bonus.BonusNormal;
import bonus.BonusRaio;
import bonus.BonusTrovao;
import celulas.Bloco;
import elementos.Elemento;

/**
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public class PainelBonus extends Painel implements AcessivelPainelBonus {

	private static final Hashtable<Integer, String> hashNomes = new Hashtable<Integer, String>();
	static {
		hashNomes.put(0, "Martelo");
		hashNomes.put(1, "Bomba");
		hashNomes.put(2, "Raio");
		hashNomes.put(3, "Ampulheta");
		hashNomes.put(4, "Trovao");
		hashNomes.put(5, "Granada");
		hashNomes.put(6, "Estrela");
	}
	private HashMap<String, Bonus> hashBonus;
	private BonusNormal bonusSelecionado;

	public PainelBonus(Jogo jogo, GridPanel gridBonusPanel) {
		super(jogo, gridBonusPanel);
		hashBonus = new HashMap<>();
		hashBonus.put("Martelo", new BonusMartelo(this));
		hashBonus.put("Bomba", new BonusBomba(this));
		hashBonus.put("Raio", new BonusRaio(this));
		hashBonus.put("Ampulheta", new BonusAmpulheta(this));
		hashBonus.put("Trovao", new BonusTrovao(this));
		hashBonus.put("Granada", new BonusGranada(this));
		hashBonus.put("Estrela", new BonusEstrela(this));
		iniciar();
	}

	@Override
	public void iniciar() {
		for (String nome : hashNomes.values()) {
			atualizar(hashBonus.get(nome).getPosicao().getX(), 0);
		}
		super.iniciar();
	}

	@Override
	public void atualizar(int x, int y) {
		grelhaPainel.put(x, y, hashBonus.get(getNomeBonus(x))
				.getCellRepresentation());
		redesenharGrelha();
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent, int x, int y) {
		if (getBonusSelecionado() != null) {
			bonusSelecionado.inverterSelecionado();
			if (bonusSelecionado.getNome().equals("Bonus" + getNomeBonus(x))) {
				deselecionarBonus();
			} else {
				hashBonus.get(getNomeBonus(x)).selecionar();
			}
		} else {
			hashBonus.get(getNomeBonus(x)).selecionar();
		}
	}

	public void setBonusSelecionado(BonusNormal bonus) {
		this.bonusSelecionado = bonus;
		if (bonusSelecionado != null) {
			bonusSelecionado.inverterSelecionado();
		}
	}

	public BonusNormal getBonusSelecionado() {
		return bonusSelecionado;
	}

	@Override
	public void deselecionarBonus() {
		bonusSelecionado = null;
	}

	@Override
	public Bloco getBloco(Posicao posicao) {
		return jogo.getBloco(posicao);
	}

	public String getNomeBonus(int x) {
		return hashNomes.get(x);
	}

	@Override
	public void executarBonusSelecionado(Bloco blocoOrigem) {
		bonusSelecionado.executar(blocoOrigem);
	}

	@Override
	public void adicionarListaBlocosComSombra(Bloco bloco) {
		jogo.adicionarListaBlocosComSombra(bloco);
	}

	@Override
	public void destruicaoParcial(Elemento elemento) {
		jogo.destruicaoParcial(elemento);
	}

	@Override
	public void destruicaoTotal(Bloco bloco) {
		jogo.destruicaoTotal(bloco);
	}

	@Override
	public int nextInt(int numero) {
		return jogo.nextInt(numero);
	}

	@Override
	public int getMaxX() {
		return jogo.getMaxX();
	}

	@Override
	public int getMaxY() {
		return jogo.getMaxY();
	}

	@Override
	public void incrementarNivelBonus(String chave, int numeroDeElementos) {
		(hashBonus.get(chave)).incrementarNivel((numeroDeElementos > 2)
				&& (numeroDeElementos < 5) ? 1 : 2);
	}

	@Override
	public String getNomeBonusSelecionado() {
		return bonusSelecionado.getNome();
	}

	public LinkedList<Bloco> getListaBlocos() {
		return jogo.getListaBlocos();
	}

	@Override
	public void incrementarNivelVida(int incremento) {
		jogo.incrementarNivelVida(incremento);
	}
}
