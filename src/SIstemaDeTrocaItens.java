import java.util.ArrayList;
import java.util.Scanner;

public class SIstemaDeTrocaItens {

    private Jogador jogador = null;
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public void executar(){

    }
    private void preencherDados(){
        Jogador jogador1 = new Jogador("jogador1@gmail.com","Cristiano Ronaldo",111222);
        jogador1.addItem(new Item("Espada da Lua Sombria","Espada Grande","Arma",700.0));
        jogador1.addItem(new Item("Cajado Pedrilhante", "Cajado Mágico","Arma Projétil",300.0));
        jogadores.add(jogador1);

        Jogador jogador2 = new Jogador("jogador2@gmail.com","Lionel Messi",333444);
        jogador2.addItem(new Item("Lâmina Blasfêmica", "Espada de Fogo","Arma",800.0));
        jogador2.addItem(new Item("Poção de Vida","Poção para curar vida","Consumível",75.0));
        jogadores.add(jogador2);

        Jogador jogador3 = new Jogador("jogador3@gmail.com","Kylian Mbappé",444555);
        jogador3.addItem(new Item("Nagakiba","Katana Grande", "Arma", 500.0));
        jogador3.addItem(new Item("Poção de Escudo","Poção para curar escudo","Consumível",75.0));
        jogadores.add(jogador3);

    }

}
