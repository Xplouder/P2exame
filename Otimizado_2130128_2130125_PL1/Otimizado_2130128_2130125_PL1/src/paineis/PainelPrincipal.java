package paineis;

import interfaces.AcessivelPainelPrincipal;
import interfaces.Verificavel;

import java.awt.event.MouseEvent;
import java.util.LinkedList;

import outros.Corrente;
import outros.Grupo;
import outros.Jogo;
import outros.Posicao;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;
import pt.ipleiria.estg.dei.gridpanel.MediaLoader;
import pt.ipleiria.estg.dei.utils.FileHandler;
import bonus.BonusNormal;
import celulas.Bloco;
import celulas.Celula;
import celulas.Parede;
import elementos.Ampulheta;
import elementos.Anel;
import elementos.Balde;
import elementos.Bomba;
import elementos.Camarao;
import elementos.Carne;
import elementos.Caveira;
import elementos.Elemento;
import elementos.ElementoComCorrente;
import elementos.ElementoSemCorrente;
import elementos.Elmo;
import elementos.Estrela;
import elementos.Folha;
import elementos.Granada;
import elementos.Madeira;
import elementos.Martelo;
import elementos.PesoPesado;
import elementos.Raio;
import elementos.Trovao;

/**
 * O PainelPrincipal é a classe que gere a pontuação e toda a grelha. Ela é
 * responsável pelas funções essenciais no jogo tais como: iterar elementos,
 * agrupar os elementos, incrementar pontuação do jogo, explodir grupos (o
 * agrupamento é feito na classe Grupo para que esta classe (PainelPrincipal)
 * não ficasse demasiado grande e complexa. Contudo, esta classe é que é a
 * responsável de agrupar os elementos, isto é, fazer a explosão e incrementar a
 * pontuação), decrementar o nível de resistência dos blocos, criar Blocos,
 * Paredes e Elementos, trocar elementos, entre outras coisas.
 * 
 * Para tais métodos, esta classe precisa de ter essencialmente um grelha
 * (constituída por Células, (super classe da qual herda o Bloco e a Parede)).
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public class PainelPrincipal extends PainelIteravel implements Verificavel,
		AcessivelPainelPrincipal {

	// Indica o número total de elementos normais a colocar no nivel. Máx: 8
	private static final int NUMERO_DE_ELEMENTOS = 8;

	// Indica o número total de elementos bonus a colocar no nivel. Máx: 7
	private static final int NUMERO_DE_BONUS = 8;
	private static final int ITERACAO = 100;
	private static final int MAX_X = 20;
	private static final int MAX_Y = 10;
	private Celula[][] grelha;
	private LinkedList<Bloco> blocos;
	private LinkedList<Elemento> elementos;
	private LinkedList<Bloco> blocosComSombra;
	private LinkedList<Integer> elementosDoJogo;
	private Bloco blocoOrigem;
	private Bloco blocoDestino;
	private boolean elementoPendente;
	private Posicao posicaoMovido;

	public PainelPrincipal(Jogo jogo, GridPanel gridMainPanel) {
		super(jogo, gridMainPanel, 30000);
		grelha = new Celula[MAX_X][MAX_Y];
		blocos = new LinkedList<>();
		elementos = new LinkedList<>();
		blocosComSombra = new LinkedList<>();
		elementosDoJogo = new LinkedList<Integer>();
		elementoPendente = false;
		iniciar();
	}

	@Override
	public void iniciar() {
		escolherElementosAJogar();
		carregarNivel();
		eliminarPossiveisGruposIniciais();
		for (int x = 0; x < MAX_X; x++) {
			for (int y = 0; y < MAX_Y; y++) {
				atualizar(x, y);
			}
		}
		super.iniciar();
	}

	public void escolherElementosAJogar() {
		boolean ok;
		int aleatorio, j = 0;
		int[] elementosJaEscolhidos = new int[NUMERO_DE_ELEMENTOS];

		/**
		 * Escolhe "NUMERO_DE_ELEMENTOS" elementos normais para jogar ATENÇÃO:
		 * Pode incluir Caveira e Balde (não são ElementoBonus)
		 */
		while (elementosDoJogo.size() != NUMERO_DE_ELEMENTOS) {
			do {
				ok = true;
				aleatorio = 1 + nextInt(NUMERO_DE_ELEMENTOS);
				for (int i = 0; i < j; i++) {
					if (elementosJaEscolhidos[i] == aleatorio) {
						ok = false;
					}
				}
			} while (!ok);
			elementosJaEscolhidos[j] = aleatorio;
			elementosDoJogo.add(aleatorio);
			j++;
		}

		elementosJaEscolhidos = new int[NUMERO_DE_BONUS];
		j = 0;
		/**
		 * Escolhe "NUMERO_DE_ELEMENTOS" elementos Bonus para jogar
		 */
		while (elementosDoJogo.size() != (NUMERO_DE_ELEMENTOS + NUMERO_DE_BONUS)) {
			do {
				ok = true;
				aleatorio = NUMERO_DE_ELEMENTOS + nextInt(7);
				for (int i = 0; i < j; i++) {
					if (elementosJaEscolhidos[i] == aleatorio) {
						ok = false;
					}
				}
			} while (!ok);
			elementosJaEscolhidos[j] = aleatorio;
			elementosDoJogo.add(aleatorio);
			j++;
		}

	}

	public void carregarNivel() {
		FileHandler handler = new FileHandler("/niveis/nivel" + 13// nextInt(14)
				+ ".txt");
		String conteudo = handler.readFile();
		String[] colunas = null;
		int y = 0;
		for (String linha : conteudo.split("\n")) {
			colunas = linha.split(" ");
			for (int x = 0; x < colunas.length; x++) {
				if (colunas[x].matches("[0-2]E0")) {
					criarBlocoComElemento(new Bloco(new Posicao(x, y), this,
							Character.getNumericValue(colunas[x].charAt(0))),
							null);
				} else if (colunas[x].matches("[0-2]E[1-2]")) {
					criarBlocoComElemento(
							new Bloco(new Posicao(x, y), this,
									Character.getNumericValue(colunas[x]
											.charAt(0))),
							new Corrente(Character.getNumericValue(colunas[x]
									.charAt(2))));
				} else if (colunas[x].matches("[0-2]?00")) {
					criarBloco(new Bloco(new Posicao(x, y), this,
							Character.getNumericValue(colunas[x].charAt(0))));
				} else {
					criarParede(new Parede(this, new Posicao(x, y)));
				}
			}
			y++;
		}
	}

	public void criarBloco(Bloco bloco) {
		grelha[bloco.getPosicao().getX()][bloco.getPosicao().getY()] = bloco;
		blocos.add(bloco);
	}

	public void criarParede(Parede parede) {
		grelha[parede.getPosicao().getX()][parede.getPosicao().getY()] = parede;
	}

	public void criarBlocoComElemento(Bloco bloco, Corrente corrente) {
		criarBloco(bloco);
		criarElemento(bloco, corrente);
	}

	public void criarElemento(Bloco bloco, Corrente corrente) {
		int aleatorio;
		if (corrente == null) {
			aleatorio = nextInt(elementosDoJogo.size());
		} else {
			do {
				aleatorio = nextInt(elementosDoJogo.size());
			} while (elementosDoJogo.get(aleatorio) == 2); // impedir caveiras
			// (não podem ter corrente)
		}

		switch (elementosDoJogo.get(aleatorio)) {
		case 1:
			bloco.setElemento(new Balde(bloco, corrente));
			break;
		case 2:
			bloco.setElemento(new Caveira(bloco));
			break;
		case 3:
			bloco.setElemento(new Anel(bloco, corrente));
			break;
		case 4:
			bloco.setElemento(new Camarao(bloco, corrente));
			break;
		case 5:
			bloco.setElemento(new Carne(bloco, corrente));
			break;
		case 6:
			bloco.setElemento(new Elmo(bloco, corrente));
			break;
		case 7:
			bloco.setElemento(new Folha(bloco, corrente));
			break;
		case 8:
			bloco.setElemento(new Madeira(bloco, corrente));
			break;
		case 9:
			bloco.setElemento(new Ampulheta(bloco, corrente));
			break;
		case 10:
			bloco.setElemento(new Bomba(bloco, corrente));
			break;
		case 11:
			bloco.setElemento(new Estrela(bloco, corrente));
			break;
		case 12:
			bloco.setElemento(new Granada(bloco, corrente));
			break;
		case 13:
			bloco.setElemento(new Martelo(bloco, corrente));
			break;
		case 14:
			bloco.setElemento(new Raio(bloco, corrente));
			break;
		case 15:
			bloco.setElemento(new Trovao(bloco, corrente));
			break;
		case 16:
			bloco.setElemento(new PesoPesado(bloco, corrente));
			break;
		}

		elementos.add(bloco.getElemento());
	}

	public void eliminarPossiveisGruposIniciais() {
		boolean formaGrupo;
		do {
			formaGrupo = false;
			for (int e = 0; e < elementos.size(); e++) {
				Elemento elemento = elementos.get(e);
				if (Grupo.podeAgrupar(elemento, this)) {
					Elemento eTemp = elemento;
					Bloco bloco = elemento.getBloco();
					bloco.setElemento(null);

					if (!naoTemCorrente(eTemp)) {
						criarElemento(bloco, new Corrente(
								((ElementoComCorrente) eTemp).getNivel()));
					} else {
						criarElemento(bloco, null);
					}
					formaGrupo = true;
				}
			}
		} while (formaGrupo);
	}

	@Override
	public void atualizar(int x, int y) {
		grelhaPainel.put(x, y, grelha[x][y].getCellRepresentation());
		super.redesenharGrelha();
	}

	@Override
	public void iterar() {
		iterarElementos();
		criarNovosElementos();
		destroiNovosGrupos();
		if (passouTempoCadencia()) {
			destruir2elementosAleatorios();
		}
	}

	public void iterarElementos() {
		LinkedList<Elemento> elementosARemover = new LinkedList<>();
		for (Elemento elemento : elementos) {
			if (elemento.aindaEstaNoPainel()) {
				if (naoTemCorrente(elemento)) {
					elemento.iterar();
				}
			} else {
				elementosARemover.add(elemento);
			}
		}

		for (Elemento elemento : elementosARemover) {
			elementos.remove(elemento);
		}
	}

	public void criarNovosElementos() {
		for (int x = 0; x < grelha.length; x++) {
			Bloco bloco = getBloco(new Posicao(x, 0));
			if ((bloco != null) && (bloco.getElemento() == null)) {
				criarElemento(bloco, null);
			}
		}
	}

	public void destroiNovosGrupos() {
		for (int i = 0; i < elementos.size(); i++) {
			if (Grupo.podeAgrupar(elementos.get(i), this)) {
				Grupo g = new Grupo(elementos.get(i), this);
				g.destruir(jogo);
			}
		}
	}

	public void destruir2elementosAleatorios() {
		for (int i = 0; i < 2; i++) {
			Elemento elemento;
			do {
				elemento = elementos.get(nextInt(elementos.size()));
			} while (!naoTemCorrente(elemento));
			elemento.explodirParcial();
			if (elemento instanceof Caveira) {
				((Caveira) elemento).decrementarNivelBloco();
			}
			atualizarTempo();
		}
	}

	public boolean naoTemCorrente(Elemento elemento) {
		return (elemento instanceof ElementoSemCorrente)
				|| !((ElementoComCorrente) elemento).temCorrente();
	}

	@Override
	public int getIteracao() {
		return ITERACAO;
	}

	@Override
	public int getMaxX() {
		return MAX_X;
	}

	@Override
	public int getMaxY() {
		return MAX_Y;
	}

	public Elemento getElemento(Posicao posicao) {
		Bloco bloco = getBloco(posicao);
		return bloco != null ? bloco.getElemento() : null;
	}

	@Override
	public Bloco getBloco(Posicao posicao) {
		int x = posicao.getX();
		int y = posicao.getY();
		return (posicao.verificaLimites(MAX_X, MAX_Y)
				&& (grelha[x][y] instanceof Bloco) ? (Bloco) grelha[x][y]
				: null);
	}

	@Override
	public boolean isBlocoSemElemento(Posicao posicao) {
		Bloco bloco = getBloco(posicao);
		return (bloco != null) && (bloco.getElemento() == null);
	}

	public void moverElemento(Bloco origem, Posicao destino, Elemento elemento) {
		getBloco(destino).setElemento(elemento);
		origem.setElemento(null);
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent, int x, int y) {
		Posicao posicaoTemp = new Posicao(x, y);
		if ((posicaoMovido != null)
				&& posicaoMovido.isPosicaoIgual(posicaoTemp)) {
			return;
		} else {
			posicaoMovido = posicaoTemp;
			removerSombras();
		}

		Elemento elementoMovido = getElemento(posicaoMovido);
		BonusNormal selecionado = jogo.getBonusSelecionado();
		if ((elementoMovido != null) && (selecionado != null)) {
			selecionado.sombrear(elementoMovido.getBloco());
		}
	}

	public void removerSombras() {
		for (Bloco bloco : blocosComSombra) {
			bloco.setSombra(false);
		}
		blocosComSombra.clear();
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent, int x, int y) {
		if (!jogo.isConcluido()) {
			blocoOrigem = getBloco(new Posicao(x, y));
			BonusNormal bonusSelecionado = jogo.getBonusSelecionado();
			if (blocoOrigem != null) {
				if (bonusSelecionado == null) {
					Elemento e = blocoOrigem.getElemento();
					if ((e != null) && naoTemCorrente(e)) {
						elementoPendente = true;
					} else {
						audio("/sons/beep.wav");
					}
				} else {
					bonusSelecionado.executar(blocoOrigem);
				}
			} else {
				elementoPendente = false;
				if (bonusSelecionado != null) {
					jogo.deselecionarBonus();
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent, int x, int y) {
		if (elementoPendente) {
			blocoDestino = getBloco(new Posicao(x, y));

			if ((blocoDestino != null)
					&& blocoDestino.isAdjacente(blocoOrigem.getPosicao())) {
				Elemento e = blocoDestino.getElemento();

				if ((e != null) && naoTemCorrente(e)
						&& !e.saoElementosIguais(blocoOrigem.getElemento())) {
					trocarElementos(blocoOrigem, blocoDestino);
					elementoPendente = false;

					if (!Grupo.podeAgrupar(blocoOrigem.getElemento(), this)
							&& !Grupo.podeAgrupar(blocoDestino.getElemento(),
									this)) {
						new Thread() {
							public void run() {
								try {
									sleep(175);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								audio("/sons/beep.wav");
								trocarElementos(blocoDestino, blocoOrigem);
							};
						}.start();
					}
				} else {
					audio("/sons/beep.wav");
					elementoPendente = false;
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		for (Bloco bloco : blocosComSombra) {
			bloco.setSombra(false);
		}
	}

	public void trocarElementos(Bloco blocoOrigem, Bloco blocoDestino) {
		Elemento origem = blocoOrigem.getElemento();
		blocoOrigem.setElemento(blocoDestino.getElemento());
		blocoDestino.setElemento(origem);
	}

	@Override
	public void destruicaoParcial(Elemento elemento) {

		if (elemento != null) {
			elemento.explodirParcial();
			atualizarTempo();
		}
	}

	@Override
	public void destruicaoTotal(Bloco bloco) {
		removerSombras();
		Elemento elemento = bloco.getElemento();
		if (elemento != null) {
			elemento.explodirTotal();
		} else {
			bloco.destruir(true);
		}
		atualizarTempo();
	}

	public void incrementarNivelBonus(String chave, int incremento) {
		jogo.incrementarNivelBonus(chave, incremento);
	}

	public void removerElementoDaLista(Posicao posicao) {
		for (int e = 0; e < elementos.size(); e++) {
			if (elementos.get(e).getPosicao().isPosicaoIgual(posicao)) {
				elementos.remove(e);
			}
		}
	}

	public boolean vizinhosNaoIteramBaixo(Posicao posicao) {
		int x0 = posicao.getX();
		int y0 = posicao.getY();
		int x;
		int y;

		for (Elemento elemento : elementos) {
			x = elemento.getPosicao().getX();
			y = elemento.getPosicao().getY();
			if (((x == (x0 + 1)) || (x == (x0 - 1))) && (y <= y0)) {
				Posicao posicaoElemento = elemento.getPosicao();
				if (naoTemCorrente(elemento)
						&& elemento.podeCair(posicaoElemento
								.posicaoDelta(new Posicao(0, 1)))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean isConcluido() {
		for (Bloco bloco : blocos) {
			if (bloco.getNivel() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void limparGrelha() {
		new Thread() {
			public void run() {
				for (int y = 0; y < grelha[0].length; y++) {
					limparLinha(y);
					try {
						sleep(25);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};

		}.start();
	}

	public void limparLinha(int y) {
		for (int x = 0; x < grelha.length; x++) {
			criarParede(new Parede(this, new Posicao(x, y)));
			atualizar(x, y);
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void audio(String nomeFicheiro) {
		MediaLoader.getClip(nomeFicheiro).start();
	}

	@Override
	public LinkedList<Bloco> getListaBlocos() {
		return blocos;
	}

	@Override
	public void adicionarListaBlocosComSombra(Bloco bloco) {
		bloco.setSombra(true);
		blocosComSombra.add(bloco);
	}

	@Override
	public int nextInt(int numero) {
		return jogo.nextInt(numero);
	}

	@Override
	public void incrementarNivelVida(int numeroDeElementos) {
		jogo.incrementarNivelVida(numeroDeElementos);
	}
}