import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class main{
    
    public static void main(String[] args) throws IOException{
        FileWriter arq = new FileWriter("d:\\resultados.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        long m1;
        long time_ini = 0;
        long time_fim = 0;


        System.out.println("\n");
        V_hashTable tabela = new V_hashTable();
      
        time_ini = System.nanoTime();
        for(int i=0; i<100;i++){
            Veiculo x = new Veiculo();
            tabela.put(x.getChassi(),x);

            System.out.println("Tamanho:" + tabela.getSize() + "   Elementos:" + tabela.getTamanho()); 
        }
        time_fim = System.nanoTime();
        m1 = time_fim - time_ini;
        System.out.println(m1);
       
        //gravarArq.printf("Tempo para inserir 10 carros:  " + m1 + " ns");
        arq.close();
        System.out.println("\n");
    }
}