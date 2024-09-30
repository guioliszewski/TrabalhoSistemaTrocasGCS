public class Item {
    private int id;
    private String nome;
    private String descricao;
    private String tipo;
    private double valor;
    private static int countID = 1;

    public Item(String nome, String descricao, String tipo, double valor){
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.id = countID++;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "Nome: " + nome +
                "Descricao: " + descricao +
                "Tipo: " + tipo +
                "Valor: R$ " + valor;
    }
}
