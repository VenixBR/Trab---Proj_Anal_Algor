public class principal{
    
    public static void main(String[] args){
        Veiculo[] vetorcriacao = new Veiculo[1000];
        l_hashTable mapa = new l_hashTable(100);
        long t2 = System.nanoTime();
        for(int i=0; i<vetorcriacao.length; i++){
            vetorcriacao[i] = new Veiculo();
            mapa.put(vetorcriacao[i].getChassi(), vetorcriacao[i]);
        }
        long Tempo2 = System.nanoTime()- t2;
        System.out.println("O tempo foi de "+ Tempo2+ " ns para inserir todos os veiculos");


        if (mapa != null){
            long t0 = System.nanoTime();
            System.out.println("Existem " +mapa.buscaFord() + " veiculos da Ford Cadastrados");
            long Tempo0 = System.nanoTime()- t0;
            System.out.println("O tempo foi de "+ Tempo0 + " ns para achar todos os veiculos da Ford");

            long t1 = System.nanoTime();
            mapa.removeChassi();
            long Tempo1 = System.nanoTime()- t1;
            System.out.println("O tempo foi de "+ Tempo1 + " ns para remover os veiculos");
            
        }
    }
}