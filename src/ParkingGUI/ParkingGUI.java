package ParkingGUI;

import java.awt.BorderLayout;
import java.io.*;
import java.util.GregorianCalendar;
import java.awt.EventQueue;

import parking.Parking;
import parking.mesto.ParkingMesto;
import parking.vozila.Vozilo;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class ParkingGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea textAreaEditor;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblRegistarskeOznake;
	private JTextField textFieldRegistarskeOznake;
	private JButton btnIzvestajOVozilu;
	private JLabel label;
	private JLabel lblBrojParkingMesta;
	private JTextField textFieldBrojParkingMesta;
	private JButton btnIzvestajOMestu;
	private JLabel label_1;
	private JButton btnPrikaziZauzeta;
	private JButton btnPrikaziSlobodna;
	private JLabel label_2;
	private JButton btnIzvestajOParkingu;
	private JButton btnObrisiEditor;
	private JButton btnIzadji;
	private JLabel label_4;
	private ParkingMesto[] parkingMesta;
	private LinkedList<Vozilo> vozila;
	private JButton btnOsvezi;
	private JButton btnSacuvaj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingGUI frame = new ParkingGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ParkingGUI() {
		setResizable(false);
		setSize(new Dimension(600, 400));
		setPreferredSize(new Dimension(600, 400));
		setTitle("Parking monitoring");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.WEST);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);

		parkingMesta = new ParkingMesto[Parking.getUkupanBrojMesta()];
		vozila = new LinkedList<Vozilo>();
	}

	public ParkingMesto[] getParkingMesta() {
		return parkingMesta;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextAreaEditor());
		}
		return scrollPane;
	}

	private JTextArea getTextAreaEditor() {
		if (textAreaEditor == null) {
			textAreaEditor = new JTextArea();
			textAreaEditor.setToolTipText("Editor teksta za prikaz podataka");
		}
		return textAreaEditor;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(160, 10));
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(getLblRegistarskeOznake());
			panel.add(getTextFieldRegistarskeOznake());
			panel.add(getBtnIzvestajOVozilu());
			panel.add(getLabel());
			panel.add(getLblBrojParkingMesta());
			panel.add(getTextFieldBrojParkingMesta());
			panel.add(getBtnIzvestajOMestu());
			panel.add(getLabel_2());
			panel.add(getBtnPrikaziZauzeta());
			panel.add(getBtnPrikaziSlobodna());
			panel.add(getLabel_1());
			panel.add(getBtnOsvezi());
			panel.add(getBtnIzvestajOParkingu());
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(10, 40));
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_1.add(getBtnSacuvaj());
			panel_1.add(getLabel_4());
			panel_1.add(getBtnObrisiEditor());
			panel_1.add(getBtnIzadji());
		}
		return panel_1;
	}

	private JLabel getLblRegistarskeOznake() {
		if (lblRegistarskeOznake == null) {
			lblRegistarskeOznake = new JLabel("Registarske oznake");
			lblRegistarskeOznake.setPreferredSize(new Dimension(105, 20));
			lblRegistarskeOznake.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lblRegistarskeOznake;
	}

	private JTextField getTextFieldRegistarskeOznake() {
		if (textFieldRegistarskeOznake == null) {
			textFieldRegistarskeOznake = new JTextField();
			textFieldRegistarskeOznake.setToolTipText("Registarske oznake vozila za pretragu");
			textFieldRegistarskeOznake.setPreferredSize(new Dimension(150, 20));
			textFieldRegistarskeOznake.setColumns(13);
		}
		return textFieldRegistarskeOznake;
	}

	private JButton getBtnIzvestajOVozilu() {
		if (btnIzvestajOVozilu == null) {
			btnIzvestajOVozilu = new JButton("Izvestaj o vozilu");
			btnIzvestajOVozilu.setToolTipText(
					"Prikazuje na editoru teksta sveobuhvata izvestaj o vozilu sa zadatim registarskim oznakama");
			btnIzvestajOVozilu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int brojac = 0;
						for (int i = 0; i < vozila.size(); i++) {
							if (vozila.get(i).getRegistarskaOznaka()
									.equalsIgnoreCase(textFieldRegistarskeOznake.getText())) {
								textAreaEditor.setText(vozila.get(i).toStringU());
								break;
							} else {
								brojac++;
							}
						}
						if (brojac == vozila.size() && !textFieldRegistarskeOznake.getText().equals(""))
							textAreaEditor.setText("Vozilo nikada nije koristilo parking.");
					} catch (Exception e1) {
						textAreaEditor.setText("GRESKA!");
					}
				}
			});
			btnIzvestajOVozilu.setPreferredSize(new Dimension(150, 25));
		}
		return btnIzvestajOVozilu;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setPreferredSize(new Dimension(150, 20));
		}
		return label;
	}

	private JLabel getLblBrojParkingMesta() {
		if (lblBrojParkingMesta == null) {
			lblBrojParkingMesta = new JLabel("Broj parking mesta");
			lblBrojParkingMesta.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblBrojParkingMesta.setPreferredSize(new Dimension(105, 20));
		}
		return lblBrojParkingMesta;
	}

	private JTextField getTextFieldBrojParkingMesta() {
		if (textFieldBrojParkingMesta == null) {
			textFieldBrojParkingMesta = new JTextField();
			textFieldBrojParkingMesta.setToolTipText("Redni broj parking mesta za pretragu");
			textFieldBrojParkingMesta.setPreferredSize(new Dimension(150, 20));
			textFieldBrojParkingMesta.setColumns(13);
		}
		return textFieldBrojParkingMesta;
	}

	private JButton getBtnIzvestajOMestu() {
		if (btnIzvestajOMestu == null) {
			btnIzvestajOMestu = new JButton("Izvestaj o mestu");
			btnIzvestajOMestu.setToolTipText(
					"Prikazuje na editoru teskta sveobuhvatan izvestaj o parking mestu pod navedenim rednim brojem");
			btnIzvestajOMestu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int brojac = 0;
						for (int i = 0; i < parkingMesta.length; i++) {
							String brojMesta = "" + parkingMesta[i].getBrojParkingMesta();
							if (brojMesta.equals(textFieldBrojParkingMesta.getText())) {
								if (!parkingMesta[i].isSlobodno())
									textAreaEditor.append(parkingMesta[i].toStringU());
								else
									textAreaEditor.append(parkingMesta[i].toStringI());
								break;
							} else {
								brojac++;
							}
						}
						if (brojac == parkingMesta.length)
							textAreaEditor.setText("Ne postoji trazeno parking mesto");
					} catch (Exception e1) {
						textAreaEditor.setText("GRESKA!");
					}
				}
			});
			btnIzvestajOMestu.setPreferredSize(new Dimension(150, 25));
		}
		return btnIzvestajOMestu;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
			label_1.setPreferredSize(new Dimension(150, 20));
		}
		return label_1;
	}

	private JButton getBtnPrikaziZauzeta() {
		if (btnPrikaziZauzeta == null) {
			btnPrikaziZauzeta = new JButton("Prikazi zauzeta");
			btnPrikaziZauzeta.setToolTipText("Prikazuje na editoru teksta redne brojeve zauzetih parking mesta");
			btnPrikaziZauzeta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						textAreaEditor.setText("ZAUZETA: \n");
						for (int i = 0; i < parkingMesta.length; i += 10) {
							int brojac = 0;
							for (int j = i; j < i + 10; j++)
								if (!parkingMesta[j].isSlobodno()) {
									textAreaEditor.append("" + parkingMesta[j].getBrojParkingMesta() + ", ");
									brojac++;
								}
							if (brojac == 10)
								textAreaEditor.append("\n");
						}
					} catch (Exception e1) {
						textAreaEditor.setText("GRESKA!");
					}
				}
			});
			btnPrikaziZauzeta.setPreferredSize(new Dimension(150, 25));
		}
		return btnPrikaziZauzeta;
	}

	private JButton getBtnPrikaziSlobodna() {
		if (btnPrikaziSlobodna == null) {
			btnPrikaziSlobodna = new JButton("Prikazi slobodna");
			btnPrikaziSlobodna.setToolTipText("Prikazuje na editoru testa redne brojeve slobodnih parking mesta");
			btnPrikaziSlobodna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						textAreaEditor.setText("SLOBODNA: \n");
						for (int i = 0; i < parkingMesta.length; i += 10) {
							int brojac = 0;
							for (int j = i; j < i + 10; j++)
								if (parkingMesta[j].isSlobodno()) {
									textAreaEditor.append("" + parkingMesta[j].getBrojParkingMesta() + ", ");
									brojac++;
								}
							if (brojac == 10)
								textAreaEditor.append("\n");
						}
					} catch (Exception e1) {
						textAreaEditor.setText("GRESKA!");
					}
				}
			});
			btnPrikaziSlobodna.setPreferredSize(new Dimension(150, 25));
		}
		return btnPrikaziSlobodna;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
			label_2.setPreferredSize(new Dimension(150, 20));
		}
		return label_2;
	}

	private JButton getBtnIzvestajOParkingu() {
		if (btnIzvestajOParkingu == null) {
			btnIzvestajOParkingu = new JButton("Izvestaj");
			btnIzvestajOParkingu.setToolTipText("Prikazuje sveobuhvatan izvestaj na editoru teksta");
			btnIzvestajOParkingu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						int dan = new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH);
						int mesec = new GregorianCalendar().get(GregorianCalendar.MONTH) + 1;
						int godina = new GregorianCalendar().get(GregorianCalendar.YEAR);
						String datum = "" + dan + "." + mesec + "." + godina + ".";
						textAreaEditor.setText("Datum:" + datum + "\n" + "Parking: parking" + "\n" + "Kapacitet: "
								+ Parking.getUkupanBrojMesta() + "parking mesta \n");

						for (int i = 0; i < parkingMesta.length; i++) {
							if (!parkingMesta[i].isSlobodno())
								textAreaEditor.append("\n" + parkingMesta[i].toStringU());
							else
								textAreaEditor.append("\n" + parkingMesta[i].toStringI());
						}
					} catch (Exception e) {
						textAreaEditor.setText("GRESKA!");
					}
				}
			});
			btnIzvestajOParkingu.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			btnIzvestajOParkingu.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnIzvestajOParkingu.setPreferredSize(new Dimension(150, 25));
		}
		return btnIzvestajOParkingu;
	}

	private JButton getBtnObrisiEditor() {
		if (btnObrisiEditor == null) {
			btnObrisiEditor = new JButton("Obrisi editor");
			btnObrisiEditor.setToolTipText("Brise sadrzaj tekst editora");
			btnObrisiEditor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textAreaEditor.setText(null);
				}
			});
			btnObrisiEditor.setPreferredSize(new Dimension(150, 25));
		}
		return btnObrisiEditor;
	}

	private JButton getBtnIzadji() {
		if (btnIzadji == null) {
			btnIzadji = new JButton("Izadji");
			btnIzadji.setToolTipText("Izlaz iz aplikacije");
			btnIzadji.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnIzadji.setPreferredSize(new Dimension(150, 25));
		}
		return btnIzadji;
	}

	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("");
			label_4.setPreferredSize(new Dimension(140, 20));
		}
		return label_4;
	}

	private JButton getBtnOsvezi() {
		if (btnOsvezi == null) {
			btnOsvezi = new JButton("Osvezi");
			btnOsvezi.setToolTipText("Inicijalizuje nove trenutne podatke o parkingu");
			btnOsvezi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						GregorianCalendar vremeInicijalizacije = new GregorianCalendar();
						GregorianCalendar vremeZauzetoOd = new GregorianCalendar();
						GregorianCalendar vremeSlobodnoOd = new GregorianCalendar();
						GregorianCalendar vremeUsloU = new GregorianCalendar();
						GregorianCalendar vremeIzasloIz = new GregorianCalendar();

						int godina = vremeInicijalizacije.get(GregorianCalendar.YEAR);
						int mesec = vremeInicijalizacije.get(GregorianCalendar.MONTH);
						int dan = vremeInicijalizacije.get(GregorianCalendar.DAY_OF_MONTH);
						int sat = vremeInicijalizacije.get(GregorianCalendar.HOUR);
						int minut = vremeInicijalizacije.get(GregorianCalendar.MINUTE);
						int sekund = vremeInicijalizacije.get(GregorianCalendar.SECOND);

						if (sat == 0) {
							if (dan == 1) {
								if (mesec == 0) {
									godina--;
									mesec = 11;
									dan = 31;
									sat = 23;
								} else if (mesec == 4 || mesec == 6 || mesec == 9) {
									mesec--;
									dan = 30;
									sat = 23;
								} else if (mesec == 2) {
									mesec--;
									dan = 29;
									sat = 23;
								} else {
									mesec--;
									dan = 31;
									sat = 23;
								}
							} else {
								dan--;
								sat = 23;
							}
						} else {
							sat--;
						}

						vremeUsloU.set(godina, mesec, dan, sat, minut, sekund);

						if (minut > 50) {
							if (sat == 23) {
								if (dan == 31) {
									if (mesec == 11) {
										godina++;
										mesec = 0;
										dan = 1;
										sat = 0;
										minut -= 50;
									} else if (mesec == 0 || mesec == 2 || mesec == 4 || mesec == 6 || mesec == 7
											|| mesec == 9) {
										mesec++;
										dan = 1;
										sat = 0;
										minut -= 50;
									}
								} else if (dan == 30) {
									if (mesec == 3 || mesec == 5 || mesec == 8 || mesec == 10) {
										mesec++;
										dan = 1;
										sat = 0;
										minut -= 50;
									} else if (mesec != 1) {
										dan++;
										sat = 0;
										minut -= 50;
									}
								} else if (dan == 29) {
									if (mesec == 1) {
										mesec++;
										dan = 1;
										sat = 0;
										minut -= 50;
									} else {
										dan++;
										sat = 0;
										minut -= 50;
									}
								}
							} else {
								sat++;
								minut -= 50;
							}
						} else
							minut++;

						vremeZauzetoOd.set(godina, mesec, dan, sat, minut, sekund);

						if (sat == 23) {
							if (dan == 31) {
								if (mesec == 11) {
									godina++;
									mesec = 0;
									dan = 1;
									sat = 0;
								} else if (mesec == 0 || mesec == 2 || mesec == 4 || mesec == 6 || mesec == 7
										|| mesec == 9) {
									mesec++;
									dan = 1;
									sat = 0;
								}
							} else if (dan == 30) {
								if (mesec == 3 || mesec == 5 || mesec == 8 || mesec == 10) {
									mesec++;
									dan = 1;
									sat = 0;
								} else if (mesec != 1) {
									dan++;
									sat = 0;
								}
							} else if (dan == 29) {
								if (mesec == 1) {
									mesec++;
									dan = 1;
									sat = 0;
								} else {
									dan++;
									sat = 0;
								}
							}
						} else
							sat++;

						vremeSlobodnoOd.set(godina, mesec, dan, sat, minut, sekund);

						if (minut > 50) {
							if (sat == 23) {
								if (dan == 31) {
									if (mesec == 11) {
										godina++;
										mesec = 0;
										dan = 1;
										sat = 0;
										minut -= 50;
									} else if (mesec == 0 || mesec == 2 || mesec == 4 || mesec == 6 || mesec == 7
											|| mesec == 9) {
										mesec++;
										dan = 1;
										sat = 0;
										minut -= 50;
									}
								} else if (dan == 30) {
									if (mesec == 3 || mesec == 5 || mesec == 8 || mesec == 10) {
										mesec++;
										dan = 1;
										sat = 0;
										minut -= 50;
									} else if (mesec != 1) {
										dan++;
										sat = 0;
										minut -= 50;
									}
								} else if (dan == 29) {
									if (mesec == 1) {
										mesec++;
										dan = 1;
										sat = 0;
										minut -= 50;
									} else {
										dan++;
										sat = 0;
										minut -= 50;
									}
								}
							} else {
								sat++;
								minut -= 50;
							}
						} else
							minut++;

						vremeIzasloIz.set(godina, mesec, dan, sat, minut, sekund);

						for (int i = 0; i < (parkingMesta.length / 2); i++) {
							Vozilo vozilo = new Vozilo();
							vozilo.setUsloU(vremeUsloU);
							vozilo.setRegistarskaOznaka("BG00" + i + "AA");
							vozilo.setImaMesecnuPretplatu(false);
							vozilo.setPrijavljenoPoliciji(false);
							vozila.add(vozilo);
							ParkingMesto parkingMesto = new ParkingMesto();
							parkingMesto.setBrojParkingMesta(i + 1);
							parkingMesto.setSlobodno(false);
							parkingMesto.setZauzetoOd(vremeZauzetoOd);
							parkingMesto.setVozilo(vozilo);
							parkingMesta[i] = parkingMesto;
						}
						for (int i = parkingMesta.length / 2; i < parkingMesta.length; i++) {
							Vozilo vozilo = new Vozilo();
							vozilo.setIzasloU(vremeIzasloIz);
							vozilo.setRegistarskaOznaka("BG00" + i + "AA");
							vozilo.setImaMesecnuPretplatu(false);
							vozilo.setPrijavljenoPoliciji(false);
							vozila.add(vozilo);
							ParkingMesto parkingMesto = new ParkingMesto();
							parkingMesto.setBrojParkingMesta(i + 1);
							parkingMesto.setSlobodno(true);
							parkingMesto.setSlobodnoOd(vremeSlobodnoOd);
							parkingMesto.setVozilo(vozilo);
							parkingMesta[i] = parkingMesto;
						}
					} catch (Exception e) {
						textAreaEditor.setText("GRESKA!");
					}
				}
			});
			btnOsvezi.setPreferredSize(new Dimension(75, 15));
		}
		return btnOsvezi;
	}

	private JButton getBtnSacuvaj() {
		if (btnSacuvaj == null) {
			btnSacuvaj = new JButton("Sacuvaj");
			btnSacuvaj.setToolTipText("Cuva kompletan izvestaj u txt fajlu");
			btnSacuvaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						int dan = new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH);
						int mesec = new GregorianCalendar().get(GregorianCalendar.MONTH) + 1;
						int godina = new GregorianCalendar().get(GregorianCalendar.YEAR);
						String datum = "" + dan + "." + mesec + "." + godina + ".";

						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(datum + ".txt")));

						out.println("Datum:" + datum + ", ");
						out.println("Parking: parking" + ", ");
						out.println("Kapacitet: " + Parking.getUkupanBrojMesta() + " parking mesta ");
						out.println();

						for (int i = 0; i < parkingMesta.length; i++) {
							if (!parkingMesta[i].isSlobodno())
								out.println(parkingMesta[i].toStringU());
							else
								out.println(parkingMesta[i].toStringI());
						}
						out.close();
						textAreaEditor.setText("Sacuvan izvestaj pod nazivom: " + datum + ".txt");
					} catch (Exception e) {
						textAreaEditor.setText("GRESKA!");
					}
				}
			});
			btnSacuvaj.setPreferredSize(new Dimension(150, 25));
		}
		return btnSacuvaj;
	}
}
