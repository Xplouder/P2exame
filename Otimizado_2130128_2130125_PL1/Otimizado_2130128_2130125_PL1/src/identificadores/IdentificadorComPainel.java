package identificadores;

import outros.Posicao;
import paineis.Painel;

/**
 * Esta super classe abstrata tem um painel (super classe abstrata) e uma
 * posi��o.
 * 
 * Esta classe 'IdentificadorComPainel' � abstrata j� que ainda n�o � aqui que �
 * definido o m�todo getCellRepresentation(), mas sim nos objetos mais abaixo na
 * hierarquia.
 * 
 * Toda a classe (abaixo desta hierarquia) que tem um painel tamb�m tem de saber
 * onde se localiza na grelha do painel em quest�o, isto �, tem de ter uma
 * posi��o. Tem um m�todo getter da posi��o porque qualquer que seja o objeto
 * abaixo da classe, ele tem de enviar as coordenadas para atualizar a sua
 * posi��o a quem lhe faculta essa informa��o. At� mesmo a parede precisa deste
 * m�todo uma vez em todo o jogo, porque quando instanciamos uma parede,
 * enviamos a sua posi��o para se situar na grelha e a colocar l�.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
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
