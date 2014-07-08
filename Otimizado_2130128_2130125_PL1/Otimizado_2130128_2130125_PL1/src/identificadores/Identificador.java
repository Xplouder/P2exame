package identificadores;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;

/**
 * 
 * 'Identificador' � uma super classe abstrata que tem um nomeImagem e deste
 * modo todo o objeto que herda desta super classe saber representar-se
 * graficamente atrav�s de uma imagem.
 * 
 * Tem um m�todo abstrato getCellRepresentation() � o que os objetos herdam
 * desta super classe. Como s�o muito variados e cada um tem a sua forma de se
 * representar, ent�o faz desta classe uma classe abstrata com o m�todo
 * getCellRepresentation () abstrato. Uns apenas precisam de representar a sua
 * imagem, outros, como no caso do Elemento e do Bloco, por vezes necessitam de fazer
 * Overlay de imagens.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 * 
 */
public abstract class Identificador {
	protected String nomeImagem;

	public Identificador(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public abstract CellRepresentation getCellRepresentation();
}
