public class LDE implements Lista {// TAD Lista duplamente encadeada
    private Noh inicio;
    private Noh fim;
    public int tamanho = 0;

    public LDE() {
        this.inicio = null;
        this.fim = null;
    }

    public void insereInicio(Veiculo info) {
        Noh novo = new Noh(info);
        if (inicio == null) {
            inicio = novo;
            fim = novo;
        }

        else {
            novo.setProx(inicio);
            inicio.setAnt(novo);
            inicio = novo;
        }
        tamanho++;
    }

    public void insereFim(Veiculo info) {
        Noh novo = new Noh(info);
        if (fim == null) {
            inicio = novo;
            fim = novo;
        } else {
            novo.setAnt(fim);
            fim.setProx(novo);
            fim = novo;
        }
        tamanho++;
    }

    public boolean remove(Veiculo i) {
        Noh p = inicio;
        while (p != null && p.getInfo() != i) // busca
            p = p.getProx();
        if (p == null) // não achou, então não faz nada e retorna false
            return false;
        if (p == inicio) { // info estah no inicio
            inicio = p.getProx();
            if (inicio != null)
                inicio.setAnt(null);
            else
                fim = null;
        } else if (p.getProx() == null) { // info estah no fim
            p.getAnt().setProx(null);
            fim = p.getAnt();
        } else { // info estah no meio
            p.getAnt().setProx(p.getProx());
            p.getProx().setAnt(p.getAnt());
        }
        tamanho--;
        return true;
    }
    public Noh getInicio() {
        
        return inicio;
    }
    public Noh getFim() {
        
        return fim;
    }
    public void setInicio(Noh n){
        this.inicio = n;
    }
    public void setFim(Noh n){
        this.fim = n;
    }
    public int getTamanho(){
        return tamanho;
    }

 
}
