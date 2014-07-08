package delegacoes;

import interfaces.ReporNivel;

/**
 * O NiveladorReiniciavel é uma classe "delegante" que possui um método
 * distinto, o repor o nível todo a zero, que apenas só é viável para os blocos
 * ou para os bonus.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public class NiveladorReiniciavel extends Nivelador implements ReporNivel {

	public NiveladorReiniciavel(int nivel, int nivelMaximo) {
		super(nivel, nivelMaximo);
	}

	@Override
	public void reporNivelZero() {
		nivel = 0;
	}
}
