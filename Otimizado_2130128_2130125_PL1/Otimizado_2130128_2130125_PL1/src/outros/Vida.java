package outros;

import delegacoes.NiveladorVida;
import identificadores.IdentificadorComPainel;
import interfaces.Atualizavel;
import interfaces.Iteravel;
import interfaces.SaberNivelZeroVida;
import paineis.PainelVida;
import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

/**
 * 
 * A Vida é uma classe que tem uma posição na grelha do PainelVida e faz
 * terminar o jogo caso o seu nível seja zero.
 * 
 * A Vida pode ainda ser incrementada caso se formem grupos com elementos do
 * tipo Balde ou se use o BonusAmpulheta.
 * 
 * A vida delega tudo o que respeita ao seu nível para o niveladorVida.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public class Vida extends IdentificadorComPainel<PainelVida> implements
		Iteravel, Atualizavel, SaberNivelZeroVida {

	private NiveladorVida niveladorVida;

	public Vida(PainelVida painelVida) {
		super("/imagens_vida/vida_", painelVida, new Posicao(0, 0));
		niveladorVida = new NiveladorVida();
	}

	@Override
	public void iterar() {
		if (!isVidaNivelZero()) {
			niveladorVida.decrementarNivel();
			atualizar(posicao.getX(), posicao.getY());
		}
	}

	public void incrementarNivelVida(int incremento) {
		niveladorVida.incrementarNivel(incremento);
		atualizar(posicao.getX(), posicao.getY());
	}

	@Override
	public boolean isVidaNivelZero() {
		return niveladorVida.isVidaNivelZero();
	}

	@Override
	public CellRepresentation getCellRepresentation() {
		return new SingleImageCellRepresentation(nomeImagem
				+ niveladorVida.getNivel() + ".png");
	}

	@Override
	public void atualizar(int x, int y) {
		painel.atualizar(x, y);
		painel.atualizarTempo();
	}
}
