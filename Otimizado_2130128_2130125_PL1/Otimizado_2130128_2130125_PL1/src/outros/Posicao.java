package outros;

import interfaces.SaberPosicaoIgual;
import interfaces.SaberAdjacente;

/**
 * 
 * Classe que permite saber uma dada posição numa dada grelha. Devem ter esta
 * classe todos as classes que estejam nessa dada grelha e que precisam de se
 * representar graficamente.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public class Posicao implements SaberAdjacente, SaberPosicaoIgual {
	private int x;
	private int y;

	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Posicao posicaoDelta(Posicao posicao) {
		return new Posicao(x + posicao.x, y + posicao.y);
	}

	@Override
	public boolean isAdjacente(Posicao comparacao) {
		return ((Math.abs(x - comparacao.x) == 1 && y == comparacao.y) || (x == comparacao.x && Math
				.abs(y - comparacao.y) == 1));
	}

	public boolean verificaLimites(int maxX, int maxY) {
		return x < maxX && x >= 0 && y < maxY && y >= 0;
	}

	@Override
	public boolean isPosicaoIgual(Posicao posicao) {
		return x == posicao.x && y == posicao.y;
	}
}
