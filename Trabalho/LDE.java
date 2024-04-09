public class LDE implements Lista {// TAD Lista duplamente encadeada
    private Noh inicio;
    private Noh fim;

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

    }

    public boolean remove(Veiculo info) {
        Noh p = inicio;
        while (p != null && p.getInfo() != info) // busca
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

 
}
