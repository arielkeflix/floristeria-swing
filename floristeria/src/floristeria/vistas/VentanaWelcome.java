 
package floristeria.vistas;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

import floristeria.modelo.Floreria;

public class VentanaWelcome extends JPanel {

	private Floreria floreria;

	public VentanaWelcome(Floreria floreria) {

		this.floreria = floreria;

		Font miFuente = new Font("Arial", Font.BOLD, 22);

		// setBounds(50, 50, 600, 520);

		setLayout(null);

		JLabel nombreFloreria = new JLabel("Bienvenido a la floristeria: " + floreria.getNombre());

		nombreFloreria.setHorizontalAlignment(SwingConstants.CENTER);

		nombreFloreria.setVerticalAlignment(SwingConstants.CENTER);

		nombreFloreria.setBounds(0, 0, 900, 500);

		nombreFloreria.setFont(miFuente);

		add(nombreFloreria);

	}

}
