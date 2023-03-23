
import java.util.LinkedList;

/**
 *
 * @author Catarina Samina 57637, Francisco Lopes 71391 e Beatriz Correia 72479
 *
 */


public class LinhaJogo {

    private static class Node {

        private final Node left;
        private final Node right;
        private final Peca peca;
        private Node up;
        private Node down;
        private final Node[]direcoes;

        /**
         * Formacao do Node
         * @param peca
         */

        public Node (Peca peca) {
            this.left = null;
            this.right = null;

            this.peca = peca;

            if (peca.getClass().getName().equals("Dupla")) {
                this.up = null;
                this.down = null;
                this.direcoes = new Node[]{left, up, right, down};
            }
            else {
                if (peca.getVert()) {
                    this.direcoes = new Node[]{up, down};
                }
                else {
                    this.direcoes = new Node[]{left, right};
                }
            }
        }

        public String toString() {
            return left + " " + peca.toString() + " " + right;
        }
    }

    private Peca []pecas;
    private int comprimento;
    private int largura;
    private final LinkedList<Node> cantos;
    private final LinkedList<Peca> Tabuleiro;
    private Node first;
    private int boardXmin;
    private int boardYmin;
    private int boardXmax;
    private int boardYmax;

    /**
     * Construtor da classe LinhaJogo
     * @param pecas Array de pecas que se encontram no tabuleiro
     */

    public LinhaJogo (Peca [] pecas) {
        this.pecas = pecas;
        this.comprimento = 0;
        this.largura = 0;
        this.cantos = new LinkedList<Node>();
        this.Tabuleiro = new LinkedList<Peca>();
        this.first = null;
        this.boardXmin = 0;
        this.boardYmin = 0;
        this.boardXmax = 0;
        this.boardYmax = 0;
    }

    /**
     * Onde e feita a atualizacao da atributo comprimento
     * @param x Comprimento do tabuleiro
     */
    public void setComprimento (int x) {
        this.comprimento = x;
    }

    /**
     * Onde e feita a atualizacao do atributo largura
     * @param x Largura do tabuleiro
     */
    public void setLargura (int x) {
        this.largura = x;
    }

    /**
     * Onde e dado o atributo cantos
     * @return LinkedList
     */
    public LinkedList<Node> getCantos () {
        return this.cantos;
    }

    /**
     * Vai verificar se o canto escolhido esta correto
     * @param canto Peca que esta num canto e pode receber uma outra peca
     * @return Se o canto escolhido estiver correto vai dar a peca que se encontra no tabuleiro se não não retorna nenhuma peca
     */

    public Peca checkCantos(Peca canto) {
        for (Node cantos : cantos) {
            if ((cantos.peca.getLado1() == canto.getLado1() && cantos.peca.getLado2() == canto.getLado2()) || (cantos.peca.getLado1() == canto.getLado2() && cantos.peca.getLado2() == canto.getLado1())) {
                return cantos.peca;
            }
        }
        return null;
    }

    /**
     * Vai procurar os cantos disponiveis para jogar
     * @param canto Peca que esta no tabuleiro e pode ser jogada
     * @return Os lados possiveis para serem jogados
     */
    public Node findCantos (Peca canto) {
        for (Node cantos : cantos) {
            if ((cantos.peca.getLado1() == canto.getLado1() && cantos.peca.getLado2() == canto.getLado2()) || (cantos.peca.getLado1() == canto.getLado2() && cantos.peca.getLado2() == canto.getLado1())) {
                return cantos;
            }
        }
        return null;
    }

    /**
     * 
     * @param peca Peca escolhida
     * @param canto Onde pode ser adicionada uma peca
     * @param i Direcao do canto
     */
    public void updatePecas (Peca peca, Node canto, int i) {
        if (canto.peca.getClass().getName().equals("Dupla")) {
            if (canto.peca.getVert()) {
                if (i == 0) {
                    if (canto.peca.getLado1() != peca.getLado2()) {
                        peca.changeLados();
                    }
                    peca.setUpdateCord(canto.peca.getX() - 2, canto.peca.getY() + 1);
                    updateBoardVariablesmax(peca.getX(), peca.getY());
                    if ((canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) && (canto.direcoes[2] != null || checkColision(canto.peca, peca, 2)) && (canto.direcoes[3] != null || checkColision(canto.peca, peca, 3))) {
                        cantos.remove(canto);
                    }
                } else if (i == 1) {
                    peca.setVert();
                    if (peca.getLado2() != canto.peca.getLado1()) {
                        peca.changeLados();
                    }
                    peca.setUpdateCord(canto.peca.getX(), canto.peca.getY() - 3);
                    if ((canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) && (canto.direcoes[2] != null || checkColision(canto.peca, peca, 2)) && (canto.direcoes[3] != null || checkColision(canto.peca, peca, 3))) {
                        cantos.remove(canto);
                    }
                } else if (i == 2) {
                    if (canto.peca.getLado2() != peca.getLado1()) {
                        peca.changeLados();
                    }
                    peca.setUpdateCord(canto.peca.getX() + 1, canto.peca.getY() + 1);
                    updateBoardVariablesmax(peca.getX() + 1, peca.getY());
                    if ((canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) && (canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) && (canto.direcoes[3] != null || checkColision(canto.peca, peca, 3))) {
                        cantos.remove(canto);
                    }
                } else {
                    peca.setVert();
                    if (canto.peca.getLado1() != peca.getLado1()) {
                        peca.changeLados();
                    }
                    peca.setUpdateCord(canto.peca.getX(), canto.peca.getY() + 3);
                    updateBoardVariablesmax(peca.getX(), peca.getY() + 2);
                    if ((canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) && (canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) && (canto.direcoes[2] != null || checkColision(canto.peca, peca, 2))) {
                        cantos.remove(canto);
                    }
                }
            }
            else {
                if (i == 0) {
                    if (canto.peca.getLado1() != peca.getLado2()) {
                        peca.changeLados();
                    }
                    peca.setUpdateCord(canto.peca.getX()-2, canto.peca.getY());
                    if ((canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) && (canto.direcoes[2] != null || checkColision(canto.peca, peca, 2)) && (canto.direcoes[3] != null || checkColision(canto.peca, peca, 3))) {
                        cantos.remove(canto);
                    }
                }
                else if (i == 1) {
                    peca.setVert();
                    if (canto.peca.getLado1() != peca.getLado2()) {
                        peca.changeLados();
                    }
                    peca.setUpdateCord(canto.peca.getX()+1, canto.peca.getY()-3);
                    if ((canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) && (canto.direcoes[2] != null || checkColision(canto.peca, peca, 2)) && (canto.direcoes[3] != null || checkColision(canto.peca, peca, 3))) {
                        cantos.remove(canto);
                    }
                }
                else if (i == 2) {
                    if (canto.peca.getLado1() != peca.getLado1()) {
                        peca.changeLados();
                    }
                    peca.setUpdateCord(canto.peca.getX()+3, canto.peca.getY());
                    updateBoardVariablesmax(peca.getX()+2,peca.getY());
                    if ((canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) && (canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) && (canto.direcoes[3] != null || checkColision(canto.peca, peca, 3))) {
                        cantos.remove(canto);
                    }
                }
                else {
                    if (canto.peca.getLado1() != peca.getLado1()) {
                        peca.changeLados();
                    }
                    peca.setVert();
                    peca.setUpdateCord(canto.peca.getX()+1,canto.peca.getY()+1);
                    updateBoardVariablesmax(peca.getX(),peca.getY()+2);
                    if ((canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) && (canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) && (canto.direcoes[2] != null || checkColision(canto.peca, peca, 2))) {
                        cantos.remove(canto);
                    }
                }
            }
        }
        else {
            if (canto.peca.getVert()) {
                if (peca.getClass().getName().equals("Dupla")) {
                    if (i == 0) {
                        peca.setUpdateCord(canto.peca.getX()-1,canto.peca.getY()-1);
                        updateBoardVariablesmax(peca.getX()+2, peca.getY());
                        if (canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) {
                            cantos.remove(canto);
                        }
                    }
                    else {
                        peca.setUpdateCord(canto.peca.getX()-1,canto.peca.getY()+3);
                        updateBoardVariablesmax(peca.getX()+2, peca.getY());
                        if (canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) {
                            cantos.remove(canto);
                        }
                    }
                }
                else {
                    peca.setVert();
                    if (i == 0) {
                        if (peca.getLado2() != canto.peca.getLado1()) {
                            peca.changeLados();
                        }
                        peca.setUpdateCord(canto.peca.getX(), canto.peca.getY()-3);
                        if (canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) {
                            cantos.remove(canto);
                        }
                    }
                    else {
                        if (peca.getLado1() != canto.peca.getLado2()) {
                            peca.changeLados();
                        }
                        peca.setUpdateCord(canto.peca.getX(), canto.peca.getY()+3);
                        updateBoardVariablesmax(peca.getX(), peca.getY()+2);
                        if (canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) {
                            cantos.remove(canto);
                        }
                    }
                }
            }
            else {
                if (peca.getClass().getName().equals("Dupla")) {
                    peca.setVert();
                    if (i == 0) {
                        peca.setUpdateCord(canto.peca.getX()-1,canto.peca.getY()-1);
                        updateBoardVariablesmax(peca.getX(), peca.getY()+2);
                        if (canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) {
                            cantos.remove(canto);
                        }
                    }
                    else {
                        peca.setUpdateCord(canto.peca.getX()+2,canto.peca.getY()-1);
                        updateBoardVariablesmax(peca.getX(), peca.getY()+2);
                        if (canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) {
                            cantos.remove(canto);
                        }
                    }
                }
                else {
                    if (i == 0) {
                        if (peca.getLado2() != canto.peca.getLado1()) {
                            peca.changeLados();
                        }
                        peca.setUpdateCord(canto.peca.getX()-2,canto.peca.getY());
                        if (canto.direcoes[1] != null || checkColision(canto.peca, peca, 1)) {
                            cantos.remove(canto);
                        }
                    }
                    else {
                        if (peca.getLado1() != canto.peca.getLado2()) {
                            peca.changeLados();
                        }
                        peca.setUpdateCord(canto.peca.getX()+2,canto.peca.getY());
                        updateBoardVariablesmax(peca.getX()+1, peca.getY());
                        if (canto.direcoes[0] != null || checkColision(canto.peca, peca, 0)) {
                            cantos.remove(canto);
                        }
                    }
                }
            }
        }
    }

    /**
     * Coloca as coordenadas da peca mais a direita e para baixo que se encontra no tabuleiro
     * @param xmax Coordenada x da peca mais a direita e para baixo que se encontra no tabuleiro
     * @param ymax Coordenada y da peca mais a direita e para baixo que se encontra no tabuleiro
     */

    public void updateBoardVariablesmax (int xmax, int ymax) {
        if (xmax > boardXmax) {
            boardXmax = xmax;
        }

        if (ymax > boardYmax) {
            boardYmax = ymax;
        }
    }

    /**
     * Coloca as coordenadas da peca mais a esquerda e para cima do tabuleiro, sendo que estas tem de ser menores que o tamanho do tabuleiro
     * @param peca Peca que se encontra mais para a esquerda e para cima no tabuleiro
     */

    public void updateBoardVariablesmin (Peca peca) {
        if (peca.getX() < boardXmin) {
            boardXmin = peca.getX();
        }

        if (peca.getY() < boardYmin) {
            boardYmin = peca.getY();
        }
    }

    /**
     * Vai pesquisar por uma colisao
     * @param xmin Coordenada x mais para a esquerda e para cima no tabuleiro
     * @param ymin Coordenada y mais para a esquerda e para cima no tabuleiro
     * @param xmax Coordenada x mais para a direita e para baixo no tabuleiro
     * @param ymax Coordenada y mais para a direita e para baixo no tabuleiro
     * @return true or false, ou seja, se ha colisao ou nao
     */
    public boolean searchColision (int xmin, int ymin, int xmax, int ymax) {
        int xpeca = 0;
        int ypeca = 0;
        for (Peca peca: Tabuleiro) {
            boolean dupla = peca.getClass().getName().equals("Dupla");
            if (peca.getVert()) {
                xpeca = peca.getX();
                ypeca = peca.getY() + 2;
            }
            else if (!dupla) {
                xpeca = peca.getX()+1;
                ypeca = peca.getY();
            }
            else {
                xpeca = peca.getX() + 2;
                ypeca = peca.getY();
            }
//            System.out.println((boardXmax - xmin > comprimento) + " " + boardXmax + " " + xmin + " " + peca);
            if ((boardXmax - xmin >= comprimento) || (xmax - boardXmin >= comprimento) || (boardYmax - ymin >= largura) || (ymax - boardYmin >= largura)) {
                return true;
            }
            else if (peca.getX() >= xmin && xpeca <= xmax && ymin >= peca.getY()  && ymax <= ypeca) {
                return true;
            }
            else if (xmin >= peca.getX() && xmax <= xpeca && peca.getY() >= ymin && peca.getY() <= ymax) {
                return true;
            }

        }
        return false;
    }

    /**
     * Vai verificar se ha uma colisao
     * @param canto Peca que se encontra no tabuleiro e pode ser jogada
     * @param peca	Peca que ira ser jogada pelo jogador
     * @param i A direcao para onde a peca pode ser jogada
     * @return true or false, ou seja, se ha colisao ou nao
     */
    public boolean checkColision (Peca canto, Peca peca, int i) {
        boolean colide = false;
        boolean dupla = canto.getClass().getName().equals("Dupla");
        boolean pecadupla = peca.getClass().getName().equals("Dupla");
        if (dupla) {
            if (canto.getVert()) {
                if (i == 0) {
                    colide = searchColision(canto.getX()-2, canto.getY()+1, canto.getX()-1, canto.getY()+1);
                }
                else if (i == 1) {
                    colide = searchColision(canto.getX(), canto.getY()-3, canto.getX(), canto.getY()-1);
                }
                else if (i == 2) {
                    colide = searchColision(canto.getX()+1, canto.getY() + 1, canto.getX()+2, canto.getY()+1);
                }
                else {
                    colide = searchColision(canto.getX(), canto.getY()+3, canto.getX(), canto.getY()+5);
                }
            }
            else {
                if (i == 0) {
                    colide = searchColision(canto.getX()-2, canto.getY(), canto.getX()-1, canto.getY());
                }
                else if (i == 1) {
                    colide = searchColision(canto.getX()+1, canto.getY()-3, canto.getX()+1, canto.getY()-1);
                }
                else if (i == 2) {
                    colide = searchColision(canto.getX()+3, canto.getY(), canto.getX()+4, canto.getY());
                }
                else {
                    colide = searchColision(canto.getX()+1, canto.getY()+1, canto.getX()+1, canto.getY()+3);
                }
            }
        }
        else {
            if (canto.getVert()) {
                if (pecadupla) {
                    if (i == 0) {
                        colide = searchColision(canto.getX()-1, canto.getY()-1, canto.getX()+1, canto.getY()-1);
                    }
                    else {
                        colide = searchColision(canto.getX()-1, canto.getY()+3, canto.getX()+1, canto.getY()+3);
                    }
                }
                else {
                    if (i == 0) {
                        colide = searchColision(canto.getX(), canto.getY()-3, canto.getX(), canto.getY()-1);
                    }
                    else {
                        colide = searchColision(canto.getX(), canto.getY()+3, canto.getX(), canto.getY()+5);
                    }
                }
            }
            else {
                if (pecadupla) {
                    if (i == 0) {
                        colide = searchColision(canto.getX()-1, canto.getY()-1, canto.getX()-1, canto.getY()+1);
                    }
                    else {
                        colide = searchColision(canto.getX()+2, canto.getY()-1, canto.getX()+2, canto.getY()+1);
                    }
                }
                else {
                    if (i == 0) {
                        colide = searchColision(canto.getX()-2, canto.getY(), canto.getX()-1, canto.getY());
                    }
                    else {
                        colide = searchColision(canto.getX()+2, canto.getY(), canto.getX()+3, canto.getY());
                    }
                }
            }
        }
        return colide;
    }

    /**
     * Adiciona a peca jogada a peca que esta disponivel no tabuleiro
     * @param i 
     * @param pec
     * @param canto
     * @param peca Peca que vai ser jogada
     */

    public void addCantotopiece (int i, Node pec, Node canto, Peca peca) {
        if (canto.peca.getClass().getName().equals("Dupla")) {
            if (i == 0 || i == 1) {
                pec.direcoes[1] = canto;
            }
            else if (i == 2 || i == 3) {
                pec.direcoes[0] = canto;
            }
        }
        else {
            if (!peca.getClass().getName().equals("Dupla")) {
                if (i == 0) {
                    pec.direcoes[1] = canto;
                }
                else if (i == 1) {
                    pec.direcoes[0] = canto;
                }
            }
            else {
                if (canto.peca.getVert()) {
                    if (i == 0) {
                        pec.direcoes[3] = canto;
                    }
                    else if (i == 1) {
                        pec.direcoes[1] = canto;
                    }
                }
                else {
                    if (i == 0) {
                        pec.direcoes[2] = canto;
                    }
                    else if (i == 1) {
                        pec.direcoes[0] = canto;
                    }
                }
            }
        }
    }

    /**
     * Verifica se a peca jogada e adicionada ou nao ao tabuleiro
     * @param peca Peca que foi jogada pelo jogador
     * @param canto Peca disponivel para jogo no tabuleiro
     * @return true or false, ou seja, se a peca foi jogada ou nao
     */  
    public boolean addPiece(Peca peca, Node canto) {
        boolean added = false;
        if (canto == null) {
            peca.setEscolhida();
            peca.setVert();
            updateBoardVariablesmax(peca.getX(), peca.getY()+2);
            first = new Node(peca);
            cantos.add(first);
            Tabuleiro.add(peca);
            added = true;
        }
        else {
            peca.setEscolhida();
            int i = 0;

            while ((canto.direcoes[i] != null || checkColision(canto.peca, peca, i))) {
                i++;
                if (i >= canto.direcoes.length) {
                    break;
                }
            }

            boolean dupla = canto.peca.getClass().getName().equals("Dupla");

            if ((dupla && i < 4) || (!dupla &&  i < 2)) {

                updatePecas(peca, canto, i);

                updateBoardVariablesmin(peca);

                Node pec = new Node(peca);
                canto.direcoes[i] = pec;
                addCantotopiece(i, pec, canto, peca);

                cantos.add(pec);
                Tabuleiro.add(peca);
                added = true;
            }
        }
        return added;
    }

    /**
     * Onde sera feito o print do tabuleiro e de tudo o que se encontra nele
     */
    public void printTabuleiro() {
        int line = boardYmax + Math.abs(boardYmin) + 1;
        int column = boardXmax + Math.abs(boardXmin) + 1;
        String [][]tabuleiro = new String[line + 1][column + 1];

        for (Peca peca: Tabuleiro) {
            if (peca.getLado1() != -1) {
                int x = peca.getX() + Math.abs(boardXmin);
                int y = peca.getY() + Math.abs(boardYmin);

                tabuleiro[y][x] = String.valueOf(peca.getLado1());

                if (peca.getVert()) {
                    tabuleiro[y+1][x] = "-";
                    tabuleiro[y+2][x] = String.valueOf(peca.getLado2());
                }
                else if (peca.getClass().getName().equals("Dupla")) {
                    tabuleiro[y][x+1] = "-";
                    tabuleiro[y][x+2] = String.valueOf(peca.getLado2());
                }
                else {
                    tabuleiro[y][x+1] = String.valueOf(peca.getLado2());
                }
            }
        }

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                if (tabuleiro[i][j] != null) {
                    System.out.print(tabuleiro[i][j]);
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Verifica se a jogada e possivel de ser feita
     * @param peca Peca que ira ser jogada pelo jogador
     * @param canto Peca disponivel para ser jogada no tabuleiro
     * @return true or false, se a jogada e possivel ou nao
     */
    public boolean checkJogada(Peca peca, Peca canto) {
        boolean jogada = false;
        for (Node p: cantos) {
            if ((p.peca.getLado1() == canto.getLado1() && p.peca.getLado2() == canto.getLado2()) || (p.peca.getLado1() == canto.getLado2() && p.peca.getLado2() == canto.getLado1())) {

                boolean dupla = canto.getClass().getName().equals("Dupla");
                if (dupla&& (peca.getLado1() == canto.getLado1() || peca.getLado2() == canto.getLado1())) {
                    jogada = true;
                }
                else if (!dupla && p.direcoes[0] != null && (canto.getLado2() == peca.getLado1() || canto.getLado2() == peca.getLado2())) {
                    jogada = true;
                }
                else if (!dupla && p.direcoes[1] != null && (canto.getLado1() == peca.getLado1() || canto.getLado1() == peca.getLado2())) {
                    jogada = true;
                }
            }
        }
        return jogada;
    }

    /**
     *	Verifica se a peca jogada pelo jogador pode ser adicionada a peca que se encontra no tabuleiro
     * @param peca Peca que ira ser jogada pelo jogador
     * @return true or false, ou seja, se a peca pode ser adicionada ao tabuleiro ou nao
     */
    public boolean searchifcanconnecttoEdge (Peca peca) {

        for (Node canto: cantos) {

            if (checkJogada(peca, canto.peca)) {

                int i = 0;
                while ((canto.direcoes[i] != null || checkColision(canto.peca, peca, i))) {
                    i++;
                    if (i >= canto.direcoes.length) {
                        break;
                    }
                }
                if ((i < canto.direcoes.length)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica se ha alguma jogada possivel
     * @param jogador Jogador que ira jogar nesse momento
     * @return true or false, ou seja, se ha uma jogada possivel ou nao
     */
    public boolean searchifPossiblePlay (Jogador jogador) {
        for (Peca peca: jogador.getPecas()) {

            if (!peca.getEscolhida() && searchifcanconnecttoEdge(peca)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @return
     */
    public Peca[] getPecafromCantos() {
    	Peca[] piece = new Peca[this.getCantos().size()];
    	int i = 0;
    	for (Node canto: this.getCantos()) {
    		piece[i++] = canto.peca;
    	}
    	return piece;
    }

}
