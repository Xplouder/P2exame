package interfaces;

/**
 * Devem implementar esta interface todas classes que precisam de iterar ao fim
 * de CADENCIA ter passado o seu tempo (atributo definido no PainelIteravel).
 * 
 * Iteram os elementos para saber se podem cair, itera a Vida para saber se deve
 * decrementar o seu nível restante. Para tal, esta mensagens precisam de ser
 * enviadas através dos Painéis: PainelPrincipal e PainelVida, que herdam de
 * PainelIteravel.
 * 
 * @authors Daniel Pinto e Diogo Simão.
 * 
 */
public interface Iteravel {
	void iterar();
}
