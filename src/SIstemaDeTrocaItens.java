import java.util.ArrayList;
import java.util.Scanner;

public class SIstemaDeTrocaItens {

    private static Jogador jogadorLogado = null;
    private static ArrayList<Jogador> jogadores = new ArrayList<>();

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        preencherDados();



    }



    private static void cadastrarJogador(Scanner scanner){
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Nome completo do jogador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o PIN (6 dígitos):");
        String pin = scanner.nextLine();

        if(pin.length()!=6){
            System.out.println("ERRO. O PIN deve possuir 6 dígitos");
            return;
        }
        Jogador novoJogador = new Jogador(email,nome,pin);
        jogadores.add(novoJogador);
        System.out.println("O jogador foi cadastrado com sucesso!");
    }
    private static void login(Scanner scanner){
        System.out.println("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.println("Digite seu PIN: ");
        String pin = scanner.nextLine();

        for(Jogador j : jogadores){
            if(j.getEmail().equals(email) && j.getPin().equals(pin)){
                jogadorLogado = j;
                System.out.println("O Login foi realizado com sucesso!");
                return;
            }
        }
        System.out.println("O Email ou PIN digitados estão incorretos.");
    }
    private static void listarItensJogadorLogado(){
        if(jogadorLogado==null){
            System.out.println("Nenhum jogador está logado no momento.");
            return;
        }
        jogadorLogado.listarItensOrdenados();
    }
    private static void preencherDados(){
        Jogador jogador1 = new Jogador("jogador1@gmail.com","Cristiano Ronaldo","111222");
        jogador1.addItem(new Item("Espada da Lua Sombria","Espada Grande","Arma",700.0));
        jogador1.addItem(new Item("Cajado Pedrilhante", "Cajado Mágico","Arma Projétil",300.0));
        jogadores.add(jogador1);

        Jogador jogador2 = new Jogador("jogador2@gmail.com","Lionel Messi","333444");
        jogador2.addItem(new Item("Lâmina Blasfêmica", "Espada de Fogo","Arma",800.0));
        jogador2.addItem(new Item("Poção de Vida","Poção para curar vida","Consumível",75.0));
        jogadores.add(jogador2);

        Jogador jogador3 = new Jogador("jogador3@gmail.com","Kylian Mbappé","444555");
        jogador3.addItem(new Item("Nagakiba","Katana Grande", "Arma", 500.0));
        jogador3.addItem(new Item("Poção de Escudo","Poção para curar escudo","Consumível",75.0));
        jogadores.add(jogador3);

    }

}
