package paineis;

import interfaces.Atualizavel;

import java.awt.event.MouseEvent;

import outros.Jogo;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;
import pt.ipleiria.estg.dei.gridpanel.GridPanelEventHandler;

/**
 * Esta � uma super classe abstrata da qual v�o herdar a classe PainelBonus e
 * PainelIter�vel. Todos os pain�is conhecem um jogo e t�m uma GridPanel, que �
 * o que essencialmenete esta super classe � constitu�da.
 * 
 * Esta super classe tem um m�todo abstrato (polimorfismo) porque cada painel
 * vai ter o seu pr�prio m�todo de iniciar os seus atributos, portanto este
 * m�todo vai ter de ser redefinido. Contudo, existe uma coisa que todos os
 * pain�is t�m em comum, que � grelhaPainel.setEventHandler(this), da� que este
 * m�todo n�o se designa por 'abstract' porque no fim de cada iniciar(); de um
 * dado painel, ele vai fazer super.iniciar(); para que possa ter acesso aos
 * eventos do rato.
 * 
 * Os m�todo mousePressed() ter� que ser abstrato j� que cada painel t�m o seu
 * modo de funcionar em fun��o do clique do rato. Contudo, todos os outros
 * m�todos ser�o iguais para qualquer painel, isto �, ser�o m�todos vazios que
 * n�o far�o nada. No entanto, o m�todo mouseDragged() vai ter que ser
 * redefinido no PainelPrincipal j� que este � o �nico que precisa desse m�todo.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
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
