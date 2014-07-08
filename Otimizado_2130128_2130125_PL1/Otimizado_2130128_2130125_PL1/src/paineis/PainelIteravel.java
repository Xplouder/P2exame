package paineis;

import interfaces.Iteravel;
import outros.Jogo;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;

/**
 * Esta super classe abstrata de onde herdam o PainelPrincipal e o PainelVida �
 * respons�vel por ter uma cad�ncia e um tempo.
 * 
 * A CADENCIA para o painel principal corresponde ao tempo que o utilizador tem
 * para interagir com os elementos e formar grupo ou executar um bonus. Isto �,
 * caso ele n�o faca essa intera��o, ent�o ser�o removidos aleatoriamente 2
 * elementos sem corrente. Cadencia deve ser feita, pelo menos, de 30 em 30
 * segundos.
 * 
 * A CADENCIA para o painel vida corresponde ao respetivo decremento da vida a
 * cada 10 segundos.
 * 
 * Para o painel principal: Vari�vel que regista o ultimo tempo que o utilizador
 * agrupou elementos. Caso n�o o tenha conseguido no intervalo da cadencia
 * (m�todo passouTempoCadencia()), o painel vai explodir dois elementos
 * aleat�rios sem corrente.
 * 
 * Para o painel vida: Vari�vel que regista o ultimo tempo para poder saber se
 * j� pode mandar decrementar o n�vel de vida, caso passouTempoCadencia();
 * 
 * Trata-se de uma classe abstrata visto que esta n�o deve implementar o m�todo
 * iniciar (), visto que este difere entre os dois pain�is que herdam desta
 * classe.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 *
 */
public abstract class PainelIteravel extends Painel implements Iteravel {

	protected int CADENCIA;
	protected long tempo;

	public PainelIteravel(Jogo jogo, GridPanel grelhaPainel, int cadencia) {
		super(jogo, grelhaPainel);
		CADENCIA = cadencia;
		tempo = System.currentTimeMillis();
	}

	public boolean passouTempoCadencia() {
		return tempo + CADENCIA < System.currentTimeMillis();
	}

	public void atualizarTempo() {
		tempo = System.currentTimeMillis();
	}
}
