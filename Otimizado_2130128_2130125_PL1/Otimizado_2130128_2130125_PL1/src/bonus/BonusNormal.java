package bonus;

import java.util.LinkedList;

import celulas.Bloco;
import outros.Posicao;
import paineis.PainelBonus;

/**
 * BonusNormal � uma super-classe para todos os Bonus que t�m em comum o mesmo
 * m�todo, que � o da execu��o do Bonus para um dado blocoOrigem recebido como
 * par�metro. O boolean setSombra � para o Bonus distinguir se deve aplicar
 * sombra ou explodir o bloco. Ach�mos que n�o fazia sentido ter dois m�todos,
 * um para aplicar e outro para destruir o bloco, j� que ambos faziam exatamente
 * o mesmo, com a exce��o de um por sombra ao bloco e outro destru�-lo. Com
 * efeito, decidimos usar a mesma fun��o para evitar redund�ncia de c�digo,
 * passando um boolean a informar se deve aplicar a sombra no bonus ou
 * destru�-lo.
 * 
 * A �nica diferen�a entre a sombra e a destrui��o � que, se for para destruir,
 * no fim o bonus deve reduzir o seu n�vel a zero; caso contr�rio, aplica-se
 * apenas a sombra no(s) bloco(s) respetivo(s) e ent�o o seu n�vel selecionado
 * mant�m-se. Essa parte, como era comum a todos os Bonus normais, ficou
 * declarada aqui no Bonus normal, sendo que no fim da execu��o todos os bonus
 * fazem super.executarBonusNormal(...), que procede a essa verifica��o acima
 * mencionada.
 * 
 * @authors Daniel Pinto e Diogo Sim�o
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
