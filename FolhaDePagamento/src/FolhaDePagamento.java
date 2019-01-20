import java.util.*; 

public class FolhaDePagamento {

	public FolhaDePagamento() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int input = -1, num = 0, idSindicato = 0, flag = 0, index, op = 0, dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH), 
				mes = Calendar.getInstance().get(Calendar.MONTH) + 1, ano = Calendar.getInstance().get(Calendar.YEAR),
				diasemana = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		
		System.out.println("\nDigite a operação desejada:\n\n1: Adição de empregado\n2: Remoção de empregado\n3: Lançar um cartão de ponto\n4: Lançar um resultado Venda\n5: Lançar uma taxa de serviço\n6: Alterar detalhes de um emprego\n7: Rodar a folha de pagamento para hoje\n8: Undo/redo\n9: Agenda de pagamento\n10: Criação de novas agendas de pagamento:\n0: Encerrar o programa\n");

		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Stack<ArrayList<Funcionario>> undo = new Stack<ArrayList<Funcionario>>();
		Stack<ArrayList<Funcionario>> redo = new Stack<ArrayList<Funcionario>>();
		
		while(input != 0) {
			
			try {
				
				input = scanner.nextInt();
				scanner.nextLine();
				
				if(input == 0) {
					System.out.println("\nPrograma encerrado\n");
					break;
				}
				
				if(input > 0 && input < 8) {
					
					int i;
					if(op > 0) {
						ArrayList<Funcionario> funcionariosCopia = new ArrayList<Funcionario>();
						Funcionario funcionarioCopia;
						
						for(i = 0; i < funcionarios.size(); i++) {
							
							if(funcionarios.get(i) instanceof Horista) {
								
								funcionarioCopia = new Horista(funcionarios.get(i).getNome(), funcionarios.get(i).getEndereco(),
										funcionarios.get(i).getNumero(), funcionarios.get(i).getSalarioFixo(), funcionarios.get(i).getMetodoPagamento(), 
										funcionarios.get(i).getSindicato(), funcionarios.get(i).getIdSindicato(), funcionarios.get(i).getTaxaSindical()); 
								
								funcionariosCopia.add(funcionarioCopia);
								
							}
							
							else if(funcionarios.get(i) instanceof Assalariado) {
								
								funcionarioCopia = new Assalariado(funcionarios.get(i).getNome(), funcionarios.get(i).getEndereco(),
										funcionarios.get(i).getNumero(), funcionarios.get(i).getSalarioFixo(), funcionarios.get(i).getMetodoPagamento(), 
										funcionarios.get(i).getSindicato(), funcionarios.get(i).getIdSindicato(), funcionarios.get(i).getTaxaSindical()); 
								
								funcionariosCopia.add(funcionarioCopia);
								
							}
							
							else if(funcionarios.get(i) instanceof Comissionado) {
								
								Comissionado comissionado = (Comissionado) funcionarios.get(i);
								
								funcionarioCopia = new Comissionado(comissionado.getNome(), comissionado.getEndereco(),
										comissionado.getNumero(), comissionado.getSalarioFixo(), comissionado.getMetodoPagamento(), 
										comissionado.getSindicato(), comissionado.getIdSindicato(), comissionado.getTaxaSindical(),
										comissionado.getComissao()); 
								
								funcionariosCopia.add(funcionarioCopia);
								
							}	
						}
						undo.push(funcionariosCopia);
					}
					
					op++;
				}
				
				switch(input) {
				
				case 1:
					
					Funcionario funcionario;
					float taxaSindical = 0, salario;
					
					System.out.println("\nNome:\n");
					String nome = scanner.nextLine();

					System.out.println("\nEndereço:\n");
					String endereco = scanner.nextLine();
					
					System.out.println("\nMétodo de Pagamento:\n   1 - Cheque pelo correio\n   2 - Cheque em mãos\n   3 - Depósito em conta\n");
					int metodoPagamento = scanner.nextInt();
					scanner.nextLine();
					
					System.out.println("\nPertence ao sindicato?\n   0 - Não   1 - Sim\n");
					int sindicato = scanner.nextInt();
					scanner.nextLine();
					
					if(sindicato == 1) {
						idSindicato++;
						System.out.println("\nInforme a taxa sindical:\n");
						taxaSindical = scanner.nextFloat();
					}
					
					System.out.println("\nTipo:\n   1 - Horista\n   2 - Assalariado\n   3 - Comissionado\n");
					int tipo = scanner.nextInt();
					scanner.nextLine();
					
					switch(tipo) {
					
					case 1:
						
						System.out.println("\nInforme o salário por hora trabalhada:\n");
						salario = scanner.nextFloat();
						
						funcionario = new Horista(nome, endereco, num, salario, metodoPagamento, sindicato, idSindicato, taxaSindical);
						funcionarios.add(funcionario);
						break;
						
					case 2:
						
						System.out.println("Informe o salário mensal:");
						salario = scanner.nextFloat();
						
						funcionario = new Assalariado(nome, endereco, num, salario, metodoPagamento, sindicato, idSindicato, taxaSindical);
						funcionarios.add(funcionario);
						break;
						
					case 3:
						
						System.out.println("Informe o salário bi-semanal:");
						salario = scanner.nextFloat();
						System.out.println("Informe o percentual da comissão:");
						float comissao = scanner.nextFloat();
						
						funcionario = new Comissionado(nome, endereco, num, salario, metodoPagamento, sindicato, idSindicato, taxaSindical, comissao);
						funcionarios.add(funcionario);
						break;
						
					default:
						
						System.out.println("\nNúmero invalido!\n");

					}
					
					System.out.println(nome + " adicionado! Número: " + num);
					num++;
			        
					break;
				
				case 2:
					
					 System.out.println("\nDigite o número do usuário que deseja remover:\n");
					 
					 index = scanner.nextInt();
					 
					 String empregado = funcionarios.get(index).nome;
					 funcionarios.remove(index);
					 
					 System.out.println("\n" + empregado + " removido do sistema.\n");
					 break;
			   
				case 3:
		    		 
		    		 System.out.println("\nDigite o número do funcionário:\n");
		    		 
		    		 float entradahora, entradamin, saidahora, saidamin, diferencahoras;
		    		 
		    		 index = scanner.nextInt();
		    		 scanner.nextLine();
		    		 
		    		 if(funcionarios.get(index) instanceof Horista) {
		    			 
		    			 Horista horista = (Horista) funcionarios.get(index);
		    			 horista.CartaoDePonto();

		    		 }
		    		 
		    		 else {
		    			 System.out.println("O funcionário não é horista.");
		    		 }
		    		 
		          	 break;
		          	 
				case 4:
					
			    	 System.out.println("\nDigite o número do funcionário:\n");
				    	
			    	 index = scanner.nextInt();
			    	 scanner.nextLine();
			    	 
			    	 if(funcionarios.get(index) instanceof Comissionado) {
				    	 
			    		 Comissionado comissionado = (Comissionado) funcionarios.get(index);
			    		 comissionado.ResultadoDeVenda();
			    		 
			    	 }
			    	 
			    	 else {
			    		 System.out.println("O funcionário não é comissionado.");
			    	 }
			    	
			    	 break;
			    	 
				case 5:
					
			    	 System.out.println("\nDigite o número do funcionário:\n");
				    	
			    	 index = scanner.nextInt();
			    	 scanner.nextLine();
			    	
			    	 if(funcionarios.get(index).getSindicato() == 1) {
			    		 
			    		 System.out.println("Informe o valor da taxa de serviço:");
					    	
			    		 funcionarios.get(index).setTaxaServico(scanner.nextFloat());
				    	 
				    	 System.out.println("Taxa de serviço adicionada.");
			    	 }
			    	 
			    	 else {
			    		 System.out.println("O funcionário não faz parte do sindicato.");
			    	 }
			    	
			    	 break;
		          	 
				case 6:
					
		    		 System.out.println("\nDigite o número do funcionário:\n");
				    	
		    		 index = scanner.nextInt();
		    		 scanner.nextLine();
		    		 
		    		 System.out.println("\nDigite a informação que deseja alterar:\n\n1: Nome\n2: Endereço\n3: Tipo\n4: Método de pagamento\n5: Pertencimento ao sindicato\n6: Identificação no sindicato\n7: Taxa sindical\n");
		    		 
		    		 int choice = scanner.nextInt();
		    		 scanner.nextLine();
		    		 
		    		 if(choice != 3) {
			    		 
		    			 funcionarios.get(index).alterarInformacoes(choice, num);

		    		 }
		    		 
		    		 else {
		    			 System.out.println("\nDigite o tipo do funcionário:\n   1 - Horista\n   2 - Assalariado\n   3 - Comissionado");
		    			 
		    			 int novotipo = scanner.nextInt();
		    			 scanner.nextLine();
		    			 
		    			 if(novotipo == 1) {
		    				
		    				 System.out.println("Informe o salário por hora trabalhada:");
		    				 salario = scanner.nextFloat();
		    				 
		    				 Funcionario funcionarioAux = new Horista(funcionarios.get(index).getNome(), 
		    						 funcionarios.get(index).getEndereco(), funcionarios.get(index).getNumero(), salario, funcionarios.get(index).getMetodoPagamento(), funcionarios.get(index).getSindicato(),
		    						 funcionarios.get(index).getIdSindicato(), funcionarios.get(index).getTaxaSindical());
		    				 
		    				 funcionarios.set(index, funcionarioAux); 
		    				 

		    			 }
		    				
		    			 else if(novotipo == 2) {
		    					
		    				 	System.out.println("Informe o salário mensal:");
			    				salario = scanner.nextFloat();
			    				
			    				 Funcionario funcionarioAux = new Assalariado(funcionarios.get(index).getNome(), 
			    						 funcionarios.get(index).getEndereco(), funcionarios.get(index).getNumero(), salario, funcionarios.get(index).getMetodoPagamento(), funcionarios.get(index).getSindicato(),
			    						 funcionarios.get(index).getIdSindicato(), funcionarios.get(index).getTaxaSindical());
			    				 
			    				 funcionarios.set(index, funcionarioAux); 


		    			 }
		    				
		    			 else if(novotipo == 3) {
		    					
		    				 	System.out.println("Informe o salário bi-semanal:");
		    					salario = scanner.nextFloat();
		    					
		    					System.out.println("Informe o percentual da comissão:");
		    					float comissao = scanner.nextFloat();

			    				 Funcionario funcionarioAux = new Comissionado(funcionarios.get(index).getNome(), 
			    						 funcionarios.get(index).getEndereco(), funcionarios.get(index).getNumero(), salario, funcionarios.get(index).getMetodoPagamento(), funcionarios.get(index).getSindicato(),
			    						 funcionarios.get(index).getIdSindicato(), funcionarios.get(index).getTaxaSindical(), comissao);
			    				 
			    				 funcionarios.set(index, funcionarioAux); 

		    			 }			    			 
		    			 
		    			 System.out.println("\nTipo alterado com sucesso.\n");
		    		 }
		    		 break;
		    		 
				case 7:
					
		    		 int i;
		    		 
		    		 System.out.println("\nData: " + dia + "/" + mes + "/" + ano);
		    		 System.out.println("\n-------------------------------------------------------------------------------\n");
		    		 
		    		 for(i = 0; i < num; i++) {
		    			 
		    			 if((funcionarios.get(i) instanceof Horista)&&(diasemana == 6)) {
		    				 
			    			 Horista horista = (Horista) funcionarios.get(i);
			    			 System.out.println(horista.toString());
				    		 System.out.println("\n-------------------------------------------------------------------------------\n");

		    				 
		    			 }
		    			 
		    			 else if((funcionarios.get(i) instanceof Assalariado)&&(dia == 30)&&(diasemana != 1)&&(diasemana != 7)) {
		    				 
			    			 Assalariado assalariado = (Assalariado) funcionarios.get(i);
			    			 System.out.println(assalariado.toString());
				    		 System.out.println("\n-------------------------------------------------------------------------------\n");
		    				 
		    			 }
		    			 
		    			 else if((funcionarios.get(i) instanceof Comissionado)&&(diasemana == 6)&&((flag%2 == 0)||(flag == 0))) {
		    				 
			    			 Comissionado comissionado = (Comissionado) funcionarios.get(i);
			    			 System.out.println(comissionado.toString());
				    		 System.out.println("\n-------------------------------------------------------------------------------\n");
		    				 
		    			 }
		    			 
		    			 else if(funcionarios.get(i) instanceof AgendaNova) {
		    				 
		    				 AgendaNova agendanova = (AgendaNova) funcionarios.get(i);
		    				 
		    				 if(agendanova.getFrequencia().equals("Mensal") && agendanova.getDia() == dia) {
		    					 
				    			 System.out.println(agendanova.toString());
					    		 System.out.println("\n-------------------------------------------------------------------------------\n");
		    					 
		    				 }
		    				 
		    				 else if(agendanova.getFrequencia().equals("Semanal") && agendanova.getDia() == diasemana) {
		    					 
				    			 System.out.println(agendanova.toString());
					    		 System.out.println("\n-------------------------------------------------------------------------------\n");
		    					 
		    				 }
		    				 
		    			 }
	 
		    		 }
	    			 
		    		 dia++;
	    			 diasemana++;
	    			 if((dia == 31)&&(mes == 12)) {
	    				 ano++;
	    				 mes = 1;
	    				 dia = 1;
	    			 }
	    			 if(dia == 31 && mes != 12) {
	    				 dia = 1;
	    				 mes++;
	    			 }
	    			 if(diasemana == 8) {
	    				 diasemana = 1;
	    			 }
	    			 else if(diasemana == 6) {
	    				 flag++;
	    			 }
	    			 break;
	    			 
				case 8:

			    	System.out.println("Digite a operação desejada:\n 1 - undo\n 2 - redo\n");
			    	int entrada = scanner.nextInt();
			    	
			    	if(op > 0) {
				    	if(entrada == 1) {
				    		redo.push(funcionarios);
				    		funcionarios = undo.pop();
				    		System.out.println("Sucesso!");
				    	}
				    	else if(entrada == 2) {
				    		undo.push(funcionarios);
				    		funcionarios = redo.pop();
				    		System.out.println("Sucesso!");
				    	}
			    	}
			    	
			    	else {
			    		System.out.println("\nOperação inválida!\n");
			    	}
			    	
			    	break;
					
					
				case 9:
					
			    	System.out.println("AGENDA DE PAGAMENTO:");
			    	System.out.println("\n-------------------------------------------------------------------------------\n");
			    	System.out.println("\nEmpregados pagos semanalmente:\n");
			    	for(i = 0; i < funcionarios.size(); i++){
			    		if(funcionarios.get(i) instanceof Horista) {
			    			System.out.println("   - " + funcionarios.get(i).getNome());
			    		}
			    		else if(funcionarios.get(i) instanceof AgendaNova) {
			    			
			    			AgendaNova func = (AgendaNova) funcionarios.get(i);
			    			
			    			if(func.getFrequencia().equals("Semanal")) {
				    			System.out.println("   - " + funcionarios.get(i).getNome());
			    			}
			    		}
			    		
			    	}
			    	System.out.println("\n-------------------------------------------------------------------------------\n");
			    	System.out.println("\nEmpregados pagos bi-semanalmente:\n");
			    	for(i = 0; i < funcionarios.size(); i++){
			    		if(funcionarios.get(i) instanceof Comissionado) {
			    			System.out.println("   - " + funcionarios.get(i).getNome());
			    		}
			    		
			    	}
			    	System.out.println("\n-------------------------------------------------------------------------------\n");
			    	System.out.println("\nEmpregados pagos mensalmente:\n");
			    	for(i = 0; i < funcionarios.size(); i++){
			    		if(funcionarios.get(i) instanceof Assalariado) {
			    			System.out.println("   - " + funcionarios.get(i).getNome());
			    		}
			    		else if(funcionarios.get(i) instanceof AgendaNova) {
			    			
			    			AgendaNova func = (AgendaNova) funcionarios.get(i);
			    			
			    			if(func.getFrequencia().equals("Mensal")) {
				    			System.out.println("   - " + funcionarios.get(i).getNome());
			    			}
			    		}
			    		
			    	}
			    	break;
			    	
				case 10:
					
			    	System.out.println("\nInforme o número do funcionário:\n");
			    	index = scanner.nextInt();
			    	scanner.nextLine();
			    	
			    	System.out.println("\nDigite a nova frequência de pagamento, Mensal ou Semanal.\n "
			    			+ "Caso seja mensal, digite o dia no qual deseja ser pago.\n "
			    			+ "Caso seja semanal, informe o dia da semana no qual deseja ser pago.\n");
			    	
			    	String frequencia = scanner.nextLine();
			    	int diaPagamento = scanner.nextInt();
			    	scanner.nextLine();

			    	System.out.println("\nInforme o salário do funcionário conforme sua nova agenda:\n");
			    	float novoSalario = scanner.nextFloat();
			    		
			    	AgendaNova agendanova = new AgendaNova(funcionarios.get(index).getNome(), funcionarios.get(index).getEndereco(),
			    			funcionarios.get(index).getNumero(), novoSalario, funcionarios.get(index).getMetodoPagamento(), funcionarios.get(index).getSindicato(),
			    			funcionarios.get(index).getIdSindicato(), funcionarios.get(index).getTaxaSindical(), frequencia, diaPagamento);
			    	
			    	funcionarios.set(index, agendanova);
			    	
		    		System.out.println("\nNova agenda: " + frequencia);
					
					break;
					
				default:
					System.out.println("\nNúmero inválido!\n");

				}
				
				System.out.println("\nDigite a operação desejada:\n\n1: Adição de empregado\n2: Remoção de empregado\n3: Lançar um cartão de ponto\n4: Lançar um resultado Venda\n5: Lançar uma taxa de serviço\n6: Alterar detalhes de um emprego\n7: Rodar a folha de pagamento para hoje\n8: Undo/redo\n9: Agenda de pagamento\n10: Criação de novas agendas de pagamento:\n0: Encerrar o programa\n");

				
				
			}
			
			catch(Exception e) {
				scanner.nextLine();
				System.out.println("\nOps! Parece que aconteceu um erro!\n");
				System.out.println("\nDigite a operação desejada:\n\n1: Adição de empregado\n2: Remoção de empregado\n3: Lançar um cartão de ponto\n4: Lançar um resultado Venda\n5: Lançar uma taxa de serviço\n6: Alterar detalhes de um emprego\n7: Rodar a folha de pagamento para hoje\n8: Undo/redo\n9: Agenda de pagamento\n10: Criação de novas agendas de pagamento:\n0: Encerrar o programa\n");

			}
			
			
			
		}
		
		scanner.close();

	}

}
