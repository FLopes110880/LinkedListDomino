import java.util.Scanner;

class main {

    public static void main(String []args) {
        Domino dom = new Domino();
        Scanner sc = new Scanner(System.in);
        System.out.println("Selecione as dimensoes do tabuleiro: ");
        System.out.print("Comprimento: ");
        dom.jogo.setComprimento(sc.nextInt());
        System.out.print("Largura: ");
        dom.jogo.setLargura(sc.nextInt());
        System.out.println();
        // put piece 6-6 in board
        int i = dom.checkFirstPlay();
        System.out.println("Primeiro jogador: " + i);
        int j = dom.jogadores[i].check66();
        dom.jogo.addPiece(dom.jogadores[i].getPecas()[j], null);
        System.out.println("Peca 6-6 jogada." + "\n");

        dom.jogo.printTabuleiro();

        int noplay = 0;
        do {
            i++;
            i %= 4;

            if (dom.jogo.searchifPossiblePlay(dom.jogadores[i])) {

                noplay = 0;
                System.out.println("Jogador: " + i);
                dom.jogadores[i].showpieces();
                boolean added;
                do {
                    Peca []peca = dom.jogadores[i].jogada();
                    added = dom.jogo.addPiece(peca[0], dom.jogo.findCantos(peca[1]));
                } while (!added && dom.jogo.getCantos().size() != 0);

                dom.jogo.printTabuleiro();
                System.out.println();
            }
            else {
                System.out.println("Jogador: " + i + " nao tem jogada possivel");
                noplay++;
            }

            if (noplay == 4) {
                System.out.println("Impossivel de jogar, nao existem jogadas possiveis.");
                break;
            }

        } while (!dom.jogadores[i].checkWinner() && dom.jogo.getCantos().size() != 0);

        Jogador []classificacao = dom.Classificacao();

        for (int k = 0; k < classificacao.length; k++) {
            System.out.println("Jogador: " + dom.jogadores[k].getPlayerid() + " Posicao: " + k + " Pontos: " + dom.jogadores[k].getPontos());
        }
    }
}