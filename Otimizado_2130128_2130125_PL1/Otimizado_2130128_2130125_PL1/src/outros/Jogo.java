package outros;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;

import bonus.BonusNormal;

import celulas.Bloco;
import elementos.Elemento;
import interfaceGrafica.JanelaJogo;
import interfaces.IncrementadorNivelVida;
import interfaces.Iteravel;
import interfaces.AcessivelJogo;
import paineis.PainelBonus;
import paineis.PainelPrincipal;
import paineis.PainelVida;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;

/**
 * O Jogo é uma espécie de 'manager', isto é, o que controla todo o jogo, que
 * serve de elo para que todas as classes consigam comunicar entre si.
 * 
 * O jogo tem uma pontuação que é incrementada à medida em que se vão agrupando
 * elementos. Esta pontuação é incrementada em função duma tabela (HashTable)
 * que incrementa a pontuação consoante o número de elementos agrupados. O zero
 * corresponde a uma pontuação que seja superior a 5 elementos, atribuindo 500
 * pontos. Com esta HashTable evitamos um switch para cada valor, sendo apenas
 * necessário uma condicional para ir buscar o valor zero caso o incremento seja
 * superior a cinco.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public class Jogo implements Iteravel, IncrementadorNivelVida, AcessivelJogo {

	private JanelaJogo janelaJogo;
	private Random random;
	private int pontuacao;

	private static final Hashtable<Integer, Integer> tabelaPontuacao = new Hashtable<Integer, Integer>();
	static {
		tabelaPontuacao.put(3, 10);
		tabelaPontuacao.put(4, 50);
		tabelaPontuacao.put(5, 100);
		tabelaPontuacao.put(0, 500);
	}

	private PainelPrincipal painelPrincipal;
	private PainelVida painelVida;
	private PainelBonus painelBonus;

	public Jogo(JanelaJogo janelaJogo, GridPanel gridPainelPrincipal,
			GridPanel gridPainelVida, GridPanel gridPainelBonus) {
		this.janelaJogo = janelaJogo;
		pontuacao = 0;
		random = new Random();
		painelPrincipal = new PainelPrincipal(this, gridPainelPrincipal);
		painelVida = new PainelVida(this, gridPainelVida);
		painelBonus = new PainelBonus(this, gridPainelBonus);
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void incrementarPontuacao(int incremento) {
		pontuacao += incremento <= 5 ? tabelaPontuacao.get(incremento)
				: tabelaPontuacao.get(0);
	}

	@Override
	public void iterar() {
		painelPrincipal.iterar();
		painelVida.iterar();
	}


	@Override
	public void deselecionarBonus() {
		painelBonus.deselecionarBonus();
	}

	@Override
	public void limparGrelha() {
		painelPrincipal.limparGrelha();
	}

	@Override
	public int getIteracao() {
		return painelPrincipal.getIteracao();
	}

	@Override
	public void incrementarNivelVida(int incremento) {
		painelVida.incrementarNivelVida(incremento);
	}

	@Override
	public void incrementarNivelBonus(String chave, int incremento) {
		painelBonus.incrementarNivelBonus(chave, incremento);
	}

	@Override
	public void executarBonusSelecionado(Bloco blocoOrigem) {
		painelBonus.executarBonusSelecionado(blocoOrigem);
	}

	@Override
	public void adicionarListaBlocosComSombra(Bloco bloco) {
		painelPrincipal.adicionarListaBlocosComSombra(bloco);
	}

	@Override
	public void destruicaoParcial(Elemento elemento) {
		painelPrincipal.destruicaoParcial(elemento);
	}

	@Override
	public void destruicaoTotal(Bloco bloco) {
		painelPrincipal.destruicaoTotal(bloco);
	}

	@Override
	public Bloco getBloco(Posicao posicao) {
		return painelPrincipal.getBloco(posicao);
	}

	@Override
	public int nextInt(int numero) {
		return random.nextInt(numero);
	}

	@Override
	public int getMaxX() {
		return painelPrincipal.getMaxX();
	}

	@Override
	public int getMaxY() {
		return painelPrincipal.getMaxY();
	}

	@Override
	public String getNomeBonusSelecionado() {
		return painelBonus.getNomeBonusSelecionado();
	}

	@Override
	public LinkedList<Bloco> getListaBlocos() {
		return painelPrincipal.getListaBlocos();
	}

	@Override
	public boolean isConcluido() {
		return (painelVida.isVidaNivelZero() || painelPrincipal.isConcluido());
	}

	public void terminar() {
		if (painelVida.isVidaNivelZero())
			janelaJogo.terminar(false);
		else
			janelaJogo.terminar(true);
	}

	public BonusNormal getBonusSelecionado() {
		return painelBonus.getBonusSelecionado();
	}
}
