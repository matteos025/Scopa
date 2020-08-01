import org.junit.Test;

public class Tester {
	
	@Test
	public void test1() {
		Carta[] mazzo = {new Carta(1, "Ori"), new Carta(1, "Bastone"), 
				new Carta(1, "Spada"), new Carta(1, "Coppa"), new Carta(2, "Ori"), 
				new Carta(2, "Bastone"), new Carta(2, "Spada"), new Carta(2, "Coppa"), 
				new Carta(3, "Ori"), new Carta(3, "Bastone"), new Carta(3, "Spada"), 
				new Carta(3, "Coppa"), new Carta(4, "Ori"), new Carta(4, "Bastone"), 
				new Carta(4, "Spada"), new Carta(4, "Coppa"), new Carta(5, "Ori"), 
				new Carta(5, "Bastone"), new Carta(5, "Spada"), new Carta(5, "Coppa"), 
				new Carta(6, "Ori"), new Carta(6, "Bastone"), new Carta(6, "Spada"), 
				new Carta(6, "Coppa"), new Carta(7, "Ori"), new Carta(7, "Bastone"), 
				new Carta(7, "Spada"), new Carta(7, "Coppa"), new Carta(8, "Ori"), 
				new Carta(8, "Bastone"), new Carta(8, "Spada"), new Carta(8, "Coppa"), 
				new Carta(9, "Ori"), new Carta(9, "Bastone"), new Carta(9, "Spada"), 
				new Carta(9, "Coppa"), new Carta(10, "Ori"), new Carta(10, "Bastone"), 
				new Carta(10, "Spada"), new Carta(10, "Coppa")};
		Gioco.giocoTestato(mazzo);
	}
	
	@Test
	public void test2() {
		Carta[] mazzo = {new Carta(1, "Ori"), new Carta(2, "Ori"), 
				new Carta(1, "Spada"), new Carta(1, "Coppa"), new Carta(1, "Bastone"), 
				new Carta(2, "Bastone"), new Carta(2, "Spada"), new Carta(2, "Coppa"), 
				new Carta(3, "Ori"), new Carta(3, "Bastone"), new Carta(3, "Spada"), 
				new Carta(3, "Coppa"), new Carta(4, "Ori"), new Carta(4, "Bastone"), 
				new Carta(4, "Spada"), new Carta(4, "Coppa"), new Carta(5, "Ori"), 
				new Carta(5, "Bastone"), new Carta(5, "Spada"), new Carta(5, "Coppa"), 
				new Carta(6, "Ori"), new Carta(6, "Bastone"), new Carta(6, "Spada"), 
				new Carta(6, "Coppa"), new Carta(7, "Ori"), new Carta(7, "Bastone"), 
				new Carta(7, "Spada"), new Carta(7, "Coppa"), new Carta(8, "Ori"), 
				new Carta(8, "Bastone"), new Carta(8, "Spada"), new Carta(8, "Coppa"), 
				new Carta(9, "Ori"), new Carta(9, "Bastone"), new Carta(9, "Spada"), 
				new Carta(9, "Coppa"), new Carta(10, "Ori"), new Carta(10, "Bastone"), 
				new Carta(10, "Spada"), new Carta(10, "Coppa")};
		Gioco.giocoTestato(mazzo);
	}
	
	@Test
	public void test3() {
		Carta[] mazzo = {new Carta(2, "Spada"), new Carta(1, "Coppa"), 
				new Carta(1, "Bastone"), new Carta(1, "Spada"), new Carta(2, "Ori"), 
				new Carta(1, "Ori"), new Carta(2, "Bastone"), new Carta(2, "Coppa"), 
				new Carta(3, "Ori"), new Carta(3, "Bastone"), new Carta(3, "Coppa"), 
				new Carta(3, "Spada"), new Carta(4, "Spada"), new Carta(4, "Bastone"), 
				new Carta(5, "Bastone"), new Carta(4, "Ori"), new Carta(4, "Coppa"), 
				new Carta(5, "Spada"), new Carta(5, "Ori"), new Carta(6, "Coppa"), 
				new Carta(5, "Coppa"), new Carta(6, "Ori"), new Carta(8, "Coppa"), 
				new Carta(7, "Ori"), new Carta(6, "Bastone"), new Carta(7, "Coppa"), 
				new Carta(8, "Ori"), new Carta(8, "Bastone"), new Carta(6, "Spada"), 
				new Carta(10, "Spada"), new Carta(9, "Spada"), new Carta(8, "Spada"), 
				new Carta(7, "Spada"), new Carta(10, "Bastone"), new Carta(9, "Coppa"), 
				new Carta(9, "Ori"), new Carta(7, "Bastone"), new Carta(10, "Ori"), 
				new Carta(9, "Bastone"), new Carta(10, "Coppa")};
		Gioco.giocoTestato(mazzo);
	}
	
	@Test
	public void test4() {
		Carta[] mazzo = {new Carta(3, "Spada"), new Carta(1, "Spada"), 
				new Carta(2, "Spada"), new Carta(1, "Coppa"), new Carta(3, "Bastone"), 
				new Carta(1, "Ori"), new Carta(2, "Coppa"), new Carta(1, "Bastone"), 
				new Carta(3, "Coppa"), new Carta(4, "Spada"), new Carta(4, "Bastone"), 
				new Carta(2, "Bastone"), new Carta(3, "Ori"), new Carta(6, "Spada"), 
				new Carta(4, "Ori"), new Carta(2, "Ori"), new Carta(4, "Coppa"), 
				new Carta(6, "Ori"), new Carta(5, "Bastone"), new Carta(5, "Spada"), 
				new Carta(7, "Spada"), new Carta(7, "Bastone"), new Carta(5, "Coppa"), 
				new Carta(6, "Coppa"), new Carta(8, "Spada"), new Carta(7, "Ori"), 
				new Carta(5, "Ori"), new Carta(6, "Bastone"), new Carta(8, "Bastone"), 
				new Carta(9, "Spada"), new Carta(10, "Coppa"), new Carta(7, "Coppa"), 
				new Carta(9, "Bastone"), new Carta(9, "Coppa"), new Carta(10, "Bastone"), 
				new Carta(8, "Coppa"), new Carta(9, "Ori"), new Carta(10, "Ori"), 
				new Carta(10, "Spada"), new Carta(8, "Ori")};
		Gioco.giocoTestato(mazzo);
	}
	
	@Test
	public void test5() {
		Carta[] mazzo = {new Carta(3, "Ori"), new Carta(1, "Spada"), 
				new Carta(1, "Bastone"), new Carta(1, "Coppa"), new Carta(4, "Spada"), 
				new Carta(1, "Ori"), new Carta(2, "Coppa"), new Carta(2, "Bastone"), 
				new Carta(5, "Coppa"), new Carta(2, "Ori"), new Carta(2, "Spada"), 
				new Carta(3, "Coppa"), new Carta(5, "Bastone"), new Carta(3, "Spada"), 
				new Carta(3, "Bastone"), new Carta(4, "Bastone"), new Carta(6, "Coppa"), 
				new Carta(4, "Ori"), new Carta(5, "Spada"), new Carta(4, "Coppa"), 
				new Carta(7, "Ori"), new Carta(6, "Bastone"), new Carta(6, "Spada"), 
				new Carta(5, "Ori"), new Carta(8, "Bastone"), new Carta(7, "Bastone"), 
				new Carta(6, "Ori"), new Carta(7, "Spada"), new Carta(8, "Coppa"), 
				new Carta(8, "Ori"), new Carta(8, "Spada"), new Carta(7, "Coppa"), 
				new Carta(10, "Bastone"), new Carta(9, "Ori"), new Carta(9, "Coppa"), 
				new Carta(9, "Spada"), new Carta(10, "Spada"), new Carta(10, "Ori"), 
				new Carta(9, "Bastone"), new Carta(10, "Coppa")};
		Gioco.giocoTestato(mazzo);
	}
	
	@Test
	public void test6() {
		Carta[] mazzo = {new Carta(3, "Ori"), new Carta(1, "Bastone"), 
				new Carta(3, "Coppa"), new Carta(1, "Ori"), new Carta(3, "Spada"), 
				new Carta(2, "Coppa"), new Carta(3, "Bastone"), new Carta(2, "Bastone"), 
				new Carta(5, "Coppa"), new Carta(2, "Ori"), new Carta(2, "Spada"), 
				new Carta(1, "Spada"), new Carta(5, "Bastone"), new Carta(1, "Coppa"), 
				new Carta(4, "Spada"), new Carta(4, "Bastone"), new Carta(6, "Coppa"), 
				new Carta(4, "Ori"), new Carta(5, "Spada"), new Carta(4, "Coppa"), 
				new Carta(7, "Ori"), new Carta(6, "Bastone"), new Carta(6, "Spada"), 
				new Carta(5, "Ori"), new Carta(8, "Bastone"), new Carta(7, "Bastone"), 
				new Carta(6, "Ori"), new Carta(7, "Spada"), new Carta(8, "Coppa"), 
				new Carta(8, "Ori"), new Carta(8, "Spada"), new Carta(7, "Coppa"), 
				new Carta(10, "Bastone"), new Carta(9, "Ori"), new Carta(9, "Coppa"), 
				new Carta(9, "Spada"), new Carta(10, "Spada"), new Carta(10, "Ori"), 
				new Carta(9, "Bastone"), new Carta(10, "Coppa")};
		Gioco.giocoTestato(mazzo);
	}
	
	@Test
	public void test7() {
		Carta[] mazzo = {new Carta(3, "Ori"), new Carta(3, "Spada"), 
				new Carta(3, "Bastone"), new Carta(1, "Ori"), new Carta(3, "Coppa"), 
				new Carta(2, "Coppa"), new Carta(1, "Bastone"), new Carta(2, "Bastone"), 
				new Carta(5, "Coppa"), new Carta(2, "Ori"), new Carta(2, "Spada"), 
				new Carta(1, "Spada"), new Carta(5, "Bastone"), new Carta(1, "Coppa"), 
				new Carta(4, "Spada"), new Carta(4, "Bastone"), new Carta(6, "Coppa"), 
				new Carta(4, "Ori"), new Carta(5, "Spada"), new Carta(4, "Coppa"), 
				new Carta(7, "Ori"), new Carta(6, "Bastone"), new Carta(6, "Spada"), 
				new Carta(5, "Ori"), new Carta(8, "Bastone"), new Carta(7, "Bastone"), 
				new Carta(6, "Ori"), new Carta(7, "Spada"), new Carta(8, "Coppa"), 
				new Carta(8, "Ori"), new Carta(8, "Spada"), new Carta(7, "Coppa"), 
				new Carta(10, "Bastone"), new Carta(9, "Ori"), new Carta(9, "Coppa"), 
				new Carta(9, "Spada"), new Carta(10, "Spada"), new Carta(10, "Ori"), 
				new Carta(9, "Bastone"), new Carta(10, "Coppa")};
		Gioco.giocoTestato(mazzo);
	}
	
}