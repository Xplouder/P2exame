package elementos;

import celulas.Bloco;

/**
 * A Caveira é um elemento que é um elemento Incrementador, assim como os
 * elementosEspeciais. A sua função é, ao agrupar, incrementar o nível de
 * resistência do bloco onde se situa.
 * 
 * @authors Daniel Pinto e Diogo Simão.
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
