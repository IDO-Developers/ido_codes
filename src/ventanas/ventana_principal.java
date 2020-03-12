package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class ventana_principal extends JFrame implements Printable {

	private JPanel contentPane;
	public JButton btnImprimir;

	public static JLabel lblNombre = null;
	public static JLabel lblIdentidad = null;
	public static JLabel lblModalidad = null;
	public static JLabel lblHora = null;
	public static JLabel lblFecha = null;
	public static JLabel lblCodigo = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ventana_principal frame = new ventana_principal();
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
	public ventana_principal() {
		setType(Type.UTILITY);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 608);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInstitutoDepartamentalDe = new JLabel("Instituto Departamental de Oriente\r\n");
		lblInstitutoDepartamentalDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstitutoDepartamentalDe.setForeground(new Color(0, 0, 0));
		lblInstitutoDepartamentalDe.setFont(new Font("Cambria", Font.BOLD, 18));
		lblInstitutoDepartamentalDe.setBounds(0, 21, 474, 49);
		contentPane.add(lblInstitutoDepartamentalDe);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(141, 32, 174, 165);
		contentPane.add(label_1);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/recursos/logo_ido.png"));
		final ImageIcon icono = new ImageIcon(
				logo.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_DEFAULT));
		label_1.setIcon(icono);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnImprimir.setVisible(false);
				imprimirInformacionDelAlumno();

			}
		});
		btnImprimir.setFont(new Font("Calibri", Font.BOLD, 14));
		btnImprimir.setBackground(new Color(102, 205, 170));
		btnImprimir.setBounds(182, 11, 99, 21);
		contentPane.add(btnImprimir);

		JLabel lblInformacinDelAlumno = new JLabel("Informaci\u00F3n del alumno:");
		lblInformacinDelAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDelAlumno.setForeground(new Color(0, 0, 128));
		lblInformacinDelAlumno.setFont(new Font("Cambria", Font.BOLD, 16));
		lblInformacinDelAlumno.setBounds(0, 188, 474, 28);
		contentPane.add(lblInformacinDelAlumno);

		JLabel lblIdentidadDelAlumno = new JLabel("Identidad del alumno:");
		lblIdentidadDelAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidadDelAlumno.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		lblIdentidadDelAlumno.setBounds(0, 219, 474, 21);
		contentPane.add(lblIdentidadDelAlumno);

		JLabel lblCdigo = new JLabel("C\u00F3digo: ");
		lblCdigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCdigo.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		lblCdigo.setBounds(0, 264, 474, 21);
		contentPane.add(lblCdigo);

		JLabel label_8 = new JLabel("Hora :");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("Cambria", Font.BOLD, 15));
		label_8.setBounds(98, 353, 72, 21);
		contentPane.add(label_8);

		lblHora = new JLabel("Dato");
		lblHora.setForeground(new Color(0, 0, 128));
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Cambria", Font.BOLD, 15));
		lblHora.setBounds(166, 353, 214, 21);
		contentPane.add(lblHora);

		JLabel label_10 = new JLabel("Fecha :");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Cambria", Font.BOLD, 15));
		label_10.setBounds(98, 321, 72, 21);
		contentPane.add(label_10);

		lblFecha = new JLabel();
		lblFecha.setForeground(new Color(0, 0, 128));
		lblFecha.setText("Dato");
		lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha.setFont(new Font("Cambria", Font.BOLD, 14));
		lblFecha.setBounds(166, 321, 214, 21);
		contentPane.add(lblFecha);

		JLabel lblDireccin = new JLabel("Lic. Suyapa Cerna");
		lblDireccin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccin.setForeground(Color.BLACK);
		lblDireccin.setFont(new Font("Cambria", Font.BOLD, 11));
		lblDireccin.setBounds(88, 495, 135, 20);
		contentPane.add(lblDireccin);

		JLabel lblDirector = new JLabel();
		lblDirector.setBackground(Color.WHITE);
		lblDirector.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirector.setForeground(new Color(0, 0, 128));
		lblDirector.setFont(new Font("Cambria", Font.BOLD, 15));
		lblDirector.setBounds(235, 385, 197, 99);
		contentPane.add(lblDirector);
		final ImageIcon logod = new ImageIcon(getClass().getResource("/recursos/director.jpg"));
		final ImageIcon iconod = new ImageIcon(logod.getImage().getScaledInstance(lblDirector.getWidth(),
				lblDirector.getHeight(), Image.SCALE_DEFAULT));
		lblDirector.setIcon(iconod);

		JLabel lblSecre = new JLabel();
		lblSecre.setBackground(Color.WHITE);
		lblSecre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecre.setForeground(new Color(0, 0, 128));
		lblSecre.setFont(new Font("Cambria", Font.BOLD, 15));
		lblSecre.setBounds(44, 404, 211, 80);
		contentPane.add(lblSecre);
		final ImageIcon logos = new ImageIcon(getClass().getResource("/recursos/secretaria.png"));
		final ImageIcon iconos = new ImageIcon(
				logos.getImage().getScaledInstance(lblSecre.getWidth(), lblSecre.getHeight(), Image.SCALE_DEFAULT));
		lblSecre.setIcon(iconos);

		JLabel lblMscHenriRenan = new JLabel("V\u00B0B\u00B0 MSc. Henri \u00C1lvarez ");
		lblMscHenriRenan.setHorizontalAlignment(SwingConstants.CENTER);
		lblMscHenriRenan.setForeground(Color.BLACK);
		lblMscHenriRenan.setFont(new Font("Cambria", Font.BOLD, 11));
		lblMscHenriRenan.setBounds(275, 495, 122, 20);
		contentPane.add(lblMscHenriRenan);

		JLabel lblSecretaria = new JLabel("Secretar\u00EDa");
		lblSecretaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecretaria.setForeground(Color.BLACK);
		lblSecretaria.setFont(new Font("Cambria", Font.BOLD, 11));
		lblSecretaria.setBounds(88, 508, 135, 21);
		contentPane.add(lblSecretaria);

		JLabel lblDireccin_1 = new JLabel("Direcci\u00F3n. ");
		lblDireccin_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccin_1.setForeground(Color.BLACK);
		lblDireccin_1.setFont(new Font("Cambria", Font.BOLD, 11));
		lblDireccin_1.setBounds(275, 508, 122, 21);
		contentPane.add(lblDireccin_1);

		JLabel label = new JLabel("__________________");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Cambria", Font.BOLD, 15));
		label.setBounds(98, 476, 122, 21);
		contentPane.add(label);

		JLabel label_2 = new JLabel("__________________");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setFont(new Font("Cambria", Font.BOLD, 15));
		label_2.setBounds(233, 476, 199, 21);
		contentPane.add(label_2);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(98, 237, 282, 28);
		contentPane.add(panel);
		panel.setLayout(null);

		lblIdentidad = new JLabel("Dato");
		lblIdentidad.setBounds(97, 0, 185, 28);
		panel.add(lblIdentidad);
		lblIdentidad.setForeground(new Color(0, 100, 0));
		lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setForeground(new Color(0, 0, 128));
		lblUsuario.setFont(new Font("Cambria", Font.BOLD, 15));
		lblUsuario.setBounds(10, 0, 91, 28);
		panel.add(lblUsuario);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(98, 282, 282, 28);
		contentPane.add(panel_1);

		lblCodigo = new JLabel("Dato");
		lblCodigo.setBounds(98, 0, 184, 28);
		panel_1.add(lblCodigo);
		lblCodigo.setForeground(new Color(0, 100, 0));
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasea.setForeground(new Color(0, 0, 128));
		lblContrasea.setFont(new Font("Cambria", Font.BOLD, 15));
		lblContrasea.setBounds(10, 0, 91, 28);
		panel_1.add(lblContrasea);

		JLabel lblNotaParaMatricular = new JLabel("Nota: para matricular ingrese a www.ido.edu.hn");
		lblNotaParaMatricular.setForeground(new Color(0, 0, 0));
		lblNotaParaMatricular.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotaParaMatricular.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNotaParaMatricular.setBounds(0, 535, 474, 34);
		contentPane.add(lblNotaParaMatricular);

		JLabel lblCredencialesParaMatricular = new JLabel("Credenciales para matricular: ");
		lblCredencialesParaMatricular.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredencialesParaMatricular.setForeground(new Color(0, 0, 0));
		lblCredencialesParaMatricular.setFont(new Font("Cambria", Font.BOLD, 17));
		lblCredencialesParaMatricular.setBounds(0, 160, 474, 34);
		contentPane.add(lblCredencialesParaMatricular);

	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex == 0) {
			Graphics2D g2d = (Graphics2D) graphics;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			contentPane.printAll(graphics);
			contentPane.printAll(graphics);
			return PAGE_EXISTS;
		} else {
			return NO_SUCH_PAGE;
		}
	}

	public void imprimirInformacionDelAlumno() {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setPrintable(this);
		try {
			printerJob.print();
		} catch (PrinterException ex) {
			System.out.println("Error:" + ex);
		}
	}

	public static String getFecha() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'del' yyyy");
		date = cal.getTime();
		return df.format(date);
	}

	public static void getHora() {
		Calendar cal = Calendar.getInstance();
		Date fecha = cal.getTime();
		DateFormat formatter = DateFormat.getTimeInstance();
		lblHora.setText(formatter.format(fecha));
	}
}
