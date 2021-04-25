package floristeria.vistas;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import floristeria.modelo.Floreria;

public class VentanaContacto extends JPanel {

	private Floreria floreria;		
	
	public VentanaContacto(Floreria floreria) {

		this.floreria = floreria;

		Font miFuente = new Font("Arial", Font.BOLD, 16);

		//setBounds(50, 50, 600, 520);

		setLayout(null);

		JLabel nombreFloreria = new JLabel("Nombre de la floreria: "+ floreria.getNombre());

		nombreFloreria.setBounds(250, 50, 400, 30);

		nombreFloreria.setFont(miFuente);		

		add(nombreFloreria);
		
		JLabel direccionFloreria = new JLabel("Direccion de la floreria: "+ floreria.getDireccion());

		direccionFloreria.setBounds(250, 100, 400, 30);

		direccionFloreria.setFont(miFuente);		

		add(direccionFloreria);
		
		JLabel telefonoFloreria = new JLabel("Telefono de la floreria: "+ floreria.getTelefono());

		telefonoFloreria.setBounds(250, 150, 400, 30);

		telefonoFloreria.setFont(miFuente);		

		add(telefonoFloreria);
   }


	
}	