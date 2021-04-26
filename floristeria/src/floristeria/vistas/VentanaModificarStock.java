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
import javax.swing.JSpinner;
import javax.swing.JTextField;

import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import floristeria.modelo.Arbol;
import floristeria.modelo.Decoracion;
import floristeria.modelo.Flor;
import floristeria.modelo.Floreria;

public class VentanaModificarStock extends JPanel {

	Floreria floreria;

	public VentanaModificarStock(Floreria floreria) {

		this.floreria = floreria;
		Font miFuente = new Font("Arial", Font.BOLD, 18);

		setLayout(null);

		JLabel lbTitulo = new JLabel("MODIFICAR STOCK");
		lbTitulo.setFont(miFuente);
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(0, 10, 900, 50);
		add(lbTitulo);

		StockArbol stockArbol = new StockArbol();
		stockArbol.setBounds(50, 70, 800, 100);
		add(stockArbol);

		StockFlor stockFlor = new StockFlor();
		stockFlor.setBounds(50, 170, 800, 100);
		add(stockFlor);

		StockDecoracion stockDecoracion = new StockDecoracion();
		stockDecoracion.setBounds(50, 270, 800, 100);
		add(stockDecoracion);

	}

	class StockArbol extends JPanel {

		JSpinner spAltura;
		JSpinner tfCantidad;
		JTextField tfPrecio;

		public StockArbol() {

			setLayout(new GridLayout(2, 5, 2, 2));

			JLabel lbTipo = new JLabel("Tipo de producto");
			lbTipo.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbTipo);

			JLabel lbCantidad = new JLabel("Cantidad");
			lbCantidad.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbCantidad);

			JLabel lbAltura = new JLabel("Altura en cm");
			lbAltura.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbAltura);

			add(new JLabel(""));
			add(new JLabel(""));

			JLabel lbArboles = new JLabel("Arboles");
			lbArboles.setHorizontalAlignment(SwingConstants.CENTER);
			lbArboles.setBackground(Color.WHITE);
			lbArboles.setOpaque(true);
			add(lbArboles);

			tfCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			add(tfCantidad);

			spAltura = new JSpinner(new SpinnerNumberModel(40, 40, 200, 10));
			add(spAltura);

			JButton btAgregar = new JButton("Agregar ");
			btAgregar.addActionListener(new AgregarArbol());
			add(btAgregar);

			JButton btBorrar = new JButton("Borrar");
			btBorrar.addActionListener(new BorrarArbol());
			add(btBorrar);
		}

		private class BorrarArbol implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Arbol a : floreria.getArboles()) {

					if (a.getAltura() == (int) spAltura.getValue()) {
						if (a.getCantidad() >= (int) tfCantidad.getValue()) {
							a.setCantidad(a.getCantidad() - (int) tfCantidad.getValue());

							JOptionPane.showMessageDialog(null, "STOCK ARBOLES MODIFICADOS ", "Importante!!",
									JOptionPane.INFORMATION_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null,
									"No hay stock suficiente del arbol de lbAltura " + spAltura.getValue().toString()
											+ " cm " + " para btBorrar: " + tfCantidad.getValue(),
									"Importante!!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		private class AgregarArbol implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Arbol a : floreria.getArboles()) {

					if (a.getAltura() == (int) spAltura.getValue()) {

						a.setCantidad(a.getCantidad() + (int) tfCantidad.getValue());
						JOptionPane.showMessageDialog(null, "STOCK ARBOLES MODIFICADOS ", "Importante!!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	class StockFlor extends JPanel {

		JSpinner tfCantidad;
		JTextField tfPrecio;
		String color;

		public StockFlor() {

			setLayout(new GridLayout(2, 4, 2, 2));

			color = "Rojo"; // inicializo la variable para que no sea null antes de seleccionar

			JLabel lbTipo = new JLabel("Tipo de producto: ");
			lbTipo.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbTipo);

			JLabel lbCantidad = new JLabel("Cantidad ");
			lbCantidad.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbCantidad);

			JLabel lbColores = new JLabel("Color ");
			lbColores.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbColores);

			add(new JLabel("")); // relleno
			add(new JLabel(""));

			JLabel lbFlores = new JLabel("Flores");
			lbFlores.setHorizontalAlignment(SwingConstants.CENTER);
			lbFlores.setBackground(Color.WHITE);
			lbFlores.setOpaque(true);
			add(lbFlores);

			tfCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			add(tfCantidad);

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

				}
			});
			comboColor.setBackground(Color.WHITE);
			comboColor.setOpaque(true);

			add(comboColor);

			/************************************************************************************/

			JButton btAgregar = new JButton("Agregar ");
			btAgregar.addActionListener(new AgregarFlor());
			add(btAgregar);

			JButton btBorrar = new JButton("Borrar ");
			btBorrar.addActionListener(new BorrarFlor());
			add(btBorrar);

		}

		private class BorrarFlor implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (Flor f : floreria.getFlores()) {

					if (f.getColor().equals(color)) {
						if (f.getCantidad() - (int) tfCantidad.getValue() >= 0) {
							f.setCantidad(f.getCantidad() - (int) tfCantidad.getValue());
							JOptionPane.showMessageDialog(null, "STOCK FLORES MODIFICADOS ", "Importante!!",
									JOptionPane.INFORMATION_MESSAGE);
						} else
							JOptionPane
									.showMessageDialog(
											null, "No hay stock suficiente de la flor color  " + color
													+ " para eliminar: " + tfCantidad.getValue(),
											"Importante!!", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		}

		private class AgregarFlor implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (Flor f : floreria.getFlores()) {

					if (f.getColor().equals(color)) {

						f.setCantidad(f.getCantidad() + (int) tfCantidad.getValue());
						JOptionPane.showMessageDialog(null, "STOCK FLORES MODIFICADOS ", "Importante!!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		}
	}

	class StockDecoracion extends JPanel {

		JSpinner tfCantidad;
		JTextField tfPrecio;
		ButtonGroup grupo;
		JRadioButton btMadera, btPlastico;

		public StockDecoracion() {

			setLayout(new GridLayout(2, 4, 2, 2));

			JLabel lbTipo = new JLabel("Tipo de producto");
			lbTipo.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbTipo);

			JLabel lbCantidad = new JLabel("Cantidad");
			lbCantidad.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbCantidad);

			JLabel lbMaterial = new JLabel("Material");
			lbMaterial.setHorizontalAlignment(SwingConstants.CENTER);
			add(lbMaterial);

			add(new JLabel("")); // relleno
			add(new JLabel(""));

			JLabel lbDecoracion = new JLabel("Decoracion");
			lbDecoracion.setHorizontalAlignment(SwingConstants.CENTER);
			lbDecoracion.setBackground(Color.WHITE);
			lbDecoracion.setOpaque(true);
			add(lbDecoracion);

			tfCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			add(tfCantidad);

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

			JButton btAgregar = new JButton("Agregar ");
			btAgregar.addActionListener(new AgregarDecoracion());
			add(btAgregar);

			JButton btBorrar = new JButton("Borrar ");
			btBorrar.addActionListener(new BorrarDecoracion());
			add(btBorrar);
		}

		private class BorrarDecoracion implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				String mat = (btMadera.isSelected()) ? btMadera.getText() : btPlastico.getText();

				for (Decoracion d : floreria.getDecoraciones()) {

					if (d.getMaterial().equals(mat)) {

						if (d.getCantidad() - (int) tfCantidad.getValue() >= 0) {

							d.setCantidad(d.getCantidad() - (int) tfCantidad.getValue());
							JOptionPane.showMessageDialog(null, "STOCK DECORACION MODIFICADOS ", "Importante!!",
									JOptionPane.INFORMATION_MESSAGE);
						} else
							JOptionPane.showMessageDialog(null,
									"No hay stock suficiente de la lbDecoracion de " + d.getMaterial()
											+ " para btBorrar: " + tfCantidad.getValue(),
									"Importante!!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		private class AgregarDecoracion implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				String mat = (btMadera.isSelected()) ? btMadera.getText() : btPlastico.getText();

				for (Decoracion d : floreria.getDecoraciones()) {

					if (d.getMaterial().equals(mat)) {

						d.setCantidad(d.getCantidad() + (int) tfCantidad.getValue());
						JOptionPane.showMessageDialog(null, "STOCK DECORACION MODIFICADOS ", "Importante!!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

}
