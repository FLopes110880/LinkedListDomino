import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestJogador {

	    Peca[] peca = {new Peca(0, 0), new Peca(1, 0), new Peca(1, 1), new Peca(2, 0), new Peca(2, 1), new Peca(2, 2), new Peca(3, 0),
	            new Peca(3, 1), new Peca(3, 2), new Peca(3, 3), new Peca(4, 0), new Peca(4, 1), new Peca(4, 2), new Peca(4, 3),
	            new Peca(4, 4), new Peca(5, 0), new Peca(5, 1), new Peca(5, 2), new Peca(5, 3), new Peca(5, 4), new Peca(5, 5),
	            new Peca(6, 0), new Peca(6, 1), new Peca(6, 2), new Peca(6, 3), new Peca(6, 4), new Peca(6, 5), new Peca(6, 6)
	    };

	    LinhaJogo lj = new LinhaJogo(peca);

	    Peca[] pecajog1 = {peca[0], peca[4], peca[7], peca[11], peca[18], peca[24], peca[27]};

	    Jogador jogador1 = new Manual(lj, pecajog1, 0);

	    Peca[] pecajog2 = {peca[5], peca[8], peca[9], peca[17], peca[20], peca[21], peca[25]};

	    Jogador jogador2 = new Manual(lj, pecajog2, 1);

	    Peca[] pecajog3 = {peca[3], peca[6], peca[10], peca[12], peca[13], peca[22], peca[26]};

	    Jogador jogador3 = new Manual(lj, pecajog3, 2);

	    Peca[] pecajog4 = {peca[1], peca[2], peca[14], peca[15], peca[16], peca[19], peca[23]};

	    Jogador jogador4 = new Manual(lj, pecajog4, 3);

	    Jogador[] test = {jogador1, jogador2, jogador3, jogador4};

	    @Test
	    public void testCalcPontos() {
	        assertEquals(41, jogador1.calcPontos());
	        assertEquals(48, jogador2.calcPontos());
	        assertEquals(40, jogador3.calcPontos());
	        assertEquals(39, jogador4.calcPontos());
	    }

	    Peca[] peca1 = {new Peca(0, 0), new Peca(1, 0), new Peca(1, 1), new Peca(2, 0),
	                    new Peca(2, 1), new Peca(2, 2), new Peca(3, 0), new Peca(3, 1),
	                    new Peca(3, 2), new Peca(3, 3), new Peca(4, 0), new Peca(4, 1),
	                    new Peca(4, 2), new Peca(4, 3), new Peca(4, 4), new Peca(5, 0),
	                    new Peca(5, 1), new Peca(5, 2), new Peca(5, 3), new Peca(5, 4),
	                    new Peca(5, 5), new Peca(6, 0), new Peca(6, 1), new Peca(6, 2),
	                    new Peca(6, 3), new Peca(6, 4), new Peca(6, 5), new Peca(6, 6)
	    };

	    Peca[] pecajog11 = {peca[0], peca[4], peca[7], peca[11], peca[18], peca[24], peca[27]};

	    Peca[] pecajog22 = {peca[5], peca[8], peca[9], peca[17], peca[20], peca[21], peca[25]};

	    Peca[] pecajog33 = {peca[3], peca[6], peca[10], peca[12], peca[13], peca[22], peca[26]};

	    Peca[] pecajog44 = {peca[1], peca[2], peca[14], peca[15], peca[16], peca[19], peca[23]};

	    @Test
	    public void testcheckWinner() {
	        pecajog11[1].setEscolhida(); pecajog11[2].setEscolhida();pecajog11[3].setEscolhida();pecajog11[4].setEscolhida();pecajog11[5].setEscolhida();pecajog11[6].setEscolhida();
	        Jogador jogador11 = new Manual(lj, pecajog11, 0);

	        pecajog22[0].setEscolhida();pecajog22[1].setEscolhida();pecajog22[2].setEscolhida();pecajog22[3].setEscolhida();pecajog22[5].setEscolhida();pecajog22[6].setEscolhida();
	        Jogador jogador22 = new Manual(lj, pecajog22, 1);

	        pecajog33[0].setEscolhida();pecajog33[1].setEscolhida();pecajog33[3].setEscolhida();pecajog33[4].setEscolhida();pecajog33[6].setEscolhida();
	        Jogador jogador33 = new Manual(lj, pecajog33, 2);

	        pecajog44[0].setEscolhida();pecajog44[1].setEscolhida();pecajog44[2].setEscolhida();pecajog44[3].setEscolhida();pecajog44[4].setEscolhida();pecajog44[5].setEscolhida();pecajog44[6].setEscolhida();
	        Jogador jogador44 = new Manual(lj, pecajog44, 3);

	        assertTrue(jogador44.checkWinner());
	        assertTrue(jogador11.checkWinner());
	        assertFalse(jogador22.checkWinner());
	        assertFalse(jogador33.checkWinner());
	    }

	    @Test
	    public void testcheck66 () {

	        pecajog11[1].setEscolhida(); pecajog11[2].setEscolhida();pecajog11[3].setEscolhida();pecajog11[4].setEscolhida();pecajog11[5].setEscolhida();pecajog11[6].setEscolhida();
	        Jogador jogador11 = new Manual(lj, pecajog11, 0);

	        pecajog22[0].setEscolhida();pecajog22[1].setEscolhida();pecajog22[2].setEscolhida();pecajog22[3].setEscolhida();pecajog22[5].setEscolhida();pecajog22[6].setEscolhida();
	        Jogador jogador22 = new Manual(lj, pecajog22, 1);

	        pecajog33[0].setEscolhida();pecajog33[1].setEscolhida();pecajog33[3].setEscolhida();pecajog33[4].setEscolhida();pecajog33[6].setEscolhida();
	        Jogador jogador33 = new Manual(lj, pecajog33, 2);

	        pecajog44[0].setEscolhida();pecajog44[1].setEscolhida();pecajog44[2].setEscolhida();pecajog44[3].setEscolhida();pecajog44[4].setEscolhida();pecajog44[5].setEscolhida();pecajog44[6].setEscolhida();
	        Jogador jogador44 = new Manual(lj, pecajog44, 3);

	        assertEquals(6, jogador11.check66());
	        assertEquals(-1, jogador22.check66());
	        assertEquals(-1, jogador33.check66());
	        assertEquals(-1, jogador44.check66());
	    }

}
