import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testDomino {
	
	    Peca[] peca1 = {new Peca(0, 0), new Peca(1, 0), new Peca(1, 1), new Peca(2, 0),
	                    new Peca(2, 1), new Peca(2, 2), new Peca(3, 0), new Peca(3, 1),
	                    new Peca(3, 2), new Peca(3, 3), new Peca(4, 0), new Peca(4, 1),
	                    new Peca(4, 2), new Peca(4, 3), new Peca(4, 4), new Peca(5, 0),
	                    new Peca(5, 1), new Peca(5, 2), new Peca(5, 3), new Peca(5, 4),
	                    new Peca(5, 5), new Peca(6, 0), new Peca(6, 1), new Peca(6, 2),
	                    new Peca(6, 3), new Peca(6, 4), new Peca(6, 5), new Peca(6, 6)
	    };

	    Peca[] pecajog11 = {peca1[0], peca1[4], peca1[7], peca1[11], peca1[18], peca1[24], peca1[27]};

	    Peca[] pecajog22 = {peca1[5], peca1[8], peca1[9], peca1[17], peca1[20], peca1[21], peca1[25]};

	    Peca[] pecajog33 = {peca1[3], peca1[6], peca1[10], peca1[12], peca1[13], peca1[22], peca1[26]};

	    Peca[] pecajog44 = {peca1[1], peca1[2], peca1[14], peca1[15], peca1[16], peca1[19], peca1[23]};

	    LinhaJogo lj = new LinhaJogo(peca1);

	    Jogador []jogadores = {new Manual(lj, pecajog11,0), new Manual(lj, pecajog22, 1), new Manual(lj, pecajog33, 2), new Manual(lj, pecajog44, 3)};

	    Domino dom = new Domino();

	    @Test
	    public void testcheckFirstPlay() {
	        dom.jogadores[0] = jogadores[0];
	        dom.jogadores[1] = jogadores[1];
	        dom.jogadores[2] = jogadores[2];
	        dom.jogadores[3] = jogadores[3];
	        assertEquals(0, dom.checkFirstPlay());
	        assertNotEquals(1, dom.checkFirstPlay());
	    }

	    @Test
	    public void testClassificacao() {
	        pecajog11[0].setEscolhida();pecajog11[1].setEscolhida();pecajog11[2].setEscolhida();pecajog11[3].setEscolhida();pecajog11[4].setEscolhida();pecajog11[5].setEscolhida();pecajog11[6].setEscolhida();
	        pecajog22[0].setEscolhida();pecajog22[1].setEscolhida();pecajog22[2].setEscolhida();pecajog22[3].setEscolhida();pecajog22[4].setEscolhida();pecajog22[6].setEscolhida();
	        pecajog33[0].setEscolhida();pecajog33[1].setEscolhida();pecajog33[2].setEscolhida();pecajog33[3].setEscolhida();pecajog33[4].setEscolhida();pecajog33[6].setEscolhida();
	        pecajog44[0].setEscolhida();pecajog44[1].setEscolhida();pecajog44[2].setEscolhida();pecajog44[3].setEscolhida();pecajog44[5].setEscolhida();pecajog44[6].setEscolhida();
	        Jogador []jogadores1 = {new Manual(lj, pecajog11,0), new Manual(lj, pecajog22, 1), new Manual(lj, pecajog33, 2), new Manual(lj, pecajog44, 3)};
	        dom.jogadores[0] = jogadores1[0];
	        dom.jogadores[1] = jogadores1[1];
	        dom.jogadores[2] = jogadores1[2];
	        dom.jogadores[3] = jogadores1[3];
	        assertArrayEquals(new Jogador[]{jogadores1[0], jogadores1[1], jogadores1[3], jogadores1[2]}, dom.Classificacao());

	    }

}
