public class l_hashTable {

    public LDE[] vetorLista;
    private int tamanho;

    public l_hashTable(int x) {
        this.vetorLista = new LDE[x];
    }

    public int hash(int chave) {

        int pos = (int) ((chave * 0.635789) % 1 * vetorLista.length);

        return pos;
    }

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
                    return;
                }
            }
            veiculos_lista.insereInicio(carro);
            this.vetorLista[hash] = veiculos_lista;

        }
        tamanho++;
    }

    private void resize() {
        LDE[] novo = new LDE[2 * this.vetorLista.length];
        int realocados = 0;
        for (int i = 0; i < this.vetorLista.length; i++) {
            if (this.vetorLista[i] != null)
                for (Noh n = vetorLista[i].getInicio(); n != null; n = n.getProx()) {
                    novo = rehash(n.getInfo().getChassi(), n.getInfo(), novo);
                    realocados++;
                }
        }
        System.out.println("Resize realizado, vetor aumentado de " + vetorLista.length + " para " + novo.length);
        System.out.println(realocados + " carros realocados.");
        this.vetorLista = novo;
    }

    private LDE[] rehash(int chave, Veiculo carro, LDE[] vetor) {
        int pos = (int) ((chave * 0.635789) % 1 * vetor.length);

        if (vetor[pos] == null) {
            vetor[pos] = new LDE();
            vetor[pos].insereInicio(carro);
        } else {
            for (Noh n = vetor[pos].getInicio(); n != null; n = n.getProx()) {
                if (n.getInfo().getChassi() == chave) {
                    return null;
                }
            }
            vetor[pos].insereInicio(carro);
        }
        return vetor;
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

    public void remove(int chave) {
        int hash = hash(chave);
        LDE veiculos_lista = this.vetorLista[hash];
        if (veiculos_lista != null) {
            Noh p = veiculos_lista.getInicio();
            while (p != null && p.getInfo().getChassi() != chave) { //busca
                p = p.getProx();
            }
            if (p == null) { // não achou, então não faz nada e retorna false
                System.out.println("Veiculo nao encontrado");
                return;
            }
            if (p == veiculos_lista.getInicio()) { //info está no início
                veiculos_lista.setInicio(p.getProx());
            if (p.getProx() != null) {
                p.getProx().setAnt(null);
                System.out.println("Veiculo com chassi " + p.getInfo().getChassi() + "encontrado e removido");
            }
            } else if (p.getProx() == null) { //info está no fim
                p.getAnt().setProx(null);
                veiculos_lista.setFim(p.getAnt());
                System.out.println("Veiculo com chassi " + p.getInfo().getChassi() + "encontrado e removido");
            } else { //info está no meio
                p.getAnt().setProx(p.getProx());
                p.getProx().setAnt(p.getAnt());
                System.out.println("Veiculo com chassi " + p.getInfo().getChassi() + "encontrado e removido");
            }
        }
    }
    public Noh busca(int chave){
        int hash = hash(chave);
        LDE veiculos_lista = this.vetorLista[hash];
        if (veiculos_lista != null) {
            Noh p = veiculos_lista.getInicio();
            while (p != null) { //busca
                p = p.getProx();
            
                if (p.getInfo().getChassi() == chave){
                    System.out.println("Veiculo com chassi " + p.getInfo().getChassi() + "encontrado");
                    return p;
                }
            }
            
        }
        else{
            System.out.println("Veiculo nao encontrado");
        }
        return null;
        }
}