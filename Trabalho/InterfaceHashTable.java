public interface InterfaceHashTable {
    public long universalHash(int chave); // OK
    public int hash(int chave); // OK
    public void resize(); // OK(
    // public abstract void rehash(int chave, Veiculo carro, Veiculo[] vetor); -- rehash integrado a resize())
    public void put(int chave, Veiculo carro); // OK
    // public void remove(int chave); - integrado junto a removeChassi()
    public Veiculo getVeiculo(int chave);  // OK
    public int buscaFord(); // OK
    public void removeChassi(); // OK
    public boolean contains(int chave); // OK
    // public Veiculo getVetor(int i); - nao pode ser usado na lde
    public int getSize(); // OK
    public int getBusy(); // OK
    public int getHash(int chave); // OK
}
