package delegacoes;

/**
 * 
 * "Delegado" NiveladorBonus que herda de NiveladorReinicial e que tem um método
 * específico de inverter o Bonus, que é responsável por incrementar o nível do
 * bonus ou de decrementar, caso já esteja selecionado.
 * 
 * Em inverterSelecionado() não é possível fazer:
 * 
 * if (isNivelMaximo()) incrementarNivel(incremento);
 * 
 * porque todos os métodos, quando incrementam o seu nível, incrementam até
 * atingir o nivelMaximo e essa proteção é feita no incrementarNivel(int
 * incremento).
 * 
 * Como, para selecionar o bonus, temos de ter nível == nivelMaximo + 1, então
 * aqui é feito manualmente nível ++ em vez de chamar o incrementarNivel(int)
 * 
 * @authors Daniel Pinto e Diogo Simão.
 *
 */
public class NiveladorBonus extends NiveladorReiniciavel {

	public NiveladorBonus(int nivelMaximo) {
		super(nivelMaximo, nivelMaximo);
	}

	public void inverterSelecionado() {
		if (isNivelMaximo())
			nivel++;
		else
			decrementarNivel();
	}
}
