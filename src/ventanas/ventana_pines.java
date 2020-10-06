package ventanas;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import conexion.conexion;
import controles.control_pines;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JTextPane;
import java.awt.Window.Type;

public class ventana_pines extends JFrame {
	public JScrollPane scrollFunciones;
	public JButton btnAtras;
	public static String nombreEmpresa = null;
	public static String totalDatos = null;
	public JButton btnMostrar;

	public int pagina = 0;

	public JPanel contentPane;
	public JTextField txtBusquedaCargos;
	public JScrollPane barraCargos;
	public JTable tablaCargos;

	public TableRowSorter trsfiltroCodigo;
	String filtroCodigo;
	public static String hora_fecha_reporte;
	public static String ruta_logo;
	public static JLabel label_2;
	private JLabel lblSeleccioneModalidad;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public ventana_pines() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(0);
		setBounds(100, 100, 855, 557);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollFunciones = new JScrollPane();

		JPanel panelTablaCargos = new JPanel();
		panelTablaCargos.setLayout(null);
		panelTablaCargos.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelTablaCargos.setBackground(Color.WHITE);
		panelTablaCargos.setBounds(10, 10, 431, 500);
		contentPane.add(panelTablaCargos);

		JLabel lblCargosRegistrados = new JLabel("LISTA DE PINES ENTREGADOS:");
		lblCargosRegistrados.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		lblCargosRegistrados.setBounds(10, 11, 264, 19);
		panelTablaCargos.add(lblCargosRegistrados);

		JLabel lblBuscarAlumno = new JLabel("Buscar Alumno:");
		lblBuscarAlumno.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblBuscarAlumno.setBounds(10, 58, 99, 22);
		panelTablaCargos.add(lblBuscarAlumno);

		txtBusquedaCargos = new JTextField();
		txtBusquedaCargos.setHorizontalAlignment(SwingConstants.CENTER);
		txtBusquedaCargos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtBusquedaCargos.setColumns(10);
		txtBusquedaCargos.setBounds(151, 61, 270, 19);
		panelTablaCargos.add(txtBusquedaCargos);
		InputMap map4 = txtBusquedaCargos.getInputMap(JComponent.WHEN_FOCUSED);
		txtBusquedaCargos.addKeyListener(new KeyListener() {
			@Override
			// metodo para buscar en la tabla
			public void keyTyped(KeyEvent ke) {
				trsfiltroCodigo = new TableRowSorter(tablaCargos.getModel());
				tablaCargos.setRowSorter(trsfiltroCodigo);

				if (txtBusquedaCargos.getText().length() == 30)
					ke.consume();

			}

			@Override
			public void keyPressed(KeyEvent ke) {

			}

			@Override
			public void keyReleased(KeyEvent ke) {
				String cadena = (txtBusquedaCargos.getText());
				txtBusquedaCargos.setText(cadena);
				repaint();
				filtro();
			}
		});

		barraCargos = new JScrollPane(tablaCargos, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelTablaCargos.add(barraCargos);
		barraCargos.setBounds(10, 91, 411, 376);

		tablaCargos = new JTable();
		barraCargos.setViewportView(tablaCargos);

		label_2 = new JLabel();
		label_2.setBounds(355, 41, 49, 44);
		panelTablaCargos.add(label_2);

		btnMostrar = new JButton("Ver detalles");
		btnMostrar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnMostrar.setBackground(new Color(0, 206, 209));
		btnMostrar.setBounds(10, 470, 411, 19);
		panelTablaCargos.add(btnMostrar);

		JButton btnImprimirReporte = new JButton("Imprimir Reporte");
		btnImprimirReporte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (totalDatos == null) {
					JOptionPane.showMessageDialog(null, "No hay registros disponibles para imprimir un reporte");
				} else {
					String ampm;
					Calendar cal = new GregorianCalendar();
					ampm = cal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
					String fecha = getFechaYHora() + ampm;
					nombreEmpresa = "IDO";
					int total = Integer.valueOf(totalDatos);
					String i = null;
					if (total <= 46) {
						i = "1";
					} else {
						if (total > 46 && total <= 92) {
							i = "2";
						} else {
							if (total > 92 && total <= 138) {
								i = "3";
							} else {
								if (total > 138 && total <= 184) {
									i = "4";
								} else {
									if (total > 184 && total <= 230) {
										i = "5";
									} else {
										if (total > 230 && total <= 276) {
											i = "6";
										} else {
											if (total > 276 && total <= 322) {
												i = "7";
											} else {
												if (total > 322 && total <= 368) {
													i = "8";
												} else {
													if (total > 368 && total <= 414) {
														i = "9";
													} else {
														if (total > 414 && total <= 460) {
															i = "10";
														} else {
															i = "Mas de 10 paginas";

														}

													}

												}
											}
										}
									}
								}
							}
						}
					}

					String encabezado = "Reporte de pines entregados ";

					utilJTablePrint(tablaCargos, encabezado, "Pagina {0} de " + i + "          Impreso por: "
							+ ventana_principal.user.toString() + "          " + fecha, true);

				}
			}
		});
		btnImprimirReporte.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnImprimirReporte.setBackground(new Color(60, 179, 113));
		btnImprimirReporte.setBounds(284, 12, 137, 19);
		panelTablaCargos.add(btnImprimirReporte);
		
		lblSeleccioneModalidad = new JLabel("Seleccione Modalidad:");
		lblSeleccioneModalidad.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblSeleccioneModalidad.setBounds(10, 36, 264, 19);
		panelTablaCargos.add(lblSeleccioneModalidad);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(151, 36, 270, 20);
		panelTablaCargos.add(comboBox);
		
		JPanel panelTablaCargos_1 = new JPanel();
		panelTablaCargos_1.setLayout(null);
		panelTablaCargos_1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelTablaCargos_1.setBackground(Color.WHITE);
		panelTablaCargos_1.setBounds(451, 11, 383, 499);
		contentPane.add(panelTablaCargos_1);
		
		JLabel lblInformacinDelAlumno = new JLabel("INFORMACION DEL ALUMNO:");
		lblInformacinDelAlumno.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		lblInformacinDelAlumno.setBounds(10, 12, 259, 19);
		panelTablaCargos_1.add(lblInformacinDelAlumno);
		
		JLabel lblNombreCompleto = new JLabel("Nombre completo.");
		lblNombreCompleto.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblNombreCompleto.setBounds(10, 42, 143, 22);
		panelTablaCargos_1.add(lblNombreCompleto);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(10, 75, 363, 19);
		panelTablaCargos_1.add(textField);
		
		JLabel label_2_1 = new JLabel();
		label_2_1.setBounds(355, 41, 49, 44);
		panelTablaCargos_1.add(label_2_1);
		
		JLabel lblNombreCompleto_1 = new JLabel("Identidad.");
		lblNombreCompleto_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblNombreCompleto_1.setBounds(10, 105, 143, 22);
		panelTablaCargos_1.add(lblNombreCompleto_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 138, 363, 19);
		panelTablaCargos_1.add(textField_1);
		
		JLabel lblNombreCompleto_2 = new JLabel("Modalidad.");
		lblNombreCompleto_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblNombreCompleto_2.setBounds(10, 172, 143, 22);
		panelTablaCargos_1.add(lblNombreCompleto_2);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(10, 205, 363, 19);
		panelTablaCargos_1.add(textField_2);
		
		JLabel lblUsuario = new JLabel("Usuario.");
		lblUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblUsuario.setBounds(10, 235, 143, 22);
		panelTablaCargos_1.add(lblUsuario);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(10, 268, 363, 19);
		panelTablaCargos_1.add(textField_3);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a.");
		lblContrasea.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblContrasea.setBounds(10, 298, 143, 22);
		panelTablaCargos_1.add(lblContrasea);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(10, 331, 363, 19);
		panelTablaCargos_1.add(textField_4);
		
		JTextPane txtpnNotaLaInformacin = new JTextPane();
		txtpnNotaLaInformacin.setText("Nota: La informaci\u00F3n del alumno debe ser confidencial, esta informaci\u00F3n es mostrada solamente con la intenci\u00F3n de verificar la entrega de pines a los alumnos.\r\n(Una vez entregado el Pin, este mismo puede ser actualizado, ya sea por motivos de olvido o perdida del documento, pero solo con el permiso de la directiva del colegio.)\r\n");
		txtpnNotaLaInformacin.setBounds(10, 374, 363, 93);
		panelTablaCargos_1.add(txtpnNotaLaInformacin);
		
				btnAtras = new JButton("Regresar");
				btnAtras.setBounds(271, 11, 102, 23);
				panelTablaCargos_1.add(btnAtras);
				btnAtras.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
				btnAtras.setBackground(new Color(255, 127, 80));
				btnAtras.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String Rol = ventana_login.rol.toString();
						String Nombre = ventana_login.nombre.toString();
						String NombreRol = ventana_login.NombreRol.toString();
						ventana_principal principal = new ventana_principal();
						Timer time = new Timer();
						time.schedule(principal.tarea, 0, 1000);
						principal.setLocationRelativeTo(null);
						principal.setVisible(true);
						principal.buscarRol();
						principal.lblFechaActual.setText(ventana_principal.getFecha());
						principal.setTitle("Usuario: " + Nombre + "    Permisos: " + NombreRol);
						if (Rol.equals("1")) {
							principal.btnUsuarios.setEnabled(true);
							principal.btn7_10.setEnabled(true);
							principal.btn8_9_11_12.setEnabled(true);
							principal.btnComprobarMatricula.setEnabled(true);
							principal.btnCredencialesRegistradas.setEnabled(true);
							principal.btnMatricula.setEnabled(true);
							principal.btnPrematricula.setEnabled(true);
						} else {
							if (Rol.equals("2")) {
								principal.btnUsuarios.setEnabled(false);
								principal.btnUsuarios.setEnabled(true);
								principal.btn7_10.setEnabled(true);
								principal.btn8_9_11_12.setEnabled(true);
								principal.btnComprobarMatricula.setEnabled(true);
								principal.btnCredencialesRegistradas.setEnabled(true);
								principal.btnMatricula.setEnabled(true);
								principal.btnPrematricula.setEnabled(true);

							} else {
								if (Rol.equals("3")) {
									principal.btnUsuarios.setEnabled(false);
									principal.btnUsuarios.setEnabled(false);
									principal.btn7_10.setEnabled(false);
									principal.btn8_9_11_12.setEnabled(false);
									principal.btnComprobarMatricula.setEnabled(false);
									principal.btnCredencialesRegistradas.setEnabled(false);
									principal.btnMatricula.setEnabled(false);
									principal.btnPrematricula.setEnabled(false);
									principal.btnComprobarMatricula.setEnabled(false);
									principal.btnImprimir.setEnabled(false);

								} else {
									principal.btnUsuarios.setEnabled(false);
									principal.btn7_10.setEnabled(true);
									principal.btn8_9_11_12.setEnabled(true);
									principal.btnComprobarMatricula.setEnabled(true);
									principal.btnCredencialesRegistradas.setEnabled(true);
									principal.btnMatricula.setEnabled(true);
									principal.btnPrematricula.setEnabled(true);

								}

							}

						}
						dispose();
					}
				});

	}
	
	

	public void construirTabla() {
		String titulos[] = { "Nombre", "Grado", "Identidad", "Modalidad", "Usuario", "Contraseña" };
		String informacion[][] = control_pines.obtenerMatriz();
		tablaCargos = new JTable(informacion, titulos);
		barraCargos.setViewportView(tablaCargos);
		for (int c = 0; c < tablaCargos.getColumnCount(); c++) {
			Class<?> col_class = tablaCargos.getColumnClass(c);
			tablaCargos.setDefaultEditor(col_class, null);
			tablaCargos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tablaCargos.getTableHeader().setReorderingAllowed(false);
			tablaCargos.getColumnModel().getColumn(1).setPreferredWidth(100);
			tablaCargos.getColumnModel().getColumn(2).setPreferredWidth(100);
			tablaCargos.getColumnModel().getColumn(3).setPreferredWidth(80);
			tablaCargos.getColumnModel().getColumn(4).setPreferredWidth(80);
			tablaCargos.getColumnModel().getColumn(5).setPreferredWidth(200);

			// alinear datos de sueldo y horaextra a la derecha en la tabla
			DefaultTableCellRenderer tcr;
			tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.RIGHT);
			tablaCargos.getColumnModel().getColumn(3).setCellRenderer(tcr);
			tablaCargos.getColumnModel().getColumn(4).setCellRenderer(tcr);

			DefaultTableCellRenderer tcr1;
			tcr1 = new DefaultTableCellRenderer();
			tcr1.setHorizontalAlignment(SwingConstants.CENTER);
			tablaCargos.getColumnModel().getColumn(0).setCellRenderer(tcr1);
			tablaCargos.getColumnModel().getColumn(1).setCellRenderer(tcr1);
		}
	}

	public void filtro() {
		filtroCodigo = txtBusquedaCargos.getText();
		trsfiltroCodigo.setRowFilter(RowFilter.regexFilter(txtBusquedaCargos.getText(), 0, 1, 2, 3, 4, 5));
	}


	public void utilJTablePrint(JTable jTable, String header, String footer, boolean showPrintDialog) {
		boolean fitWidth = true;
		boolean interactive = true;
		JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
		try {
			boolean complete = jTable.print(mode, new MessageFormat(header), new MessageFormat(footer), showPrintDialog,
					null, interactive);
			if (complete) {
				JOptionPane.showMessageDialog(jTable, "Print complete (Impresión completa)",
						"Print result (Resultado de la impresión)", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(jTable, "Print canceled (Impresión cancelada)",
						"Print result (Resultado de la impresión)", JOptionPane.WARNING_MESSAGE);
			}
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(jTable, "Print fail (Fallo de impresión): " + pe.getMessage(),
					"Print result (Resultado de la impresión)", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static String getFechaYHora() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("dd'/'MMMMM'/'yyyy HH:mm:ss ");
		date = cal.getTime();
		return df.format(date);
	}
	
	public void obtenerTotalDatosReporte() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("select * from Prematriculas, users, Roles, Modalidades, Grupos where Nombre_Rol= 'Alumno'");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				totalDatos = rsr.getString("id");
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
