package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class login extends JFrame {

	private JPanel contentPane;
	public JTextField txtUsuario;
	public JTextField txtContraseña;
	public JButton btnIngresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(51, 49, 321, 166);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("USUARIO: ");
		lblUsuario.setBounds(31, 43, 84, 14);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(125, 40, 150, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A");
		lblContrasea.setBounds(31, 72, 84, 14);
		panel.add(lblContrasea);
		
		txtContraseña = new JTextField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(125, 69, 150, 20);
		panel.add(txtContraseña);
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setBounds(125, 100, 150, 23);
		panel.add(btnIngresar);
	}
}
