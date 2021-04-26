package floristeria.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import floristeria.modelo.Arbol;
import floristeria.modelo.Decoracion;
import floristeria.modelo.Flor;
import floristeria.modelo.Floreria;
import floristeria.modelo.Registro;
import floristeria.modelo.Tiquet;

public class VentanaVentas extends JPanel {

	private Floreria floreria;
	private ActualizarPrecio actualizarPrecio;
	private double precioTotal;
	private Tiquet tiquet;
	private PanelDerecho panelDerecho;
	private JTextField tfPrecioTot;
	private JTextField tfPrecioArbol, tfPrecioFlor, tfPrecioDecoracion;
	private JSpinner spAltura;
	private String material;
	private double precioArbol, precioFlor, precioDecoracion;

	public VentanaVentas(Floreria floreria) {

		Font miFuente = new Font("Arial", Font.BOLD, 18);

		this.floreria = floreria;
		tiquet = new Tiquet();

		actualizarPrecio = new ActualizarPrecio();

		setLayout(null);

		JLabel lbTitulo = new JLabel("VENTAS");
		lbTitulo.setBounds(0, 10, 900, 30);
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(miFuente);
		add(lbTitulo);

		PanelIzquierdo panelizquierdo = new PanelIzquierdo();
		panelizquierdo.setBounds(0, 50, 520, 470);

		panelDerecho = new PanelDerecho();
		panelDerecho.setBounds(530, 50, 300, 470);

		add(panelizquierdo);
		add(panelDerecho);
	}

	public void recargarPrecios() {

		precioArbol = buscarPrecio("arbol", spAltura.getValue().toString());
		precioFlor = buscarPrecio("flor", "Rojo");
		precioDecoracion = buscarPrecio("decoracion", material);

		tfPrecioArbol.setText("€ " + precioArbol);
		tfPrecioFlor.setText("€ " + precioFlor);
		tfPrecioDecoracion.setText("€ " + precioDecoracion);
	}

	class PanelIzquierdo extends JPanel {

		public PanelIzquierdo() {

			Font miFuente = new Font("Arial", Font.BOLD, 14);

			setLayout(null);

			JLabel lbTitulo = new JLabel("AÑADIR PRODUCTOS");
			lbTitulo.setFont(miFuente);
			lbTitulo.setBounds(250, 0, 200, 30);
			add(lbTitulo);

			VentaArbol ventaArbol = new VentaArbol();
			ventaArbol.setBounds(50, 40, 400, 130);
			add(ventaArbol);

			VentaFlor ventaFlor = new VentaFlor();
			ventaFlor.setBounds(50, 180, 400, 130);
			add(ventaFlor);

			VentaDecoracion ventaDecoracion = new VentaDecoracion();
			ventaDecoracion.setBounds(50, 320, 400, 130);
			add(ventaDecoracion);

		}

	}

	class PanelDerecho extends JPanel {

		private JTable table;
		private String[] columnas;
		private JScrollPane srScroll;

		public PanelDerecho() {

			Font miFuente = new Font("Arial", Font.BOLD, 14);

			columnas = new String[] { "Tipo", "Cantidad", "Precio  " };

			srScroll = new JScrollPane();

			setLayout(null);
			JLabel lbTitulo = new JLabel("TIQUET VENTA");
			lbTitulo.setFont(miFuente);
			lbTitulo.setBounds(90, 0, 100, 35);
			add(lbTitulo);

			srScroll.setBounds(1, 40, 280, 300);
			add(srScroll);
			cargarTabla();

			JLabel lbPrecioTot = new JLabel("Precio total: ");
			lbPrecioTot.setBounds(100, 360, 80, 30);
			add(lbPrecioTot);

			tfPrecioTot = new JTextField("€ 0.0 ");
			tfPrecioTot.setBounds(180, 360, 80, 30);
			add(tfPrecioTot);

			JButton btEliminar = new JButton("Eliminar ultimo registro"); // elimina el ultimo registro y actualiza el
																			// stock
			btEliminar.setBounds(0, 410, 170, 30);
			btEliminar.addActionListener(new EliminarRegistroUltimo());
			add(btEliminar);

			JButton btGenerar = new JButton("Generar tiquet");
			btGenerar.setBounds(175, 410, 115, 30);
			btGenerar.addActionListener(new GenerarTiquet());
			add(btGenerar);

		}

		public void cargarTabla() {

			String informacion[][] = new String[tiquet.getRegistros().size()][3]; // creo la matriz con el size de la
																					// list en 'x' y las columnas en 'y'

			int x = 0;
			for (Registro r : tiquet.getRegistros()) {

				switch (r.getTipo()) { // formateo la salida segun el lbTipo si es Arbol, Flor o Decoracion

				case "Arbol": {
					informacion[x][0] = r.getTipo() + " de " + r.getCaracteristica() + " cm";
					break;
				}
				case "Flor": {
					informacion[x][0] = r.getTipo() + " de color " + r.getCaracteristica();
					break;
				}
				case "Decoracion": {
					informacion[x][0] = r.getTipo() + " de " + r.getCaracteristica();
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + r.getTipo());
				}

				informacion[x][1] = r.getCantidad() + "";
				informacion[x][2] = r.getPrecio() + "";
				x++;
			}
			table = new JTable(informacion, columnas);

			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(140);
			table.getColumnModel().getColumn(1).setPreferredWidth(70);
			table.getColumnModel().getColumn(2).setPreferredWidth(67);

			srScroll.setViewportView(table);
		}
	}

	class VentaArbol extends JPanel {

		JSpinner tfCantidad;

		public VentaArbol() {

			setLayout(new GridLayout(5, 2, 2, 2));

			precioArbol = buscarPrecio("arbol", "40");

			JLabel lbTipo = new JLabel("          Tipo de producto:   ");
			add(lbTipo);

			JLabel lbArboles = new JLabel("Arboles");
			lbArboles.setHorizontalAlignment(SwingConstants.CENTER);
			lbArboles.setBackground(Color.WHITE);
			lbArboles.setOpaque(true);
			add(lbArboles);

			JLabel lbCantidad = new JLabel("          Cantidad:   ");
			add(lbCantidad);

			tfCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			add(tfCantidad);

			JLabel altura = new JLabel("          Altura en cm:   ");
			add(altura);

			spAltura = new JSpinner(new SpinnerNumberModel(40, 40, 200, 10));
			add(spAltura);

			JLabel jlPrecio = new JLabel("          Precio por unidad:   ");
			add(jlPrecio);

			tfPrecioArbol = new JTextField("€ " + precioArbol);
			tfPrecioArbol.setEditable(false);
			tfPrecioArbol.setBackground(Color.WHITE);
			add(tfPrecioArbol);

			add(new JLabel()); // relleno

			spAltura.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {

					precioArbol = buscarPrecio("arbol", spAltura.getValue().toString());

					tfPrecioArbol.setText("€ " + precioArbol);
				}
			});

			JButton fillArbol = new JButton("Añadir Arbol");
			fillArbol.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (okStock("arbol", (int) tfCantidad.getValue(), spAltura.getValue().toString())) {

						tiquet.agregarRegistro(new Registro("Arbol", spAltura.getValue() + "",
								(int) tfCantidad.getValue(), precioArbol * (int) tfCantidad.getValue()));

						panelDerecho.cargarTabla();
						actualizarPrecio.actualizar();
					} else
						JOptionPane.showMessageDialog(null, "No hay stock suficiente del arbol de altura  "
								+ spAltura.getValue().toString() + " cm", "Importante!!", JOptionPane.ERROR_MESSAGE);
				}
			});
			add(fillArbol);
		}
	}

	class VentaFlor extends JPanel {

		JSpinner tfCantidad;

		String color;

		public VentaFlor() {
			setLayout(new GridLayout(5, 2, 2, 2));

			precioFlor = buscarPrecio("flor", "Rojo");
			color = "Rojo";

			JLabel lbTipo = new JLabel("Tipo de producto:");
			lbTipo.setHorizontalAlignment(SwingConstants.RIGHT);
			add(lbTipo);

			JLabel flores = new JLabel("Flores");
			flores.setHorizontalAlignment(SwingConstants.CENTER);
			flores.setBackground(Color.WHITE);
			flores.setOpaque(true);
			add(flores);

			JLabel lbCantidad = new JLabel("          Cantidad:");
			add(lbCantidad);

			tfCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			add(tfCantidad);

			JLabel jlcolor = new JLabel("          Color:");
			add(jlcolor);

			/* combo de eleccion ******************************/
			JComboBox<String> comboColor = new JComboBox<>();
			comboColor.addItem("Rojo");
			comboColor.addItem("Blanco");
			comboColor.addItem("Amarillo");
			comboColor.addItem("Azul");

			comboColor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					color = (String) comboColor.getSelectedItem();
					precioFlor = buscarPrecio("flor", color);
					tfPrecioFlor.setText("€ " + precioFlor);

				}
			});
			comboColor.setBackground(Color.WHITE);
			comboColor.setOpaque(true);
			add(comboColor);

			JLabel jlPrecio = new JLabel("          Precio por unidad:");
			add(jlPrecio);

			tfPrecioFlor = new JTextField("€ " + precioFlor);
			tfPrecioFlor.setEditable(false);
			tfPrecioFlor.setBackground(Color.WHITE);
			add(tfPrecioFlor);

			add(new JLabel());

			JButton fillFlor = new JButton("Añadir Flor");
			fillFlor.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (okStock("flor", (int) tfCantidad.getValue(), color)) {

						tiquet.agregarRegistro(new Registro("Flor", color, (int) tfCantidad.getValue(),
								precioFlor * (int) tfCantidad.getValue()));

						panelDerecho.cargarTabla();

						actualizarPrecio.actualizar();
					} else
						JOptionPane.showMessageDialog(null, "No hay stock suficiente de la flor color  " + color,
								"Importante!!", JOptionPane.ERROR_MESSAGE);
				}
			});
			add(fillFlor);
		}
	}

	class VentaDecoracion extends JPanel {

		JSpinner tfCantidad;

		ButtonGroup grupo;
		JRadioButton btMadera, btPlastico;

		public VentaDecoracion() {

			setLayout(new GridLayout(5, 2, 2, 2));

			material = "Plastico";

			precioDecoracion = buscarPrecio("decoracion", "Plastico");

			JLabel lbTipo = new JLabel("          Tipo de producto:");
			add(lbTipo);

			JLabel decoracion = new JLabel("Decoracion");
			decoracion.setHorizontalAlignment(SwingConstants.CENTER);
			decoracion.setBackground(Color.WHITE);
			decoracion.setOpaque(true);
			add(decoracion);

			JLabel lbCantidad = new JLabel("          Cantidad:");
			add(lbCantidad);

			tfCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			add(tfCantidad);

			JLabel jlMaterial = new JLabel("          Material:");
			add(jlMaterial);

			grupo = new ButtonGroup();
			JPanel gMaterial = new JPanel();
			gMaterial.setBackground(Color.WHITE);

			btMadera = new JRadioButton("Madera", false);
			btPlastico = new JRadioButton("Plastico", true);
			grupo.add(btMadera);
			grupo.add(btPlastico);
			gMaterial.add(btMadera);
			gMaterial.add(btPlastico);
			add(gMaterial);

			JLabel jlPrecio = new JLabel("          Precio por unidad:");
			add(jlPrecio);

			tfPrecioDecoracion = new JTextField("€ " + precioDecoracion);
			tfPrecioDecoracion.setEditable(false);
			tfPrecioDecoracion.setBackground(Color.WHITE);
			add(tfPrecioDecoracion);

			add(new JLabel());

			btMadera.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					material = "Madera";
					precioDecoracion = buscarPrecio("decoracion", "Madera");
					tfPrecioDecoracion.setText("€ " + precioDecoracion);
				}
			});
			btPlastico.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					material = "Plastico";
					precioDecoracion = buscarPrecio("decoracion", "Plastico");
					tfPrecioDecoracion.setText("€ " + precioDecoracion);
				}
			});

			JButton btAgregar = new JButton("Añadir Decoracion");
			btAgregar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (okStock("decoracion", (int) tfCantidad.getValue(), material)) {

						tiquet.agregarRegistro(new Registro("Decoracion", material, (int) tfCantidad.getValue(),
								precioDecoracion * (int) tfCantidad.getValue()));
						panelDerecho.cargarTabla();
						actualizarPrecio.actualizar();

					} else
						JOptionPane.showMessageDialog(null, "No hay stock suficiente de la decoracion de  " + material,
								"Importante!!", JOptionPane.ERROR_MESSAGE);
				}
			});
			add(btAgregar);
		}
	}

	private class EliminarRegistroUltimo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (tiquet.getRegistros().size() > 0) {

				switch (tiquet.getRegistros().get(tiquet.getRegistros().size() - 1).getTipo()) { // formateo la salida
																									// segun el lbTipo
																									// si es Arbol, Flor
																									// o Decoracion

				case "Arbol": {
					for (int x = 0; x < floreria.getArboles().size(); x++) {
						if ((floreria.getArboles().get(x).getAltura()) == // busco por altura
								(Integer.valueOf(tiquet.getRegistros().get(tiquet.getRegistros().size() - 1)
										.getCaracteristica()))) {
							floreria.getArboles().get(x).setCantidad(floreria.getArboles().get(x).getCantidad()
									+ tiquet.getRegistros().get(tiquet.getRegistros().size() - 1).getCantidad());
						}

					}
					break;
				}
				case "Flor": {
					for (int x = 0; x < floreria.getFlores().size(); x++) {
						if ((floreria.getFlores().get(x).getColor()).equals(
								tiquet.getRegistros().get(tiquet.getRegistros().size() - 1).getCaracteristica())) {
							floreria.getFlores().get(x).setCantidad(floreria.getFlores().get(x).getCantidad()
									+ tiquet.getRegistros().get(tiquet.getRegistros().size() - 1).getCantidad());
						}
					}
					break;
				}
				case "Decoracion": {
					for (int x = 0; x < floreria.getDecoraciones().size(); x++) {
						if ((floreria.getDecoraciones().get(x).getMaterial()).equals(
								tiquet.getRegistros().get(tiquet.getRegistros().size() - 1).getCaracteristica())) {
							floreria.getDecoraciones().get(x)
									.setCantidad(floreria.getDecoraciones().get(x).getCantidad() + tiquet.getRegistros()
											.get(tiquet.getRegistros().size() - 1).getCantidad());
						}
					}
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: ");
				}
				tiquet.getRegistros().remove(tiquet.getRegistros().size() - 1);
				panelDerecho.cargarTabla();
			} else
				JOptionPane.showMessageDialog(null, "NO SE PUEDE GENERAR EL TIQUET PORQUE  SE ENCUENTRA VACIO : ",
						"Importante!!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private class GenerarTiquet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (tiquet.getRegistros().size() > 0) {

				floreria.agregarTiquet(tiquet);
				tiquet = new Tiquet();
				panelDerecho.cargarTabla();
				tfPrecioTot.setText("€ 0.0");

			} else
				JOptionPane.showMessageDialog(null, "NO SE PUEDE GENERAR EL TIQUET PORQUE  SE ENCUENTRA VACIO : ",
						"Importante!!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private class ActualizarPrecio {

		public void actualizar() {

			precioTotal = 0;
			for (Registro r : tiquet.getRegistros()) {
				precioTotal += r.getPrecio();
			}
			tfPrecioTot.setText("€ " + precioTotal);
		}
	}

	private boolean okStock(String lbTipo, int lbCantidad, String caracteristica) {

		if (lbTipo.equals("arbol")) {
			for (Arbol a : floreria.getArboles()) {
				if ((a.getAltura() == Integer.parseInt(caracteristica)) && (a.getCantidad() >= lbCantidad)) {

					a.setCantidad(a.getCantidad() - lbCantidad);
					return true;
				}
			}
		} else if (lbTipo.equals("flor")) {
			for (Flor f : floreria.getFlores()) {
				if ((f.getColor().equals(caracteristica)) && (f.getCantidad() >= lbCantidad)) {

					f.setCantidad(f.getCantidad() - lbCantidad);
					return true;
				}
			}
		} else if (lbTipo.equals("decoracion")) {
			for (Decoracion d : floreria.getDecoraciones()) {

				if ((d.getMaterial().equals(caracteristica)) && (d.getCantidad() >= lbCantidad)) {

					d.setCantidad(d.getCantidad() - lbCantidad);
					return true;
				}
			}
		}
		return false;
	}

	private Double buscarPrecio(String lbTipo, String caracteristica) {

		if (lbTipo.equals("arbol")) {
			for (Arbol a : floreria.getArboles()) {
				if (a.getAltura() == Integer.parseInt(caracteristica))
					return a.getPrecio();
			}
		} else if (lbTipo.equals("flor")) {
			for (Flor f : floreria.getFlores()) {
				if (f.getColor().equals(caracteristica))
					return f.getPrecio();
			}
		} else if (lbTipo.equals("decoracion")) {
			for (Decoracion d : floreria.getDecoraciones()) {
				if (d.getMaterial().equals(caracteristica))
					return d.getPrecio();
			}
		}
		return null;
	}

}
