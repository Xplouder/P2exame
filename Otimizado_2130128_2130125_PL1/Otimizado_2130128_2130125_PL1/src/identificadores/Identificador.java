package identificadores;

import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;

/**
 * 
 * 'Identificador' é uma super classe abstrata que tem um nomeImagem e deste
 * modo todo o objeto que herda desta super classe saber representar-se
 * graficamente através de uma imagem.
 * 
 * Tem um método abstrato getCellRepresentation() é o que os objetos herdam
 * desta super classe. Como são muito variados e cada um tem a sua forma de se
 * representar, então faz desta classe uma classe abstrata com o método
 * getCellRepresentation () abstrato. Uns apenas precisam de representar a sua
 * imagem, outros, como no caso do Elemento e do Bloco, por vezes necessitam de fazer
 * Overlay de imagens.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public abstract class Identificador {
	protected String nomeImagem;

	public Identificador(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public abstract CellRepresentation getCellRepresentation();
}
