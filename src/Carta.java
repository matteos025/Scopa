import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Carta {
	
	private static Map<Integer, String> stringhe;

	static {
		stringhe = new HashMap<>();
		stringhe.put(1, "Asso");
		stringhe.put(2, "Due");
		stringhe.put(3, "Tre");
		stringhe.put(4, "Quattro");
		stringhe.put(5, "Cinque");
		stringhe.put(6, "Sei");
		stringhe.put(7, "Sette");
		stringhe.put(8, "Fante");
		stringhe.put(9, "Cavallo");
		stringhe.put(10, "Re");
	}
	
	private int numero;
	private Seme seme;
	
	public Carta(int numero, String sem) {
		if (numero < 1 || numero > 10) {
			throw new IllegalArgumentException();
		} else {
			this.numero = numero;
		}
		if (sem.equals("Ori")) {
			seme = Seme.Ori;
		} else if (sem.equals("Bastone")) {
			seme = Seme.Bastoni;
		} else if (sem.equals("Spada")) {
			seme = Seme.Spade;
		} else if (sem.equals("Coppa")) {
			seme = Seme.Coppe;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public int prendiNumero() {
		return this.numero;
	}
	
	public Seme prendiSeme() {
		return this.seme;
	}
	
	public String prendiStringa() {
		return stringhe.get(numero);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Carta)) {
			return false;
		}
		Carta carta = (Carta) o;
		return carta.prendiNumero() == numero && 
				carta.prendiSeme() == seme;
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(numero, seme);
	}
}