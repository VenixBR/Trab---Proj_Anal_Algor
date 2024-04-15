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
        FileWriter table = new FileWriter("/home/pedropotter/Documents/PAA/testes pedro/Testes Vetor/10Veiculos/elementos4.txt");
        PrintWriter gravarTable = new PrintWriter(table);    

        //Arquivo para salvar todos os elementos da lista após a remoção
        FileWriter table_m = new FileWriter("/home/pedropotter/Documents/PAA/testes pedro/Testes Vetor/10Veiculos/remocao4.txt");
        PrintWriter gravarTable_m = new PrintWriter(table_m);    

        //Arquivo para salvar os tempos de execução
        FileWriter tempos = new FileWriter("/home/pedropotter/Documents/PAA/testes pedro/Testes Vetor/10Veiculos/tempos4.txt");
        PrintWriter gravarTempos = new PrintWriter(tempos);

        //Variáveis para salvar os tempos
        long time_ini = 0;
        long time_fim = 0;
        long t10, t100, t1k,t100k, t1mi, tp, tford, tr;

        //Criação da tabela
        V_hashTable tabela = new V_hashTable(10);
      
        //INSERÇÃO DE 10
        time_ini = System.nanoTime();
        for(int i=0;i<10;i++){
            Veiculo x = new Veiculo();
            tabela.put(x.getChassi(),x);
        }
        time_fim = System.nanoTime();
        t10 = time_fim - time_ini;

        // INSERÇÃO DE 100
        for(int i=10;i<100;i++){
            Veiculo x = new Veiculo();
            tabela.put(x.getChassi(),x);
        }
        time_fim = System.nanoTime();
        t100 = time_fim - time_ini;
        
        // INSERÇÃO DE 1.000
        for(int i=100;i<1000;i++){
            Veiculo x = new Veiculo();
            tabela.put(x.getChassi(),x);
        }
        time_fim = System.nanoTime();
        t1k = time_fim - time_ini;

        //INSERÇÃO DE 100.000
        for(int i=1000;i<100000;i++){
            Veiculo x = new Veiculo();
            tabela.put(x.getChassi(),x);
        }
        time_fim = System.nanoTime();
        t100k = time_fim - time_ini;

        //INSERÇÃO DE 1.000.000
        for(int i=100000;i<1000000;i++){
            Veiculo x = new Veiculo();
            tabela.put(x.getChassi(),x);
        }
        time_fim = System.nanoTime();
        t1mi = time_fim - time_ini;


        //IMPRESSÃO DE TODOS OS ELEMENTOS
        time_ini = System.nanoTime();
        Veiculo egg;
        for(int i=0; i<tabela.getSize();i++){
            egg = tabela.getVetor(i);
            if(egg != null)
                System.out.println(egg);
        }
        time_fim = System.nanoTime();
        tp = time_fim - time_ini;

        //CONTAR TODOS OS VEÍCULOS DA FORD
        time_ini = System.nanoTime();
        System.out.println(tabela.buscaFord());
        time_fim = System.nanoTime();
        tford = time_fim - time_ini;



        //------------------------------------ SALVA O ARQUIVO DA TABELA ------------------------------------

         
        gravarTable.println("TESTE 01\n");
        gravarTable.println("Tamanho:" + tabela.getSize() + "   Elementos:" + tabela.getBusy()+"\n");

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


        //REMOÇÃO DE VEÍCULOS COM CHASSI <=202050000
        time_ini = System.nanoTime();
        tabela.removeChassi();
        time_fim = System.nanoTime();
        tr = time_fim - time_ini;


        //------------------------------ SALVA O ARQUIVO DA TABELA APÓS REMOÇÃO -----------------------------

         
        gravarTable_m.println("TESTE 01\n");
        gravarTable_m.println("Tamanho:" + tabela.getSize() + "   Elementos:" + tabela.getBusy()+"\n");

        vazio = 0;
        for(int i=0; i<tabela.getSize();i++){
            egg = tabela.getVetor(i);
            if(egg != null)
                gravarTable_m.println("Vetor["+i+"]  " + "Hash: " + tabela.getHash(egg.getChassi()) + "  " + egg);
            else {
                gravarTable_m.println("Vetor["+i+"]");
                vazio++;
            }
        }

        gravarTable.println("\n\nEspaços Vazios: " + vazio);
        

        //---------------------------------------------------------------------------------------------------


        //----------------------------------- SALVA O ARQUIVO DOS TEMPOS  -----------------------------------


        gravarTempos.printf("-------------------------------\n");
        gravarTempos.printf("  VEÍCULOS   |   TEMPO (ns)    \n");
        gravarTempos.printf("-------------------------------\n");
        gravarTempos.printf("    10       | " + t10 +      "\n");
        gravarTempos.printf("    100      | " + t100 +     "\n");
        gravarTempos.printf("   1.000     | " + t1k +      "\n");
        gravarTempos.printf("  100.000    | " + t100k +    "\n");
        gravarTempos.printf(" 1.000.000   | " + t1mi +     "\n");
        gravarTempos.printf("-------------------------------\n\n\n");
        gravarTempos.printf("TEMPO PARA IMPRESSÃO:    " + tp + " ns\n");
        gravarTempos.printf("TEMPO PARA REMOÇÃO:      " + tr + " ns\n");
        gravarTempos.printf("TEMPO PARA MOSTRAR FORD: " + tford + " ns\n");
        
        


        //---------------------------------------------------------------------------------------------------

        //Fecha os arquivos
        table.close();
        tempos.close();
        table_m.close();

        System.out.println("terminou");


        //System.out.println("\n");
    }
}
