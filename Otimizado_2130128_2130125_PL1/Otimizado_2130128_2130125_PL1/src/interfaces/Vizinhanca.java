package interfaces;

/**
 * A interface 'Vizinhanca' serve para um dado Elemento, ao iterar, poder
 * comunicar com o PainelPrincipal, (através do seu bloco!, pois só o conhece a
 * ele), para saber se pode cair diagonal caso NENHUM dos elementos das colunas
 * subjacentes poderem iterar para baixo.
 * 
 * O bloco, por sua vez e ao receber essa solicitação, envia as sua posição
 * relativa à grelha no PainelPrincipal e o PainelPrincipal responde ao Elemento
 * através do bloco, conforme a situação, se pode ou não cair (boolean true ou
 * false), isto porque quem conhece e controla o mundo (a grelha) é o
 * PainelPrincipal, ele é que é responsável por tais decisões.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 */
public interface Vizinhanca {
	boolean vizinhosNaoIteramBaixo();
}
