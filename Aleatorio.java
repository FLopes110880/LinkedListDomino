
import java.util.Random;

public class Aleatorio extends Jogador {

    public Aleatorio (LinhaJogo linha, Peca []pecas, int id) {
        super(linha, pecas, id);
    }

    public Peca[] jogada() {
        Random rand = new Random();
        Peca []result = new Peca[2];
        Peca chosen;

        System.out.println("Jogador Aleatorio.");
        do {
            do {
                chosen = this.getPecaChoise(rand.nextInt(this.getPecas().length));
            } while (chosen.getEscolhida());

            result[0] = chosen;

            if (getLinha().searchifcanconnecttoEdge(result[0])) {
                Peca[] edge = getLinha().getPecafromCantos();
                do {
                    chosen = edge[rand.nextInt(edge.length)];
                } while (getLinha().checkCantos(chosen) == null);
            }

            result[1] = chosen;

        } while (!getLinha().checkJogada(result[0], result[1]));


        return result;
    }
}
