import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class main{
    
    public static void main(String[] args) throws IOException{
        //System.out.println("\n");



        //PARA MEDIR OS TEMPOS
        //O método "System.nanoTime()" retorna quantos nanossegundos faltam para a meia noite.
        //Pra pegar o tempo de execução é só salvar o tempo antes de começar e após a finalização
        //de cada, fo fim faz o fim menos o início e deu. Tem que fazer todos de uma vez, pois 
        //cada vez que roda o programa gera carros diferentes e o resultado vai ser diferente.
        //eu coloquei as funções que salvam arquivos, no fim do código tem uma que printa, só
        //só adaptar e salvar os resultados no arquivo, fica mais fácil.



        //Arquivo para salvar todos os elementos da lista
        FileWriter table = new FileWriter("d:\\Trabalho\\Tabelas\\tabela.txt");
        PrintWriter gravarTable = new PrintWriter(table);    

        //Arquivo para salvar os tempos de execução
        FileWriter tempos = new FileWriter("d:\\Trabalho\\Tempos\\tempos.txt");
        PrintWriter gravarTempos = new PrintWriter(tempos);

        //Variáveis para salvar os tempos
        long time_ini = 0;
        long time_fim = 0;
        long m1;

        //Criação da tabela
        V_hashTable tabela = new V_hashTable();
      
        //Atribuição do tempo de início dos testes
        time_ini = System.nanoTime();

        //Adiciona i elementos na tabela
        for(int i=0; i<90;i++){
            Veiculo x = new Veiculo();
            tabela.put(x.getChassi(),x);
            System.out.println("Tamanho:" + tabela.getSize() + "   Elementos:" + tabela.getBusy()); 
        }

        //Salva o tempo final do teste
        time_fim = System.nanoTime();

        //Calcula o tempo gasto
        m1 = time_fim - time_ini;
        //System.out.println(m1);


        //System.out.println("Carros Ford: " + tabela.buscaFord());

        //Para fazer os outros testes é só copiar esse for e alterar o i


        //------------------------------- PARTE QUE SALVA O ARQUIVO DA TABELA -------------------------------

         
        gravarTable.println("TESTE 01\n");
        gravarTable.println("Tamanho:" + tabela.getSize() + "   Elementos:" + tabela.getBusy()+"\n");

        Veiculo egg;
        int vazio = 0;
        for(int i=0; i<tabela.getSize();i++){
            egg = tabela.getVetor(i);
            if(egg != null)
                gravarTable.println("Vetor["+i+"]  " + "Hash: " + tabela.getHash(egg.getChassi()) + "  " + egg);
            else {
                gravarTable.println("Vetor["+i+"]");
                vazio++;
            }
        }

        gravarTable.println("\n\nEspaços Vazios: " + vazio);
        

        //---------------------------------------------------------------------------------------------------

        tabela.removeChassi();
        System.out.println("terminou");


        gravarTempos.printf("Tempo para inserir 10 carros:  " + m1 + " ns");

        //Fecha os arquivos
        table.close();
        tempos.close();


        //System.out.println("\n");
    }
}
