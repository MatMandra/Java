package lab.dbapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class diagnoza extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
					diagnoza frame = new diagnoza();
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
	public diagnoza() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 301);
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
		
		JButton btnNewButton_2 = new JButton("ZAPISZ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(240, 208, 79, 22);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("ANULUJ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(331, 208, 99, 22);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("NR_CHOROBY");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(135, 68, 127, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("NR_WIZYTY");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(135, 33, 127, 16);
		panel.add(lblNewLabel_3);
		initDbParams();
		
	}
	private void dodajDiagnoza() { 

		if (isDbReady) {
			java.sql.Statement stmt = null;
			Connection connect = null;
			int Nr_Lekarza = Integer.parseInt(textField.getText());

			try{
			connect = DriverManager.getConnection(dbParams.getDbUrl(), dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = connect.createStatement();
			stmt.executeUpdate("INSERT INTO LEKARZ (NR_LEKARZA, NAZWISKO, IMIE, TELEFON) " + " VALUES ('"+Nr_Lekarza+"','"+textField_1.getText()+"')");
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
	private void usunDiagnoza() { 

		if (isDbReady) {
			java.sql.Statement stmt = null;
			Connection connect = null;
			int Nr_Lekarza = Integer.parseInt(textField.getText());

			try{
			connect = DriverManager.getConnection(dbParams.getDbUrl(), dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = connect.createStatement();
			stmt.executeUpdate("DELETE FROM LEKARZ WHERE NR_LEKARZA = "+Nr_Lekarza+"");
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
