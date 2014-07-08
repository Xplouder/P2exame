package paineis;

import interfaces.IncrementadorNivelVida;
import interfaces.SaberNivelZeroVida;

import java.awt.event.MouseEvent;

import outros.Jogo;
import outros.Vida;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;

/**
 * Esta classe tem uma vida e � ela a respons�vel por dizer � vida tudo o que
 * tem a fazer, isto �:
 * 
 * O PainelVida tem uma cad�ncia de 15 segundos, na qual, passado este tempo, a
 * vida deve decrementar um n�vel. Ao passar este tempo, a vida j� sabe que tem
 * de fazer isto. O PainelVida verifica se esse tempo j� passou, e conforme seja
 * ou n�o verdade, assim a vida decrementa ou n�o decrementa. O PainelVida
 * tamb�m tem a responsabilidade de saber se o n�vel da vida se esgotou de modo
 * a enviar essa informa��o ao Jogo para que ele mande parar a thread e o jogo
 * termina. Caso isso aconte�a. o PainelVida informa ao jogador que perdeu o
 * jogo devido ao n�vel de vida ter chegado ao fim.
 * 
 * Aqui, apesar de n�o ser essencial, ach�-mos por bem criar um mousePressed()
 * que, caso o jogador tenha um bonus selecionado e clique sobre a vida, o bonus
 * fica automaticamente deselecionado.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 * 
 */
public class PainelVida extends PainelIteravel implements IncrementadorNivelVida, SaberNivelZeroVida {
	private Vida vida;

	public PainelVida(Jogo jogo, GridPanel gridLifePanel) {
		super(jogo, gridLifePanel, 15000);
		vida = new Vida(this);
		iniciar();
	}

	@Override
	public void iterar() {
		if (passouTempoCadencia())
			vida.iterar();
	}

	@Override
	public void iniciar() {
		atualizar(0, 0);
		super.iniciar();
	}

	@Override
	public void atualizar(int x, int y) {
		grelhaPainel.put(x, y, vida.getCellRepresentation());
		redesenharGrelha();
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent, int x, int y) {
		if (jogo.getBonusSelecionado()!=null)
			jogo.deselecionarBonus();
	}

	@Override
	public boolean isVidaNivelZero() {
		return vida.isVidaNivelZero();
	}

	@Override
	public void incrementarNivelVida(int incremento) {
		vida.incrementarNivelVida(incremento);
	}
}
