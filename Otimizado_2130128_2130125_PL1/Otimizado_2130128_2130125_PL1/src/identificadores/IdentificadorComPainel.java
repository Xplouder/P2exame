package identificadores;

import outros.Posicao;
import paineis.Painel;

/**
 * Esta super classe abstrata tem um painel (super classe abstrata) e uma
 * posição.
 * 
 * Esta classe 'IdentificadorComPainel' é abstrata já que ainda não é aqui que é
 * definido o método getCellRepresentation(), mas sim nos objetos mais abaixo na
 * hierarquia.
 * 
 * Toda a classe (abaixo desta hierarquia) que tem um painel também tem de saber
 * onde se localiza na grelha do painel em questão, isto é, tem de ter uma
 * posição. Tem um método getter da posição porque qualquer que seja o objeto
 * abaixo da classe, ele tem de enviar as coordenadas para atualizar a sua
 * posição a quem lhe faculta essa informação. Até mesmo a parede precisa deste
 * método uma vez em todo o jogo, porque quando instanciamos uma parede,
 * enviamos a sua posição para se situar na grelha e a colocar lá.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public abstract class IdentificadorComPainel<TipoPainel extends Painel> extends IdentificadorSabePosicao {
	
	protected TipoPainel painel;
	protected Posicao posicao;
	
	public IdentificadorComPainel(String nomeImagem, TipoPainel painel, Posicao posicao) {
		super(nomeImagem);
		this.painel = painel;
		this.posicao = posicao;
	}
	
	public TipoPainel getPainel () {
		return painel;
	}
	
	@Override
	public Posicao getPosicao () {
		return posicao;
	}
}
