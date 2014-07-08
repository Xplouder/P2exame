package paineis;

import interfaces.Atualizavel;

import java.awt.event.MouseEvent;

import outros.Jogo;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;
import pt.ipleiria.estg.dei.gridpanel.GridPanelEventHandler;

/**
 * Esta é uma super classe abstrata da qual vão herdar a classe PainelBonus e
 * PainelIterável. Todos os painéis conhecem um jogo e têm uma GridPanel, que é
 * o que essencialmenete esta super classe é constituída.
 * 
 * Esta super classe tem um método abstrato (polimorfismo) porque cada painel
 * vai ter o seu próprio método de iniciar os seus atributos, portanto este
 * método vai ter de ser redefinido. Contudo, existe uma coisa que todos os
 * painéis têm em comum, que é grelhaPainel.setEventHandler(this), daí que este
 * método não se designa por 'abstract' porque no fim de cada iniciar(); de um
 * dado painel, ele vai fazer super.iniciar(); para que possa ter acesso aos
 * eventos do rato.
 * 
 * Os método mousePressed() terá que ser abstrato já que cada painel têm o seu
 * modo de funcionar em função do clique do rato. Contudo, todos os outros
 * métodos serão iguais para qualquer painel, isto é, serão métodos vazios que
 * não farão nada. No entanto, o método mouseDragged() vai ter que ser
 * redefinido no PainelPrincipal já que este é o único que precisa desse método.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public abstract class Painel implements Atualizavel, GridPanelEventHandler {
	protected GridPanel grelhaPainel;
	protected Jogo jogo;

	public Painel(Jogo jogo, GridPanel grelhaPainel) {
		this.jogo = jogo;
		this.grelhaPainel = grelhaPainel;
	}

	public void iniciar() {
		grelhaPainel.setEventHandler(this);
	}

	public void redesenharGrelha() {
		grelhaPainel.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent, int x, int y) {

	}

	@Override
	public abstract void mousePressed(MouseEvent mouseEvent, int x, int y);

	@Override
	public void mouseDragged(MouseEvent mouseEvent, int x, int y) {

	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent, int x, int y) {

	}
	
	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		
	}
}
