package delegacoes;

import interfaces.Decrementavel;
import interfaces.SaberNivel;
import interfaces.Incrementavel;
import interfaces.SaberNivelMaximo;


/**
 * O Nivelador é uma classe que, no fundo, serve como delegação para evitar
 * código repetido na corrente, no bloco, na vida e nos bonus.
 * 
 * Os atributos nível e nivelMaximo pertencem a todas as classes acima referidas
 * e os métodos aqui presentes são usados em todas as classes. Estes métodos são
 * todos respeitantes a acessos e/ou modificações a estes atributos.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public class Nivelador implements SaberNivel, SaberNivelMaximo, Incrementavel, Decrementavel {
	protected int nivel;
	protected final int nivelMaximo;

	public Nivelador(int nivel, int nivelMaximo) {
		this.nivel = nivel;
		this.nivelMaximo = nivelMaximo;
	}

	@Override
	public boolean isNivelMaximo() {
		return nivel == nivelMaximo;
	}

	@Override
	public int getNivel() {
		return nivel;
	}

	@Override
	public void incrementarNivel(int incremento) {
		nivel = (nivel + incremento < nivelMaximo ? nivel + incremento : nivelMaximo);
	}

	@Override
	public void decrementarNivel() {
		if (nivel != 0)
			nivel--;
	}
}
