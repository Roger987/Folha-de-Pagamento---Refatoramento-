import java.util.Scanner;

public abstract class Funcionario {

	protected String nome;
	protected String endereco;
	protected int numero;
	protected float salario;
	protected int metodoPagamento;
	protected int sindicato = 0;
	protected int idSindicato;
	protected float taxaSindical = 0;
	protected float taxaServico = 0;
	
	public Funcionario(String nome, String endereco, int numero, float salario, int metodoPagamento, int sindicato, int idSindicato, float taxaSindical) {
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
		this.salario = salario;
		this.sindicato = sindicato;
		this.idSindicato = idSindicato;
		this.taxaSindical = taxaSindical;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getMetodoPagamento() {
		return metodoPagamento;
	}
	
	public int getSindicato() {
		return sindicato;
	}
	
	public int getIdSindicato() {
		return idSindicato;
	}
	
	public float getTaxaSindical() {
		return taxaSindical;
	}
	
	public float getSalarioFixo() {
		return salario;
	}
	
	public void setTaxaServico(float taxaServico) {
		this.taxaServico = taxaServico;
	}
	
	public String toString() {
		
		String result = "\nNome: " + nome + "\nEndere�o: " + endereco +
				"\nN�mero de identifica��o: " + numero;
		
		if(metodoPagamento == 1) {
			result += "\nM�todo de pagamento: Cheque pelo correio.";
		}
		else if(metodoPagamento == 2) {
			result += "\nM�todo de pagamento: Cheque em m�os.";
		}
		else if(metodoPagamento == 3) {
			result += "\nM�todo de pagamento: D�bito em conta.";
		}
		
		if(sindicato == 1) {
			result += "\nIdentifica��o no sindicato: " + idSindicato + 
					"\nTotal de taxas do sindicato: " + (taxaSindical + taxaServico) + "%";
		}

		return result;
		
	}
	
	public void alterarInformacoes(int choice, int size) {
		
		Scanner scanner = new Scanner(System.in);
		
		switch(choice) {
		
		case 1:
			
			 System.out.println("\nDigite o nome do funcion�rio conforme deseja alterar:\n");
			 
			 this.nome = scanner.nextLine();
			 
			 System.out.println("\nNome alterado com sucesso.\n");
			 break;
			 
		case 2:
			
			 System.out.println("\nDigite o endere�o do funcion�rio conforme deseja alterar:\n");
			 
			 this.endereco = scanner.nextLine();
			 
			 System.out.println("\nEndere�o alterado com sucesso.\n");
			 break;
			 
		case 4:
			
			 System.out.println("\nSelecione o m�todo de pagamento:\n   1 - Cheque pelo correio\n   2 - Cheque em m�os\n   3 - Dep�sito em conta\n");
			 
			 this.metodoPagamento = scanner.nextInt();
			 scanner.nextLine();
			 
			 System.out.println("\nM�todo de pagamento alterado com sucesso.\n");
			 break;
			 
		case 5:
			
			 System.out.println("\nPertence ao sindicato?\n   0 - N�o\n   1 - Sim\n");
			 
			 this.sindicato = scanner.nextInt();
			 scanner.nextLine();
			 
			 System.out.println("\nPertin�ncia ao sindicato alterada com sucesso.\n");
			 break;
			 
		case 6:
			
			 System.out.println("\nDigite a nova identifica��o no sindicato do funcion�rio conforme deseja alterar:\n");
			 
			 int novoid = scanner.nextInt();
			 scanner.nextLine();
			 
			 if(novoid <= size) {
				 System.out.println("\nN�mero inv�lido.\nTente novamente.\n");
			 }
			 
			 else {
				 this.idSindicato = novoid;
				 System.out.println("\nIdentifica��o no sindicato alterada com sucesso.\n");
			 }
			 break;
			 
		case 7:
			
			 System.out.println("Digite a taxa sindical do funcion�rio conforme deseja alterar:\n");
			 
			 this.taxaSindical = scanner.nextFloat();
			 
			 System.out.println("Taxa sindical alterada com sucesso.\n");
			 break;
			 
		default:
			 System.out.println("Opera��o inv�lida");
			 break;
			
		}

	}
	
	public abstract double getSalario();

}
