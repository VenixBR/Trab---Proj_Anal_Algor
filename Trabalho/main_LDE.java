public class main_LDE {

    public static void main(String[] args) {
        Veiculo[] vetorcriacao = new Veiculo[10];
        l_hashTable mapa = new l_hashTable(10);
        long t0 = System.nanoTime();
        for (int i = 0; i < vetorcriacao.length; i++) {
            vetorcriacao[i] = new Veiculo();
            mapa.put(vetorcriacao[i].getChassi(), vetorcriacao[i]);
        }
        long Tempo0 = System.nanoTime() - t0;
        System.out.println("O tempo foi de " + Tempo0 + " ns para inserir todos os veiculos");

        if (mapa != null) {
            long t1 = System.nanoTime();
            mapa.getAll();
            long Tempo1 = System.nanoTime() - t1;
            System.out.println("O tempo foi de " + Tempo0 + " ns para inserir todos os veiculos");
            System.out.println("O tempo foi de " + Tempo1 + " ns para mostrar todos os veiculos da tabela");
            long t2 = System.nanoTime();
            System.out.println("Existem " + mapa.buscaFord() + " veiculos da Ford Cadastrados");
            long Tempo2 = System.nanoTime() - t2;
            System.out.println("O tempo foi de " + Tempo2 + " ns para achar todos os veiculos da Ford");

            long t3 = System.nanoTime();
            mapa.removeChassi();
            long Tempo3 = System.nanoTime() - t3;
            System.out.println("O tempo foi de " + Tempo3 + " ns para remover os veiculos");

        }
    }
}