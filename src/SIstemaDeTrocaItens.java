import javax.mail.MessagingException;
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
            
            System.out.println("========= SISTEMA DE INVENTÁRIO =========");
            System.out.println("0: Sair do programa");
            System.out.println("1: Cadastrar um novo Jogador");
            System.out.println("2: Fazer Login");
            System.out.println("3: Listar Itens do Jogador Logado");
            System.out.println("4: Pesquisar Item pelo Nome");
            System.out.println("5: Remover Item do Jogador Logado");
            System.out.println("6: Listar Itens de Outros Jogadores");
            System.out.println("7: Cadastrar Item para o Jogador Logado");
            System.out.println("8: Criar Proposta de Troca");
            System.out.println("9: Checar Propostas Recebidas");
            System.out.println("10: Exibir Estatísticas do Sistema");
            System.out.println("11: Adicionar Item aos Favoritos");
            System.out.println("12: Remover Item dos Favoritos");
            System.out.println("13: Listar Itens Favoritos");
            System.out.println("=========================================");


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
                    removerItemJogadorLogado(scanner);
                    break;
                case 6:
                    listarItensOutrosJogadoresPorPreco();
                    break;
                case 7:
                    cadastrarItem(scanner);
                    break;
                case 8:
                    criarPropostaDeTroca(scanner);
                    break;
                case 9:
                    checarPropostasRecebidas(scanner);
                    break;
                case 10:
                    exibirEstatisticasGerais();
                    break;
                case 11:
                    adicionarItemFavorito(scanner);
                    break;
                case 12:
                    removerItemFavorito(scanner);
                    break;
                case 13:
                    listarItensFavoritos();
                    break;
                default:
                    System.out.println("ERRO: Opção inválida.");
                    break;
            }
        }
    }

    private static void cadastrarItem(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("Nenhum jogador está logado no momento");
            return;
        }
        Item novoItem = new Item(null, null, null, 0);
        System.out.println("Insira o nome do item:");
        novoItem.setNome(scanner.nextLine());
        System.out.println("Insira uma descrição:");
        novoItem.setDescricao(scanner.nextLine());
        System.out.println("Insira o tipo do item:");
        novoItem.setTipo(scanner.nextLine());
        System.out.println("Insira o valor do item:");
        novoItem.setValor(scanner.nextDouble());
        jogadorLogado.addItem(novoItem);
        System.out.println("Item Cadastrado!"+"\n");
    }

    private static void pesquisaItem(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("ERRO: Nenhum jogador está logado no momento.");
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
            System.out.println("ERRO: Nenhum item encontrado com as informações fornecidas.");
        }
    }

    private static void listarItensOutrosJogadoresPorPreco() {
        if (jogadorLogado == null) {
            System.out.println("ERRO: Nenhum jogador está logado no momento.");
            return;
        }

        boolean achouItens = false;

        for (Jogador jogador : jogadores) {
            if (!jogador.equals(jogadorLogado)) {
                ArrayList<Item> itens = jogador.getItens();

                if (itens.isEmpty()) {
                    continue;
                }

                itens.sort(new ComparadorItemPreco());

                System.out.println("Itens do jogador " + jogador.getNome() + " ordenados por preço: ");
                for (Item item : itens) {
                    System.out.println(item + "\n");
                }

                achouItens = true;
            }
        }
        if (!achouItens) {
            System.out.println("ERRO: Nenhum item encontrado com outros jogadores.");
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
            System.out.println("ERRO: O PIN deve possuir 6 dígitos"+"\n");
            return;
        }
        Jogador novoJogador = new Jogador(email, nome, pin);
        jogadores.add(novoJogador);
        System.out.println("O jogador foi cadastrado com sucesso!"+"\n");
    }

    private static void login(Scanner scanner) {
        System.out.println("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.println("Digite seu PIN: ");
        String pin = scanner.nextLine();

        for (Jogador j : jogadores) {
            if (j.getEmail().equals(email) && j.getPin().equals(pin)) {
                jogadorLogado = j;
                System.out.println("O Login foi realizado com sucesso!"+"\n");
                return;
            }
        }
        System.out.println("ERRO: O Email ou PIN digitados estão incorretos."+"\n");
    }

    private static void listarItensJogadorLogado() {
        if (jogadorLogado == null) {
            System.out.println("ERRO: Nenhum jogador está logado no momento."+"\n");
            return;
        }
        jogadorLogado.listarItensOrdenados();
    }

    private static void criarPropostaDeTroca(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("ERRO: Nenhum jogador está logado no momento."+"\n");
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
            System.out.println("ERRO: Jogador não encontrado."+"\n");
            return;
        }

        System.out.println("\n"+"Escolha um item seu para oferecer na troca");
        listarItensJogadorLogado();
        System.out.println("Digite o ID do item: ");

        int idItemProposto = scanner.nextInt();
        Item itemProposto = null;

        for (Item item : jogadorLogado.getItens()) {
            if (item.getID() == (idItemProposto)) {
                itemProposto = item;
                break;
            }
        }

        if (itemProposto == null) {
            System.out.println("ERRO: Item não encontrado."+"\n");
            return;
        }

        System.out.println("=================================="+"\nEscolha o item que deseja receber");
        for (Item item : jogadorRecebe.getItens()) {
            System.out.println(item);
        }
        System.out.println("Digite o ID do item: ");
        int idItemRecebido = scanner.nextInt();
        Item itemRecebido = null;

        for (Item item : jogadorRecebe.getItens()) {
            if (item.getID() == (idItemRecebido)) {
                itemRecebido = item;
                break;
            }
        }

        if (itemRecebido == null) {
            System.out.println("ERRO: Item não encontrado."+"\n");
            return;
        }

        System.out.println("\n"+"Aguarde um momento...");

        PropostaDeTroca proposta = new PropostaDeTroca(jogadorLogado, jogadorRecebe, itemProposto, itemRecebido);
        jogadorRecebe.addPropostaRecebida(proposta);

        EnviarEmail.enviarEmail(jogadorLogado, jogadorRecebe, itemProposto, itemRecebido);

        System.out.println("Proposta de troca enviada!"+"\n");
    }

    private static void checarPropostasRecebidas(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("ERRO: Nenhum jogador está logado no momento."+"\n");
            return;
        }

        if (jogadorLogado.getPropostasRecebidas().isEmpty()) {
            System.out.println("ERRO: Você não tem propostas de troca no momento."+"\n");
            return;
        }

        System.out.println("Propostas recebidas: ");
        int index = 1;
        for (PropostaDeTroca proposta : jogadorLogado.getPropostasRecebidas()) {
            System.out.println(index + " | " + proposta);
            index++;
        }

        System.out.println("\n"+"Digite o número da proposta que deseja aceitar ou recusar, ou 0 para voltar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= jogadorLogado.getPropostasRecebidas().size()) {
            PropostaDeTroca proposta = jogadorLogado.getPropostasRecebidas().get(escolha - 1);
            System.out.println("Digite 1 para aceitar ou 2 para recusar: ");
            int decisao = scanner.nextInt();
            scanner.nextLine();

            if (decisao == 1) {
                proposta.aceitar();
                System.out.println("Você aceitou a proposta."+"\n");
                realizarTroca(proposta);
            } else {
                proposta.recusar();
                System.out.println("Você recusou a proposta."+"\n");
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
                if (status.equals("Aguardando")) {
                    propostasAguardando++;
                } else if (status.equals("Aceita")) {
                    propostasAceitas++;
                } else if (status.equals("Recusada")) {
                    propostasDeclinadas++;
                }
            }
        }
        System.out.println("Estatísticas Gerais do Sistema");
        System.out.println("Total de usuários: " + totalUsuarios);
        System.out.println("Total de itens: " + totalItens + " (Soma total dos preços: R$ " + somaPrecoItens + ")");
        System.out.println("Propostas aceitas: " + propostasAceitas);
        System.out.println("Propostas declinadas: " + propostasDeclinadas);
        System.out.println("Propostas aguardando resposta: " + propostasAguardando + "\n");
    }

    private static void realizarTroca(PropostaDeTroca proposta) {
        if (proposta.isAceita()) {
            proposta.getJogadorPropoe().getItens().remove(proposta.getItemProposto());
            proposta.getJogadorRecebe().getItens().remove(proposta.getItemRecebido());

            proposta.getJogadorPropoe().addItem(proposta.getItemRecebido());
            proposta.getJogadorRecebe().addItem(proposta.getItemProposto());

            System.out.println("Troca realizada com sucesso!"+"\n");
        } else {
            System.out.println("A troca não foi aceita."+"\n");
        }
    }

    private static void adicionarItemFavorito(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("ERRO: Nenhum jogador está logado no momento."+"\n");
            return;
        }

        System.out.println("Digite o nome do jogador dono do item: ");
        String nomeJogador = scanner.nextLine();
        Jogador jogadorDono = null;

        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equalsIgnoreCase(nomeJogador)) {
                jogadorDono = jogador;
                break;
            }
        }

        if (jogadorDono == null) {
            System.out.println("ERRO: Jogador não encontrado."+"\n");
            return;
        }

        System.out.println("Digite o nome do item: ");
        String nomeItem = scanner.nextLine();
        Item itemFavorito = null;

        for (Item item : jogadorDono.getItens()) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                itemFavorito = item;
                break;
            }
        }

        if (itemFavorito == null) {
            System.out.println("ERRO: Item não encontrado."+"\n");
            return;
        }

        jogadorLogado.addFavorito(itemFavorito);
    }

    private static void removerItemFavorito(Scanner scanner) {
        if (jogadorLogado == null) {
            System.out.println("ERRO: Nenhum jogador está logado no momento."+"\n");
            return;
        }

        System.out.println("Digite o nome do item: ");
        String nomeItem = scanner.nextLine();
        Item itemFavorito = null;

        for (Item item : jogadorLogado.getFavoritos()) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                itemFavorito = item;
                break;
            }
        }

        if (itemFavorito == null) {
            System.out.println("ERRO: Item não encontrado nos favoritos."+"\n");
            return;
        }

        jogadorLogado.removerFavorito(itemFavorito);
    }

    private static void listarItensFavoritos() {
        if (jogadorLogado == null) {
            System.out.println("ERRO: Nenhum jogador está logado no momento."+"\n");
            return;
        }

        if (jogadorLogado.getFavoritos().isEmpty()) {
            System.out.println("ERRO: Nenhum item encontrado para o jogador informado."+"\n"); 
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
        jogador2.addItem(new Item("Foice Maligna", "Foice venenosa", "Arma", 800.0));
        jogador2.addItem(new Item("Elixir de Vida", "Poção para curar vida", "Consumível", 75.0));
        jogadores.add(jogador2);

        Jogador jogador3 = new Jogador("jogador3@gmail.com", "Kylian Mbappe", "555666");
        jogador3.addItem(new Item("Nagakiba", "Katana Grande", "Arma", 500.0));
        jogador3.addItem(new Item("Elixir de Escudo", "Poção para curar escudo", "Consumível", 75.0));
        jogadores.add(jogador3);
    }

    private static void removerItemJogadorLogado(Scanner scanner){
        if (jogadorLogado == null) {
            System.out.println("ERRO: Nenhum jogador está logado no momento."+"\n");
            return;
        }

        System.out.println("Escolha um item para remover"+"\n");
        jogadorLogado.listarItensOrdenados();
        if (jogadorLogado.getItens().isEmpty()) {
            return; }        
        System.out.println("Digite o ID do item: ");
        int idItemARemover = scanner.nextInt();
        Item itemARemover = null;


        for (Item item : jogadorLogado.getItens()) {
            if (item.getID() == (idItemARemover)) {
                itemARemover = item;
                break;
            }
        }

        jogadorLogado.removerItem(itemARemover);
        System.out.println("Item removido com sucesso."+"\n");

        if (itemARemover == null) {
            System.out.println("ERRO: Item não encontrado."+"\n");;
        }

        return;
    }

}

