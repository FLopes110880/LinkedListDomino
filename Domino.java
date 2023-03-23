import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Catarina Samina 57637, Francisco Lopes 71391 e Beatriz Correia 72479
 *
 */

class Domino {

    protected final Peca []pecas;
    protected final Jogador []jogadores;
    protected final LinhaJogo jogo;

    /**
     * Construtor da classe domino
     */
    public Domino() {
        this.pecas = new Peca[28];

        this.jogo = new LinhaJogo(pecas);

        this.jogadores = new Jogador[4];

        createPieces();
        shuffle();
        givepieces();
    }

    /**
     * Onde sao criadas as pecas
     */
    private void createPieces() {
        int result = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i+1; j++) {
                if (i == j) {
                    this.pecas[result++] = new Dupla(i,j);
                }
                else {
                    this.pecas[result++] = new Peca(i,j);
                }
            }
        }
    }

    /**
     * Onde as pecas sao baralhadas aleatoriamente
     */
    private void shuffle() {
        Random rand = new Random();

        for (int i = 0; i < pecas.length; i++) {
            int random = rand.nextInt(28);
            Peca peca = pecas[random];
            pecas[random] = pecas[i];
            pecas[i] = peca;
        }
    }

    /**
     * Onde as pecas sao distribuidas pelos jogadores 
     */
    private void givepieces() {
    	Peca[] splitpecas = new Peca[7];
        System.arraycopy(pecas,0,splitpecas, 0, 7);
        jogadores[0] = new Manual(jogo, splitpecas,0);
        splitpecas = new Peca[7];
        System.arraycopy(pecas, 7, splitpecas, 0, 7);
        jogadores[1] = new Bot2(jogo, splitpecas, 1);
        splitpecas = new Peca[7];
        System.arraycopy(pecas, 14, splitpecas, 0, 7);
        jogadores[2] = new Bot1(jogo, splitpecas, 2);
        splitpecas = new Peca[7];
//        for (int i = 1; i < 3; i++) {
//            splitpecas = new Peca[7];
//            System.arraycopy(pecas,i*7,splitpecas, 0, 7);
//            jogadores[i] = new Manual(jogo, splitpecas, i);
//        }
        System.arraycopy(pecas,21,splitpecas, 0, 7);
        jogadores[3] = new Aleatorio(jogo, splitpecas, 3);
    }

    /**
     * Onde e dado o ranking dos jogadores por pontuacao por ordem crescente
     * @return Array de jogadores 
     */
    public Jogador[] Classificacao() {

        Jogador[] classificacao = jogadores;

        for (int i = 0; i < classificacao.length; i++) {

            Jogador jogador = classificacao[i];
            int j = i - 1;

            while (j >= 0 && classificacao[j].calcPontos() > jogador.calcPontos()) {
                classificacao[j+1] = classificacao[j];
                j--;
            }
            classificacao[j+1] = jogador;
        }

        return classificacao;
    }

    /**
     * Verifica quem e o primeiro jogador a jogar, ou seja, o que tem a peca 6 6
     * @return O ID do jogador que ira comecar a jogar
     */
    public int checkFirstPlay() {
        for (int i = 0; i < jogadores.length; i++) {
            for (int j = 0; j < jogadores[i].getPecas().length; j++) {
                if (jogadores[i].getPecas()[j].getLado1() == 6 && jogadores[i].getPecas()[j].getLado2() == 6) {
                    return i;
                }
            }
        }
        return -1;
    }
}