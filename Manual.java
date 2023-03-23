
import java.util.Scanner;

/**
 * 
 * @author Catarina Samina 57637, Francisco Lopes 71391 e Beatriz Correia 72479
 * 
 */
public class Manual extends Jogador{

	/**
	 * 
	 * @param linha	Linha de Jogo
	 * @param pecas Conjunto de pecas
	 * @param id ID do jogador
	 */
    public Manual (LinhaJogo linha, Peca []pecas, int id) {
        super(linha, pecas, id);
    }

    /**
     * Onde se a realizam as jogadas
     */
    public Peca[] jogada() {
        Scanner sc = new Scanner(System.in);
        Peca []result;
        while (true) {
            result = new Peca[2];
            String choice;
            Peca chosen;
            String[] idk;

            while (true) {
                System.out.println("Choose Player Piece: ");
                choice = sc.next();
                idk = choice.split("");
                chosen = new Peca(Integer.parseInt(idk[0]), Integer.parseInt(idk[2]));
                if (idk[1].equals("|") && (chosen = checkPieces(chosen)) != null && !chosen.getEscolhida()) {
                    break;
                }
                else {
                    System.out.println("Selected Piece doesn't exist in this player hand. Please select another.");
                }
            }

            // result[0] Peca do jogador;
            result[0] = chosen;

            if (getLinha().searchifcanconnecttoEdge(result[0])) {
                while (true) {
                    System.out.println("Choose Edge: ");
                    choice = sc.next();
                    idk = choice.split("");
                    chosen = new Peca(Integer.parseInt(idk[0]), Integer.parseInt(idk[2]));
                    chosen = getLinha().checkCantos(chosen);
                    if (idk[1].equals("|") && chosen != null) {
                        break;
                    }
                    System.out.println("Edge selected doesn't exist. Please select another");
                }

                //result[1] Canto selecionado pelo jogador
                result[1] = chosen;

                if (getLinha().checkJogada(result[0], result[1])) {
                    break;
                }
                else {
                    System.out.println("Selected Piece and Selected Edge don't correspond. Select another Edge.");
                }
            }
            else {
                System.out.println("Invalid Piece, please select a piece that matchs a edge.");
            }
        }

        return result;
    }
}
