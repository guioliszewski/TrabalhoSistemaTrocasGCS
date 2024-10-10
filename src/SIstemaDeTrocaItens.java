import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SIstemaDeTrocaItens {

    private static Jogador jogadorLogado = null;
    private static ArrayList<Jogador> jogadores = new ArrayList<>();

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        preencherDados();
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("0: Sair do programa");
            System.out.println("1: Cadastrar um novo Jogador");
            System.out.println("2: Fazer Login");
            System.out.println("3: Listar Itens do Jogador Logado");
            System.out.println("4: Pesquisar Item");
            System.out.println("5: Listar Itens de Jogadores por Preço");
            System.out.println("6: Criar proposta de troca");
            System.out.println("7: Checar propostas recebidas");
            System.out.println("8: Exibe as Estatísticas Gerais");
            System.out.println("9: Adicionar item aos favoritos");
            System.out.println("10: Remover item dos favoritos");
            System.out.println("11: Listar itens favoritos");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println("Finalizando o programa...");
                    return;
                case 1:
                    cadastrarJogador(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    listarItensJogadorLogado();
                    break;
                case 4:
                    pesquisaItem(scanner);
                    break;
                case 5:
                    listarItensOutrosJogadoresPorPreco();
                    break;
                case 6:
                    criarPropostaDeTroca(scanner);
                    break;
                case 7:
                    checarPropostasRecebidas(scanner);
                    break;
                case 8:
                    exibirEstatisticasGerais();
                    break;
                case 9:
                    adicionarItemFavorito(scanner);
                    break;
                case 10:
                    removerItemFavorito(scanner);
                    break;
                case 11:
                    listarItensFavoritos();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void pesquisaItem(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("Nenhum jogador está logado no momento.");
            return;
        }

        System.out.println("Insira parte do nome, descrição ou o tipo do item: ");
        String termoBusca = scanner.nextLine().toLowerCase();

        boolean itemEncontrado = false;
        for (Item item : jogadorLogado.getItens()) {
            if (item.getNome().toLowerCase().contains(termoBusca)
                    || item.getDescricao().toLowerCase().contains(termoBusca)
                    || item.getTipo().toLowerCase().contains(termoBusca)) {
                System.out.println("Item encontrado: " + item);
                itemEncontrado = true;
            }
        }

        if (!itemEncontrado) {
            System.out.println("Nenhum item encontrado com as informações fornecidas.");
        }
    }

    private static void listarItensOutrosJogadoresPorPreco() {
        if (jogadorLogado == null) {
            System.out.println("Nenhum jogador está logado no momento.");
            return;
        }

        for (Jogador jogador : jogadores) {
            if (!jogador.equals(jogadorLogado)) {
                ArrayList<Item> itens = jogador.getItens();

                itens.sort((item1, item2) -> Double.compare(item1.getValor(), item2.getValor()));

                System.out.println("Itens do jogador " + jogador.getNome() + " ordenados por preço:");
                for (Item item : itens) {
                    System.out.println(item);
                }
            }
        }
    }

    private static void cadastrarJogador(Scanner scanner) {
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Nome completo do jogador: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o PIN (6 dígitos):");
        String pin = scanner.nextLine();

        if (pin.length() != 6) {
            System.out.println("ERRO. O PIN deve possuir 6 dígitos");
            return;
        }
        Jogador novoJogador = new Jogador(email, nome, pin);
        jogadores.add(novoJogador);
        System.out.println("O jogador foi cadastrado com sucesso!");
    }

    private static void login(Scanner scanner) {
        System.out.println("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.println("Digite seu PIN: ");
        String pin = scanner.nextLine();

        for (Jogador j : jogadores) {
            if (j.getEmail().equals(email) && j.getPin().equals(pin)) {
                jogadorLogado = j;
                System.out.println("O Login foi realizado com sucesso!");
                return;
            }
        }
        System.out.println("O Email ou PIN digitados estão incorretos.");
    }

    private static void listarItensJogadorLogado() {
        if (jogadorLogado == null) {
            System.out.println("Nenhum jogador está logado no momento.");
            return;
        }
        jogadorLogado.listarItensOrdenados();
    }

    private static void criarPropostaDeTroca(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("Nenhum jogador está logado no momento.");
            return;
        }

        System.out.println("Digite o nome do jogador com quem deseja propor a troca: ");
        String nomeJogadorRecebe = scanner.nextLine();
        Jogador jogadorRecebe = null;

        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equalsIgnoreCase(nomeJogadorRecebe)) {
                jogadorRecebe = jogador;
                break;
            }
        }

        if (jogadorRecebe == null) {
            System.out.println("Jogador não encontrado.");
            return;
        }

        System.out.println("Escolha um item seu para oferecer na troca:");
        listarItensJogadorLogado();
        System.out.println("Digite o nome do item:");
        String nomeItemProposto = scanner.nextLine();
        Item itemProposto = null;

        for (Item item : jogadorLogado.getItens()) {
            if (item.getNome().equalsIgnoreCase(nomeItemProposto)) {
                itemProposto = item;
                break;
            }
        }

        if (itemProposto == null) {
            System.out.println("Item não encontrado.");
            return;
        }

        System.out.println("Escolha o item que deseja receber:");
        for (Item item : jogadorRecebe.getItens()) {
            System.out.println(item);
        }
        System.out.println("Digite o nome do item:");
        String nomeItemRecebido = scanner.nextLine();
        Item itemRecebido = null;

        for (Item item : jogadorRecebe.getItens()) {
            if (item.getNome().equalsIgnoreCase(nomeItemRecebido)) {
                itemRecebido = item;
                break;
            }
        }

        if (itemRecebido == null) {
            System.out.println("Item não encontrado.");
            return;
        }

        PropostaDeTroca proposta = new PropostaDeTroca(jogadorLogado, jogadorRecebe, itemProposto, itemRecebido);
        jogadorRecebe.addPropostaRecebida(proposta);

        System.out.println("Proposta de troca enviada.");
    }

    private static void checarPropostasRecebidas(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("Nenhum jogador está logado no momento.");
            return;
        }

        if (jogadorLogado.getPropostasRecebidas().isEmpty()) {
            System.out.println("Você não tem propostas de troca no momento.");
            return;
        }

        System.out.println("Propostas recebidas:");
        int index = 1;
        for (PropostaDeTroca proposta : jogadorLogado.getPropostasRecebidas()) {
            System.out.println(index + ": " + proposta);
            index++;
        }

        System.out.println("Digite o número da proposta que deseja aceitar ou recusar, ou 0 para voltar:");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= jogadorLogado.getPropostasRecebidas().size()) {
            PropostaDeTroca proposta = jogadorLogado.getPropostasRecebidas().get(escolha - 1);
            System.out.println("Digite 1 para aceitar ou 2 para recusar:");
            int decisao = scanner.nextInt();
            scanner.nextLine();

            if (decisao == 1) {
                proposta.aceitar();
                System.out.println("Você aceitou a proposta.");
                realizarTroca(proposta);
            } else {
                proposta.recusar();
                System.out.println("Você recusou a proposta.");
            }
        }
    }

    public static void exibirEstatisticasGerais() {
        int totalUsuarios = jogadores.size();
        int totalItens = 0;
        double somaPrecoItens = 0.0;
        int propostasAceitas = 0;
        int propostasDeclinadas = 0;
        int propostasAguardando = 0;

        for (Jogador jogador : jogadores) {
            List<Item> itens = jogador.getItens();
            totalItens += itens.size();
            for (Item item : itens) {
                somaPrecoItens += item.getValor();
            }
            List<PropostaDeTroca> propostas = jogador.getPropostasRecebidas();
            for (PropostaDeTroca proposta : propostas) {
                String status = proposta.getStatus();
                if (status.equals("aguardando")) {
                    propostasAguardando++;
                } else if (status.equals("aceita")) {
                    propostasAceitas++;
                } else if (status.equals("recusada")) {
                    propostasDeclinadas++;
                }
            }
        }
        System.out.println("Estatísticas Gerais do Sistema:");
        System.out.println("Total de usuários: " + totalUsuarios);
        System.out.println("Total de itens: " + totalItens + " (Soma total dos preços: R$ " + somaPrecoItens + ")");
        System.out.println("Propostas aceitas: " + propostasAceitas);
        System.out.println("Propostas declinadas: " + propostasDeclinadas);
        System.out.println("Propostas aguardando resposta: " + propostasAguardando);
    }

    private static void realizarTroca(PropostaDeTroca proposta) {
        if (proposta.isAceita()) {
            proposta.getJogadorPropoe().getItens().remove(proposta.getItemProposto());
            proposta.getJogadorRecebe().getItens().remove(proposta.getItemRecebido());

            proposta.getJogadorPropoe().addItem(proposta.getItemRecebido());
            proposta.getJogadorRecebe().addItem(proposta.getItemProposto());

            System.out.println("Troca realizada com sucesso!");
        } else {
            System.out.println("A troca não foi aceita.");
        }
    }

    private static void adicionarItemFavorito(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("Nenhum jogador está logado no momento.");
            return;
        }

        System.out.println("Digite o nome do jogador dono do item:");
        String nomeJogador = scanner.nextLine();
        Jogador jogadorDono = null;

        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equalsIgnoreCase(nomeJogador)) {
                jogadorDono = jogador;
                break;
            }
        }

        if (jogadorDono == null) {
            System.out.println("Jogador não encontrado.");
            return;
        }

        System.out.println("Digite o nome do item:");
        String nomeItem = scanner.nextLine();
        Item itemFavorito = null;

        for (Item item : jogadorDono.getItens()) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                itemFavorito = item;
                break;
            }
        }

        if (itemFavorito == null) {
            System.out.println("Item não encontrado.");
            return;
        }

        jogadorLogado.addFavorito(itemFavorito);
    }

    private static void removerItemFavorito(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("Nenhum jogador está logado no momento.");
            return;
        }

        System.out.println("Digite o nome do item:");
        String nomeItem = scanner.nextLine();
        Item itemFavorito = null;

        for (Item item : jogadorLogado.getFavoritos()) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                itemFavorito = item;
                break;
            }
        }

        if (itemFavorito == null) {
            System.out.println("Item não encontrado nos favoritos.");
            return;
        }

        jogadorLogado.removerFavorito(itemFavorito);
    }

    private static void listarItensFavoritos() {
        if (jogadorLogado == null) {
            System.out.println("Nenhum jogador está logado no momento.");
            return;
        }

        jogadorLogado.listarFavoritos();
    }

    private static void preencherDados() {
        Jogador jogador1 = new Jogador("jogador1@gmail.com", "Cristiano Ronaldo", "111222");
        jogador1.addItem(new Item("Espada da Lua Sombria", "Espada Grande", "Arma", 700.0));
        jogador1.addItem(new Item("Cajado Pedrilhante", "Cajado Mágico", "Arma Projétil", 300.0));
        jogadores.add(jogador1);

        Jogador jogador2 = new Jogador("jogador2@gmail.com", "Lionel Messi", "333444");
        jogador2.addItem(new Item("Lâmina Blasfêmica", "Espada de Fogo", "Arma", 800.0));
        jogador2.addItem(new Item("Poção de Vida", "Poção para curar vida", "Consumível", 75.0));
        jogadores.add(jogador2);

        Jogador jogador3 = new Jogador("jogador3@gmail.com", "Kylian Mbappé", "444555");
        jogador3.addItem(new Item("Nagakiba", "Katana Grande", "Arma", 500.0));
        jogador3.addItem(new Item("Poção de Escudo", "Poção para curar escudo", "Consumível", 75.0));
        jogadores.add(jogador3);
    }

}
