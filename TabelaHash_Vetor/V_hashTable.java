public class V_hashTable {

    private Veiculo[] vetor;
    private int tamanho;
    
    public V_hashTable(){
        this.vetor = new Veiculo[100];
        this.tamanho = 0;
    }

    private int hash(int chave, int sond, Veiculo[] vector){
        return (chave%vector.length + sond)%vector.length; 
    }

    private Veiculo[] resize(){
        Veiculo novo[] = new Veiculo[2*this.vetor.length];
        for(int i=0; i<this.vetor.length;i++){
            if(this.vetor[i]!=null)
                rehash(this.vetor[i].getChassi(), this.vetor[i], novo); 
        }
        return novo;
    }
    
    private void rehash(int chave, Veiculo valor, Veiculo[] vector){
        int sondagem = 0;
        int hash;

        while(sondagem < vector.length){
            hash = hash(chave,sondagem, vector);

            if(vector[hash] == null){
                vector[hash] = valor;
                return;
            }
            sondagem++;
        }
    }

    public void put(int chave, Veiculo valor){
        int sondagem = 0;
        int hash;

        if((double)tamanho/this.vetor.length >= 0.75){
            this.vetor = resize();
        }

        while(sondagem < this.vetor.length){
            hash = hash(chave,sondagem, this.vetor);

            if(this.vetor[hash] == null || this.vetor[hash].getChassi() == chave){
                this.vetor[hash] = valor;
                tamanho++;
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
            hash = hash(chave,sondagem, this.vetor);

            if(vetor[hash].getChassi() == chave){
                this.vetor[hash] = null;
                tamanho--;
                break;
            }
            sondagem++;
        }
        return veiculo;
    }

    public Veiculo get(int chave){
        int sondagem = 0;
        int hash = 0;

        while(sondagem < this.vetor.length){
            hash = hash(chave,sondagem, this.vetor);

            if(this.vetor[hash].getChassi() == chave){
                return this.vetor[hash];
            }
            sondagem++;
        }
        return null;
    }

    public int getSize(){
        return this.vetor.length;
    }

    public int getTamanho(){
        return tamanho;
    }

    public boolean contains(int chave){
        int sondagem = 0;
        int hash = 0;
        while(sondagem < this.vetor.length){
            hash = hash(chave,sondagem, this.vetor);

            if(this.vetor[hash] != null){   
                if(vetor[hash].getChassi() == chave){
                    return true;
                }
            }
            sondagem++;
        }
        return false;
    }

    

}
