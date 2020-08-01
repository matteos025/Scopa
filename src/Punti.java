import java.util.Set;

public class Punti {
	private int puntiDiMazzo;
	private int numeroCarte;
	private int numeroOri;
	private boolean setteBello;
	private boolean oriFatti;
	private boolean carteFatte;
	private boolean primieraFatta;
	private int scope;
	private int primieraOri;
	private int primieraBastoni;
	private int primieraCoppe;
	private int primieraSpade;
	
	public Punti() {
		puntiDiMazzo = 0;
		numeroCarte = 0;
		numeroOri = 0;
		setteBello = false;
		oriFatti = false;
		carteFatte = false;
		primieraFatta = false;
		scope = 0;
		primieraOri = 0;
		primieraBastoni = 0;
		primieraCoppe = 0;
		primieraSpade = 0;
	}
	
	public int prendiPunti() {
		return puntiDiMazzo;
	}
	
	public void presoSetteBello() {
		if (setteBello) {
			throw new IllegalArgumentException();
		}
		puntiDiMazzo++;
		setteBello = true;
	}
	
	public void presoOri(int increm) {
		numeroOri += increm;
		if (!oriFatti && numeroOri > 5) {
			oriFatti = true;
			puntiDiMazzo++;
		}
	}
	
	public void presoCarte(int increm) {
		numeroCarte += increm;
		if (!carteFatte && numeroCarte > 20) {
			carteFatte = true;
			puntiDiMazzo++;
		}
	}
	
	public void scopa() {
		scope++;
		puntiDiMazzo++;
	}
	
	
	public void aggiornaPrimiera(Set<Carta> cartePrese) {
		for (Carta carta: cartePrese) {
			Seme seme = carta.prendiSeme();
			int valore = Gioco.conversionePrimiera(carta.prendiNumero());
			if (seme == Seme.Ori) {
				primieraOri = Math.max(primieraOri, valore);
			} else if (seme == Seme.Bastoni) {
				primieraBastoni = Math.max(primieraBastoni, valore);
			} else if (seme == Seme.Coppe) {
				primieraCoppe = Math.max(primieraCoppe, valore);
			} else {
				primieraSpade = Math.max(primieraSpade, valore);
			}
		}
	}
	
	public int valorePrimiera() {
		return primieraOri + primieraBastoni + primieraCoppe + primieraSpade;
	}
	
	public void primieraVinta() {
		primieraFatta = true;
		puntiDiMazzo++;
	}
	
	public boolean primieraSiONo() {
		return primieraFatta;
	}
	
	public int numeroScope() {
		return scope;
	}
	
	public boolean vintoOri() {
		return oriFatti;
	}
	
	public boolean vintoCarte() {
		return carteFatte;
	}
	
	public boolean vintoSetteBello() {
		return setteBello;
	}
	
	public int cartePrese() {
		return numeroCarte;
	}
	
	public int rimpiazzaPrimiera(Set<Carta> carte) {
		int Bastoni = primieraBastoni;
		int Ori = primieraOri;
		int Coppe = primieraCoppe;
		int spada = primieraSpade;
		for (Carta carta : carte) {
			Seme seme = carta.prendiSeme();
			int num = carta.prendiNumero();
			if (seme == Seme.Bastoni) {
				Bastoni = Math.max(Bastoni, Gioco.conversionePrimiera(num));
			} else if (seme == Seme.Ori) {
				Ori = Math.max(Ori, Gioco.conversionePrimiera(num));
			} else if (seme == Seme.Coppe) {
				Coppe = Math.max(Coppe, Gioco.conversionePrimiera(num));
			} else {
				spada = Math.max(spada, Gioco.conversionePrimiera(num));
			}
		}
		return Bastoni + Ori + Coppe + spada;
	}
}
