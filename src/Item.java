public class Item {
    private int id;
    private String nome;
    private String descricao;
    private String tipo;
    private double valor;
    private static int countID = 1;

    public Item(String nome, String descricao, String tipo, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.id = countID++;
    }

    public double getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Descricao: " + descricao + "\n" +
                "Tipo: " + tipo + "\n" +
                "Valor: R$ " + valor + "\n";
    }

}
