/**
 * 
 * @author Catarina Samina 57637, Francisco Lopes 71391 e Beatriz Correia 72479
 *
 */

public class Peca {

    private int lado1;
    private int lado2;
    private int x;
    private int y;
    private boolean vert;
    private boolean escolhida;

    /**
     * Construtor da classe Peca
     * @param lado1 Metade 1 da peca 
     * @param lado2 Metade 2 da peca
     */
    public Peca (int lado1, int lado2) {
        if (lado1 >= 0 && lado1 <= 6 && lado2 >= 0 && lado2 <= 6) {
            this.lado1 = lado1;
            this.lado2 = lado2;
        }
        else {
            System.exit(0);
        }
        this.x = 0;
        this.y = 0;
        this.vert = false;
        this.escolhida = false;
    }

    /**
     * Onde e alterada a coordenada x
     * @param x Coordenada x onde se encontra a peca no tabuleiro
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Onde e dado o valor da coordenada x
     * @return Coordenada x da peca no tabuleiro
     */
    public int getX() {
        return this.x;
    }

    /**
     * Onde e alterada a coordenada y
     * @param y Coordenada y onde se encontra a peca no tabuleiro
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Onde e dado o valor da coordenada y
     * @return Coordenada y da peca no tabuleiro
     */
    public int getY() {
        return this.y;
    }

    /**
     * Atualizacao das coordenadas onde se encontra a peca no tabuleiro
     * @param xk Coordenada x onde se encontra a peca no tabuleiro
     * @param yk Coordenada y onde se encontra a peca no tabuleiro
     */
    public void setUpdateCord(int xk, int yk) {
        this.x = xk;
        this.y = yk;
    }

    /**
     * Onde e dado o lado 1 da peca
     * @return lado 1 da peca 
     */
    
    public int getLado1() {
        return this.lado1;
    }

    /**
     * Onde e dado o lado 2 da peca
     * @return lado 2 da peca
     */
    
    public int getLado2() {
        return this.lado2;
    }

    /**
     * Onde se altera os lados de uma peca
     */
    
    public void changeLados() {
        int l = this.lado1;
        this.lado1 = this.lado2;
        this.lado2 = l;
    }

    /**
     * Onde se verifica se a peca foi escolhida ou nao
     * @return true or false, ou seja, se foi escolhida ou nao
     */
    public boolean getEscolhida () {
        return this.escolhida;
    }

    /**
     * Onde se vai alterar a peca para escolhida
     */
    
    public void setEscolhida() {
        this.escolhida = true;
    }

    /**
     * Onde e dado se a peca esta ou nao na vertical
     * @return true or false, ou seja, se e vertical ou nao
     */ 
    public boolean getVert () {
        return this.vert;
    }
    
    /**
     * Onde se altera a variavel vert para true
     */
    public void setVert() {
        this.vert = true;
    }
    
    /**
     * Onde a peca e modificada para String
     */
    public String toString() {
        return this.lado1 + "|" + this.lado2;
    }
}