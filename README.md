# Folha-de-Pagamento---Refatoramento-
Projeto de Software - Folha de Pagamento - Refatoramento 

Funcionalidades: 
     
     - Adição de novos empregados; 
      
      - Remoção de empregados; 
      
      - Lançar cartão de ponto para empregados horistas;
      
      - Lançar um resultado de venda de um empregado comissionado; 
      
      - Alterar detalhes sobre um empregado;
      
      - Rodar a folha de pagamento para o dia atual no sistema(o sistema inicia no dia atual e a cada rodagem da folha de 
         pagamento, um dia é incrementado); 
      
      - Desfazer/refazer operações; 
      
      - Mostrar a agenda de pagamento do mês, com cada funcionário dividido em
         semanal, mensal ou bisemanal; 
      
      - Criar nova agenda de pagamento, ou seja, pode-se alterar a frequência de pagamento entre mensal e semanal e 
         escolher o dia que deseja ser pago com base nessa nova agenda.
      
 Classes:
 
      1) Funcionários:
            
            Motivação: A necessidade de uma classe que contivesse as informações gerais a qualquer funcionário.
            
            Solução: Uma superclasse abstrata, que tem como atributos informaçes gerais como nome, endereço,
                     identificação no sistema, salário, método de pagamento, a pertinência ao sindicato, taxa sindical,
                     identificação no sindicato e taxa de serviço. Possui como métodos um construtor, o método      alterarInformações
                     que edita informações sobre um usuário, e ainda um método abstrato getSalario para que suas subclasses 
                     retornassem o salário da forma específica a cada uma.
            
            Vantagens: Sem a superclasse de Funcionários, cada tipo de empregado teria que ser implementado e haveria código
                        repetido, pois todos possuiriam as mesmas informações gerais, logo essa superclasse evita isso.
            
            Desvantagens: A necessidade de fazer casts para tratar cada tipo específico de suas subclasses num ArrayList de 
                          Funcionários.
                          
                         
      2) Horista:
            
            Motivação: A necessidade de uma classe que trabalhasse com o tipo de empregado Horista e suas especifidades.
            
            Solução: Uma subclasse da superclasse Funcionários, com os atributos horas e horaExtra, que seriam necessários
                      para acumular as horas do horista quando ele batesse o cartão de ponto para que fossem utilizadas
                      no cálculo de seu salário, possui os métodos getSalário, que calcula o salário específico do horista
                      e o método CartãoDePonto, que tem a funcionalidade de acumular as horas de trabalho diárias de um
                      horista.
            
            Vantagens: Poder tratar apenas as especificidades desse tipo de funcionário em uma única classe exclusiva dele. 
            
       
       3) Assalariado:
            
            Motivação: A necessidade de uma classe que trabalhasse com o tipo de empregado Assalariado e suas especifidades.
            
            Solução: A classe mais simples do sistema, uma subclasse de Funcionários, sem nenhum atributo a mais, com o
                      método getSalario que retorna o cálculo do salário específico de um assalariado.
            
            Vantagens: Poder tratar apenas as especificidades desse tipo de funcionário em uma única classe exclusiva dele.
            
        
        4) Comissionado: 
            
            Motivação: A necessidade de uma classe que trabalhasse com o tipo de empregado Comissionado e suas especifidades.
            
            Solução: Uma subclasse de Funcionários, com os atributos comissao, que contém a porcentagem da comissão que 
                      o comissionado receberá por cada venda, e vendas, que acumulará cada resultado de venda do funcionário.
                      Possui os métodos getSalário, que retorna seu salário específico e o método ResultadoDeVenda que
                      lança um resultado de venda e o guarda no atributo vendas.
             
             Vantagens: Poder tratar apenas as especificidades desse tipo de funcionário em uma única classe exclusiva dele.
             
        
        5) AgendaNova:
             
             Motivação: A necessidade de uma subclasse auxiliar de Funcionários para tratar a funcionalidade do programa 
              de criar uma nova agenda de pagamento.
             
             Solução: Uma subclasse de Funcionários, com os atributos frequencia, que guarda a nova frequencia na qual
                      o funcionário deseja ser pago, e dia, que guarda o dia, do mês ou da semana a depender da frequencia,
                      no qual o funcionário deseja ser pago. Tem como métodos o getSalario que retorna o salário com base
                      nessa nova agenda e um construtor que além de utilizar o da suplerclasse, adiciona também o dia de
                      pagamento e a frequencia.
              
              Vantagens: Poder criar uma nova agenda de pagamento sem que se altere qualquer coisa das outras.
              
        
        6) FolhaDePagamento:
              Motivação: Uma classe que contivesse a main.
              Solução: Uma classe com a main, onde se dá todo o funcionamento do sistema. 
             
            
Distribuição dos métodos:

        Motivação: Eram necessários um método que batesse o cartão de ponto, um método que lançasse o resultado de vendas,
                    um método que alterasse informações sobre um determinado usuário, um que calculasse o salário de 
                    cada tipo de funcionário e os construtores de cada tipo.
        
        Solução:  - O metodo CartaoDePonto se localiza na classe Horista pois apenas esse tipo de funcionário pode bater
                  cartão de ponto; 
                  - O método ResultadoDeVendas se localiza na classe Comissionado pois apenas estes
                  podem lançar uma venda; 
                  - O método alterarInformacoes está na classe Funcionários pois qualquer funcionário pode editar 
                  suas informações, logo era algo inerente a todas as subclasses de Funcionarios e portanto deveria se 
                  localizar na superclasse; 
                  - Cada classe possui um construtor próprio; 
                  - A função getSalario está em Funcionarios pois é abstrata e cada tipo de empregado deveria ter implementado 
                  seu próprio cálculo de salário baseado em seu tipo.
         
         Vantagens: Cada classe possui apenas o que é necessário a ela, diminuindo a main e o risco de um erro.
         

Herança: 

      Motivação: No sistema os funcionários deveriam ser divididos em subtipos, como Horista, Assalariado e Comissionado, 
                  que são todos subtipos de um funcionário. Daí, a fim de evitar a repetição de códigos, era mais eficiente
                  se houvesse a utilização de herança.
       
      Solução: Uma superclasse de Funcionarios, com as subclasse Horista, Assalariado, Comissionado e AgendaNova.
      
      Vantagens: A não repetição de código, a utilização de funções da superclasse nas subclasses, como o construtor.
      

Abstrata:

      Motivação: Era preciso mostrar o salário de um funcionário quando se rodava a folha de pagamento, contudo cada 
                  funcionário possuía um cálculo próprio de salário, logo essa função não poderia se localizar na 
                  superclasse Funcionarios, mas em cada subclasse específica, além de que um funcionário precisava 
                  ter um tipo, não seria possível criar um objeto do tipo Funcionarios, daí a necessidade de Abstract.
                  
       Solução: A superclasse Funcionarios é abstrata, e possui a função abstrata getSalario, obrigando todas as suas subclasses
                a implementarem-na.
                
       Vantagens: Cada tipo de funcionário ter seu próprio cálculo de salário sem o risco de este ser calculado errado.

Tratamento de Exceções:

      Motivação: O código não pode parar quando ocorre um erro, deve ser informado ao usuário que ocorreu um erro ali
                  e ele deve tentar novamente, corrigindo o erro, e portanto o uso de Tratamento de exceções era extremamente
                  necessário.
      Solução: O uso de Exception, e uma mensagem avisando que ocorreu um erro e que o usuário deve tentar novamente, 
                  reconduzindo-o ao menu principal.
                  
      Vantagens: Evita que o programa pare e alerta ao usuário que há um erro ali e que ele deve corrigi-lo.           


Extensibilidade:

      Motivação: A necessidade de utilizar herança, como foi necessário que houvessem quatro subclasses, o código necessitou
                 ser extensível.
                 
      Solução: Uma superclasse Funcionarios, com quatro subclasses dela.
      
      Vantagens: A facilidade de lidar com cada tipo de funcionário.
      
 
        
        
     
