import java.util.concurrent.ThreadLocalRandom;

public class V_hashTable implements InterfaceHashTable {

    private Veiculo[] vetor;
    private int tamanho;         //número de elementos na tabela
    private int sondagem;
    private static int p = 1638431;
    
    public V_hashTable(int tamanho){
        this.vetor = new Veiculo[tamanho];
        this.tamanho = 0;
        this.sondagem = 0;
    }

//---------- MÉTODOS OBRIGATÓRIOS ------------------------------------------

    //Função Hash com sondagem
    public int hash(int chave){
        return (chave%this.vetor.length + this.sondagem)%this.vetor.length; 
    }

    public long universalHash(int chave) {
        // cálculo do numero primo?
        // int teste = 0;
        // int num = this.getSize() + 1;
        // for(; teste != 1 ; num++) {
        //     for(int i = 2; i <= num/2; i++) {
                
        //         if(num % i == 0) {
        //             i = num/2;

        //         if(i == num/2 && num % i != 0) 
        //             teste = 1;
        //     }
        // }

        int a = ThreadLocalRandom.current().nextInt(1, p-1);
        int b = ThreadLocalRandom.current().nextInt(0, p-1);
        
        int hash = ((a*chave + b) % p) % getSize();
        return hash;
    }

    //Resize aumenta o tamanho da tabela em 2x quando atinge 75% da capacidade
    public void resize(){
        Veiculo novo[] = new Veiculo[2*this.vetor.length];
        for(int i=0; i<this.vetor.length;i++){
            if(this.vetor[i]!=null){
                int hash;
                this.sondagem = 0;
        
                while(sondagem < vetor.length){
                    hash = (this.vetor[i].getChassi()%novo.length + this.sondagem)%novo.length;
        
                    if(novo[hash] == null){
                        novo[hash] = this.vetor[i];
                        return;
                    }
                    this.sondagem++;
                }
            }
        this.vetor = novo;
        }
    }
    
    //Parte do método Resize que insere os elementos na nova tabela com o novo hash
    //Rehash é opcional, teoricamente faz parte do resize
    // public void rehash(int chave, Veiculo carro, Veiculo[] vetor){
    //     int hash;
    //     this.sondagem = 0;

    //     while(sondagem < vetor.length){
    //         hash = (chave%vetor.length + this.sondagem)%vetor.length;

    //         if(vetor[hash] == null){
    //             vetor[hash] = carro;
    //             return;
    //         }
    //         this.sondagem++;
    //     }
    // }

    //Insere ou sobrescreve um elemento na tabela e chama o resize se necessário
    public void put(int chave, Veiculo carro){
        int hash;

        if((double)tamanho/this.vetor.length >= 0.75){
            resize();
        }

        this.sondagem = 0;
        while(sondagem < this.vetor.length){
            hash = hash(chave);

            if(this.vetor[hash] == null){
                this.vetor[hash] = carro;
                this.tamanho++;
                return;
            }
            else if(this.vetor[hash].getChassi() == chave){
                this.vetor[hash] = carro;
                return;
            }
            this.sondagem++;
        }
    }

    //Remove um elemento da tabela a partir da chave, se ele existir
    // public void remove(int chave){
    //     int hash = 0;
    //     this.sondagem = 0;

    //     while(this.sondagem < this.vetor.length){
    //         hash = hash(chave);

    //         if(this.vetor[hash] != null && this.vetor[hash].getChassi() == chave){
    //             this.vetor[hash] = null;
    //             this.tamanho--;
    //             break;
    //         }
    //         this.sondagem++;
    //     }
    // } 

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

    //Printa todos os carros da marca Ford
    public int buscaFord(){
        int num_ford = 0;
        for(int i=0; i<this.vetor.length; i++){
            if(this.vetor[i]!= null && this.vetor[i].getMarca() == "Ford")
                num_ford++;
        }
        return num_ford;
    }

    //Remove todos os elementos com o chassi <=202050000
    public void removeChassi(){
        for(int i=0; i<this.vetor.length; i++){
            if(this.vetor[i]!= null && this.vetor[i].getChassi() <= 202050000){
                int hash = 0;
                this.sondagem = 0;

                while(this.sondagem < this.vetor.length){
                hash = hash(this.vetor[i].getChassi());

                if(this.vetor[hash] != null && this.vetor[hash].getChassi() == this.vetor[i].getChassi()){
                   this.vetor[hash] = null;
                   this.tamanho--;
                   break;
                }
                this.sondagem++;
                }
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
