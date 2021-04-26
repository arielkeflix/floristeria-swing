package floristeria.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import floristeria.modelo.Arbol;
import floristeria.modelo.Decoracion;
import floristeria.modelo.Flor;
import floristeria.modelo.Floreria;



public class VentanaPrincipal extends JFrame {

	private Floreria floreria;

	private VentanaModificarStock ventanaModificarStock;
	private VentanaListarStock ventanaListarStock;
	private VentanaVentas ventanaVentas;
	private VentanaListarTiquets ventanaListarTiquets;
	private VentanaModificarPrecio ventanaModificarPrecio;
	private VentanaContacto ventanaContacto;
	private VentanaHelp ventanaHelp;
	private VentanaWelcome ventanaWelcome;

	private MenuBar menuBar = new MenuBar(); // necesito crearlo aqui para que lo vean creado desde todas las clases
												// internas tantoPanelPrincipal como inicio

	JScrollPane srScroll;

	public VentanaPrincipal() {

		Toolkit miPantalla = Toolkit.getDefaultToolkit();

		Dimension tamañoPantalla = miPantalla.getScreenSize();

		int alturaPantalla = tamañoPantalla.height;

		int anchoPantalla = tamañoPantalla.width;

		setSize(900, 600);

		setLocation((anchoPantalla - 900) / 2, (alturaPantalla - 600) / 2);

		PanelPrincipal p = new PanelPrincipal();
		add(p);

	}

	class PanelPrincipal extends JPanel {

		public PanelPrincipal() {

			setLayout(new BorderLayout());

			add(menuBar, BorderLayout.NORTH);

			menuBar.setVisible(false);

			VentanaInicio ventanaInicio = new VentanaInicio();

			srScroll = new JScrollPane();

			add(srScroll, BorderLayout.CENTER);

			srScroll.setViewportView(ventanaInicio);
		}
	}

	class MenuBar extends JMenuBar {

		public MenuBar() {

			JButton btStock = new JButton("MODIFICAR STOCK");
			JButton btVentas = new JButton("VENTAS");
			JButton btListarStock = new JButton("LISTAR STOCK");
			JButton btListarTiquets = new JButton("LISTAR TIQUETS");
			JButton btPrecios = new JButton("MODIFICAR PRECIOS");
			JButton btContacto = new JButton("CONTACTO");
			JButton btAyuda = new JButton("HELP");

			btPrecios.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					srScroll.setViewportView(ventanaModificarPrecio);
				}
			});

			btAyuda.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					srScroll.setViewportView(ventanaHelp);
				}
			});
			btContacto.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					srScroll.setViewportView(ventanaContacto);
				}
			});
			btListarStock.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ventanaListarStock.generarListado();
					srScroll.setViewportView(ventanaListarStock);
				}
			});
			btListarTiquets.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					ventanaListarTiquets.cargarTablas(0); // inicio desde el 1er tiquets
					srScroll.setViewportView(ventanaListarTiquets);
				}
			});
			btStock.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					srScroll.setViewportView(ventanaModificarStock);
				}
			});

			btVentas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					srScroll.setViewportView(ventanaVentas);
				}
			});
			add(btStock);
			add(btVentas);
			add(btListarStock);
			add(btListarTiquets);
			add(btPrecios);
			add(btContacto);
			add(btAyuda);
		}
	}

	class VentanaInicio extends JPanel {

		private JTextField tfNombre, tfArbol, inFlor, tfDecoracionM, inDecoracionP, tfDireccion, inTelefono;

		public VentanaInicio() {

			Font miFuente = new Font("Arial", Font.BOLD, 18);

			setLayout(null);

			PanelIzquierdo panelizquierdo = new PanelIzquierdo();
			panelizquierdo.setBounds(0, 50, 400, 350);

			PanelDerecho panelDerecho = new PanelDerecho();
			panelDerecho.setBounds(450, 50, 450, 350);

			add(panelizquierdo);
			add(panelDerecho);

			JLabel lbTitulo = new JLabel("FORMULARIO DE INICIO OPERACIONES FLORISTERIA");
			lbTitulo.setFont(miFuente);
			lbTitulo.setBounds(200, 10, 500, 40);
			add(lbTitulo);

			JButton btCrear = new JButton("Crear floristeria");
			btCrear.setBounds(300, 420, 150, 40);
			add(btCrear);
			btCrear.addActionListener(new CrearFloristeria());
		}

		private class PanelIzquierdo extends JPanel {

			public PanelIzquierdo() {

				Font miFuente = new Font("Arial", Font.BOLD, 14);

				setLayout(null);

				JLabel lbTitulo = new JLabel("Rellene los datos de la floreria ");
				lbTitulo.setFont(miFuente);
				lbTitulo.setBounds(100, 20, 250, 30);
				add(lbTitulo);

				JLabel lbNombre = new JLabel("Nombre de la floreria: ");
				lbNombre.setBounds(20, 90, 150, 30);
				add(lbNombre);

				tfNombre = new JTextField();
				tfNombre.setBounds(170, 90, 150, 30);
				tfNombre.setBackground(Color.WHITE);
				add(tfNombre);

				JLabel lbDireccion = new JLabel("Direccion de la floreria: ");
				lbDireccion.setBounds(20, 130, 150, 30);
				add(lbDireccion);

				tfDireccion = new JTextField();
				tfDireccion.setBounds(170, 130, 150, 30);
				tfDireccion.setBackground(Color.WHITE);
				add(tfDireccion);

				JLabel lTelefono = new JLabel("Telefono de la floreria: ");
				lTelefono.setBounds(20, 170, 150, 30);
				add(lTelefono);

				inTelefono = new JTextField();
				inTelefono.setBounds(170, 170, 150, 30);
				inTelefono.setBackground(Color.WHITE);
				add(inTelefono);
			}
		}

		private class PanelDerecho extends JPanel {

			public PanelDerecho() {

				Font miFuente = new Font("Arial", Font.BOLD, 14);

				setLayout(null);

				JLabel lbTitulo = new JLabel("Rellene los precios por unidad");
				lbTitulo.setFont(miFuente);
				lbTitulo.setBounds(50, 20, 350, 30);
				add(lbTitulo);

				JLabel lFormato = new JLabel("Formato 'X.XX'");
				lFormato.setBounds(200, 60, 350, 30);
				add(lFormato);

				JLabel lArbol = new JLabel("Arboles,  cada 10 cm:  ");
				lArbol.setBounds(0, 90, 300, 30);
				add(lArbol);
				tfArbol = new JTextField();
				tfArbol.setBackground(Color.WHITE);
				tfArbol.setBounds(200, 90, 150, 30);
				add(tfArbol);

				JLabel lFlor = new JLabel("Flores :  ");
				lFlor.setBounds(0, 130, 300, 30);
				add(lFlor);
				inFlor = new JTextField();
				inFlor.setBackground(Color.WHITE);
				inFlor.setBounds(200, 130, 150, 30);
				add(inFlor);

				JLabel lbDecoracionM = new JLabel("Decoracion de madera :  ");
				lbDecoracionM.setBounds(0, 170, 300, 30);
				add(lbDecoracionM);
				tfDecoracionM = new JTextField();
				tfDecoracionM.setBounds(200, 170, 150, 30);
				tfDecoracionM.setBackground(Color.WHITE);
				add(tfDecoracionM);

				JLabel lbDecoracionP = new JLabel("Decoracion de plastico:  ");
				add(lbDecoracionP);
				lbDecoracionP.setBounds(0, 210, 300, 30);
				inDecoracionP = new JTextField();
				inDecoracionP.setBackground(Color.WHITE);
				inDecoracionP.setBounds(200, 210, 150, 30);
				add(inDecoracionP);

			}
		}

		private class CrearFloristeria implements ActionListener {
			private Double aux = 0.0;

			@Override
			public void actionPerformed(ActionEvent e) {

				if (validar()) {

					String colores[] = { "Rojo", "Amarillo", "Blanco", "Azul" };

					floreria = new Floreria(tfNombre.getText(), tfDireccion.getText(), inTelefono.getText());

					VentanaPrincipal.super.setTitle("Floreria: " + floreria.getNombre());

					for (int x = 10; x < 210; x += 10) {

						floreria.agregarArbol(new Arbol(x * Double.parseDouble(tfArbol.getText()) / 10, x));
					}
					for (int x = 0; x < colores.length; x++) {
						floreria.agregarFlor(new Flor(Double.parseDouble(inFlor.getText()), colores[x]));
					}
					floreria.agregarDecoracion(new Decoracion(Double.parseDouble(tfDecoracionM.getText()), "Madera"));

					floreria.agregarDecoracion(new Decoracion(Double.parseDouble(inDecoracionP.getText()), "Plastico"));

					ventanaModificarStock = new VentanaModificarStock(floreria);

					ventanaListarStock = new VentanaListarStock(floreria);

					ventanaListarTiquets = new VentanaListarTiquets(floreria);

					ventanaVentas = new VentanaVentas(floreria);

					ventanaModificarPrecio = new VentanaModificarPrecio(floreria, ventanaVentas);

					ventanaContacto = new VentanaContacto(floreria);

					ventanaHelp = new VentanaHelp(floreria);

					ventanaWelcome = new VentanaWelcome(floreria);

					menuBar.setVisible(true);

					srScroll.setViewportView(ventanaWelcome);
				}
			}

			private boolean validar() {

				if (tfNombre.getText().isEmpty() || tfDireccion.getText().isEmpty() || inTelefono.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campo  de la floreria  ",
							"Importante!!", JOptionPane.ERROR_MESSAGE);
					return false;
				}

				if (validarDouble(tfArbol, "Precio arbol") && validarDouble(inFlor, "Precio flor")
						&& validarDouble(tfDecoracionM, "Precio decoracion de madera")
						&& validarDouble(inDecoracionP, "Precio decoracion de plastico"))
					return true;

				else
					return false;
			}

			private boolean validarDouble(JTextField campo, String nombre) {

				if (campo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El precio del campo '" + nombre + "' no puede estar vacio ",
							"Importante!!", JOptionPane.ERROR_MESSAGE);
					return false;
				} else {
					try {
						aux = Double.parseDouble(campo.getText());

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null,
								"El precio del campo '" + nombre + "' debe estar en un formato numerico 'x.xx' ",
								"Importante!!", JOptionPane.ERROR_MESSAGE);
						return false;
					}
					return true;
				}
			}
		}
	}
}
