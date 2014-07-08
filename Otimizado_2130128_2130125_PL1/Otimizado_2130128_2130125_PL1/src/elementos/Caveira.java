package elementos;

import celulas.Bloco;

/**
 * A Caveira � um elemento que � um elemento Incrementador, assim como os
 * elementosEspeciais. A sua fun��o �, ao agrupar, incrementar o n�vel de
 * resist�ncia do bloco onde se situa.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 * 
 */
public class Caveira extends ElementoSemCorrente {

	public Caveira(Bloco bloco) {
		super("/imagens_elementos/caveira", bloco);
	}

	public void decrementarNivelBloco() {
		bloco.decrementarNivel(false);
	}

	@Override
	public void explodirParcial() {
		bloco.setElemento(null);
		decrementarNivelBloco();
	}

	@Override
	public void explodirGrupo() {
		bloco.setElemento(null);
		getBloco().incrementaNivel();
	}
}
