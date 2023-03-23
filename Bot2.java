import java.util.Collections;
import java.util.Comparator;

public class Bot2 extends Jogador {

    public Bot2 (LinhaJogo linha, Peca []pecas, int id) {
        super(linha, pecas, id);
    }

    public void CustomSort (Peca []pecas) {
        Peca chosen;
        for (int i = 1; i < pecas.length; ++i) {
            chosen = pecas[i];

            int j = i - 1;
            while (j >= 0 && ((pecas[j].getLado1() + pecas[j].getLado2()) < (chosen.getLado1() + chosen.getLado2()))) {
                pecas[j + 1] = pecas[j];
                j = j - 1;
            }

            pecas[j + 1] = chosen;
        }
    }

    protected Peca[] jogada() {

        Peca []result = new Peca[2];

        Peca []temp = new Peca[7];

        temp = this.getPecas();

        CustomSort(temp);

        int i = 0;

        while (temp[i] != null) {
            if (!temp[i].getEscolhida() && getLinha().searchifcanconnecttoEdge(temp[i])) {
                result[0] = temp[i];
                break;
            }
            i++;
        }

        for (Peca peca: this.getLinha().getPecafromCantos()) {
            if (peca != null && getLinha().checkJogada(result[0], peca)) {
                result[1] = peca;
                break;
            }
        }

        return result;
    }
}
