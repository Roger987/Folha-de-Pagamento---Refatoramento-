
public class Assalariado extends Funcionario {


	public Assalariado(String nome, String endereco, int numero, float salario, int metodoPagamento, int sindicato,
			int idSindicato, float taxaSindical) {
		super(nome, endereco, numero, salario, metodoPagamento, sindicato, idSindicato, taxaSindical);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getSalario() {
		 
		double salarioAssalariado = salario;
		salarioAssalariado = salarioAssalariado - (salarioAssalariado * ((taxaSindical + taxaServico)/100));
		
		return salarioAssalariado;
	}
	
	public String toString() {
		
		String result = super.toString();
		
		result += "\nSalário: " + getSalario();
		return result;
		
	}

}
