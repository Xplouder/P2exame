package outros;

import delegacoes.NiveladorCorrente;
import identificadores.Identificador;
import interfaces.Decrementavel;
import interfaces.SaberNivel;
import interfaces.SaberNivelMaximo;
import pt.ipleiria.estg.dei.gridpanel.CellRepresentation;
import pt.ipleiria.estg.dei.gridpanel.SingleImageCellRepresentation;

/**
 * A Corrente extende de Identificador porque tem uma imagem e também tem um nível e
 * um nívelMaximo, que são atributos delegados ao niveladorCorrente.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public class Corrente extends Identificador implements SaberNivel, SaberNivelMaximo, Decrementavel {

	private NiveladorCorrente niveladorCorrente;

	public Corrente(int nivel) {
		super("/imagens_correntes/correnteNivel");
		niveladorCorrente = new NiveladorCorrente(nivel, 2);
	}
	
	@Override
	public int getNivel() {
		return niveladorCorrente.getNivel();
	}
	
	@Override
	public CellRepresentation getCellRepresentation() {
		return new SingleImageCellRepresentation(nomeImagem + getNivel() + ".png");
	}

	@Override
	public void decrementarNivel() {
		niveladorCorrente.decrementarNivel();
	}

	@Override
	public boolean isNivelMaximo() {
		return niveladorCorrente.isNivelMaximo();
	}
}
