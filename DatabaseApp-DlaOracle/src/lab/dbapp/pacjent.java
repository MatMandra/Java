package lab.dbapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class pacjent extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	private static final String PROPERTIES_PATH = "db/oracledb.properties";
	private DbParams dbParams = null;
	private boolean isDbReady = false;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pacjent frame = new pacjent();
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
	public pacjent() {
		setTitle("Pacjent");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 30, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(0, 65, 116, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(0, 100, 116, 22);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(0, 135, 116, 22);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("DODAJ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajPacjent();
			}
		});
		btnNewButton.setBounds(240, 208, 79, 22);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("TELEFON");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(135, 173, 97, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IMIE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(135, 103, 97, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NAZWISKO");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(135, 68, 97, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("NR_UBEZPIECZENIA");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(128, 33, 129, 16);
		panel.add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(0, 170, 116, 22);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ADRES");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(135, 138, 97, 16);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("USUŃ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usunP();
			}
		});
		btnNewButton_1.setBounds(331, 208, 99, 22);
		panel.add(btnNewButton_1);
		
		JLabel lblabyUsunPozycj = new JLabel("<html>Aby Usunąć pozycję z bazy danych, wpisz NR_UBEZPIECZENIA i wciśnij USUŃ<br> Aby operacja przebiegła pomyślnie, należy w pierwszej kolejności usunąć przypisane do Pacjenta WIZYTY </html> ");
		lblabyUsunPozycj.setHorizontalAlignment(SwingConstants.CENTER);
		lblabyUsunPozycj.setBounds(258, 16, 184, 162);
		panel.add(lblabyUsunPozycj);
		initDbParams();
		
	}
	private void dodajPacjent() { 

		if (isDbReady) {
			java.sql.Statement stmt = null;
			Connection connect = null;
			

			try{
			connect = DriverManager.getConnection(dbParams.getDbUrl(), dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = connect.createStatement();
			stmt.executeUpdate("INSERT INTO PACJENT(NR_UBEZPIECZENIA, NAZWISKO, IMIE, ADRES, TELEFON) " + " VALUES ('"+textField.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"')");
			JOptionPane.showMessageDialog(null, "Dodano Pomyślnie");
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+", State:"+e.getSQLState());	
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
		
	}
	private void usunP() { 

		if (isDbReady) {
			java.sql.Statement stmt = null;
			Connection connect = null;
		

			try{
			connect = DriverManager.getConnection(dbParams.getDbUrl(), dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = connect.createStatement();
			stmt.executeUpdate("DELETE FROM PACJENT WHERE NR_UBEZPIECZENIA = "+textField.getText()+"");
			JOptionPane.showMessageDialog(null, "Usunięto Pomyślnie");	
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+", State:"+e.getSQLState());	
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