package parking.mesto;

import java.util.GregorianCalendar;

import parking.vozila.Vozilo;

public class ParkingMesto {
	
	private int brojParkingMesta;
	private boolean slobodno;
	private GregorianCalendar zauzetoOd;
	private GregorianCalendar slobodnoOd;
	private Vozilo vozilo;
	
	public int getBrojParkingMesta() {
		return brojParkingMesta;
	}
	public void setBrojParkingMesta(int brojParkingMesta) {
		if(brojParkingMesta < 1 || brojParkingMesta > 100)
			throw new RuntimeException("GRESKA! Vrednost je van granica.");
		this.brojParkingMesta = brojParkingMesta;
	}
	public boolean isSlobodno() {
		return slobodno;
	}
	public void setSlobodno(boolean slobodno) {
		this.slobodno = slobodno;
	}
	public GregorianCalendar getZauzetoOd() {
		return zauzetoOd;
	}
	public void setZauzetoOd(GregorianCalendar zauzetoOd) {
		if(zauzetoOd == null)
			throw new RuntimeException("GRESKA! null vrednost.");
		this.zauzetoOd = zauzetoOd;
	}
	public GregorianCalendar getSlobodnoOd() {
		return slobodnoOd;
	}
	public void setSlobodnoOd(GregorianCalendar slobodnoOd) {
		if(slobodnoOd == null)
			throw new RuntimeException("GRESKA! null vrednost.");
		this.slobodnoOd = slobodnoOd;
	}
	public Vozilo getVozilo() {
		return vozilo;
	}
	public void setVozilo(Vozilo vozilo) {
		if(vozilo == null)
			throw new RuntimeException("GRESKA! null vrednost.");
		this.vozilo = vozilo;
	}
	public String toStringU() {
		return "PARKING MESTO: broj parking mesta: " + brojParkingMesta + ", slobodno: " + slobodno + 
				", zauzeto od: " + 
				"datum: " +
				zauzetoOd.get(GregorianCalendar.DAY_OF_MONTH) + "." +
				(zauzetoOd.get(GregorianCalendar.MONTH)+1) + "." +
				zauzetoOd.get(GregorianCalendar.YEAR) + ", " +
				"vreme: " +
				zauzetoOd.get(GregorianCalendar.HOUR) + ":" +
				zauzetoOd.get(GregorianCalendar.MINUTE) + ":" +
				zauzetoOd.get(GregorianCalendar.SECOND) + ", " +
				vozilo.toStringU();
	}
	public String toStringI() {
		return "PARKING MESTO: broj parking mesta: " + brojParkingMesta + ", slobodno: " + slobodno +
				", slobodno od: " +  
				"datum: " +
				slobodnoOd.get(GregorianCalendar.DAY_OF_MONTH) + "." +
				(slobodnoOd.get(GregorianCalendar.MONTH)+1) + "." +
				slobodnoOd.get(GregorianCalendar.YEAR) + ", " +
				"vreme: " +
				slobodnoOd.get(GregorianCalendar.HOUR) + ":" +
				slobodnoOd.get(GregorianCalendar.MINUTE) + ":" +
				slobodnoOd.get(GregorianCalendar.SECOND) + ", " +
				vozilo.toStringI();
	}
}
