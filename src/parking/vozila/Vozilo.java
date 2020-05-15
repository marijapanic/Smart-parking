package parking.vozila;

import java.util.GregorianCalendar;

public class Vozilo {

	private String registarskaOznaka;
	private GregorianCalendar usloU;
	private GregorianCalendar izasloU;
	private boolean prijavljenoPoliciji;
	private boolean imaMesecnuPretplatu;

	public String getRegistarskaOznaka() {
		return registarskaOznaka;
	}

	public void setRegistarskaOznaka(String registarskaOznaka) {
		if (registarskaOznaka == null)
			throw new RuntimeException("GRESKA! null vrednost.");
		if (registarskaOznaka.isEmpty())
			throw new RuntimeException("GRESKA! Prazan string.");
		if (registarskaOznaka.length() < 6 || registarskaOznaka.length() > 8)
			throw new RuntimeException("GRESKA! Neispravan format.");
		this.registarskaOznaka = registarskaOznaka;
	}

	public GregorianCalendar getUsloU() {
		return usloU;
	}

	public void setUsloU(GregorianCalendar usloU) {
		if (usloU == null)
			throw new RuntimeException("GRESKA! null vrednost.");
		this.usloU = usloU;
	}

	public GregorianCalendar getIzasloU() {
		return izasloU;
	}

	public void setIzasloU(GregorianCalendar izasloU) {
		if (izasloU == null)
			throw new RuntimeException("GRESKA! null vrednost.");
		this.izasloU = izasloU;
	}

	public boolean isPrijavljenoPoliciji() {
		return prijavljenoPoliciji;
	}

	public void setPrijavljenoPoliciji(boolean prijavljenoPoliciji) {
		this.prijavljenoPoliciji = prijavljenoPoliciji;
	}

	public boolean isImaMesecnuPretplatu() {
		return imaMesecnuPretplatu;
	}

	public void setImaMesecnuPretplatu(boolean imaMesecnuPretplatu) {
		this.imaMesecnuPretplatu = imaMesecnuPretplatu;
	}

	public String toStringU() {
		return "VOZILO: registarska oznaka: " + registarskaOznaka + ", uslo: " + "datum: "
				+ usloU.get(GregorianCalendar.DAY_OF_MONTH) + "." + (usloU.get(GregorianCalendar.MONTH) + 1) + "."
				+ usloU.get(GregorianCalendar.YEAR) + ", " + "vreme: " + usloU.get(GregorianCalendar.HOUR) + ":"
				+ usloU.get(GregorianCalendar.MINUTE) + ":" + usloU.get(GregorianCalendar.SECOND) + ", "
				+ "prijavljeno policiji: " + prijavljenoPoliciji + ", ima mesecnu pretplatu: " + imaMesecnuPretplatu;
	}

	public String toStringI() {
		return "VOZILO: registarska oznaka: " + registarskaOznaka + ", izaslo: " + "datum: "
				+ izasloU.get(GregorianCalendar.DAY_OF_MONTH) + "." + (izasloU.get(GregorianCalendar.MONTH) + 1) + "."
				+ izasloU.get(GregorianCalendar.YEAR) + ", " + "vreme: " + izasloU.get(GregorianCalendar.HOUR) + ":"
				+ izasloU.get(GregorianCalendar.MINUTE) + ":" + izasloU.get(GregorianCalendar.SECOND) + ", "
				+ "prijavljeno policiji: " + prijavljenoPoliciji + ", ima mesecnu pretplatu: " + imaMesecnuPretplatu;
	}
}
