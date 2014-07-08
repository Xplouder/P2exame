package paineis;

import interfaces.IncrementadorNivelVida;
import interfaces.SaberNivelZeroVida;

import java.awt.event.MouseEvent;

import outros.Jogo;
import outros.Vida;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;

/**
 * Esta classe tem uma vida e é ela a responsável por dizer à vida tudo o que
 * tem a fazer, isto é:
 * 
 * O PainelVida tem uma cadência de 15 segundos, na qual, passado este tempo, a
 * vida deve decrementar um nível. Ao passar este tempo, a vida já sabe que tem
 * de fazer isto. O PainelVida verifica se esse tempo já passou, e conforme seja
 * ou não verdade, assim a vida decrementa ou não decrementa. O PainelVida
 * também tem a responsabilidade de saber se o nível da vida se esgotou de modo
 * a enviar essa informação ao Jogo para que ele mande parar a thread e o jogo
 * termina. Caso isso aconteça. o PainelVida informa ao jogador que perdeu o
 * jogo devido ao nível de vida ter chegado ao fim.
 * 
 * Aqui, apesar de não ser essencial, achá-mos por bem criar um mousePressed()
 * que, caso o jogador tenha um bonus selecionado e clique sobre a vida, o bonus
 * fica automaticamente deselecionado.
 * 
 * @authors Daniel Pinto e Diogo Simão.
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
