package parking;

public class Parking {
	
	private static final int UKUPAN_BROJ_MESTA = 100;
	
	private int brojSlobodnih;
	private int brojZauzetih;
	
	public int getBrojSlobodnih() {
		return brojSlobodnih;
	}
	public void setBrojSlobodnih(int brojSlobodnih) {
		if(brojSlobodnih < 0 || brojSlobodnih > 100) 
			throw new RuntimeException("GRESKA! Vrednost je van granica.");
		this.brojSlobodnih = brojSlobodnih;
	}
	public int getBrojZauzetih() {
		return brojZauzetih;
	}
	public void setBrojZauzetih(int brojZauzetih) {
		if(brojZauzetih <0 || brojZauzetih > 100)
			throw new RuntimeException("GRESKA! Vrednost je van granica.");
		this.brojZauzetih = brojZauzetih;
	}
	public static int getUkupanBrojMesta() {
		return UKUPAN_BROJ_MESTA;
	}
	
}
