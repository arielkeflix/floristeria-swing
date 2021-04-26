package floristeria.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import floristeria.modelo.Arbol;
import floristeria.modelo.Decoracion;
import floristeria.modelo.Flor;
import floristeria.modelo.Floreria;

public class VentanaModificarPrecio extends JPanel {

	Floreria floreria;
	VentanaVentas panelVentas; // lo importo para "avisar" que se actualiza los precios

	private JTextField tfArbol, tfFlor, tfDecoracionM, tfDecoracionP, tfDireccion, tfTelefono;

	public VentanaModificarPrecio(Floreria floreria, VentanaVentas panelVentas) {

		this.panelVentas = panelVentas;
		this.floreria = floreria;
		setLayout(null);
		PanelPrecios panel = new PanelPrecios();
		panel.setBounds(200, 20, 500, 500);
		add(panel);
	}

	private class PanelPrecios extends JPanel {

		public PanelPrecios() {

			Font miFuente = new Font("Arial", Font.BOLD, 14);

			setLayout(null);

			JLabel lbTitulo = new JLabel("Rellene los precios por unidad que desea btModificar");
			lbTitulo.setFont(miFuente);
			lbTitulo.setBounds(100, 20, 600, 30);
			add(lbTitulo);

			JLabel lbFormato = new JLabel("Formato 'X.XX'");
			lbFormato.setBounds(200, 60, 350, 30);
			add(lbFormato);

			JLabel lbArbol = new JLabel("Arboles,  cada 10 cm:  ");
			lbArbol.setBounds(0, 90, 300, 30);
			add(lbArbol);
			tfArbol = new JTextField();
			tfArbol.setBackground(Color.WHITE);
			tfArbol.setBounds(200, 90, 150, 30);
			add(tfArbol);

			JLabel lbFlor = new JLabel("Flores :  ");
			lbFlor.setBounds(0, 130, 300, 30);
			add(lbFlor);
			tfFlor = new JTextField();
			tfFlor.setBackground(Color.WHITE);
			tfFlor.setBounds(200, 130, 150, 30);
			add(tfFlor);

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
			tfDecoracionP = new JTextField();
			tfDecoracionP.setBackground(Color.WHITE);
			tfDecoracionP.setBounds(200, 210, 150, 30);
			add(tfDecoracionP);

			JButton btModificar = new JButton("MODIFICAR PRECIOS");
			btModificar.setBounds(200, 280, 200, 30);
			add(btModificar);
			btModificar.addActionListener(new ModificarPrecios());
		}
	}

	private class ModificarPrecios implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			List<String> modificado = new ArrayList<>(); // por cada modificacion agrego el elemento modificado

			if (!tfArbol.getText().isEmpty() && validarDouble(tfArbol, "Precio arbol")) {
				modificado.add("ARBOLES");
				for (Arbol a : floreria.getArboles()) {

					a.setPrecio(Double.parseDouble(tfArbol.getText()) * a.getAltura() / 10);
				}
			}

			if (!tfFlor.getText().isEmpty() && validarDouble(tfFlor, "Precio flor")) {
				modificado.add(" FLORES");
				for (Flor f : floreria.getFlores()) {
					f.setPrecio(Double.parseDouble(tfFlor.getText()));
				}
			}
			for (Decoracion d : floreria.getDecoraciones()) {

				if (d.getMaterial().equals("Madera")) {

					if (!tfDecoracionM.getText().isEmpty()
							&& validarDouble(tfDecoracionM, "Precio Decoracion madera")) {
						modificado.add(" DECORACION MADERA");
						d.setPrecio(Double.parseDouble(tfDecoracionM.getText()));
					}
				} else if (d.getMaterial().equals("Plastico")) {

					if (!tfDecoracionP.getText().isEmpty()
							&& validarDouble(tfDecoracionP, "Precio Decoracion pastico")) {
						modificado.add(" DECORACION PLASTICO ");
						d.setPrecio(Double.parseDouble(tfDecoracionP.getText()));
					}
				}
			}

			String aux = ""; // acumulo los modificados en una variable para poder imprimir por pantalla
			for (String s : modificado)
				aux += s + ", ";
			panelVentas.recargarPrecios();
			if (!aux.isBlank())
				JOptionPane.showMessageDialog(null, "PRECIOS DE " + aux + " FUERON MODIFICADOS. ", "Importante!!",
						JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private boolean validarDouble(JTextField campo, String nombre) {

		//double aux;  //es usada dentr

		try {
			double aux = Double.parseDouble(campo.getText());

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,
					"El precio del campo '" + nombre + "' debe estar en un formato numerico 'x.xx' ", "Importante!!",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
