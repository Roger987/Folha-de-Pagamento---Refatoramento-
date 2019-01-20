import java.util.Scanner;

public class Horista extends Funcionario {

	private int horas = 0;
	private int horaExtra = 0;
	
	public Horista(String nome, String endereco, int numero, float salario, int metodoPagamento, int sindicato, int idSindicato, float taxaSindical) {
		super(nome, endereco, numero, salario, metodoPagamento, sindicato, idSindicato, taxaSindical);
	}

	@Override
	public double getSalario() {
		 double salarioHorista = (horas * salario) + (horaExtra * 1.5 * salario);
		 salarioHorista = salarioHorista - (salarioHorista * ((taxaSindical + taxaServico)/100));
		 return salarioHorista;
	}
	
	public String toString() {
		
		String result = super.toString();
		
		result += "\nSalário: " + getSalario();
		return result;
		
	}
	
	public void CartaoDePonto() {
		
		 Scanner scanner = new Scanner(System.in);
		 float diferencahoras = -1;
		 
		 System.out.println("\nDigite a hora e os minutos de entrada separados por uma quebra de linha:\n");
		 int entradahora = scanner.nextInt();
		 int entradamin = scanner.nextInt();
		 
		 System.out.println("\nDigite a hora e os minutos de saída separados por uma quebra de linha:\n");
		 int saidahora = scanner.nextInt();
		 int saidamin = scanner.nextInt();
		 
		 if(saidahora > entradahora) {
			 
			 diferencahoras = ((saidahora - entradahora) + ((1/60)*saidamin - (1/60)*entradamin));
			 
	   		 
	   		 if(diferencahoras > 0 && diferencahoras <= 8) {
	   			 this.horas += diferencahoras;
	   		 }
	   		 
	   		 else if(diferencahoras > 8){
	   			 this.horas += 8;
	   			 this.horaExtra += (diferencahoras - 8);
	   		 }
	   		 
	   		 System.out.println("\nCARTÃO DE PONTO\n-------------------------------------------\n");
	   		 System.out.println(nome + "\nEntrada: " + (int) entradahora + ":" + (int) entradamin + "\nSaída: " + (int) saidahora + ":" + (int) saidamin + "\nTotal de horas: " + diferencahoras + "h\n");

		 }
		 
		 else {
			 System.out.println("\nErro! Entrada inválida!\n");
		 }
		 
	}
}
