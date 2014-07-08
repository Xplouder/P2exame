package bonus;

import java.util.LinkedList;

import celulas.Bloco;
import outros.Posicao;
import paineis.PainelBonus;

/**
 * BonusNormal é uma super-classe para todos os Bonus que têm em comum o mesmo
 * método, que é o da execução do Bonus para um dado blocoOrigem recebido como
 * parâmetro. O boolean setSombra é para o Bonus distinguir se deve aplicar
 * sombra ou explodir o bloco. Achámos que não fazia sentido ter dois métodos,
 * um para aplicar e outro para destruir o bloco, já que ambos faziam exatamente
 * o mesmo, com a exceção de um por sombra ao bloco e outro destruí-lo. Com
 * efeito, decidimos usar a mesma função para evitar redundância de código,
 * passando um boolean a informar se deve aplicar a sombra no bonus ou
 * destruí-lo.
 * 
 * A única diferença entre a sombra e a destruição é que, se for para destruir,
 * no fim o bonus deve reduzir o seu nível a zero; caso contrário, aplica-se
 * apenas a sombra no(s) bloco(s) respetivo(s) e então o seu nível selecionado
 * mantém-se. Essa parte, como era comum a todos os Bonus normais, ficou
 * declarada aqui no Bonus normal, sendo que no fim da execução todos os bonus
 * fazem super.executarBonusNormal(...), que procede a essa verificação acima
 * mencionada.
 * 
 * @authors Daniel Pinto e Diogo Simão
 *
 */
public abstract class BonusNormal extends Bonus {

	public BonusNormal(PainelBonus painelBonus, String nomeImagem,
			int nivelMaximo, Posicao posicao, String som) {
		super(painelBonus, nomeImagem, nivelMaximo, posicao, som);
	}
	
	protected abstract LinkedList<Bloco> getBlocosInfluenciados(Bloco inicial);
	
	protected abstract void destruir(Bloco b);
	
	public void executar(Bloco bloco) {
		for (Bloco b:getBlocosInfluenciados(bloco)) {
			destruir(b);
		}
		inverterSelecionado();
		reporNivelZero();
	}

	public void sombrear(Bloco bloco) {
		for (Bloco b:getBlocosInfluenciados(bloco))
			painel.adicionarListaBlocosComSombra(b);
	}
	
	@Override
	protected void executarSelecao() {
		painel.setBonusSelecionado(this);
	}

}
