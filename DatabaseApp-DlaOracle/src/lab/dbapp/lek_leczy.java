package lab.dbapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class lek_leczy extends JFrame {

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
					lek_leczy frame = new lek_leczy();
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
	public lek_leczy() {
		setTitle("Lekarstwo leczy chorobę");
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
		
		JButton btnNewButton_2 = new JButton("DODAJ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajLL();
			}
		});
		btnNewButton_2.setBounds(240, 208, 79, 22);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("USUŃ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usunLL();
			}
		});
		btnNewButton.setBounds(331, 208, 99, 22);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("NR_CHOROBY");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(135, 68, 116, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("KOD LEKARSTWA");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(135, 33, 116, 16);
		panel.add(lblNewLabel_3);
		
		JLabel lblabyUsunPozycj = new JLabel("<html>Aby Usunąć pozycję z bazy danych, wpisz KOD LEKARSTWA oraz NR_CHOROBY<br> <html>");
		lblabyUsunPozycj.setHorizontalAlignment(SwingConstants.CENTER);
		lblabyUsunPozycj.setBounds(262, 13, 180, 99);
		panel.add(lblabyUsunPozycj);
		initDbParams();
		
	}
	private void dodajLL() { 

		if (isDbReady) {
			java.sql.Statement stmt = null;
			Connection connect = null;

			try{
			connect = DriverManager.getConnection(dbParams.getDbUrl(), dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = connect.createStatement();
			stmt.executeUpdate("INSERT INTO LECZY (LEKARSTWO_KOD, CHOROBA_NR_CHOROBY ) " + " VALUES ('"+textField.getText()+"','"+textField_1.getText()+"')");
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
	private void usunLL() { 

		if (isDbReady) {
			java.sql.Statement stmt = null;
			Connection connect = null;

			try{
			connect = DriverManager.getConnection(dbParams.getDbUrl(), dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = connect.createStatement();
			stmt.executeUpdate("DELETE FROM LECZY WHERE LEKARSTWO_KOD  = "+textField.getText()+" AND CHOROBA_NR_CHOROBY = "+textField_1.getText()+"");
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
