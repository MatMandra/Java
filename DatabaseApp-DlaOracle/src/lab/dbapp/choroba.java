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

public class choroba extends JFrame {

	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
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
					choroba frame = new choroba();
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
	public choroba() {
		setTitle("Choroba");
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
		
		JButton btnNewButton_2 = new JButton("DODAJ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajChoroba();
			}
		});
		btnNewButton_2.setBounds(240, 208, 79, 22);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("USUŃ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usunChoroba();
			}
		});
		btnNewButton.setBounds(331, 208, 99, 22);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("OBJAWY");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(135, 138, 97, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("OPIS");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(135, 103, 97, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NAZWA");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(135, 68, 97, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("NR_CHOROBY");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(135, 33, 97, 16);
		panel.add(lblNewLabel_3);
		
		JLabel lblabyUsunPozycj = new JLabel("<html>Aby Usunąć pozycję z bazy danych, wpisz NR_CHOROBY i wciśnij USUŃ<br> Aby operacja przebiegła pomyślnie, w pierwszej kolejności należy usunąć przypisane do choroby DIAGNOZY i LEKARSTWO_LECZY</html> ");
		lblabyUsunPozycj.setHorizontalAlignment(SwingConstants.CENTER);
		lblabyUsunPozycj.setBounds(240, 13, 202, 167);
		panel.add(lblabyUsunPozycj);
		initDbParams();
		
	}
	private void dodajChoroba() { 

		if (isDbReady) {
			java.sql.Statement stmt = null;
			Connection connect = null;
			

			try{
			connect = DriverManager.getConnection(dbParams.getDbUrl(), dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = connect.createStatement();
			stmt.executeUpdate("INSERT INTO CHOROBA (NR_CHOROBY, NAZWA, OPIS, OBJAWY) " + " VALUES ('"+textField.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"')");
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
	private void usunChoroba() { 

		if (isDbReady) {
			java.sql.Statement stmt = null;
			Connection connect = null;
			

			try{
			connect = DriverManager.getConnection(dbParams.getDbUrl(), dbParams.getDbUser(), dbParams.getDbPassword());
			stmt = connect.createStatement();
			stmt.executeUpdate("DELETE FROM CHOROBA WHERE NR_CHOROBY = "+textField.getText()+"");
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