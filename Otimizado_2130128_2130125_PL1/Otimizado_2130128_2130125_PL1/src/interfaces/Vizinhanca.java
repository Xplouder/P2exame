package interfaces;

/**
 * A interface 'Vizinhanca' serve para um dado Elemento, ao iterar, poder
 * comunicar com o PainelPrincipal, (atrav�s do seu bloco!, pois s� o conhece a
 * ele), para saber se pode cair diagonal caso NENHUM dos elementos das colunas
 * subjacentes poderem iterar para baixo.
 * 
 * O bloco, por sua vez e ao receber essa solicita��o, envia as sua posi��o
 * relativa � grelha no PainelPrincipal e o PainelPrincipal responde ao Elemento
 * atrav�s do bloco, conforme a situa��o, se pode ou n�o cair (boolean true ou
 * false), isto porque quem conhece e controla o mundo (a grelha) � o
 * PainelPrincipal, ele � que � respons�vel por tais decis�es.
 * 
 * @authors Daniel Pinto e Diogo Sim�o.
 */
public interface Vizinhanca {
	boolean vizinhosNaoIteramBaixo();
}
