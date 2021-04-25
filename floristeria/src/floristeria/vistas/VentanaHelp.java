package floristeria.vistas;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import floristeria.modelo.Floreria;

public class VentanaHelp extends JPanel {

	private Floreria floreria;		
	
	public VentanaHelp(Floreria floreria) {

		this.floreria = floreria;

		Font miFuente = new Font("Arial", Font.BOLD, 20);		

		setLayout(null);

		JLabel castellano = new JLabel("EN CASO DE INCENDIO LLAME A LOS BOMBEROS");
		castellano.setBounds(0, 0, 900, 50);
		castellano.setHorizontalAlignment(SwingConstants.CENTER);
		castellano.setFont(miFuente);	
		add(castellano);
		
		JLabel catalan = new JLabel("EN CAS D'INCENDI TRUQUEU ALS BOMBERS ");
		catalan.setBounds(0, 100, 900, 50);
		catalan.setHorizontalAlignment(SwingConstants.CENTER);
		catalan.setFont(miFuente);	
		add(catalan);
		
		JLabel english = new JLabel("IN CASE OF FIRE CALL THE FIRE DEPARTMENT");
		english.setBounds(0, 200, 900, 50);
		english.setHorizontalAlignment(SwingConstants.CENTER);
		english.setFont(miFuente);	
		add(english);
   
	
		JLabel hebrew = new JLabel("במקרה של אש התקשר למחלקת הכבאות");
		hebrew.setBounds(0, 300, 900, 50);
		hebrew.setHorizontalAlignment(SwingConstants.CENTER);
		hebrew.setFont(miFuente);	
		add(hebrew);
		
		JLabel miContacto = new JLabel("Ariel arielkeflix@gmail.com");
		miContacto.setBounds(50, 450,500, 50);		
		miContacto.setFont(miFuente);	
		add(miContacto);
		
		
   }
}
