import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Gioco {

	private static String primo = "primo";
	private static String secondo = "secondo";
	private static String terzo = "terzo";
	private static String quarto = "quarto";
	
	private static Scanner in = new Scanner(System.in);
	
	private static Map<Integer, Integer> puntiPrimiera;
	private static Map<Integer, String> nomiGiocatori;
	
	static {
		puntiPrimiera = new HashMap<>();
		puntiPrimiera.put(7, 21);
		puntiPrimiera.put(6, 18);
		puntiPrimiera.put(1, 16);
		puntiPrimiera.put(5, 15);
		puntiPrimiera.put(4, 14);
		puntiPrimiera.put(3, 13);
		puntiPrimiera.put(2, 12);
		puntiPrimiera.put(8, 10);
		puntiPrimiera.put(9, 10);
		puntiPrimiera.put(10, 10);
		nomiGiocatori = new HashMap<>();
	}

	public static int conversionePrimiera(int val) {
		return puntiPrimiera.get(val);
	}
	
	private static Carta[] ordinaMazzo() {
		int count = 39;
		List<Carta> mazzo = new ArrayList<>();
		mazzo.add(new Carta(1, "Ori"));
		mazzo.add(new Carta(1, "Bastone"));
		mazzo.add(new Carta(1, "Spada"));
		mazzo.add(new Carta(1, "Coppa"));
		mazzo.add(new Carta(2, "Ori"));
		mazzo.add(new Carta(2, "Bastone"));
		mazzo.add(new Carta(2, "Spada"));
		mazzo.add(new Carta(2, "Coppa"));
		mazzo.add(new Carta(3, "Ori"));
		mazzo.add(new Carta(3, "Bastone"));
		mazzo.add(new Carta(3, "Spada"));
		mazzo.add(new Carta(3, "Coppa"));
		mazzo.add(new Carta(4, "Ori"));
		mazzo.add(new Carta(4, "Bastone"));
		mazzo.add(new Carta(4, "Spada"));
		mazzo.add(new Carta(4, "Coppa"));
		mazzo.add(new Carta(5, "Ori"));
		mazzo.add(new Carta(5, "Bastone"));
		mazzo.add(new Carta(5, "Spada"));
		mazzo.add(new Carta(5, "Coppa"));
		mazzo.add(new Carta(6, "Ori"));
		mazzo.add(new Carta(6, "Bastone"));
		mazzo.add(new Carta(6, "Spada"));
		mazzo.add(new Carta(6, "Coppa"));
		mazzo.add(new Carta(7, "Ori"));
		mazzo.add(new Carta(7, "Bastone"));
		mazzo.add(new Carta(7, "Spada"));
		mazzo.add(new Carta(7, "Coppa"));
		mazzo.add(new Carta(8, "Ori"));
		mazzo.add(new Carta(8, "Bastone"));
		mazzo.add(new Carta(8, "Spada"));
		mazzo.add(new Carta(8, "Coppa"));
		mazzo.add(new Carta(9, "Ori"));
		mazzo.add(new Carta(9, "Bastone"));
		mazzo.add(new Carta(9, "Spada"));
		mazzo.add(new Carta(9, "Coppa"));
		mazzo.add(new Carta(10, "Ori"));
		mazzo.add(new Carta(10, "Bastone"));
		mazzo.add(new Carta(10, "Spada"));
		mazzo.add(new Carta(10, "Coppa"));

		Carta[] mazzoRandom = new Carta[40];
		
		//Ordine carte
		while (count > -1) {
			Carta carta = mazzo.remove((int) (Math.round(Math.random() * count)));
			mazzoRandom[count] = carta;
			count--;
		}
		
		return mazzoRandom;
	}
	
	private static void distribuisciMazzo(Giocatore giocatori[], Carta mazzo[]) {
		int count = 0;
		while (count < 40) {
			Carta carta = mazzo[count];
			if (count % 4 == 0) {
				giocatori[0].aggiungi(carta);
			} else if (count % 4 == 1) {
				giocatori[1].aggiungi(carta);
			} else if (count % 4 == 2) {
				giocatori[2].aggiungi(carta);
			} else {
				giocatori[3].aggiungi(carta);
			}
			count++;
		}
	}

	public static void main(String[] args) {
		while (true) {
			
			//Ordina il mazzo di carte
			Carta[] mazzoRandom = ordinaMazzo();
			
			System.out.println("0 / 1 / 2 / 3 / 4 giocatori?");
			
			//Aspetta input dal terminale
			if (in.hasNext()) {
				
				String str = in.next();
				
				if (str.length() == 1) {
					char c = str.charAt(0);
					
					//Input non e' un numero da 0 a 4
					if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4') {
						System.out.println("Input errato");
						continue;
					}
					
					//Inizializza giocatori e ordine in cui iniziano
					nomiGiocatori.put(1, "Antonio");
					nomiGiocatori.put(2, "Marco");
					nomiGiocatori.put(3, "Giuseppe");
					nomiGiocatori.put(4, "Alessandro");
					
					if (c == '0') { //Gioco tra 4 computer
						
						System.out.println("Hai scelto 0 giocatori");
						giocoTestato(mazzoRandom);
						continue;
						
					} else { //Gioco con giocatori dal terminale
						
						System.out.println("Hai scelto " + c + " giocatore");
						System.out.println("Inserisci il tuo nome:");
						
						if (in.hasNext()) { //Ottieni i nomi del giocatore dal terminale
							String name = in.next();
							
							//Determina la posizione del primo giocatore da inserire
							int num = (int) (Math.round(Math.random() * 3));
							nomiGiocatori.put(num + 1, name);
							
							System.out.print("Benvenuto " + name + ", sarai il ");
							if (num == 0) {
								System.out.print(primo);
							} else if (num == 1) {
								System.out.print(secondo);
							} else if (num == 2) {
								System.out.print(terzo);
							} else {
								System.out.print(quarto);
							}
							System.out.println(" a giocare");
							if (c == '1') {
								giocoUnoTestato(mazzoRandom, num);
								continue;
							}
							System.out.println("Inserisci il nome del secondo giocatore:");
							
							//TODO: c == '2' | '3' | '4'
							if (c == '2') {
								System.out.println("Not yet implemented");
								continue;
							}
							System.out.println("Inserisci il nome del terzo giocatore:");
							if (c == '3') {
								System.out.println("Not yet implemented");
								continue;
							}
							System.out.println("Inserisci il nome del quarto giocatore:");
							if (c == '4') {
								System.out.println("Not yet implemented");
								continue;
							}
						}
					}
				} else {
					if (str.equals("quit")) { //Exit code for the program
						return;
					} else { //Wrong input
						System.out.println("Input errato");
					}
				}
			}
		}
	}
	
	public static void giocoUnoTestato(Carta[] mazzo, int num) {
		//Punti in comune di giocatori 1 e 3 e giocatori 2 e 4
		Punti punti1e3 = new Punti();
		Punti punti2e4 = new Punti();

		//4 giocatori
		Giocatore gioc1 = new Giocatore(punti1e3);
		Giocatore gioc2 = new Giocatore(punti2e4);
		Giocatore gioc3 = new Giocatore(punti1e3);
		Giocatore gioc4 = new Giocatore(punti2e4);
		
		//Settaggio di partner e posizione avversari
		gioc1.aggiungiPartner(gioc3);
		gioc1.aggiungiDopo(gioc2);
		gioc2.aggiungiPartner(gioc4);
		gioc2.aggiungiDopo(gioc3);
		gioc3.aggiungiPartner(gioc1);
		gioc3.aggiungiDopo(gioc4);
		gioc4.aggiungiPartner(gioc2);
		gioc4.aggiungiDopo(gioc1);
		
		//Array di giocatori
		Giocatore[] giocatori = {gioc1, gioc2, gioc3, gioc4};
		
		//Distribuzione carte
		distribuisciMazzo(giocatori, mazzo);
		
		//Counter per il numero di carte ancora da giocare
		int count = 39;

		//Carte giocate sul tavolo e non ancora prese
		Set<Carta> tavolo = new HashSet<Carta>();
		
		//Tutte le somme di carte possibili
		Map<Integer, List<Set<Carta>>> somme = new HashMap<>();
		
		//Partita
		while (count > -1) {
			Giocatore giocatore;
			List<Carta> carta = null;
			int index;

			//Seleziona giocatore a cui spetta giocare
			if (count % 4 == 0) {
				giocatore = giocatori[3];
				index = 4;
			} else if (count % 4 == 1) {
				giocatore = giocatori[2];
				index = 3;
			} else if (count % 4 == 2) {
				giocatore = giocatori[1];
				index = 2;
			} else {
				giocatore = giocatori[0];
				index = 1;
			}
			
			if (index - 1 == num) { //Giocatore da terminale
				int x = 1;
				System.out.println("Le tue opzioni :");
				for (Carta card: giocatori[num].inMano()) {
					System.out.println(x + " - " + card.prendiStringa() + " " + card.prendiSeme() + " ");
					x++;
				}
				System.out.println("");
				System.out.println("Che carta vuoi giocare?");
				while (true) {
					if (in.hasNext()) {
						String str = in.next();
						if (str.length() <= 2) {
							int c = Integer.parseInt(str);
							//TODO: Add condition for when this isn't true
							if (c > 0 && c < 11) {
								carta = giocatore.gioca(c, somme);
							} else {
								break;
							}
						}
						break;
					}
				}
			} else { //Computer sceglie carta da giocare e carte da prendere
				carta = giocatore.gioca(tavolo, somme);
			}

			//Altri giocatori registrano carta giocata
			for (int i = 0; i < 4; i++) {
				if (giocatori[i] != giocatore) {
					giocatori[i].registraCarta(carta.get(0), tavolo, somme, giocatore);
				}
			}

			System.out.println("Carta giocata da giocatore " + nomiGiocatori.get(index) + ": " + 
					carta.get(0).prendiStringa() + " " + carta.get(0).prendiSeme());

			//Pulire il tavolo, attribuire punti
			boolean preso = false;
			for (Carta cart : tavolo) {
				if (cart.prendiNumero() == carta.get(0).prendiNumero()) {
					preso = true;
					tavolo.remove(cart);
					for (int i = 0; i < 4; i++) {
						giocatori[i].ultimoAPrendere = false;
					}
					giocatore.ultimoAPrendere = true;
					List<Set<Carta>> lista = somme.remove(cart.prendiNumero());
					int i = -1;
					for (Set<Carta> lista2 : lista) {
						if (lista2.size() == 1) {
							i = lista.indexOf(lista2);
						}
					}
					lista.remove(i);

					Map<Integer, List<Set<Carta>>> toDelete = new HashMap<>();
					for (Map.Entry<Integer, List<Set<Carta>>> entry : somme.entrySet()) {
						for (Set<Carta> listeDel : entry.getValue()) {
							if (listeDel.contains(cart)) {
								List<Set<Carta>> modifica = toDelete.remove(entry.getKey());
								if (modifica == null) {
									modifica = new ArrayList<>();
								}
								modifica.add(listeDel);
								toDelete.put(entry.getKey(), modifica);
							}
						}
					}

					for (Map.Entry<Integer, List<Set<Carta>>> entry: toDelete.entrySet()) {
						List<Set<Carta>> toRemove = somme.remove(entry.getKey());
						for (Set<Carta> equalSet : entry.getValue()) {
							toRemove.remove(equalSet);
						}
						if (toRemove.size() != 0) {
							somme.put(entry.getKey(), toRemove);
						}
					}


					if (lista.size() != 0) {
						somme.put(carta.get(0).prendiNumero(), lista);
					}
					if ((carta.get(0).prendiNumero() == 7 && (carta.get(0).prendiSeme() == Seme.Ori)) 
							|| ((cart.prendiNumero() == 7) && (cart.prendiSeme() == Seme.Ori))) {
						giocatore.prendiPunti().presoSetteBello();
						System.out.println("Sette bello");
					}
					if ((carta.get(0).prendiSeme() == Seme.Ori) || (cart.prendiSeme() == Seme.Ori)) {
						giocatore.prendiPunti().presoOri(1);
					}
					if (tavolo.size() == 0 && count > 0) {
						giocatore.prendiPunti().scopa();
						System.out.println("Scopa");
					}
					Set<Carta> cartePrese = new HashSet<>();
					cartePrese.add(carta.get(0));
					cartePrese.add(cart);
					giocatore.prendiPunti().aggiornaPrimiera(cartePrese);
					giocatore.prendiPunti().presoCarte(2);
					break;
				}
			}
			if (!preso) {
				if (tavolo.size() == 0) {
					Set<Carta> lst = new HashSet<>();
					lst.add(carta.get(0));
					List<Set<Carta>> lstOfLst = new ArrayList<>();
					lstOfLst.add(lst);
					somme.put(carta.get(0).prendiNumero(), lstOfLst);
					tavolo.add(carta.get(0));
				} else {
					if (somme.containsKey(carta.get(0).prendiNumero())) {
						for (int i = 0; i < 4; i++) {
							giocatori[i].ultimoAPrendere = false;
						}
						giocatore.ultimoAPrendere = true;
						List<Set<Carta>> lstDaRimuovere = somme.remove(carta.get(0).prendiNumero());
						Set<Carta> prese = new HashSet<>();
						int sum = 0;
						for (Carta car : carta) {
							if (!carta.get(0).equals(car)) {
								if (!tavolo.contains(car)) {
									throw new IllegalArgumentException();
								}
								prese.add(car);
								sum += car.prendiNumero();
							}
						}
						if (carta.get(0).prendiNumero() != sum) {
							throw new IllegalArgumentException(sum + " " + carta.get(0).prendiNumero());
						}
						lstDaRimuovere.remove(prese);
						if (lstDaRimuovere.size() != 0) {
							somme.put(carta.get(0).prendiNumero(), lstDaRimuovere);
						}
						Set<Carta> cartePrese = new HashSet<>();
						cartePrese.add(carta.get(0));
						for (Carta cartaPresa : prese) {
							tavolo.remove(cartaPresa);
							if ((cartaPresa.prendiNumero() == 7 && 
									(cartaPresa.prendiSeme() == Seme.Ori))) {
								giocatore.prendiPunti().presoSetteBello();;
							}
							if ((cartaPresa.prendiSeme() == Seme.Ori)) {
								giocatore.prendiPunti().presoOri(1);
							}
							cartePrese.add(cartaPresa);
						}
						Map<Integer, List<Set<Carta>>> toDelete = new HashMap<>();
						for (Carta card : cartePrese) {
							if (!card.equals(carta.get(0))) {
								for (Map.Entry<Integer, List<Set<Carta>>> entry : somme.entrySet()) {
									for (Set<Carta> listeDel : entry.getValue()) {
										if (listeDel.contains(card)) {
											List<Set<Carta>> modifica = toDelete.remove(entry.getKey());
											if (modifica == null) {
												modifica = new ArrayList<>();
											}
											if (modifica.contains(listeDel)) {
												toDelete.put(entry.getKey(), modifica);
												continue;
											}
											modifica.add(listeDel);
											toDelete.put(entry.getKey(), modifica);
										}
									}
								}
							}
						}
						for (Map.Entry<Integer, List<Set<Carta>>> entry: toDelete.entrySet()) {
							List<Set<Carta>> toRemove = somme.remove(entry.getKey());
							for (Set<Carta> equalSet : entry.getValue()) {
								toRemove.remove(equalSet);
							}
							if (toRemove.size() != 0) {
								somme.put(entry.getKey(), toRemove);
							}
						}

						if ((carta.get(0).prendiNumero() == 7 && (carta.get(0).prendiSeme() == Seme.Ori))) {
							giocatore.prendiPunti().presoSetteBello();;
						}
						if ((carta.get(0).prendiSeme() == Seme.Ori)) {
							giocatore.prendiPunti().presoOri(1);
						}
						if (tavolo.size() == 0 && count > 0) {
							giocatore.prendiPunti().scopa();
							System.out.println("Scopa");
						}
						giocatore.prendiPunti().aggiornaPrimiera(cartePrese);
						giocatore.prendiPunti().presoCarte(cartePrese.size());
					} else {
						Map<Integer, List<Set<Carta>>> sommeDaAggiungere = new HashMap<>();
						for (Map.Entry<Integer, List<Set<Carta>>> entry : somme.entrySet()) {
							if (entry.getKey() + carta.get(0).prendiNumero() <= 10) {
								for (Set<Carta> lst : entry.getValue()) {
									Set<Carta> newLst = new HashSet<>();
									newLst.add(carta.get(0));
									for (Carta elt : lst) {
										newLst.add(elt);
									}
									List<Set<Carta>> listaDaAggiungere = sommeDaAggiungere
											.get(entry.getKey() + carta.get(0).prendiNumero());
									if (listaDaAggiungere == null) {
										listaDaAggiungere = new ArrayList<>();
									}
									listaDaAggiungere.add(newLst);
									sommeDaAggiungere.put(entry.getKey() + carta.get(0).prendiNumero(), 
											listaDaAggiungere);
								}
							}
						}
						for (Map.Entry<Integer, List<Set<Carta>>> entry : sommeDaAggiungere
								.entrySet()) {
							List<Set<Carta>> carteInPiu = somme.remove(entry.getKey());
							if (carteInPiu == null) {
								carteInPiu = new ArrayList<>();
							}
							for (Set<Carta> liste : entry.getValue()) {
								carteInPiu.add(liste);
							}
							somme.put(entry.getKey(), carteInPiu);
						}
						List<Set<Carta>> listaNuova = somme.remove(carta.get(0).prendiNumero());
						if (listaNuova == null) {
							listaNuova = new ArrayList<>();
						}
						Set<Carta> cartaNuova = new HashSet<>();
						cartaNuova.add(carta.get(0));
						listaNuova.add(cartaNuova);
						somme.put(carta.get(0).prendiNumero(), listaNuova);
						tavolo.add(carta.get(0));
					}
				}
			}

			System.out.print("Tavolo: ");
			for (Carta cart : tavolo) {
				System.out.print(cart.prendiStringa() + " " + cart.prendiSeme() + ", ");
			}
			System.out.println("");
			count--;
		}
		
		Giocatore prendePerUltimo;
		if (giocatori[0].ultimoAPrendere) {
			prendePerUltimo = giocatori[0];
		} else if (giocatori[1].ultimoAPrendere) {
			prendePerUltimo = giocatori[1];
		} else if (giocatori[2].ultimoAPrendere) {
			prendePerUltimo = giocatori[2];
		} else {
			prendePerUltimo = giocatori[3];
		}
		for (Carta carta : tavolo) {
			if ((carta.prendiNumero() == 7 && (carta.prendiSeme() == Seme.Ori))) {
				prendePerUltimo.prendiPunti().presoSetteBello();
			}
			if ((carta.prendiSeme() == Seme.Ori)) {
				prendePerUltimo.prendiPunti().presoOri(1);
			}
		}

		prendePerUltimo.prendiPunti().aggiornaPrimiera(tavolo);
		prendePerUltimo.prendiPunti().presoCarte(tavolo.size());

		tavolo = new HashSet<>();

		int prim1 = giocatori[0].prendiPunti().valorePrimiera();
		int prim2 = giocatori[1].prendiPunti().valorePrimiera();

		if (prim1 > prim2) {
			giocatori[0].prendiPunti().primieraVinta();
		} else if (prim2 > prim1) {
			giocatori[1].prendiPunti().primieraVinta();
		}

		System.out.println(nomiGiocatori.get(1) + " e " + nomiGiocatori.get(3) + 
						   ": "  + giocatori[0].prendiPunti().prendiPunti() + " punti");
		System.out.println(nomiGiocatori.get(2) + " e " + nomiGiocatori.get(4) + 
				   		   ": "  + giocatori[1].prendiPunti().prendiPunti() + " punti");
		
	}

	public static void giocoTestato(Carta[] mazzo) {
		Punti punti1e3 = new Punti();
		Punti punti2e4 = new Punti();
		
		Giocatore gioc1 = new Giocatore(punti1e3);
		Giocatore gioc2 = new Giocatore(punti2e4);
		Giocatore gioc3 = new Giocatore(punti1e3);
		Giocatore gioc4 = new Giocatore(punti2e4);

		gioc1.aggiungiPartner(gioc3);
		gioc1.aggiungiDopo(gioc2);
		gioc2.aggiungiPartner(gioc4);
		gioc2.aggiungiDopo(gioc3);
		gioc3.aggiungiPartner(gioc1);
		gioc3.aggiungiDopo(gioc4);
		gioc4.aggiungiPartner(gioc2);
		gioc4.aggiungiDopo(gioc1);
		Giocatore[] giocatori = {gioc1, gioc2, gioc3, gioc4};

		distribuisciMazzo(giocatori, mazzo);

		for (int i = 0; i < 4; i++) {
			System.out.println("Carte di " + nomiGiocatori.get(i + 1) + ":");
			for (Carta carta: giocatori[i].inMano()) {
				System.out.print(" " + carta.prendiStringa() + " di " + carta.prendiSeme() + ",");
			}
			System.out.println("");
		}

		int count = 39;

		Set<Carta> tavolo = new HashSet<Carta>();
		Map<Integer, List<Set<Carta>>> somme = new HashMap<>();

		//Partita
		while (count > -1) {
			Giocatore giocatore;
			List<Carta> carta;
			int index;

			//A chi spetta giocare?
			if (count % 4 == 0) {
				giocatore = giocatori[3];
				index = 4;
			} else if (count % 4 == 1) {
				giocatore = giocatori[2];
				index = 3;
			} else if (count % 4 == 2) {
				giocatore = giocatori[1];
				index = 2;
			} else {
				giocatore = giocatori[0];
				index = 1;
			}

			//Giocatore sceglie carta da giocare e carte da prendere
			carta = giocatore.gioca(tavolo, somme);

			//Altri giocatori registrano carta giocata
			for (int i = 0; i < 4; i++) {
				if (giocatori[i] != giocatore) {
					giocatori[i].registraCarta(carta.get(0), tavolo, somme, giocatore);
				}
			}

			System.out.println("Carta giocata da " + nomiGiocatori.get(index) + ": " + 
					carta.get(0).prendiStringa() + " " + carta.get(0).prendiSeme());

			//Pulire il tavolo, attribuire punti
			boolean preso = false;
			for (Carta cart : tavolo) {
				if (cart.prendiNumero() == carta.get(0).prendiNumero()) {
					preso = true;
					tavolo.remove(cart);
					
					//Setta ultimo a prendere
					for (int i = 0; i < 4; i++) {
						giocatori[i].ultimoAPrendere = false;
					}
					giocatore.ultimoAPrendere = true;
					
					List<Set<Carta>> lista = somme.remove(cart.prendiNumero());
					int i = -1;
					for (Set<Carta> lista2 : lista) {
						if (lista2.size() == 1) {
							i = lista.indexOf(lista2);
						}
					}
					
					lista.remove(i);
					
					Map<Integer, List<Set<Carta>>> toDelete = new HashMap<>();
					for (Map.Entry<Integer, List<Set<Carta>>> entry : somme.entrySet()) {
						for (Set<Carta> listeDel : entry.getValue()) {
							if (listeDel.contains(cart)) {
								List<Set<Carta>> modifica = toDelete.remove(entry.getKey());
								if (modifica == null) {
									modifica = new ArrayList<>();
								}
								modifica.add(listeDel);
								toDelete.put(entry.getKey(), modifica);
							}
						}
					}

					for (Map.Entry<Integer, List<Set<Carta>>> entry: toDelete.entrySet()) {
						List<Set<Carta>> toRemove = somme.remove(entry.getKey());
						for (Set<Carta> equalSet : entry.getValue()) {
							toRemove.remove(equalSet);
						}
						if (toRemove.size() != 0) {
							somme.put(entry.getKey(), toRemove);
						}
					}

					if (lista.size() != 0) {
						somme.put(carta.get(0).prendiNumero(), lista);
					}
					if ((carta.get(0).prendiNumero() == 7 && (carta.get(0).prendiSeme() == Seme.Ori)) 
							|| ((cart.prendiNumero() == 7) && (cart.prendiSeme() == Seme.Ori))) {
						giocatore.prendiPunti().presoSetteBello();
						System.out.println("Sette bello");
					}
					if ((carta.get(0).prendiSeme() == Seme.Ori) || (cart.prendiSeme() == Seme.Ori)) {
						giocatore.prendiPunti().presoOri(1);
					}
					if (tavolo.size() == 0 && count > 0) {
						giocatore.prendiPunti().scopa();
						System.out.println("Scopa");
					}
					Set<Carta> cartePrese = new HashSet<>();
					cartePrese.add(carta.get(0));
					cartePrese.add(cart);
					giocatore.prendiPunti().aggiornaPrimiera(cartePrese);
					giocatore.prendiPunti().presoCarte(2);
					break;
				}
			}
			if (!preso) {
				if (tavolo.size() == 0) {
					Set<Carta> lst = new HashSet<>();
					lst.add(carta.get(0));
					List<Set<Carta>> lstOfLst = new ArrayList<>();
					lstOfLst.add(lst);
					somme.put(carta.get(0).prendiNumero(), lstOfLst);
					tavolo.add(carta.get(0));
				} else {
					if (somme.containsKey(carta.get(0).prendiNumero())) {
						for (int i = 0; i < 4; i++) {
							giocatori[i].ultimoAPrendere = false;
						}
						giocatore.ultimoAPrendere = true;
						List<Set<Carta>> lstDaRimuovere = somme.remove(carta.get(0).prendiNumero());
						Set<Carta> prese = new HashSet<>();
						int sum = 0;
						for (Carta car : carta) {
							if (!carta.get(0).equals(car)) {
								if (!tavolo.contains(car)) {
									throw new IllegalArgumentException();
								}
								prese.add(car);
								sum += car.prendiNumero();
							}
						}
						if (carta.get(0).prendiNumero() != sum) {
							throw new IllegalArgumentException(sum + " " + carta.get(0).prendiNumero());
						}
						lstDaRimuovere.remove(prese);
						if (lstDaRimuovere.size() != 0) {
							somme.put(carta.get(0).prendiNumero(), lstDaRimuovere);
						}
						Set<Carta> cartePrese = new HashSet<>();
						cartePrese.add(carta.get(0));
						for (Carta cartaPresa : prese) {
							tavolo.remove(cartaPresa);
							if ((cartaPresa.prendiNumero() == 7 && 
									(cartaPresa.prendiSeme() == Seme.Ori))) {
								giocatore.prendiPunti().presoSetteBello();;
							}
							if ((cartaPresa.prendiSeme() == Seme.Ori)) {
								giocatore.prendiPunti().presoOri(1);
							}
							cartePrese.add(cartaPresa);
						}
						Map<Integer, List<Set<Carta>>> toDelete = new HashMap<>();
						for (Carta card : cartePrese) {
							if (!card.equals(carta.get(0))) {
								for (Map.Entry<Integer, List<Set<Carta>>> entry : somme.entrySet()) {
									for (Set<Carta> listeDel : entry.getValue()) {
										if (listeDel.contains(card)) {
											List<Set<Carta>> modifica = toDelete.remove(entry.getKey());
											if (modifica == null) {
												modifica = new ArrayList<>();
											}
											if (modifica.contains(listeDel)) {
												toDelete.put(entry.getKey(), modifica);
												continue;
											}
											modifica.add(listeDel);
											toDelete.put(entry.getKey(), modifica);
										}
									}
								}
							}
						}
						for (Map.Entry<Integer, List<Set<Carta>>> entry: toDelete.entrySet()) {
							List<Set<Carta>> toRemove = somme.remove(entry.getKey());
							for (Set<Carta> equalSet : entry.getValue()) {
								toRemove.remove(equalSet);
							}
							if (toRemove.size() != 0) {
								somme.put(entry.getKey(), toRemove);
							}
						}

						if ((carta.get(0).prendiNumero() == 7 && (carta.get(0).prendiSeme() == Seme.Ori))) {
							giocatore.prendiPunti().presoSetteBello();;
						}
						if ((carta.get(0).prendiSeme() == Seme.Ori)) {
							giocatore.prendiPunti().presoOri(1);
						}
						if (tavolo.size() == 0 && count > 0) {
							giocatore.prendiPunti().scopa();
							System.out.println("Scopa");
						}
						giocatore.prendiPunti().aggiornaPrimiera(cartePrese);
						giocatore.prendiPunti().presoCarte(cartePrese.size());
					} else {
						Map<Integer, List<Set<Carta>>> sommeDaAggiungere = new HashMap<>();
						for (Map.Entry<Integer, List<Set<Carta>>> entry : somme.entrySet()) {
							if (entry.getKey() + carta.get(0).prendiNumero() <= 10) {
								for (Set<Carta> lst : entry.getValue()) {
									Set<Carta> newLst = new HashSet<>();
									newLst.add(carta.get(0));
									for (Carta elt : lst) {
										newLst.add(elt);
									}
									List<Set<Carta>> listaDaAggiungere = sommeDaAggiungere
											.get(entry.getKey() + carta.get(0).prendiNumero());
									if (listaDaAggiungere == null) {
										listaDaAggiungere = new ArrayList<>();
									}
									listaDaAggiungere.add(newLst);
									sommeDaAggiungere.put(entry.getKey() + carta.get(0).prendiNumero(), 
											listaDaAggiungere);
								}
							}
						}
						for (Map.Entry<Integer, List<Set<Carta>>> entry : sommeDaAggiungere
								.entrySet()) {
							List<Set<Carta>> carteInPiu = somme.remove(entry.getKey());
							if (carteInPiu == null) {
								carteInPiu = new ArrayList<>();
							}
							for (Set<Carta> liste : entry.getValue()) {
								carteInPiu.add(liste);
							}
							somme.put(entry.getKey(), carteInPiu);
						}
						List<Set<Carta>> listaNuova = somme.remove(carta.get(0).prendiNumero());
						if (listaNuova == null) {
							listaNuova = new ArrayList<>();
						}
						Set<Carta> cartaNuova = new HashSet<>();
						cartaNuova.add(carta.get(0));
						listaNuova.add(cartaNuova);
						somme.put(carta.get(0).prendiNumero(), listaNuova);
						tavolo.add(carta.get(0));
					}
				}
			}

			System.out.print("Tavolo: ");
			for (Carta cart : tavolo) {
				System.out.print(cart.prendiStringa() + " " + cart.prendiSeme() + ", ");
			}
			System.out.println("");
			count--;
		}

		Giocatore prendePerUltimo;
		if (giocatori[0].ultimoAPrendere) {
			prendePerUltimo = giocatori[0];
		} else if (giocatori[1].ultimoAPrendere) {
			prendePerUltimo = giocatori[1];
		} else if (giocatori[2].ultimoAPrendere) {
			prendePerUltimo = giocatori[2];
		} else {
			prendePerUltimo = giocatori[3];
		}
		for (Carta carta : tavolo) {
			if ((carta.prendiNumero() == 7 && (carta.prendiSeme() == Seme.Ori))) {
				prendePerUltimo.prendiPunti().presoSetteBello();
			}
			if ((carta.prendiSeme() == Seme.Ori)) {
				prendePerUltimo.prendiPunti().presoOri(1);
			}
		}

		prendePerUltimo.prendiPunti().aggiornaPrimiera(tavolo);
		prendePerUltimo.prendiPunti().presoCarte(tavolo.size());

		tavolo = new HashSet<>();

		int prim1 = giocatori[0].prendiPunti().valorePrimiera();
		int prim2 = giocatori[1].prendiPunti().valorePrimiera();

		//System.out.println(prim1 + " " + prim2);
		if (prim1 > prim2) {
			giocatori[0].prendiPunti().primieraVinta();
		} else if (prim2 > prim1) {
			giocatori[1].prendiPunti().primieraVinta();
		}

		System.out.println(nomiGiocatori.get(1) + " e " + nomiGiocatori.get(3) + 
						   ": "  + giocatori[0].prendiPunti().prendiPunti() + " punti");
		System.out.println(nomiGiocatori.get(2) + " e " + nomiGiocatori.get(4) + 
				   		   ": "  + giocatori[1].prendiPunti().prendiPunti() + " punti");
	}
}