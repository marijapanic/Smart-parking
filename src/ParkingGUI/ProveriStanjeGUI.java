package ParkingGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import parking.mesto.ParkingMesto;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ProveriStanjeGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea textAreaEditor;
	private JPanel panel;
	private JButton btnImaLiSlobodnog;
	private JButton btnPrikaziStanje;
	private JButton btnPrikazi;
	private JButton btnIzadji;
	private JLabel lbl;
	private JButton btnNewButton;
	private ParkingMesto[] parkingMesta;
	private ParkingGUI parking;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProveriStanjeGUI frame = new ProveriStanjeGUI();
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
	public ProveriStanjeGUI() {
		setTitle("Parking servis");
		setResizable(false);
		setSize(new Dimension(400, 400));
		setPreferredSize(new Dimension(400, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);

		parking = new ParkingGUI();
		parkingMesta = parking.getParkingMesta();
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
			textAreaEditor.setToolTipText("Editor za prikaz stanja");
		}
		return textAreaEditor;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(10, 80));
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(getBtnNewButton());
			panel.add(getBtnImaLiSlobodnog());
			panel.add(getLbl());
			panel.add(getBtnPrikaziStanje());
			panel.add(getBtnPrikazi());
			panel.add(getBtnIzadji());
		}
		return panel;
	}

	private JButton getBtnImaLiSlobodnog() {
		if (btnImaLiSlobodnog == null) {
			btnImaLiSlobodnog = new JButton("Ima li slobodnog");
			btnImaLiSlobodnog.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int brojac = 0;

						for (int i = 0; i < parkingMesta.length; i++) {
							if (parkingMesta[i].isSlobodno()) {
								brojac++;
							}
						}
						if (brojac != 0) {
							textAreaEditor.setText("IMA SLOBODNIH MESTA!" + "\n" + "\n");
							textAreaEditor.append("Broj slobodnih mesta je: " + brojac);
						} else
							textAreaEditor.setText("NEMA SLOBODNIH MESTA!");
					} catch (Exception e1) {
						textAreaEditor.setText("GRESKA!");
					}
				}
			});
			btnImaLiSlobodnog.setToolTipText("Prikazuje da li ima slobodnih i koliko ima slobodnih parking mesta");
			btnImaLiSlobodnog.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnImaLiSlobodnog.setPreferredSize(new Dimension(300, 40));
		}
		return btnImaLiSlobodnog;
	}

	private JButton getBtnPrikaziStanje() {
		if (btnPrikaziStanje == null) {
			btnPrikaziStanje = new JButton("Obrisi");
			btnPrikaziStanje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textAreaEditor.setText(null);
				}
			});
			btnPrikaziStanje.setToolTipText("Brise sadrzaj editora");
			btnPrikaziStanje.setPreferredSize(new Dimension(100, 30));
		}
		return btnPrikaziStanje;
	}

	private JButton getBtnPrikazi() {
		if (btnPrikazi == null) {
			btnPrikazi = new JButton("Prikazi");
			btnPrikazi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						textAreaEditor.setText("SLOBODNA: \n" + "\n");
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
			btnPrikazi.setToolTipText("Prikazuje redni broj slobodnih mesta");
			btnPrikazi.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnPrikazi.setPreferredSize(new Dimension(166, 30));
		}
		return btnPrikazi;
	}

	private JButton getBtnIzadji() {
		if (btnIzadji == null) {
			btnIzadji = new JButton("Izadji");
			btnIzadji.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnIzadji.setToolTipText("Izlaz iz aplikacije");
			btnIzadji.setPreferredSize(new Dimension(100, 30));
		}
		return btnIzadji;
	}

	private JLabel getLbl() {
		if (lbl == null) {
			lbl = new JLabel("");
			lbl.setPreferredSize(new Dimension(33, 40));
		}
		return lbl;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("...");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						for (int i = 0; i < (parkingMesta.length / 2); i++) {
							ParkingMesto parkingMesto = new ParkingMesto();
							parkingMesto.setBrojParkingMesta(i + 1);
							parkingMesto.setSlobodno(false);
							parkingMesta[i] = parkingMesto;
						}

						for (int i = parkingMesta.length / 2; i < parkingMesta.length; i++) {
							ParkingMesto parkingMesto = new ParkingMesto();
							parkingMesto.setBrojParkingMesta(i + 1);
							parkingMesto.setSlobodno(true);
							parkingMesta[i] = parkingMesto;
						}
					} catch (Exception e1) {
						textAreaEditor.setText("GRESKA!");
					}
				}
			});
			btnNewButton.setToolTipText("Inicijalizuje nove trenutne podatke o parkingu");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 6));
			btnNewButton.setPreferredSize(new Dimension(33, 40));
		}
		return btnNewButton;
	}
}
