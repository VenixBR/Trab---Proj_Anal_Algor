public class v_hashTable {

    private Veiculo[] vetor;
    
    public v_hashTable(){
        this.vetor = new Veiculo[100];
    }

    private int hash(int chave){
        return chave%this.vetor.length;
    }
    
    public void put(int chave, Veiculo valor){
        int sondagem = 0;
        int hash;

        while(sondagem <vetor.length){
            hash = (hash(chave) + sondagem)%vetor.length;

            if(vetor[hash] == null || vetor[hash].getChassi() == chave){
                vetor[hash] = valor;
                return;
            }
            sondagem++;
        }
    }

    public Veiculo remove(int chave){
        int hash = hash(chave);
        Veiculo veiculo = this.vetor[hash];
        this.vetor[hash] = null;
        return veiculo;
    }

    public Veiculo get(int chave){
        return this.vetor[hash(chave)];
    }
        
    
    
    
    
}
