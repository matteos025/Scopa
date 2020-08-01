import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Giocatore {
	
	private List<Carta> inMano;
	private int[] giocateoInMano = new int[10];
	private Giocatore partner;
	private Giocatore avvDopo;
	private Giocatore avvPrima;
	private Punti punti;
	public boolean ultimoAPrendere;
	public int[] cartaSi = new int[10]; //-2 no, -1 preferibilmente altra, 0 nessun giudizio, 
									    //1 preferibilmente si, 2 si
	
	public Giocatore(Punti punti) {
		inMano = new ArrayList<Carta>();
		ultimoAPrendere = false;
		for (int i = 0; i < 10; i++) {
			giocateoInMano[i] = 0;
			cartaSi[i] = (i == 6) ? -1 : 0;
		}
		this.punti = punti;
	}
	
	public Giocatore prendiPartner() {
		return partner;
	}
	
	public void aggiungiPartner(Giocatore partner) {
		this.partner = partner;
	}
	
	public void aggiungiDopo(Giocatore avvDopo) {
		this.avvDopo = avvDopo;
	}
	
	public void aggiungiPrima(Giocatore avvPrima) {
		this.avvPrima = avvPrima;
	}
	
	public void aggiungi(Carta carta) {
		inMano.add(carta);
		giocateoInMano[carta.prendiNumero() - 1]++;
		if (inMano.size() == 10) {
			ordina();
		}
	}
	
	public List<Carta> gioca(int i, Map<Integer, List<Set<Carta>>> somme) {
		
		// Carta giocata e carte da prendere
		List<Carta> daRitornare = new ArrayList<>();
		
		if (inMano.size() >= i) { //Se abbiamo spazio per ricevere
			
			//Aggiungi carta da giocare all'inizio della lista da ritornare
			Carta carta = inMano.remove(i - 1);
			daRitornare.add(0, carta);
			
			List<Set<Carta>> lst = somme.get(carta.prendiNumero());
			if (lst != null) { //Condizione in cui la carta giocata prendera qualcosa sul tavolo
				
				if (lst.size() == 1) { //Cosa da prendere e' forzata i.e. solo un'opzione
					for (Set<Carta> set : lst) {
						for (Carta card : set) {
							daRitornare.add(card);
						}
					}
				} else { //Piu' opzioni chiedere al giocatore terminale cosa prendere
					
					boolean checkIfSingleCard = false;
					
					for (Set<Carta> set : lst) {
						if (set.size() == 1) { //Must take single card if possible
							checkIfSingleCard = true;
							for (Carta card : set) {
								daRitornare.add(card);
							}
						}
					}
					
					if (!checkIfSingleCard) { //If no single card can decide what to pick
						System.out.println("Cosa vuoi prendere?");
						int y = 0;
						for (Set<Carta> set : lst) {
							System.out.println(y + " -");
							for (Carta car : set) {
								System.out.println(" " + car.prendiNumero() + " " + car.prendiSeme());
							}
							y++;
						}
						Scanner ab = new Scanner(System.in);
						while (!ab.hasNext());
						int a = Integer.parseInt(ab.next());
						if (a <= lst.size()) {
							Set<Carta> set = lst.get(a);
							for (Carta card : set) {
								daRitornare.add(card);
							}
						}
						ab.close();
					}
				}
			}
		} else {
			System.out.println("Error input");
		}
		return daRitornare;
	}
	
	public List<Carta> gioca(Set<Carta> tavolo, Map<Integer, List<Set<Carta>>> somme) {
		List<Carta> daRitornare = new ArrayList<>();
		if (tavolo.size() == 0) {
			Carta max = null;
			int valore = -1;
			for (Carta carta : inMano) {
				int num = carta.prendiNumero() - 1;
				if (max != null && cartaSi[num] == -2) {
					continue;
				}
				if (giocateoInMano[num] > valore) {
					max = carta;
					valore = giocateoInMano[num];
				} else if (cartaSi[max.prendiNumero() - 1] < cartaSi[num]) {
					max = carta;
					valore = giocateoInMano[num];
				}
			}
			inMano.remove(max);
			partner.cartaSi(max);
			daRitornare.add(max);
			return daRitornare;
		} else {
			//1. Cerca di fare scopa
			int somma = sommainMano(tavolo);
			if (somma <= 10) {
				int index = -1;
				for (Carta daGiocare: inMano) {
					if (daGiocare.prendiNumero() == somma) {
						index = inMano.indexOf(daGiocare);
					}
				}
				if (index != -1) {
					Carta daGiocare = inMano.remove(index);
					daRitornare.addAll(tavolo);
					daRitornare.add(0, daGiocare);
					return daRitornare;
				}
			}
			
			//2. Cerca di prendere senza lasciare scopa
			List<Carta> daGiocare = new ArrayList<>();
			boolean senzaScopa = false;
			boolean prendere = false;
			int uscite = 0;
			int siONo = 0;
			boolean setteBello = false;
			int oriInPiu = 0;
			int inManoInPiu = 0;
			int primieraInPiu = 0;
			Carta ori;
			Carta bastoni;
			Carta spade;
			Carta coppe;
			Carta inTav;
			int nuoviOri;
			int nuoveinMano;
			boolean nuovoSetteBello;
			int nuovoPrimiera;
			int sum = 0;
			for (Carta carta : inMano) {
				ori = new Carta(carta.prendiNumero(), "Ori");
				bastoni = new Carta(carta.prendiNumero(), "Bastone");
				spade = new Carta(carta.prendiNumero(), "Spada");
				coppe = new Carta(carta.prendiNumero(), "Coppa");
				inTav = null;
				if (tavolo.contains(ori)) {
					inTav = ori;
				} else if (tavolo.contains(bastoni)) {
					inTav = bastoni;
				} else if (tavolo.contains(spade)) {
					inTav = spade;
				} else if (tavolo.contains(coppe)) {
					inTav = coppe;
				}
				if (inTav != null) {
					nuoviOri = 0;
					nuoveinMano = 0;
					nuovoSetteBello = false;
					Set<Carta> daAgg = new HashSet<>(); 
					daAgg.add(inTav);
					daAgg.add(carta);
					nuoveinMano++;
					nuovoPrimiera = punti.rimpiazzaPrimiera(daAgg);
					if (inTav.prendiSeme() == Seme.Ori) {
						nuoviOri++;
						if (inTav.prendiNumero() == 7) {
							nuovoSetteBello = true;
						}
					}
					if (carta.prendiSeme() == Seme.Ori) {
						nuoviOri++;
						if (carta.prendiNumero() == 7) {
							nuovoSetteBello = true;
						}
					}
					
					sum = sommainMano(tavolo) - carta.prendiNumero();
					if (daGiocare.size() == 0) {
						if (sum > 10 || (giocateoInMano[sum - 1] == 4)) {
							senzaScopa = true;
						} else {
							uscite = giocateoInMano[sum - 1];
							siONo = cartaSi[sum - 1];
						}
						daGiocare.add(0, carta);
						daGiocare.add(1, inTav);
						prendere = true;
						inManoInPiu = nuoveinMano;
						oriInPiu = nuoviOri;
						primieraInPiu = nuovoPrimiera;
						setteBello = nuovoSetteBello;
					} else if (daGiocare.size() > 0) {
						if (senzaScopa) {
							if (sum <= 10 && 
									giocateoInMano[sum - 1] < 4 && cartaSi[sum - 1] < 2) {
								break;
							}
							if (!prendere || faiPiuPunti(setteBello, nuovoSetteBello, 
											inManoInPiu, nuoveinMano, oriInPiu, nuoviOri, 
											primieraInPiu, nuovoPrimiera)) {
								oriInPiu = nuoviOri;
								setteBello = nuovoSetteBello;
								inManoInPiu = nuoveinMano;
								primieraInPiu = nuovoPrimiera;
								daGiocare = new ArrayList<>();
								daGiocare.add(0, carta);
								daGiocare.add(1, inTav);
								prendere = true;
							}
						} else if (sum > 10 || cartaSi[sum - 1] > 1 || giocateoInMano[sum - 1] == 4) {
							oriInPiu = nuoviOri;
							setteBello = nuovoSetteBello;
							inManoInPiu = nuoveinMano;
							primieraInPiu = nuovoPrimiera;
							daGiocare = new ArrayList<>();
							daGiocare.add(0, carta);
							daGiocare.add(1, inTav);
							senzaScopa = true;
							prendere = true;
						} else {
							if (cartaSi[sum - 1] > siONo) {
								oriInPiu = nuoviOri;
								setteBello = nuovoSetteBello;
								inManoInPiu = nuoveinMano;
								primieraInPiu = nuovoPrimiera;
								daGiocare = new ArrayList<>();
								daGiocare.add(0, carta);
								daGiocare.add(1, inTav);
								siONo = cartaSi[sum - 1];
								uscite = giocateoInMano[sum - 1];
								prendere = true;
							} else if (cartaSi[sum - 1] == siONo) {
								if (giocateoInMano[sum - 1] > siONo) {
									oriInPiu = nuoviOri;
									setteBello = nuovoSetteBello;
									inManoInPiu = nuoveinMano;
									primieraInPiu = nuovoPrimiera;
									daGiocare = new ArrayList<>();
									daGiocare.add(0, carta);
									daGiocare.add(1, inTav);
									uscite = giocateoInMano[sum - 1];
									prendere = true;
								} else if (giocateoInMano[sum - 1] == siONo) {
									if (!prendere || faiPiuPunti(setteBello, 
											nuovoSetteBello, inManoInPiu, nuoveinMano, oriInPiu, 
											nuoviOri, primieraInPiu, nuovoPrimiera)) {
										oriInPiu = nuoviOri;
										setteBello = nuovoSetteBello;
										inManoInPiu = nuoveinMano;
										primieraInPiu = nuovoPrimiera;
										daGiocare = new ArrayList<>();
										daGiocare.add(0, carta);
										daGiocare.add(1, inTav);
										prendere = true;
									}
								}
							}
						}
					}	
				} else if (somme.containsKey(carta.prendiNumero())) {
					for (Set<Carta> entry : somme.get(carta.prendiNumero())) {
						nuoviOri = 0;
						nuoveinMano = 0;
						nuovoSetteBello = false;
						Set<Carta> calcPrimiera = new HashSet<>();
						calcPrimiera.add(carta);
						calcPrimiera.addAll(entry);
						nuovoPrimiera = punti.rimpiazzaPrimiera(calcPrimiera);
						Set<Carta> rimanenti = new HashSet<>();
						for (Carta inTavola : tavolo) {
							rimanenti.add(inTavola);
						}
						for (Carta daPrendere : entry) {
							int num = daPrendere.prendiNumero();
							Seme sem = daPrendere.prendiSeme();
							if (num == 7) {
								if (sem == Seme.Ori) {
									nuovoSetteBello = true;
								}
							}
							if (sem == Seme.Ori) {
								nuoviOri++;
							}
							nuoveinMano++;
							rimanenti.remove(daPrendere);
							
						}
						sum = sommainMano(rimanenti);
						if (daGiocare.size() == 0) {
							if (sum > 10 || sum == 0 || (giocateoInMano[sum - 1] == 4)) {
								senzaScopa = true;
								prendere = true;
							} else if (sum > 0) {
								uscite = giocateoInMano[sum - 1];
								siONo = cartaSi[sum - 1];
								prendere = true;
							}
							daGiocare.addAll(entry);
							daGiocare.add(0, carta);
						} else {
							if (senzaScopa) {
								if ((sum <= 10 || sum == 0) && giocateoInMano[sum - 1] < 4 && cartaSi[sum - 1] < 1) {
									break;
								}
								if (!prendere || faiPiuPunti(setteBello, nuovoSetteBello, 
										inManoInPiu, nuoveinMano, oriInPiu, nuoviOri, 
										primieraInPiu, nuovoPrimiera)) {
									oriInPiu = nuoviOri;
									setteBello = nuovoSetteBello;
									inManoInPiu = nuoveinMano;
									primieraInPiu = nuovoPrimiera;
									daGiocare = new ArrayList<>();
									daGiocare.addAll(entry);
									daGiocare.add(0, carta);
									prendere = true;
								}
							} else if (sum > 10 || cartaSi[sum - 1] > 1 || giocateoInMano[sum - 1] == 4) {
								oriInPiu = nuoviOri;
								setteBello = nuovoSetteBello;
								inManoInPiu = nuoveinMano;
								primieraInPiu = nuovoPrimiera;
								daGiocare = new ArrayList<>();
								daGiocare.addAll(entry);
								daGiocare.add(0, carta);
								senzaScopa = true;
								prendere = true;
							} else {
								if (cartaSi[sum - 1] > siONo) {
									oriInPiu = nuoviOri;
									setteBello = nuovoSetteBello;
									inManoInPiu = nuoveinMano;
									primieraInPiu = nuovoPrimiera;
									daGiocare = new ArrayList<>();
									daGiocare.addAll(entry);
									daGiocare.add(0, carta);
									siONo = cartaSi[sum - 1];
									uscite = giocateoInMano[sum - 1];
								} else if (cartaSi[sum - 1] == siONo) {
									if (giocateoInMano[sum - 1] > uscite) {
										oriInPiu = nuoviOri;
										setteBello = nuovoSetteBello;
										inManoInPiu = nuoveinMano;
										primieraInPiu = nuovoPrimiera;
										daGiocare = new ArrayList<>();
										daGiocare.addAll(entry);
										daGiocare.add(0, carta);
										uscite = giocateoInMano[sum - 1];
									} else if (giocateoInMano[sum - 1] == siONo) {
										if (!prendere || faiPiuPunti(setteBello, nuovoSetteBello, 
														inManoInPiu, nuoveinMano, oriInPiu, nuoviOri, 
														primieraInPiu, nuovoPrimiera)) {
											oriInPiu = nuoviOri;
											setteBello = nuovoSetteBello;
											inManoInPiu = nuoveinMano;
											primieraInPiu = nuovoPrimiera;
											daGiocare = new ArrayList<>();
											daGiocare.addAll(entry);
											daGiocare.add(0, carta);
											prendere = true;
										}
									}
							
								}
							}
						}
					}
				} else {
					sum = carta.prendiNumero();
					for (Carta cart : tavolo) {
						sum += cart.prendiNumero();
					}
					if (daGiocare.size() == 0) {
						daGiocare.add(carta);
						if (sum > 10 || cartaSi[sum - 1] == 1 || giocateoInMano[sum - 1] == 4) {
							senzaScopa = true;
						} else {
							uscite = giocateoInMano[sum - 1];
							siONo = cartaSi[sum - 1];
						}
					} else {
						if (!senzaScopa) {
							if (sum > 10 || cartaSi[sum - 1] == 1 || giocateoInMano[sum - 1] == 4) {
								senzaScopa = true;
								prendere = false;
								daGiocare = new ArrayList<Carta>();
								daGiocare.add(carta);
							} else if (cartaSi[sum - 1] > siONo) {
								daGiocare = new ArrayList<Carta>();
								daGiocare.add(carta);
								prendere = false;
								siONo = cartaSi[sum - 1];
								uscite = giocateoInMano[sum - 1];
							} else if (cartaSi[sum - 1] == siONo) {
								if (giocateoInMano[sum - 1] > uscite) {
									daGiocare = new ArrayList<Carta>();
									daGiocare.add(carta);
									prendere = false;
									uscite = giocateoInMano[sum - 1];
								} else if (giocateoInMano[sum - 1] == uscite && !prendere) {
									//Gioca quella con meno valore
									if (menoDi(carta, daGiocare.get(0))) {
										daGiocare = new ArrayList<Carta>();
										prendere = false;
										daGiocare.add(carta);
									}
								}
							}
						} else if (sum > 10 || cartaSi[sum - 1] == 1 || giocateoInMano[sum - 1] == 4) {
							if (!prendere) {
								if (menoDi(carta, daGiocare.get(0))) {
									daGiocare = new ArrayList<Carta>();
									daGiocare.add(carta);
									prendere = false;
								} else if (!menoDi(daGiocare.get(0), carta)) {
									if (cartaSi[carta.prendiNumero() - 1] > 
											cartaSi[daGiocare.get(0).prendiNumero() - 1]) {
										daGiocare = new ArrayList<Carta>();
										daGiocare.add(carta);
										prendere = false;
									} else if (giocateoInMano[carta.prendiNumero() - 1] > 
											giocateoInMano[daGiocare.get(0).prendiNumero() - 1]) {
										daGiocare = new ArrayList<Carta>();
										daGiocare.add(carta);
										prendere = false;
									}
								}
							}
						}
					}
				}
			}
			inMano.remove(daGiocare.get(0));
			return daGiocare;
		}
	}
	
	private boolean menoDi(Carta carta1, Carta carta2) {
		int i = 0;
		if (carta1.prendiNumero() == 7) {
			i++;
		}
		if (carta2.prendiNumero() == 7) {
			i--;
		}
		if (carta1.prendiSeme() == Seme.Ori) {
			i++;
		}
		if (carta2.prendiSeme() == Seme.Ori) {
			i--;
		}
		return i < 0;
	}
	
	private boolean faiPiuPunti(boolean setteBello, boolean nuovoSetteBello, int inManoInPiu, 
							int nuoveinMano, int oriInPiu, int nuoviOri, int primieraInPiu,
							int nuovoPrimiera) {
		int i = 0;
		if (!punti.vintoSetteBello()) {
			if (setteBello) {
				i-=4;
			}
			if (nuovoSetteBello) {
				i+=4;
			}
		}
		if (!punti.vintoCarte()) {
			if (inManoInPiu + punti.cartePrese() > 20) {
				i-=4;
			} 
			if (nuoveinMano + punti.cartePrese() > 20) {
				i+=4;
			} else if (inManoInPiu + punti.cartePrese() < 20) {
				if (inManoInPiu > nuoveinMano) {
					i--;
				} else if (nuoveinMano > inManoInPiu) {
					i++;
				}
			}
		}
		if (!punti.vintoOri()) {
			if (oriInPiu + punti.cartePrese() > 20) {
				i-=4;
			} 
			if (nuoviOri + punti.cartePrese() > 20) {
				i+=4;
			} else if (oriInPiu + punti.cartePrese() < 20) {
				if (oriInPiu > nuoviOri) {
					i--;
				} else if (nuoviOri > oriInPiu) {
					i++;
				}
			}
		}
		if (punti.valorePrimiera() < 79) {
			if (primieraInPiu > 79) {
				i-=4;
			}
			if (nuovoPrimiera > 79) {
				i+=4;
			} else if (primieraInPiu < 79) {
				if (nuovoPrimiera > primieraInPiu) {
					i++;
				} else if (primieraInPiu > nuovoPrimiera) {
					i--;
				}
			}
		}
		return i > 0;
	}
	
	private int sommainMano(Set<Carta> inMano) {
		int sum = 0;
		for (Carta carta : inMano) {
			sum += carta.prendiNumero();
		}
		return sum;
	}
	
	public void registraCarta (Carta carta, Set<Carta> tavolo, Map<Integer, List<Set<Carta>>> somme, 
			Giocatore gioc) {
		if (gioc == avvDopo) {
			if (tavolo.size() == 0) {
				cartaNo(carta);
			} else if (tavolo.size() == 1) {
				for (Carta altraCarta : tavolo) {
					if (altraCarta.prendiNumero() != carta.prendiNumero()) {
						cartaForseSi(altraCarta);
					} else {
						cartaForseNo(carta);
					}
				}
			}
		} else if (gioc == avvPrima && tavolo.size() == 1) {
			for (Carta altraCarta : tavolo) {
				if (altraCarta.prendiNumero() != carta.prendiNumero()) {
					cartaSi(altraCarta);
				}
			}
		} else if (gioc == partner && tavolo.size() == 0) {
			cartaSi(carta);
		}
		aggiornaProbabilita(carta);
	}
	
	public List<Carta> inMano() {
		return inMano;
	}
	
	public void ordina() {
		Comparator<Carta> cardComparator = new Comparator<Carta>() {
            @Override
            public int compare(Carta c1, Carta c2) {
            	if (c1.prendiNumero() < c2.prendiNumero()) {
            		return -1;
            	} else if (c1.prendiNumero() > c2.prendiNumero()) {
            		return 1;
            	} else if (c1.prendiSeme() == Seme.Ori){
            		return 1;
            	} else if (c2.prendiSeme() == Seme.Ori) {
            		return -1;
            	} else {
            		return 0;
            	}
            }
        };
        Collections.sort(inMano, cardComparator);
	}
	
	public Punti prendiPunti() {
		return punti;
	}
	
	private void aggiornaProbabilita(Carta carta) {
		giocateoInMano[carta.prendiNumero() - 1]++;
		if (giocateoInMano[carta.prendiNumero() - 1] == 4) {
			cartaSi(carta);
			cartaSi(carta);
			cartaSi(carta);
		}
	}
	
	public void print() {
		System.out.println("Carte :");
		for (int i = 0; i < 10; i++) {
			System.out.print(i+1 + ": " + giocateoInMano[i] + ", ");
		}
		System.out.println("");
	}
	
	private void cartaSi(Carta carta) {
		cartaSi[carta.prendiNumero() - 1] = 2;
	}
	
	private void cartaForseSi(Carta carta) {
		cartaSi[carta.prendiNumero() - 1] = 1;
	}
	
	private void cartaNo(Carta carta) {
		cartaSi[carta.prendiNumero() - 1] = -2;
	}
	
	private void cartaForseNo(Carta carta) {
		cartaSi[carta.prendiNumero() - 1] = -1;
	}
	
	public void carteSi() {
		for (int i = 0; i < 10; i++) {
			System.out.print(i + ": " + cartaSi[i] + ", ");
		}
		System.out.println("");
	}
}
