import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPeca {

	@Test
	void testContrutor0() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Peca(7, 7);
		});
	}
	
	@Test
	void testContrutor1() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Peca(-1, 6);
		});
	}


}
