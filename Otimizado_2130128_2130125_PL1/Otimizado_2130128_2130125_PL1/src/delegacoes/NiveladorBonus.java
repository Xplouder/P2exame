package delegacoes;

/**
 * 
 * "Delegado" NiveladorBonus que herda de NiveladorReinicial e que tem um m�todo
 * espec�fico de inverter o Bonus, que � respons�vel por incrementar o n�vel do
 * bonus ou de decrementar, caso j� esteja selecionado.
 * 
 * Em inverterSelecionado() n�o � poss�vel fazer:
 * 
 * if (isNivelMaximo()) incrementarNivel(incremento);
 * 
 * porque todos os m�todos, quando incrementam o seu n�vel, incrementam at�
 * atingir o nivelMaximo e essa prote��o � feita no incrementarNivel(int
 * incremento).
 * 
 * Como, para selecionar o bonus, temos de ter n�vel == nivelMaximo + 1, ent�o
 * aqui � feito manualmente n�vel ++ em vez de chamar o incrementarNivel(int)
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
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
