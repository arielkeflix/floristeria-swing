package floristeria.inicio;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import floristeria.modelo.Floreria;
import floristeria.vistas.VentanaPrincipal;


public class Main {

	public static void main(String[] args) {			
		
		try{
			
			  JFrame.setDefaultLookAndFeelDecorated(true);
			  JDialog.setDefaultLookAndFeelDecorated(true);
			
			}
			catch (Exception e)
			 {
				JOptionPane.showMessageDialog(null, "LA ESTETICA ESPECIFICA NO SE PUDO CARGAR FUNCIONARA NORMALMENTE : "  
						 ,"Importante!!", JOptionPane.ERROR_MESSAGE);	
			 }
		
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaPrincipal.setVisible(true);			
	}
}
