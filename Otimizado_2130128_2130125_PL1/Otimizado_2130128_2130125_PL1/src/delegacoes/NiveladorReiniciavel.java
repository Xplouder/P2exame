package delegacoes;

import interfaces.ReporNivel;

/**
 * O NiveladorReiniciavel � uma classe "delegante" que possui um m�todo
 * distinto, o repor o n�vel todo a zero, que apenas s� � vi�vel para os blocos
 * ou para os bonus.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
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
