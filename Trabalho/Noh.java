public class Noh{
    private Veiculo info; // este exemplo armazena números inteiros
    private Noh ant; // destaque para o atributo ant de anterior
    private Noh prox;

    public Noh(Veiculo info) {
        this.info = info;
        this.ant = null;
        this.prox = null;
    }

public Veiculo getInfo() { 
    return info;
 }

public Noh getProx() { 
    return prox;
 }

public void setProx(Noh n) {
    this.prox = n;
}

public void setInfo(Veiculo novoVeiculo) {
    this.info = novoVeiculo;
}

public Noh getAnt() { 
    return ant;
 }

public void setAnt(Noh n) { this.ant = n; }
}
