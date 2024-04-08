
public class V_hashTable {

    private Veiculo[] vetor;
    private int tamanho;         //número de elementos na tabela
    private int sondagem;
    
    public V_hashTable(){
        this.vetor = new Veiculo[100];
        this.tamanho = 0;
        this.sondagem = 0;
    }

    //Função Hash
    private int hash(int chave, Veiculo[] vector){
        return (chave%vector.length + this.sondagem)%vector.length; 
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
        this.sondagem = 0;
        int hash;

        while(sondagem < vector.length){
            hash = hash(chave, vector);

            if(vector[hash] == null){
                vector[hash] = valor;
                return;
            }
            this.sondagem++;
        }
    }

    //Insere ou sobrescreve um elemento na tabela e chama o resize se necessário
    public void put(int chave, Veiculo valor){
        this.sondagem = 0;
        int hash;

        if((double)tamanho/this.vetor.length >= 0.75){
            this.vetor = resize();
        }

        while(sondagem < this.vetor.length){
            hash = hash(chave, this.vetor);

            if(this.vetor[hash] == null){
                this.vetor[hash] = valor;
                this.tamanho++;
                return;
            }
            else if(this.vetor[hash].getChassi() == chave){
                this.vetor[hash] = valor;
                return;
            }
            this.sondagem++;
        }
    }

    //Remove um elemento da tabela se ele existir
    public Veiculo remove(int chave){
        this.sondagem = 0;
        int hash = 0;
        Veiculo veiculo = this.vetor[hash];

        while(sondagem < vetor.length){
            hash = hash(chave, this.vetor);

            if(vetor[hash].getChassi() == chave){
                this.vetor[hash] = null;
                this.tamanho--;
                break;
            }
            this.sondagem++;
        }
        return veiculo;
    } 

    //Faz uma busca e retorna true caso o elemento for encontrado
    public boolean contains(int chave){
        this.sondagem = 0;
        int hash = 0;
        while(this.sondagem < this.vetor.length){
            hash = hash(chave, this.vetor);

            if(this.vetor[hash] != null && vetor[hash].getChassi() == chave){   
                return true;
            }
            this.sondagem++;
        }
        return false;
    }

    //Retorna um elemento da tabela a partir da chave
    public Veiculo get(int chave){
        this.sondagem = 0;
        int hash = 0;

        while(sondagem < this.vetor.length){
            hash = hash(chave, this.vetor);

            if(this.vetor[hash].getChassi() == chave){
                return this.vetor[hash];
            }
            this.sondagem++;
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
        return this.tamanho;
    }

    //Retorna a hash de um elemento a partir da chave
    public int getHash(int chave){
        return hash(chave, this.vetor);
    }
}
