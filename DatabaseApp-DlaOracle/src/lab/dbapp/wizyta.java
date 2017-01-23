package lab.dbapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class wizyta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wizyta frame = new wizyta();
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
	public wizyta() {
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
		
		JButton btnNewButton = new JButton("ZAPISZ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(331, 173, 99, 22);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("NR_LEKARZA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(135, 173, 122, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("OBJAWY");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(135, 103, 122, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DATA rrrr-mm-dd");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(135, 68, 122, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("NR_WIZYTY");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(128, 33, 129, 16);
		panel.add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(0, 170, 116, 22);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("NOTATKI");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(135, 138, 122, 16);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("ANULUJ\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(331, 208, 99, 22);
		panel.add(btnNewButton_1);
		
		textField_5 = new JTextField();
		textField_5.setBounds(0, 208, 116, 22);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNrpacjenta = new JLabel("NR_PACJENTA");
		lblNrpacjenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNrpacjenta.setBounds(135, 211, 122, 16);
		panel.add(lblNrpacjenta);
	}

}
