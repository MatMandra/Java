package lab.dbapp;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;

public class MainFrame {

	private JFrame frame;
	private JScrollPane scrollPane;
	
	private static final String PROPERTIES_PATH = "db/oracledb.properties";
	protected static final boolean True = false;
	private DbParams dbParams = null;
	private boolean isDbReady = false;
	
	private ExecutorService wykonawca = Executors.newSingleThreadExecutor();
	

	/**
	 * Launch the application.
	 */
	public int WybranaTabelka = -1;
	public String zmienna1;
	public String zmiennaA, zmiennaB, zmiennaC,zmiennaD;
	
	
	
	
	
	private JTextField txtNrlekarza;
	private JTextField txtImieLekarza;
	private JTextField txtNazwiskoLekarza;
	private JTextField txtTelefonLekarza;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(-10, 0, 323, 552);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox Wybierz_tab = new JComboBox();
		Wybierz_tab.setToolTipText("Wybierz Tabelę");
		Wybierz_tab.setMaximumRowCount(9);
		Wybierz_tab.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Wybierz_tab.setModel(new DefaultComboBoxModel(new String[] {"Wybierz Tabelę", "Lekarze", "Pacjenci", "Wizyty", "Diagnozy", "Choroby", "Recepty", "Lekarstwa", "Lekarstwo_Leczy", "Interakcję_Leków"}));
		Wybierz_tab.setSelectedIndex(0);
		Wybierz_tab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WybranaTabelka = (int)Wybierz_tab.getSelectedIndex();
			}
	//		
			

		});
		Wybierz_tab.setBounds(12, 35, 157, 45);
		panel.add(Wybierz_tab);
		
		JButton Szukaj = new JButton("Wyszukaj Dane");
		Szukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(WybranaTabelka) {
				case 0:
					wykonawca.execute(new Runnable() {
						public void run() {
							JTable table = null ;
							table = new JTable();
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							table.setModel(model);
							
								scrollPane.setViewportView(table);
						}	
					});
					
					
						
					break;
				case 1:
					
					wykonawca.execute(new Runnable() {
						public void run() {
							JTable table = pobierzDaneDoTabeli1();
							if (table != null)
								table.setRowHeight(2 * table.getRowHeight());
								scrollPane.setViewportView(table);
						}	
					});
					break;
				case 2:
					wykonawca.execute(new Runnable() {
						public void run() {
							JTable table = pobierzDaneDoTabeli2();
							if (table != null)
								table.setRowHeight(2 * table.getRowHeight());
								scrollPane.setViewportView(table);
						}	
					});
					break;
				case 3:
					wykonawca.execute(new Runnable() {
						public void run() {
							JTable table = pobierzDaneDoTabeli3();
							if (table != null)
								table.setRowHeight(2 * table.getRowHeight());
								scrollPane.setViewportView(table);
						}	
					});
					break;
				case 4:
					wykonawca.execute(new Runnable() {
						public void run() {
							JTable table = pobierzDaneDoTabeli4();
							if (table != null)
								table.setRowHeight(2 * table.getRowHeight());
								scrollPane.setViewportView(table);
						}	
					});
					break;
				case 5:
					wykonawca.execute(new Runnable() {
						public void run() {
							JTable table = pobierzDaneDoTabeli5();
							if (table != null)
								table.setRowHeight(2 * table.getRowHeight());
								scrollPane.setViewportView(table);
						}	
					});
					break;
				case 6:
					wykonawca.execute(new Runnable() {
						public void run() {
							JTable table = pobierzDaneDoTabeli6();
							if (table != null)
								table.setRowHeight(2 * table.getRowHeight());
								scrollPane.setViewportView(table);
						}	
					});
					break;
				case 7:
					wykonawca.execute(new Runnable() {
						public void run() {
							JTable table = pobierzDaneDoTabeli7();
							if (table != null)
								table.setRowHeight(2 * table.getRowHeight());
								scrollPane.setViewportView(table);
						}	
					});
					break;
				case 8:
						wykonawca.execute(new Runnable() {
							public void run() {
								JTable table = pobierzDaneDoTabeli8();
								if (table != null)
									table.setRowHeight(2 * table.getRowHeight());
									scrollPane.setViewportView(table);
							}	
						});
						break;
				case 9:
					wykonawca.execute(new Runnable() {
						public void run() {
							JTable table = pobierzDaneDoTabeli9();
							if (table != null)
								table.setRowHeight(2 * table.getRowHeight());
								scrollPane.setViewportView(table);
						}	
					});
					break;
				}
				
				
					
			}
		});
		Szukaj.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Szukaj.setBounds(164, 35, 147, 45);
		panel.add(Szukaj);
		
		JButton btnDodajDane = new JButton("Dodaj Dane");
		btnDodajDane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(WybranaTabelka) {
					case 0:
						break;
					case 1:
						
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;
					case 7:
						break;
					case 8:
						break;
					case 9:
						break;
				
				}
			}
		});
		btnDodajDane.setBounds(171, 92, 140, 45);
		panel.add(btnDodajDane);
		
				JInternalFrame FrameLekarz = new JInternalFrame("Dodaj Lekarza");
				FrameLekarz.setBounds(12, 209, 284, 276);
				panel.add(FrameLekarz);
				FrameLekarz.setClosable(true);
				FrameLekarz.getContentPane().setEnabled(false);
				FrameLekarz.setEnabled(false);
				FrameLekarz.getContentPane().setLayout(null);
				
				txtNrlekarza = new JTextField();
				txtNrlekarza.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zmienna1 = txtNrlekarza.getText(); 
					}
				});
				txtNrlekarza.setHorizontalAlignment(SwingConstants.CENTER);
				txtNrlekarza.setText("NR_Lekarza");
				txtNrlekarza.setBounds(75, 13, 116, 22);
				FrameLekarz.getContentPane().add(txtNrlekarza);
				txtNrlekarza.setColumns(10);
				
				txtImieLekarza = new JTextField();
				txtImieLekarza.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zmiennaA = txtImieLekarza.getText(); 
					}
				});
				txtImieLekarza.setHorizontalAlignment(SwingConstants.CENTER);
				txtImieLekarza.setText("Imie");
				txtImieLekarza.setBounds(75, 48, 116, 22);
				FrameLekarz.getContentPane().add(txtImieLekarza);
				txtImieLekarza.setColumns(10);
				
				txtNazwiskoLekarza = new JTextField();
				txtNazwiskoLekarza.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zmiennaB = txtNazwiskoLekarza.getText(); 
					}
				});
				txtNazwiskoLekarza.setHorizontalAlignment(SwingConstants.CENTER);
				txtNazwiskoLekarza.setText("Nazwisko");
				txtNazwiskoLekarza.setBounds(75, 87, 116, 22);
				FrameLekarz.getContentPane().add(txtNazwiskoLekarza);
				txtNazwiskoLekarza.setColumns(10);
				
				txtTelefonLekarza = new JTextField();
				txtTelefonLekarza.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zmiennaC = txtTelefonLekarza.getText(); 
					}
				});
				txtTelefonLekarza.setHorizontalAlignment(SwingConstants.CENTER);
				txtTelefonLekarza.setText("Telefon");
				txtTelefonLekarza.setBounds(75, 122, 116, 22);
				FrameLekarz.getContentPane().add(txtTelefonLekarza);
				txtTelefonLekarza.setColumns(10);
				
				JButton Dodaj_Lekarza = new JButton("Dodaj");
				Dodaj_Lekarza.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

					}
				});
				Dodaj_Lekarza.setBounds(75, 176, 116, 25);
				FrameLekarz.getContentPane().add(Dodaj_Lekarza);
		
/*********************************************************************************/		
		
/*********************************************************************************/		
		JPanel center_panel = new JPanel();
		center_panel.setBounds(325, 0, 657, 552);
		frame.getContentPane().add(center_panel);
		center_panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		center_panel.add(scrollPane, BorderLayout.CENTER);
		initDbParams();
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	private JTable pobierzDaneDoTabeli1() {
		JTable tabela = null ;
		
		if (isDbReady) {
			ResultSet rs =null;
			Statement stmt = null;
			Connection connect = null;
			
			try {			
			connect = DriverManager.getConnection(dbParams.getDbUrl(),dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = (Statement) connect.createStatement();
			
		    String query = "SELECT * FROM LEKARZ ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				tabela = new JTable();
				DefaultTableModel modelTabeli = (DefaultTableModel) tabela.getModel();
				String[] nazwyKolumn = { "NR_LEKARZA", "IMIE", "NAZWISKO", "TELEFON"};
				modelTabeli.setColumnIdentifiers(nazwyKolumn);
				while (rs.next()) {
					Object[] objects = new Object[4];
					objects[0] = rs.getInt("NR_LEKARZA");
					objects[1] = rs.getString("IMIE");
					objects[2] = rs.getString("NAZWISKO");
					objects[3] = rs.getString("TELEFON");
					modelTabeli.addRow(objects);;
					}
				tabela.setModel(modelTabeli);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+", State:"+e.getSQLState());
			e.printStackTrace();			    		
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		return tabela;
}
	private JTable pobierzDaneDoTabeli2() {
		JTable tabela = null ;
		
		if (isDbReady) {
			ResultSet rs =null;
			Statement stmt = null;
			Connection connect = null;
			
			try {			
			connect = DriverManager.getConnection(dbParams.getDbUrl(),dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = (Statement) connect.createStatement();
			
		    String query = "SELECT * FROM PACJENT ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				tabela = new JTable();
				DefaultTableModel modelTabeli = (DefaultTableModel) tabela.getModel();
				String[] nazwyKolumn = { "NR_UBEZPIECZENIA", "NAZWISKO", "IMIE","ADRES","TELEFON"};
				modelTabeli.setColumnIdentifiers(nazwyKolumn);
				while (rs.next()) {
					Object[] objects = new Object[5];
					objects[0] = rs.getInt("NR_UBEZPIECZENIA");
					objects[1] = rs.getString("NAZWISKO");
					objects[2] = rs.getString("IMIE");
					objects[3] = rs.getString("ADRES");
					objects[4] = rs.getString("TELEFON");
					modelTabeli.addRow(objects);;
					}
				tabela.setModel(modelTabeli);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+", State:"+e.getSQLState());
			e.printStackTrace();			    		
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		return tabela;
}
	private JTable pobierzDaneDoTabeli3() {
		JTable tabela = null ;
		
		if (isDbReady) {
			ResultSet rs =null;
			Statement stmt = null;
			Connection connect = null;
			
			try {			
			connect = DriverManager.getConnection(dbParams.getDbUrl(),dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = (Statement) connect.createStatement();
			
		    String query = "SELECT * FROM WIZYTA ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				tabela = new JTable();
				DefaultTableModel modelTabeli = (DefaultTableModel) tabela.getModel();
				String[] nazwyKolumn = { "NR_WIZYTY", "DATA_WIZYTY", "OBJAWY", "NOTATKI","LEKARZ_NR_LEKARZA","PACJENT_NR_UBEZPIECZENIA"};
				modelTabeli.setColumnIdentifiers(nazwyKolumn);
				while (rs.next()) {
					Object[] objects = new Object[6];
					objects[0] = rs.getInt("NR_WIZYTY");
					objects[1] = rs.getString("DATA_WIZYTY");
					objects[2] = rs.getString("OBJAWY");
					objects[3] = rs.getString("NOTATKI");
					objects[4] = rs.getInt("LEKARZ_NR_LEKARZA");
					objects[5] = rs.getInt("PACJENT_NR_UBEZPIECZENIA");
					modelTabeli.addRow(objects);;
					}
				tabela.setModel(modelTabeli);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+", State:"+e.getSQLState());
			e.printStackTrace();			    		
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		return tabela;
}
	private JTable pobierzDaneDoTabeli4() {
		JTable tabela = null ;
		
		if (isDbReady) {
			ResultSet rs =null;
			Statement stmt = null;
			Connection connect = null;
			
			try {			
			connect = DriverManager.getConnection(dbParams.getDbUrl(),dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = (Statement) connect.createStatement();
			
		    String query = "SELECT * FROM DIAGNOZA ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				tabela = new JTable();
				DefaultTableModel modelTabeli = (DefaultTableModel) tabela.getModel();
				String[] nazwyKolumn = { "WIZYTA_NR_WIZYTY", "CHOROBA_NR_CHOROBY"};
				modelTabeli.setColumnIdentifiers(nazwyKolumn);
				while (rs.next()) {
					Object[] objects = new Object[2];
					objects[0] = rs.getInt("WIZYTA_NR_WIZYTY");
					objects[1] = rs.getInt("CHOROBA_NR_CHOROBY");
					modelTabeli.addRow(objects);;
					}
				tabela.setModel(modelTabeli);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+", State:"+e.getSQLState());
			e.printStackTrace();			    		
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		return tabela;
}
	private JTable pobierzDaneDoTabeli5() {
		JTable tabela = null ;
		
		if (isDbReady) {
			ResultSet rs =null;
			Statement stmt = null;
			Connection connect = null;
			
			try {			
			connect = DriverManager.getConnection(dbParams.getDbUrl(),dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = (Statement) connect.createStatement();
			
		    String query = "SELECT * FROM CHOROBA ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				tabela = new JTable();
				DefaultTableModel modelTabeli = (DefaultTableModel) tabela.getModel();
				String[] nazwyKolumn = { "NR_CHOROBY", "NAZWA", "OPIS", "OBJAWY"};
				modelTabeli.setColumnIdentifiers(nazwyKolumn);
				while (rs.next()) {
					Object[] objects = new Object[4];
					objects[0] = rs.getInt("NR_CHOROBY");
					objects[1] = rs.getString("NAZWA");
					objects[2] = rs.getString("OPIS");
					objects[3] = rs.getString("OBJAWY");
					modelTabeli.addRow(objects);;
					}
				tabela.setModel(modelTabeli);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+", State:"+e.getSQLState());
			e.printStackTrace();			    		
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		return tabela;
}
	private JTable pobierzDaneDoTabeli6() {
		JTable tabela = null ;
		
		if (isDbReady) {
			ResultSet rs =null;
			Statement stmt = null;
			Connection connect = null;
			
			try {			
			connect = DriverManager.getConnection(dbParams.getDbUrl(),dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = (Statement) connect.createStatement();
			
		    String query = "SELECT * FROM RECEPTA ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				tabela = new JTable();
				DefaultTableModel modelTabeli = (DefaultTableModel) tabela.getModel();
				String[] nazwyKolumn = { "WIZYTA_NR_WIZYTY", "LEKARSTWO_KOD"};
				modelTabeli.setColumnIdentifiers(nazwyKolumn);
				while (rs.next()) {
					Object[] objects = new Object[2];
					objects[0] = rs.getInt("WIZYTA_NR_WIZYTY");
					objects[1] = rs.getInt("LEKARSTWO_KOD");
					modelTabeli.addRow(objects);;
					}
				tabela.setModel(modelTabeli);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+", State:"+e.getSQLState());
			e.printStackTrace();			    		
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		return tabela;
}
	private JTable pobierzDaneDoTabeli7() {
		JTable tabela = null ;
		
		if (isDbReady) {
			ResultSet rs =null;
			Statement stmt = null;
			Connection connect = null;
			
			try {			
			connect = DriverManager.getConnection(dbParams.getDbUrl(),dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = (Statement) connect.createStatement();
			
		    String query = "SELECT * FROM LEKARSTWO ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				tabela = new JTable();
				DefaultTableModel modelTabeli = (DefaultTableModel) tabela.getModel();
				String[] nazwyKolumn = { "KOD", "NAZWA"};
				modelTabeli.setColumnIdentifiers(nazwyKolumn);
				while (rs.next()) {
					Object[] objects = new Object[2];
					objects[0] = rs.getInt("KOD");
					objects[1] = rs.getString("NAZWA");

					modelTabeli.addRow(objects);;
					}
				tabela.setModel(modelTabeli);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+", State:"+e.getSQLState());
			e.printStackTrace();			    		
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		return tabela;
}
	private JTable pobierzDaneDoTabeli8() {
		JTable tabela = null ;
		
		if (isDbReady) {
			ResultSet rs =null;
			Statement stmt = null;
			Connection connect = null;
			
			try {			
			connect = DriverManager.getConnection(dbParams.getDbUrl(),dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = (Statement) connect.createStatement();
			
		    String query = "SELECT * FROM LECZY ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				tabela = new JTable();
				DefaultTableModel modelTabeli = (DefaultTableModel) tabela.getModel();
				String[] nazwyKolumn = { "LEKARSTWO_KOD", "CHOROBA_NR_CHOROBY"};
				modelTabeli.setColumnIdentifiers(nazwyKolumn);
				while (rs.next()) {
					Object[] objects = new Object[2];
					objects[0] = rs.getInt("LEKARSTWO_KOD");
					objects[1] = rs.getInt("CHOROBA_NR_CHOROBY");
					modelTabeli.addRow(objects);;
					}
				tabela.setModel(modelTabeli);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+", State:"+e.getSQLState());
			e.printStackTrace();			    		
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		return tabela;
}
	private JTable pobierzDaneDoTabeli9() {
		JTable tabela = null ;
		
		if (isDbReady) {
			ResultSet rs =null;
			Statement stmt = null;
			Connection connect = null;
			
			try {			
			connect = DriverManager.getConnection(dbParams.getDbUrl(),dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = (Statement) connect.createStatement();
			
		    String query = "SELECT * FROM WCHODZI_W_INTERAKCJE";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				tabela = new JTable();
				DefaultTableModel modelTabeli = (DefaultTableModel) tabela.getModel();
				String[] nazwyKolumn = { "LEKARSTWO_KOD", "LEKARSTWO_KOD1"};
				modelTabeli.setColumnIdentifiers(nazwyKolumn);
				while (rs.next()) {
					Object[] objects = new Object[2];
					objects[0] = rs.getInt("LEKARSTWO_KOD");
					objects[1] = rs.getInt("LEKARSTWO_KOD1");
					modelTabeli.addRow(objects);;
					}
				tabela.setModel(modelTabeli);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage()+", State:"+e.getSQLState());
			e.printStackTrace();			    		
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connect != null)
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
		return tabela;
}
	
	private void initDbParams() {
		DbParams dbParams = new DbParams();
		
		try {
			if (dbParams.loadParams(PROPERTIES_PATH)) {
				if (Util.registerDbDriver(dbParams.getDriverUrl(),dbParams.getDriverClass())){
					isDbReady=true;
					this.dbParams = dbParams;
				}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
			


