public class principal{
    
    public static void main(String[] args){
        Veiculo[] vetorcriacao = new Veiculo[1000];
        l_hashTable mapa = new l_hashTable(100);
        for(int i=0; i<vetorcriacao.length; i++){
            vetorcriacao[i] = new Veiculo();
            mapa.put(vetorcriacao[i].getChassi(), vetorcriacao[i]);
        }

        if (mapa != null){
            mapa.removeChassi();
            mapa.getVeiculo(vetorcriacao[1].getChassi());
            System.out.println(mapa.buscaFord());
            
        }
    }
}