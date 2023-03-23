import java.util.LinkedList;

/**
 * 
 * @author Catarina Samina 57637, Francisco Lopes 71391 e Beatriz Correia 72479
 *
 */

abstract class Jogador {

    private final Peca []pecas;
    private final int playerid;
    private int pontos;
    private LinhaJogo linha;

    /**
     * Construtor da classe jogador
     * @param linha Tabuleiro
     * @param pecas	Array de Peca
     * @param playerid	ID do jogador
     */
    public Jogador(LinhaJogo linha, Peca[] pecas, int playerid) {
        this.pecas = pecas;
        this.playerid = playerid;
        this.pontos = 0;
        this.linha = linha;
    }

    /**
     * Onde sao dadas as pecas do jogador
     * @return Todas as pecas do jogador
     */
    public Peca[] getPecas () {
        return this.pecas;
    }
    
    /**
     * Onde e dada a peca escolhida
     * @param x Numero da peca dentro do array pecas
     * @return Peca escolhida
     */
    public Peca getPecaChoise (int x) {
        return this.pecas[x];
    }

    /**
     * Onde e dado o id do jogador
     * @return ID do jogador
     */
    public int getPlayerid() {
        return this.playerid;
    }

    /**
     * Onde e dada o atributo linha
     * @return LinhaJogo, Tabuleiro
     */
    public LinhaJogo getLinha() {
        return this.linha;
    }

    /**
     * Onde sao mostradas as pecas na mao do jogador
     */
    public void showpieces() {
        for(Peca peca: pecas) {
            if (!peca.getEscolhida()) {
                System.out.println(peca);
            }
        }
    }

    /**
     * Onde são calculados os pontos do jogador
     * @return Pontos do jogador
     */
    public int calcPontos() {
        int result = 0;
        for (Peca peca: pecas) {
            if (!peca.getEscolhida()) {
                result += (peca.getLado1() + peca.getLado2());
            }
        }
        return result;
    }

    /**
     * Onde sao dados os pontos do jogador
     * @return Pontos do jogador
     */
    public int getPontos() {
        return this.pontos;
    }

    /**
     * Onde sao atualizados os pontos do jogador
     * @param pontos Pontos do jogador
     */
    public void setPontos (int pontos) {
        this.pontos = pontos;
    }

    /**
     * Verifica se a peca que o jogador escolheu esta na sua mao
     * @param choice Peca escolhida
     * @return Peca escolhida no caso de estar na mao do jogador ou nenhuma peca no caso deste nao ter na mao
     */
    public Peca checkPieces(Peca choice) {
        for (Peca peca : pecas) {
            if (choice.getLado1() == peca.getLado1() && choice.getLado2() == peca.getLado2() || (choice.getLado1() == peca.getLado2() && choice.getLado2() == peca.getLado1())) {
                return peca;
            }
        }
        return null;
    }

    /**
     * Onde sao calculados os pontos e se verifica o vencedor
     * @return true or false, ou seja, se o jogador e ou nao vencedor
     */
    public boolean checkWinner() {
        return (this.pontos = calcPontos()) == 0;
    }

    /**
     * Verifica a posicao da peca inicial na mao do jogador
     * @return Se a peca for a 6 6 retorna a sua posicao se nao retorna -1
     */
    public int check66 () {
        for (int i = 0; i < pecas.length; i++) {
            if (pecas[i].getLado1() == 6 && pecas[i].getLado2() == 6) {
                return i;
            }
        }
        return -1;
    }

    protected abstract Peca[] jogada();
}