package delegacoes;

import interfaces.SaberNivelZeroVida;

/**
 * A delega��o NiveladorVida serve para delegar atributos n�vel e nivelMaximo
 * e os seus respectivos m�todos. Tem tamb�m um m�todo especial que diz se o
 * n�vel j� est� a zero, �til para saber se o jogo j� acabou.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 *
 */
public class NiveladorVida extends Nivelador implements SaberNivelZeroVida {

	public NiveladorVida() {
		super(20, 20);
	}

	@Override
	public boolean isVidaNivelZero() {
		return nivel == 0;
	}
}
