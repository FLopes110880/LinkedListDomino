import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestLinhaJogo {

	
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

	    @Test
	    public void testcheckCantos() {
	        lj.addPiece(pecajog11[6], null);
	        lj.addPiece(pecajog22[6], lj.findCantos(pecajog11[6]));
	        assertNotEquals(null, lj.checkCantos(pecajog11[6]));
	        assertNotEquals(null, lj.checkCantos(pecajog11[6]));
	        assertEquals(null, lj.checkCantos(pecajog11[1]));
	    }

	    @Test
	    public void testfindCantos() {
	        assertEquals(null, lj.findCantos(pecajog11[2]));
	        assertEquals(null, lj.findCantos(pecajog22[3]));
	        assertEquals(null, lj.findCantos(pecajog11[1]));
	    }

	    @Test
	    public void testsearchColision() {
	        lj.addPiece(pecajog11[6], null);
	        lj.addPiece(pecajog22[6], lj.findCantos(pecajog11[6]));
	        assertTrue(lj.searchColision(0,1,0,3));
	        assertTrue(lj.searchColision(-1,0,-1,2));
	        assertTrue(lj.searchColision(-2,1,-1,1));
	    }

}
