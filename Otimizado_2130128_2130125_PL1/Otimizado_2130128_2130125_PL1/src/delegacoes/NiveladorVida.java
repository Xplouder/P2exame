package delegacoes;

import interfaces.SaberNivelZeroVida;

/**
 * A delegação NiveladorVida serve para delegar atributos nível e nivelMaximo
 * e os seus respectivos métodos. Tem também um método especial que diz se o
 * nível já está a zero, útil para saber se o jogo já acabou.
 * 
 * @authors Daniel Pinto e Diogo Simão.
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
