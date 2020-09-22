package algoritmo;

public class Ladrao extends ProgramaLadrao {
	
	// Array que armazena valores onde o poupador foi detectado a esquedar do ladrao
	private int[] poupadorDectadoEsquerda = {5,6,10,11,14,15};
	
	// Array que armazena valores onde o poupador foi detectado a direita do ladrao
	private int[] poupadorDectadoDireita = {8,9,12,13,17,18};
		
	// Array que armazena valores onde o poupador foi detectado a cima do ladrao
	private int[] poupadorDectadoCima = {0,1,2,3,4,7};
	
	// Array que armazena valores onde o poupador foi detectado a baixo do ladrao
	private int[] poupadorDectadoBaixo = {19,20,21,22,23,16};
	
	public int acao() {
		return agir();
	}
	
// Ladrão bom é o que anda em grupo!

// private void agrupar(){
//}
	
	
// Ideia para metodo:
//	analisar e juntar dados para espertize de agente poupador.
	
//	public int analisarRodada() {
//	}
	
	private int previnirMovimentos() {
		// Previne movimentos
		int[] movimentos = {11,12,7,16};
		int[] movimentosPossiveis = new int[4];
		int count = 0;
		for(int i = 0; i < movimentos.length - 1; i++) {
			if(sensor.getVisaoIdentificacao()[movimentos[i]] == 0 || sensor.getVisaoIdentificacao()[movimentos[i]] >= 100 && sensor.getVisaoIdentificacao()[movimentos[i]] < 200) {
				movimentosPossiveis[count] = movimentos[i];
				count++;
			}
		}

		return previnir(movimentosPossiveis);
	}
	
	// Age em perseguir um poupador evitando movimentos inueis
	// OBS: previnirMovimentos tem que direcionar para melhor possição dentre as escolhidas
	private int agir() {
		// Persegue poupador
		for(int i = 0; i < sensor.getVisaoIdentificacao().length; i++) {
			if(sensor.getVisaoIdentificacao()[i] >= 100 && sensor.getVisaoIdentificacao()[i] < 200 ) {
				
				if(poupadorEncontrado(poupadorDectadoEsquerda, i) || sensor.getVisaoIdentificacao()[11] >= 100 && sensor.getVisaoIdentificacao()[11] < 200) {
					if(sensor.getVisaoIdentificacao()[11] >= 1 && sensor.getVisaoIdentificacao()[11] < 6) {
						previnirMovimentos();
					}
					return 4;
				}
				
				if(poupadorEncontrado(poupadorDectadoDireita, i) || sensor.getVisaoIdentificacao()[12] >= 100 && sensor.getVisaoIdentificacao()[12] < 200) {
					if(sensor.getVisaoIdentificacao()[12] >= 1 && sensor.getVisaoIdentificacao()[12] < 6) {
						previnirMovimentos();
					}
					return 3;
				}
				
				if(poupadorEncontrado(poupadorDectadoCima, i) || sensor.getVisaoIdentificacao()[7] >= 100 && sensor.getVisaoIdentificacao()[7] < 200) {
					if(sensor.getVisaoIdentificacao()[7] >= 1 && sensor.getVisaoIdentificacao()[7] < 6) {
						previnirMovimentos();
					}
					return 1;
				}
				
				if(poupadorEncontrado(poupadorDectadoBaixo, i) || sensor.getVisaoIdentificacao()[16] >= 100 && sensor.getVisaoIdentificacao()[16] < 200) {
					if(sensor.getVisaoIdentificacao()[16] >= 1 && sensor.getVisaoIdentificacao()[16] < 6) {
						previnirMovimentos();
					}
					return 2;
				}
			}
		}
		
		return (int) (Math.random() * 5);
	}
	
	//Faz um  random dos valores de uma array, especie de random customizado para um conjunto de valores escolhido pelo o usuario
	private int previnir(int[] movimentos) {
		if(movimentos.length != 0) {
			return movimentos[(int) Math.floor(Math.random()*movimentos.length)];			
		}
		return (int) (Math.random() * 5); 
	}
	
	// Verifica um poupador localizado a partir de uma direção e o id do poupador.
	public boolean poupadorEncontrado(int[] vetor, int poupador) {
		for(int i = 0; i < vetor.length - 1; i++) {
			if(vetor[i] == poupador) {
				return true;
			}
		}
		return false;
	}
}