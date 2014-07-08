package paineis;

import interfaces.Iteravel;
import outros.Jogo;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;

/**
 * Esta super classe abstrata de onde herdam o PainelPrincipal e o PainelVida é
 * responsável por ter uma cadência e um tempo.
 * 
 * A CADENCIA para o painel principal corresponde ao tempo que o utilizador tem
 * para interagir com os elementos e formar grupo ou executar um bonus. Isto é,
 * caso ele não faca essa interação, então serão removidos aleatoriamente 2
 * elementos sem corrente. Cadencia deve ser feita, pelo menos, de 30 em 30
 * segundos.
 * 
 * A CADENCIA para o painel vida corresponde ao respetivo decremento da vida a
 * cada 10 segundos.
 * 
 * Para o painel principal: Variável que regista o ultimo tempo que o utilizador
 * agrupou elementos. Caso não o tenha conseguido no intervalo da cadencia
 * (método passouTempoCadencia()), o painel vai explodir dois elementos
 * aleatórios sem corrente.
 * 
 * Para o painel vida: Variável que regista o ultimo tempo para poder saber se
 * já pode mandar decrementar o nível de vida, caso passouTempoCadencia();
 * 
 * Trata-se de uma classe abstrata visto que esta não deve implementar o método
 * iniciar (), visto que este difere entre os dois painéis que herdam desta
 * classe.
 * 
 * @authors Daniel Pinto e Diogo Simão.
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
