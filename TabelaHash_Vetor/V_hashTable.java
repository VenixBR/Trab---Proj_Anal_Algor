
public class V_hashTable {

    private Veiculo[] vetor;
    private int tamanho;         //número de elementos na tabela
    
    public V_hashTable(){
        this.vetor = new Veiculo[100];
        this.tamanho = 0;
    }

    //Função Hash
    private int hash(int chave, int sond, Veiculo[] vector){
        return (chave%vector.length + sond)%vector.length; 
    }

    //Resize aumenta o tamanho da tabela em 2x quando atinge 75% da capacidade
    private Veiculo[] resize(){
        Veiculo novo[] = new Veiculo[2*this.vetor.length];
        for(int i=0; i<this.vetor.length;i++){
            if(this.vetor[i]!=null)
                rehash(this.vetor[i].getChassi(), this.vetor[i], novo); 
        }
        return novo;
    }
    
    //Parte do método Resize que insere os elementos na nova tabela com o novo hash
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

    //Insere ou sobre escreve um elemento na tabela e chama o resize se necessário
    public void put(int chave, Veiculo valor){
        int sondagem = 0;
        int hash;

        if((double)tamanho/this.vetor.length >= 0.75){
            this.vetor = resize();
        }

        while(sondagem < this.vetor.length){
            hash = hash(chave,sondagem, this.vetor);

            if(this.vetor[hash] == null){
                this.vetor[hash] = valor;
                tamanho++;
                return;
            }
            else if(this.vetor[hash].getChassi() == chave){
                this.vetor[hash] = valor;
                return;
            }
            sondagem++;
        }
    }

    //Remove um elemento da tabela se ele existir
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

    //Faz uma busca e retorna true caso o elemento for encontrado
    public boolean contains(int chave){
        int sondagem = 0;
        int hash = 0;
        while(sondagem < this.vetor.length){
            hash = hash(chave,sondagem, this.vetor);

            if(this.vetor[hash] != null && vetor[hash].getChassi() == chave){   
                return true;
            }
            sondagem++;
        }
        return false;
    }

    //Retorna um elemento da tabela a partir da chave
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

    //Retorna um elemento da tabela a partir do índice do vetor
    public Veiculo getVetor(int i){
        if (this.vetor[i] != null)
            return this.vetor[i];
        else return null;
    }

    //Retorna o tamanho atual do vetor
    public int getSize(){
        return this.vetor.length;
    }

    //Retorna quantos elementos há no vetor
    public int getTamanho(){
        return tamanho;
    }

    //Retorna a hash de um elemento a partir da chave
    public int getHash(int chave){
        return hash(chave, 0, this.vetor);
    }
}
