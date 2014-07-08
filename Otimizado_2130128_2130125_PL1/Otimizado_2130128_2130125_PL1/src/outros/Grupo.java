package outros;

import interfaces.Incrementador;

import java.util.LinkedList;

import paineis.PainelPrincipal;
import elementos.Elemento;

/**
 * Esta classe é responsável por verificar se existe agrupamento para um dado
 * elemento, passado como argumento à função podeAgrupar(...)
 * 
 * 
 */
public class Grupo {
	private LinkedList<Elemento> elementos;
	private LinkedList<Elemento> processados;
	private LinkedList<Elemento> horizontaisPorProcessar;
	private LinkedList<Elemento> verticaisPorProcessar;

	private LinkedList<Elemento> aux; // Lista temporaria. Atributo para
										// aumentar desempenho...

	private PainelPrincipal painel;

	public Grupo(Elemento elemento, PainelPrincipal painel) {
		elementos = new LinkedList<>();
		processados = new LinkedList<>();
		horizontaisPorProcessar = new LinkedList<>();
		verticaisPorProcessar = new LinkedList<>();
		aux = new LinkedList<>();
		this.painel = painel;

		horizontaisPorProcessar.add(elemento);
		verticaisPorProcessar.add(elemento);
		processarElementos();

		if (elementos.size() < 3) {
			throw new RuntimeException(
					"Não se podem construir grupos com menos do que 3 elementos!");
		}
	}

	private void processarElementos() {
		do {
			while (horizontaisPorProcessar.size() > 0) {
				Elemento elemAProcessar = horizontaisPorProcessar.removeFirst();
				processados.add(elemAProcessar);
				LinkedList<Elemento> horizontais = obterElementosHorizontais(elemAProcessar);
				for (Elemento e : horizontais) {
					if (!elementos.contains(e)) {
						elementos.add(e);
					}
					if (!processados.contains(e)) {
						verticaisPorProcessar.add(e);
					}
				}
			}

			while (verticaisPorProcessar.size() > 0) {
				Elemento elemAProcessar = verticaisPorProcessar.removeFirst();
				processados.add(elemAProcessar);
				LinkedList<Elemento> verticais = obterElementosVerticais(elemAProcessar);
				for (Elemento e : verticais) {
					if (!elementos.contains(e)) {
						elementos.add(e);
					}
					if (!processados.contains(e)) {
						horizontaisPorProcessar.add(e);
					}
				}

			}
		} while ((horizontaisPorProcessar.size() > 0)
				|| (verticaisPorProcessar.size() > 0));
	}

	private LinkedList<Elemento> obterElementosHorizontais(Elemento elemento) {
		int x0 = elemento.getPosicao().getX();
		int y0 = elemento.getPosicao().getY();
		aux.clear();

		aux.add(elemento);
		for (int x = x0 + 1; x < painel.getMaxX(); x++) {
			Elemento eDireita = painel.getElemento(new Posicao(x, y0));
			if ((eDireita != null) && elemento.saoElementosIguais(eDireita)) {
				aux.add(eDireita);
			} else {
				break;
			}
		}

		for (int x = x0 - 1; x >= 0; x--) {
			Elemento eEsquerda = painel.getElemento(new Posicao(x, y0));
			if ((eEsquerda != null) && elemento.saoElementosIguais(eEsquerda)) {
				aux.add(eEsquerda);
			} else {
				break;
			}
		}

		if (aux.size() < 3) {
			aux.clear();
		}

		return aux;
	}

	private LinkedList<Elemento> obterElementosVerticais(Elemento elemento) {
		int x0 = elemento.getPosicao().getX();
		int y0 = elemento.getPosicao().getY();
		aux.clear();

		aux.add(elemento);
		for (int y = y0 + 1; y < painel.getMaxY(); y++) {
			Elemento eBaixo = painel.getElemento(new Posicao(x0, y));
			if ((eBaixo != null) && elemento.saoElementosIguais(eBaixo)) {
				aux.add(eBaixo);
			} else {
				break;
			}
		}

		for (int y = y0 - 1; y >= 0; y--) {
			Elemento eCima = painel.getElemento(new Posicao(x0, y));
			if ((eCima != null) && elemento.saoElementosIguais(eCima)) {
				aux.add(eCima);
			} else {
				break;
			}
		}

		if (aux.size() < 3) {
			aux.clear();
		}

		return aux;
	}

	public static boolean podeAgrupar(Elemento elemento,
			PainelPrincipal painelPrincipal) {

		int x0 = elemento.getPosicao().getX();
		int y0 = elemento.getPosicao().getY();

		int numHorizontal = 1;
		int numVertical = 1;

		for (int x = x0 + 1; x < painelPrincipal.getMaxX(); x++) {
			Elemento eDireita = painelPrincipal.getElemento(new Posicao(x, y0));
			if ((eDireita != null) && elemento.saoElementosIguais(eDireita)) {
				numHorizontal++;
			} else {
				break;
			}
		}

		for (int x = x0 - 1; x >= 0; x--) {
			Elemento eEsquerda = painelPrincipal
					.getElemento(new Posicao(x, y0));
			if ((eEsquerda != null) && elemento.saoElementosIguais(eEsquerda)) {
				numHorizontal++;
			} else {
				break;
			}
		}

		for (int y = y0 + 1; y < painelPrincipal.getMaxY(); y++) {
			Elemento eCima = painelPrincipal.getElemento(new Posicao(x0, y));
			if ((eCima != null) && elemento.saoElementosIguais(eCima)) {
				numVertical++;
			} else {
				break;
			}
		}

		for (int y = y0 - 1; y >= 0; y--) {
			Elemento eBaixo = painelPrincipal.getElemento(new Posicao(x0, y));
			if ((eBaixo != null) && elemento.saoElementosIguais(eBaixo)) {
				numVertical++;
			} else {
				break;
			}
		}

		return (numHorizontal > 2) || (numVertical > 2);
	}

	public void destruir(Jogo jogo) {
		for (Elemento e : elementos) {
			e.explodirGrupo();
		}

		jogo.incrementarPontuacao(elementos.size());

		Elemento primeiro = elementos.getFirst();
		if (primeiro instanceof Incrementador) {
			((Incrementador) primeiro).incrementarNivel(elementos.size());
		}
	}
}