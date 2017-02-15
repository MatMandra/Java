import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kalkulator {

	private JFrame frame;
	private JTextField wyswietlacz;

	
	private enum Operacja {
		BRAK, DODAWANIE, ODEJMOWANIE, MNOZENIE, DZIELENIE, PIERWIASTEK, ODWROTNOSC, LOGARYTM, PROCENT
	}
	
	private Operacja biezacaOperacja = Operacja.BRAK;
	
	private Double liczba = null;
	
	private boolean wyczyscPrzed = false;
	
	private Double getDoubleValue() {
		Double number = null;
		String numberText = wyswietlacz.getText();
		if (numberText != null && 
				(numberText = numberText.trim()).length() > 0 ) {
					number = Double.parseDouble(
							numberText.replace(',',  '.'));
		}
		return number;
	}
	
	private void setDoubleValue(Double number) {
		if(number != null) {
			String value = number.toString().replace(',','.');
			wyswietlacz.setText(value);

		}
	}
	
	private void oblicz() {
		if ( liczba != null) {
			Double liczba2 = getDoubleValue();
			if (liczba2 != null) {
				if (Operacja.DODAWANIE.equals(biezacaOperacja)) {
					liczba = liczba + liczba2;
				} else if (Operacja.ODEJMOWANIE.equals(biezacaOperacja)) {
					liczba = liczba - liczba2;
				} else if (Operacja.MNOZENIE.equals(biezacaOperacja)) {
					liczba = liczba * liczba2;
				} else if (Operacja.DZIELENIE.equals(biezacaOperacja)) {
					liczba = liczba / liczba2;
				} else if (Operacja.PIERWIASTEK.equals(biezacaOperacja)) {
					liczba = Math.sqrt(liczba);
				} else if (Operacja.ODWROTNOSC.equals(biezacaOperacja)) {
					liczba = 1 / liczba;
				} else if (Operacja.LOGARYTM.equals(biezacaOperacja)) {
					liczba = Math.log(liczba);
				} else if (Operacja.PROCENT.equals(biezacaOperacja)) {
					liczba = liczba*(liczba/100);
				}
				
				setDoubleValue(liczba);
				
				
				}
			}
		}
	
	
	private void wcisnietaOperacja(Operacja operacja) {
		this.biezacaOperacja = operacja;
		liczba = getDoubleValue();
		wyczyscPrzed = true;
		
	}
	
	private void wcisnietaCyfra(String cyfra) {
		if(wyczyscPrzed) {
			wyswietlacz.setText(cyfra);
			wyczyscPrzed = false;
		} else {
			wyswietlacz.setText(wyswietlacz.getText() + cyfra);
		}
	}
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Kalkulator window = new Kalkulator();
					window.frame.pack();
				
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Kalkulator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.window);
		
		JPanel kontener_Panel = new JPanel();
		frame.getContentPane().add(kontener_Panel, BorderLayout.CENTER);
		kontener_Panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_wyswietlacz = new JPanel();
		panel_wyswietlacz.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		kontener_Panel.add(panel_wyswietlacz, BorderLayout.NORTH);
		panel_wyswietlacz.setLayout(new BorderLayout(0, 0));
		
		wyswietlacz = new JTextField();
		panel_wyswietlacz.add(wyswietlacz);
		wyswietlacz.setColumns(10);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biezacaOperacja = Operacja.BRAK;
				liczba = null;
				wyczyscPrzed = false;
				wyswietlacz.setText("");
			}
		});
		panel_wyswietlacz.add(btnC, BorderLayout.EAST);
		
		JPanel panel_funkcyjny = new JPanel();
		panel_funkcyjny.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		kontener_Panel.add(panel_funkcyjny, BorderLayout.EAST);
		panel_funkcyjny.setLayout(new GridLayout(4, 2, 5, 5));
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.DODAWANIE);
			}
		});
		panel_funkcyjny.add(btnPlus);
		
		JButton butMinus = new JButton("-");
		butMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.ODEJMOWANIE);
			}
		});
		panel_funkcyjny.add(butMinus);
		
		JButton btnRay = new JButton("*");
		btnRay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.MNOZENIE);
			}
		});
		panel_funkcyjny.add(btnRay);
		
		JButton btnDzielenie = new JButton("/");
		btnDzielenie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.DZIELENIE);
			}
		});
		panel_funkcyjny.add(btnDzielenie);
		
		JButton btnPierw = new JButton("\u221A");
		btnPierw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.PIERWIASTEK);
			}
		});
		panel_funkcyjny.add(btnPierw);
		
		JButton butProc = new JButton("%");
		butProc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.PROCENT);
			}
		});
		panel_funkcyjny.add(butProc);
		
		JButton btnLog = new JButton("Log");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.LOGARYTM);
			}
		});
		panel_funkcyjny.add(btnLog);
		
		JButton btnx = new JButton("1/X");
		btnx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaOperacja(Operacja.ODWROTNOSC);
			}
		});
		panel_funkcyjny.add(btnx);
		
		JPanel center_panel = new JPanel();
		kontener_Panel.add(center_panel, BorderLayout.CENTER);
		center_panel.setLayout(new GridLayout(4, 3, 5, 5));
		
		JButton but_7 = new JButton("7");
		but_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("7");
			}
		});
		center_panel.add(but_7);
		
		JButton but_8 = new JButton("8");
		but_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("8");
			}
		});
		center_panel.add(but_8);
		
		JButton but_9 = new JButton("9");
		but_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("9");
			}
		});
		center_panel.add(but_9);
		
		JButton but_4 = new JButton("4");
		but_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("4");
			}
		});
		center_panel.add(but_4);
		
		JButton but_5 = new JButton("5");
		but_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("5");
			}
		});
		center_panel.add(but_5);
		
		JButton but_6 = new JButton("6");
		but_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("6");
			}
		});
		center_panel.add(but_6);
		
		JButton but_1 = new JButton("1");
		but_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("1");
			}
		});
		center_panel.add(but_1);
		
		JButton but_2 = new JButton("2");
		but_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("2");
			}
		});
		center_panel.add(but_2);
		
		JButton but_3 = new JButton("3");
		but_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("3");
			}
		});
		center_panel.add(but_3);
		
		JButton but_przecinek = new JButton(",");
		but_przecinek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		center_panel.add(but_przecinek);
		
		JButton but_0 = new JButton("0");
		but_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wcisnietaCyfra("0");
			}
		});
		center_panel.add(but_0);
		
		JButton but_równa_się = new JButton("=");
		but_równa_się.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oblicz();
			}
		});
		center_panel.add(but_równa_się);
		frame.setBounds(100, 100, 450, 300);  /*rozmaiar*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // aby zamknąć aplikację EXIT_ON_CLOSE X 
	}

}
