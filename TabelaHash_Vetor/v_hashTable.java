public class v_hashTable {

    private Veiculo[] vetor;
    private int tamanho=0;
    
    public v_hashTable(){
        this.vetor = new Veiculo[100];
    }

    private int hash(int chave){
        return chave%this.vetor.length;
    }

    private Veiculo[] resize(){
        Veiculo novo[] = new Veiculo[2*this.vetor.length];
        for(int i=0; i<this.vetor.length;i++){
           put(this.vetor[i].getChassi(), this.vetor[i], novo); 
        }
        return novo;
    }
    
    public void put(int chave, Veiculo valor, Veiculo[] vetor){
        int sondagem = 0;
        int hash;
        if(tamanho/vetor.length >= 0.75){
            Veiculo[] novo;
            vetor = resize();
        }
        tamanho++;

        while(sondagem < vetor.length){
            hash = (hash(chave) + sondagem)%vetor.length;

            if(vetor[hash] == null || vetor[hash].getChassi() == chave){
                vetor[hash] = valor;
                return;
            }
            sondagem++;
        }
    }








    

    public Veiculo remove(int chave){
        int sondagem = 0;
        int hash = 0;
        Veiculo veiculo = this.vetor[hash];

        while(sondagem < vetor.length){
            hash = (hash(chave) + sondagem)%vetor.length;

            if(vetor[hash].getChassi() == chave){
                this.vetor[hash] = null;
                break;
            }
            sondagem++;
        }
        return veiculo;
    }

    public Veiculo get(int chave){
        return this.vetor[hash(chave)];
    }

    public int getSize(){
        return this.vetor.length;
    }

    public boolean contains(int chave){
        int sondagem = 0;
        int hash = 0;
        while(sondagem < vetor.length){
            hash = (hash(chave) + sondagem)%vetor.length;

            if(vetor[hash].getChassi() == chave){
                return true;
            }
            sondagem++;
        }
        return false;
    }

    
        
    
    
    
    
}
