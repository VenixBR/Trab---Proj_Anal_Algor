
public class V_hashTable {

    private Veiculo[] vetor;
    private int tamanho;         //número de elementos na tabela
    private int sondagem;
    
    public V_hashTable(){
        this.vetor = new Veiculo[100];
        this.tamanho = 0;
        this.sondagem = 0;
    }

//---------- MÉTODOS OBRIGATÓRIOS ------------------------------------------

    //Função Hash com sondagem
    private int hash(int chave){
        return (chave%this.vetor.length + this.sondagem)%this.vetor.length; 
    }

    //Resize aumenta o tamanho da tabela em 2x quando atinge 75% da capacidade
    private void resize(){
        Veiculo novo[] = new Veiculo[2*this.vetor.length];
        for(int i=0; i<this.vetor.length;i++){
            if(this.vetor[i]!=null)
                rehash(this.vetor[i].getChassi(), this.vetor[i], novo); 
        }
        this.vetor = novo;
    }
    
    //Parte do método Resize que insere os elementos na nova tabela com o novo hash
    //Rehash é opcional, teoricamente faz parte do resize
    private void rehash(int chave, Veiculo valor, Veiculo[] vetor){
        int hash;
        this.sondagem = 0;

        while(sondagem < vetor.length){
            hash = (chave%vetor.length + this.sondagem)%vetor.length;

            if(vetor[hash] == null){
                vetor[hash] = valor;
                return;
            }
            this.sondagem++;
        }
    }

    //Insere ou sobrescreve um elemento na tabela e chama o resize se necessário
    public void put(int chave, Veiculo valor){
        int hash;

        if((double)tamanho/this.vetor.length >= 0.75){
            resize();
        }

        this.sondagem = 0;
        while(sondagem < this.vetor.length){
            hash = hash(chave);

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

    //Remove um elemento da tabela a partir da chave, se ele existir
    public void remove(int chave){
        int hash = 0;
        this.sondagem = 0;

        while(this.sondagem < this.vetor.length){
            hash = hash(chave);

            if(this.vetor[hash] != null && this.vetor[hash].getChassi() == chave){
                this.vetor[hash] = null;
                this.tamanho--;
                break;
            }
            this.sondagem++;
        }
    } 

    //Retorna um elemento da tabela a partir da chave
    public Veiculo getVeiculo(int chave){
        int hash = 0;
        this.sondagem = 0;

        while(this.sondagem < this.vetor.length){
            hash = hash(chave);

            if(this.vetor[hash].getChassi() == chave){
                return this.vetor[hash];
            }
            this.sondagem++;
        }
        return null;
    }

//--------------------------------------------------------------------------

    //Conta quantos elementos são da Ford com uma busca
    public int buscaFord(){
        int numFord = 0;
        for(int i=0; i<this.vetor.length; i++){
            if(this.vetor[i]!= null && this.vetor[i].getMarca() == "Ford")
                numFord++;
        }
        return numFord;
    }

    //Remove todos os elementos com o chassi <=202050000
    public void removeChassi(){
        for(int i=0; i<this.vetor.length; i++){
            if(this.vetor[i]!= null && this.vetor[i].getChassi() <= 202050000){
                remove(this.vetor[i].getChassi());
            }     
        }
    }

    //Faz uma busca e retorna true caso o elemento for encontrado
    public boolean contains(int chave){
        int hash = 0;
        this.sondagem = 0;
        while(this.sondagem < this.vetor.length){
            hash = hash(chave);

            if(this.vetor[hash] != null && vetor[hash].getChassi() == chave){   
                return true;
            }
            this.sondagem++;
        }
        return false;
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
    public int getBusy(){
        return this.tamanho;
    }

    //Retorna a hash de um elemento a partir da chave
    public int getHash(int chave){
        this.sondagem = 0;
        return hash(chave);
    }
}
