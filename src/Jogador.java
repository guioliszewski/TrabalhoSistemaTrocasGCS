import java.util.ArrayList;

public class Jogador {
    private String email;
    private String nomeCompleto;
    private String pin;
    private ArrayList<Item> itens;
    private ArrayList<PropostaDeTroca> propostasRecebidas = new ArrayList<>();

    public Jogador(String email, String nomeCompleto, String pin){
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

    public String getNome(){
        return nomeCompleto;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }
    public void addItem(Item item){
        itens.add(item);
    }
    public void removerItem(Item item){
        itens.remove(item);
    }
    public void listarItensOrdenados(){
        for(int i = 0;i<itens.size();i++){
            for(int j = 0;j< itens.size();j++){
                if(itens.get(j).getNome().compareTo(itens.get(j+1).getNome())>0){
                    Item aux = itens.get(j);
                    itens.set(j,itens.get(j+1));
                    itens.set(j+1,aux);
                }
            }
        }
        System.out.println("Itens do jogador " + nomeCompleto + ":");
        for(int i=0;i< itens.size();i++){
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

}
