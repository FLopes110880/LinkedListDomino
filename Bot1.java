import java.sql.SQLSyntaxErrorException;

public class Bot1 extends Jogador{

    public Bot1 (LinhaJogo linha, Peca []pecas, int id) {
        super(linha, pecas, id);
    }

    public void insertionSortPecas (Peca []pecadupla, int k) {

        Peca chosen;
        for (int i = 1; i < k; ++i) {
            chosen = pecadupla[i];

            int j = i - 1;
            while (j >= 0 && ((pecadupla[j].getLado1() + pecadupla[j].getLado2()) < (chosen.getLado1() + chosen.getLado2()))) {
                pecadupla[j + 1] = pecadupla[j];
                j = j - 1;
            }
            pecadupla[j + 1] = chosen;
        }
    }

    protected Peca[] jogada() {

        Peca []result = new Peca[2];

        Peca []pecadupla = new Peca[this.getPecas().length];
        int ipecadupla = 0;
        Peca []pecasimples = new Peca[this.getPecas().length];
        int jpecasimples = 0;
        //parte em que o bot escolhe a peca
        for (Peca peca: this.getPecas()) {
            if (peca.getClass().getName().equals("Dupla") && !peca.getEscolhida()) {
                pecadupla[ipecadupla++] = peca;
            }
            else if (!peca.getEscolhida()) {
                pecasimples[jpecasimples++] = peca;
            }
        }

        if (ipecadupla != 0) {
            insertionSortPecas(pecadupla, ipecadupla);
        }

        if (jpecasimples != 0) {
            insertionSortPecas(pecasimples, jpecasimples);
        }

        Peca[] temp = new Peca[ipecadupla+jpecasimples];
        //concatenar a pecasimples para a pecadupla
        for (int i = 0; i < ipecadupla + jpecasimples; i++) {
            if (i < ipecadupla) {
                temp[i] = pecadupla[i];
            }
            else {
                temp[i] = pecasimples[i-ipecadupla];
            }
        }

        int i = 0;

        while (temp[i] != null) {
            if (getLinha().searchifcanconnecttoEdge(temp[i])) {
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
