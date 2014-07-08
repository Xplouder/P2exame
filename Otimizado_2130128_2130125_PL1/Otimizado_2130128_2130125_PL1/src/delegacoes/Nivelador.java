package delegacoes;

import interfaces.Decrementavel;
import interfaces.SaberNivel;
import interfaces.Incrementavel;
import interfaces.SaberNivelMaximo;


/**
 * O Nivelador � uma classe que, no fundo, serve como delega��o para evitar
 * c�digo repetido na corrente, no bloco, na vida e nos bonus.
 * 
 * Os atributos n�vel e nivelMaximo pertencem a todas as classes acima referidas
 * e os m�todos aqui presentes s�o usados em todas as classes. Estes m�todos s�o
 * todos respeitantes a acessos e/ou modifica��es a estes atributos.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
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
