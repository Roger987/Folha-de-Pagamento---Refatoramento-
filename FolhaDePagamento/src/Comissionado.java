import java.util.Scanner;

public class Comissionado extends Funcionario {
	
	private float comissao = 0;
	private float vendas = 0;
	
	public Comissionado(String nome, String endereco, int numero, float salario, int metodoPagamento, int sindicato,
			int idSindicato, float taxaSindical, float comissao) {
		super(nome, endereco, numero, salario, metodoPagamento, sindicato, idSindicato, taxaSindical);
		this.comissao = comissao;
	}

	public float getComissao() {
		return comissao;
	}

	public double getSalario() {

		 double salarioComissionado = salario + (vendas * (comissao)/100);
		 salarioComissionado = salarioComissionado - (salarioComissionado * ((taxaSindical + taxaServico)/100));
		
		return salarioComissionado;
	}
	
	public String toString() {
		
		String result = super.toString();
		
		result += "\nSalário: " + getSalario();
		return result;
		
	}
	
	public void ResultadoDeVenda() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nInforme o Resultado de venda:\n");
	    	
   	 	float venda = scanner.nextFloat();
   	 	this.vendas += venda;
   	 
   	 	System.out.println("\nRESULTADO DE VENDA:\n----------------------------------------\nFuncionário: " + nome + "\nResultado: $" + venda + "\n");
		 
		
	}

}
