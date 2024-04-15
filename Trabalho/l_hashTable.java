import java.util.concurrent.ThreadLocalRandom;

public class l_hashTable implements InterfaceHashTable {

    public LDE[] vetorLista;
    private int tamanho;
    private static int p = 100003;

    public l_hashTable(int x) {
        this.vetorLista = new LDE[x];
    }

    public int hash(int chave) {

        int pos = (int) ((chave * 0.635789) % 1 * this.vetorLista.length);

        return pos;
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
        
        int hash = ((a*chave + b) % p) % vetorLista.length;
        return hash;
    }

    public void resize() {
        LDE[] novo = new LDE[2 * this.vetorLista.length];
        for (int i = 0; i < this.vetorLista.length; i++) {
            if (this.vetorLista[i] != null)
                for (Noh n = vetorLista[i].getInicio(); n != null; n = n.getProx()) {

                    int chave = n.getInfo().getChassi();
                    int pos = hash(chave);

                    if (novo[pos] == null) {
                        novo[pos] = new LDE();
                        novo[pos].insereInicio(n.getInfo());

                    } else {
                        for (Noh novoNoh = novo[pos].getInicio(); novoNoh != null; novoNoh = novoNoh.getProx()) {
                            if (novoNoh.getInfo().getChassi() == chave) {
                                return;
                            }
                        }
                        novo[pos].insereInicio(n.getInfo());
                    }
                }
        }
        // System.out.println("Resize realizado, vetor aumentado de " +
        // vetorLista.length + " para " + novo.length);
        // System.out.println(realocados + " carros realocados.");
        this.vetorLista = novo;
    }

    // public LDE[] rehash(int chave, Veiculo carro, LDE[] vetor) {
    //     int pos = hash(chave);

    //     if (vetor[pos] == null) {
    //         vetor[pos] = new LDE();
    //         vetor[pos].insereInicio(carro);
    //     } else {
    //         for (Noh n = vetor[pos].getInicio(); n != null; n = n.getProx()) {
    //             if (n.getInfo().getChassi() == chave) {
    //                 return null;
    //             }
    //         }
    //         vetor[pos].insereInicio(carro);
    //     }
    //     return vetor;
    // }

    public void put(int chave, Veiculo carro) {

        if ((double) tamanho / this.vetorLista.length >= 0.75) {
            resize();
        }

        int hash = hash(chave);
        LDE veiculos_lista = this.vetorLista[hash];
        if (veiculos_lista == null) {
            veiculos_lista = new LDE();
            veiculos_lista.insereInicio(carro);
            this.vetorLista[hash] = veiculos_lista;
        } else {
            for (Noh n = veiculos_lista.getInicio(); n != null; n = n.getProx()) {
                if (n.getInfo().getChassi() == chave) {
                    n.setInfo(carro);
                    return;
                }
            }
            veiculos_lista.insereInicio(carro);
            this.vetorLista[hash] = veiculos_lista;

        }
        tamanho++;
    }

    public Veiculo getVeiculo(int chave) {
        int hash = hash(chave);
        int pos = 0;
        LDE veiculos_lista = this.vetorLista[hash];
        if (veiculos_lista != null) {
            for (Noh n = veiculos_lista.getInicio(); n != null; n = n.getProx()) {
                if (n.getInfo().getChassi() == chave) {
                    System.out.println("Veículo encontrado na posição " + hash + " do vetor, nó número " + pos
                            + " e com informações: " + n.getInfo());
                    return n.getInfo();
                }
                pos++;
            }
            System.out.println("Veículo não encontrado.");
        }

        return null;

    }

    public void getAll() {
        for (int i = 0; i < vetorLista.length; i++) {
            if (vetorLista[i] != null) {
                Noh p = vetorLista[i].getInicio();
                while (p != null) {
                    System.out.println("Veiculo de chassi " + p.getInfo().getChassi() + " e marca " + p.getInfo().getMarca());
                    p = p.getProx();
                }
            }
        }
    }

    public void removeChassi() {
        for (int i = 0; i < vetorLista.length; i++) {
            if (vetorLista[i] != null) {
                Noh p = vetorLista[i].getInicio();
                while (p != null) {
                    if (p.getInfo().getChassi() <= 202050000) // busca
                        if (vetorLista[i].remove(p.getInfo())) {
                        }
                    p = p.getProx();
                }
            }
        }

    }

    public boolean contains(int chave) {
        int hash = hash(chave);
        Noh inicio = vetorLista[hash].getInicio();

        for(Noh p = inicio; p != null ; p = p.getProx()) {
            if(p.getInfo().getChassi() == chave)
                return true;
        }
        return false;
    }

    public int buscaFord() {
        LDE[] veiculos_lista = this.vetorLista;
        int f = 0;
        for (int i = 0; i < veiculos_lista.length; i++) {
            if (veiculos_lista[i] != null) {
                Noh p = veiculos_lista[i].getInicio();
                while (p != null) { // busca
                    if (p.getInfo().isMarcaFord()) {
                        f++;
                    }
                    p = p.getProx();
                }
            }
        }
        return f;
    }

    public int getSize(){
        return this.vetorLista.length;
    }

    public int getBusy(){
        return this.tamanho;
    }

    public int getHash(int chave){
        return hash(chave);
    }
}