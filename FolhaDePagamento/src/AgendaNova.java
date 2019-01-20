
public class AgendaNova extends Funcionario {

	String frequencia;
	int dia;
	
	public AgendaNova(String nome, String endereco, int numero, float salario, int metodoPagamento, int sindicato,
			int idSindicato, float taxaSindical, String frequencia, int dia) {
		super(nome, endereco, numero, salario, metodoPagamento, sindicato, idSindicato, taxaSindical);
		this.frequencia = frequencia;
		this.dia = dia;
	}

	@Override
	public double getSalario() {
		return salario;
	}
	
	public String getFrequencia() {
		return frequencia;
	}
	
	public int getDia() {
		return dia;
	}
	
	public String toString() {
		
		String result = super.toString();
		
		result += "\nSalário: " + getSalario();
		return result;
		
	}

}
