import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Jogador {
    private String email;
    private String nomeCompleto;
    private String pin;
    private ArrayList<Item> itens;
    private ArrayList<Item> favoritos = new ArrayList<>();
    private ArrayList<PropostaDeTroca> propostasRecebidas = new ArrayList<>();

    public Jogador(String email, String nomeCompleto, String pin) {
        this.email = email;
        this.nomeCompleto = nomeCompleto;
        this.pin = pin;
        this.itens = new ArrayList<>();

    }

    public String getEmail() {
        return email;
    }

    public String getPin() {
        return pin;
    }

    public String getNome() {
        return nomeCompleto;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void addItem(Item item) {
        itens.add(item);
    }

    public void removerItem(Item item) {
        itens.remove(item);
    }

    public void listarItensOrdenados() {
        if (itens.isEmpty()) {
            System.out.println("ERRO: Nenhum item encontrado para o jogador informado.");
            return;
        }
        Collections.sort(itens, new ComparadorItemPreco());
        System.out.println("Itens do jogador " + nomeCompleto + ":");
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            System.out.println(item);
        }

    }

    public ArrayList<PropostaDeTroca> getPropostasRecebidas() {
        return propostasRecebidas;
    }

    public void addPropostaRecebida(PropostaDeTroca proposta) {
        propostasRecebidas.add(proposta);
    }

    public ArrayList<Item> getFavoritos() {
        return favoritos;
    }

    public void addFavorito(Item item) {
        if (!favoritos.contains(item)) {
            favoritos.add(item);
            System.out.println("Item adicionado aos favoritos.");
        } else {
            System.out.println("Item já está nos favoritos.");
        }
    }

    public void removerFavorito(Item item) {
        if (favoritos.remove(item)) {
            System.out.println("Item removido dos favoritos.");
        } else {
            System.out.println("ERRO: Item não encontrado nos favoritos.");
        }
    }

    public void listarFavoritos() {
        System.out.println("Itens favoritos do jogador " + nomeCompleto + ":");
        for (Item item : favoritos) {
            System.out.println(item);
        }
    }
}