package floristeria.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	VentanaVentas panelVentas; //lo importo para "avisar" que se actualiza los precios 
	
	private JTextField inNombre, inArbol,inFlor,inDecoracionM, inDecoracionP,inDireccion, inTelefono  ;	
	
	public VentanaModificarPrecio(Floreria floreria, VentanaVentas panelVentas ) {
		
		this.panelVentas = panelVentas;
		this.floreria = floreria;
		setLayout(null);
		PanelPrecios panel = new PanelPrecios();
		panel.setBounds(200, 20, 500, 500);
		add(panel);				
	}
	
	private class PanelPrecios extends JPanel{				
		
		
		public PanelPrecios() {
			
			Font miFuente = new Font("Arial", Font.BOLD, 14);
			
			setLayout(null);
			
			JLabel titulo = new JLabel("Rellene los precios por unidad que desea modificar");			
			titulo.setFont(miFuente);
			titulo.setBounds(100, 20,600, 30);
			add(titulo);						
			
			JLabel lFormato = new JLabel("Formato 'X.XX'");
			lFormato.setBounds(200, 60, 350, 30);
			add(lFormato);										
			
			JLabel lArbol = new JLabel("Arboles,  cada 10 cm:  ");
			lArbol.setBounds(0, 90, 300, 30);
			add(lArbol);				
			inArbol = new JTextField();
			inArbol.setBackground(Color.WHITE);
			inArbol.setBounds(200, 90, 150, 30);
			add( inArbol);
			
			JLabel lFlor = new JLabel("Flores :  ");
			lFlor.setBounds(0, 130, 300, 30);
			add( lFlor);								
			inFlor = new JTextField();
			inFlor.setBackground(Color.WHITE);
			inFlor.setBounds(200, 130, 150, 30);
			add(inFlor);
			
			JLabel lDecoracionM = new JLabel("Decoracion de madera :  ");
			lDecoracionM.setBounds(0, 170, 300, 30);
			add( lDecoracionM);				
			inDecoracionM = new JTextField();				
			inDecoracionM.setBounds(200, 170, 150, 30);
			inDecoracionM.setBackground(Color.WHITE);
			add(inDecoracionM);
		
			JLabel lDecoracionP = new JLabel("Decoracion de plastico:  ");
			add( lDecoracionP);
			lDecoracionP.setBounds(0,210, 300, 30);
			inDecoracionP = new JTextField();
			inDecoracionP.setBackground(Color.WHITE);
			inDecoracionP.setBounds(200, 210, 150, 30);
			add(inDecoracionP);
			
			JButton modificar = new JButton("MODIFICAR PRECIOS");
			modificar.setBounds(200, 280, 200, 30);
			add(modificar);
			modificar.addActionListener(new ModificarPrecios());
		}
	}
	private class ModificarPrecios implements ActionListener {
		private Double aux = 0.0;

		@Override
		public void actionPerformed(ActionEvent e) {							
				
								
				if (!inArbol.getText().isEmpty() && validarDouble(inArbol, "Precio arbol")) {
					for (Arbol a : floreria.getArboles()) {

						a.setPrecio(Double.parseDouble(inArbol.getText()) * a.getAltura() / 10);						
					}
				}
				
				if (!inFlor.getText().isEmpty() && validarDouble(inFlor, "Precio flor")) {

					for (Flor f : floreria.getFlores()) {
						f.setPrecio(Double.parseDouble(inFlor.getText()));						
					}
				}
				

					for (Decoracion d : floreria.getDecoraciones()) {
						
						if (d.getMaterial().equals("Madera")) {
							if (!inDecoracionM.getText().isEmpty() && validarDouble(inDecoracionM, "Precio Decoracion madera")) {
							d.setPrecio(Double.parseDouble(inDecoracionM.getText()));
							}
						}
					   else if (d.getMaterial().equals("Plastico")) {
						   if (!inDecoracionP.getText().isEmpty() && validarDouble(inDecoracionP, "Precio Decoracion pastico")) {
							d.setPrecio(Double.parseDouble(inDecoracionP.getText()));
						   }
						}
					}
					panelVentas.recargarPrecios();
					JOptionPane.showMessageDialog(null, "PRECIOS MODIFICADOS " ,"Importante!!", JOptionPane.INFORMATION_MESSAGE);						
				}																				
		}

		private boolean validarDouble(JTextField campo, String nombre) {

			double aux; 
			
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


