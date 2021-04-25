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
		
		JLabel titulo = new JLabel("MODIFICAR STOCK");
		titulo.setFont(miFuente);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 10,900, 50);
		add(titulo);
		
		StockArbol stockArbol = new StockArbol();
		stockArbol.setBounds(50, 70, 800, 100);		
		add(stockArbol);
		
		StockFlor stockFlor = new StockFlor();
		stockFlor.setBounds(50, 170, 800, 100);
		add(stockFlor);
		
		StockDecoracion stockDecoracion = new StockDecoracion();
		stockDecoracion.setBounds(50,270,800, 100);
		add(stockDecoracion);		
		
	}
	
	class StockArbol extends JPanel {
		
		JSpinner inAltura;
		JSpinner inCantidad;
		JTextField InPrecio;

		public StockArbol() {

			setLayout(new GridLayout(2,5, 2, 2));

			JLabel tipo = new JLabel("Tipo de producto");
			tipo.setHorizontalAlignment(SwingConstants.CENTER);
			add(tipo);
			
			JLabel cantidad = new JLabel("Cantidad");
			cantidad.setHorizontalAlignment(SwingConstants.CENTER);
			add(cantidad);
			
			JLabel altura = new JLabel("Altura en cm");
			altura.setHorizontalAlignment(SwingConstants.CENTER);
			add(altura);			
			
			add(new JLabel(""));
			add(new JLabel(""));

			JLabel arboles = new JLabel("Arboles");
			arboles.setHorizontalAlignment(SwingConstants.CENTER);
			arboles.setBackground(Color.WHITE);
			arboles.setOpaque(true);
			add(arboles);

			inCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));			
			add(inCantidad);

			inAltura = new JSpinner(new SpinnerNumberModel(40, 40, 200, 10));
			add(inAltura);									

			JButton agregar = new JButton("Agregar ");
			agregar.addActionListener(new AgregarArbol());
			add(agregar);
			
			JButton borrar = new JButton("Borrar");
			borrar.addActionListener(new BorrarArbol());
			add(borrar);
		}
		
		private class BorrarArbol implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Arbol a : floreria.getArboles()) {

					if (a.getAltura() == (int) inAltura.getValue()) {						
						if (a.getCantidad() >= (int) inCantidad.getValue()) {
						      a.setCantidad(a.getCantidad() - (int) inCantidad.getValue());
						
						    JOptionPane.showMessageDialog(null, "STOCK ARBOLES MODIFICADOS " ,"Importante!!", JOptionPane.INFORMATION_MESSAGE);
						}
						else  JOptionPane.showMessageDialog(null, "No hay stock suficiente del arbol de altura " + 
								inAltura.getValue().toString() +" cm "+ " para borrar: " + inCantidad.getValue(),"Importante!!", JOptionPane.ERROR_MESSAGE);
					}
				}				
			}
		}
		
		private class AgregarArbol implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Arbol a : floreria.getArboles()) {

					if (a.getAltura() == (int) inAltura.getValue()) {						
						
						a.setCantidad(a.getCantidad() + (int) inCantidad.getValue());
						JOptionPane.showMessageDialog(null, "STOCK ARBOLES MODIFICADOS " ,"Importante!!", JOptionPane.INFORMATION_MESSAGE);
					}
				}				
			}
		}
	}

	class StockFlor extends JPanel {		
		
		JSpinner inCantidad;
		JTextField InPrecio;		
		String color;
		
		public StockFlor() {

			setLayout(new GridLayout(2,4, 2, 2));
			
			color = "Rojo";  //inicializo la variable para que no sea null antes de seleccionar

			JLabel tipo = new JLabel("Tipo de producto: ");
			tipo.setHorizontalAlignment(SwingConstants.CENTER);
			add(tipo);
			
			JLabel cantidad = new JLabel("Cantidad ");
			cantidad.setHorizontalAlignment(SwingConstants.CENTER);
			add(cantidad);
			
			JLabel colores = new JLabel("Color ");
			colores.setHorizontalAlignment(SwingConstants.CENTER);
			add(colores);
			
			add(new JLabel(""));
			add(new JLabel(""));

			JLabel flores = new JLabel("Flores");
			flores.setHorizontalAlignment(SwingConstants.CENTER);
			flores.setBackground(Color.WHITE);
			flores.setOpaque(true);
			add(flores);


			inCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));			
			add(inCantidad);							
			
			/*combo de eleccion ******************************/			
			JComboBox<String> comboColor = new JComboBox<>();
			
			comboColor.addItem("Rojo");			
			comboColor.addItem("Blanco");
			comboColor.addItem("Amarillo");	
			comboColor.addItem("Azul");
			comboColor.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				color =	(String)comboColor.getSelectedItem();
					
				}
			});	
			comboColor.setBackground(Color.WHITE);
			comboColor.setOpaque(true);
			
			add(comboColor);			
			
			/************************************************************************************/	

			JButton agregar = new JButton("Agregar ");
			agregar.addActionListener(new AgregarFlor());
			add(agregar);
			
			JButton borrar = new JButton("Borrar ");
			borrar.addActionListener(new BorrarFlor());
			add(borrar);

		}
		
		private class BorrarFlor implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (Flor f : floreria.getFlores()) {

					if (f.getColor().equals(color)) {
                       if (f.getCantidad() -(int) inCantidad.getValue() >= 0 ) {
						  f.setCantidad(f.getCantidad() - (int) inCantidad.getValue());
						  JOptionPane.showMessageDialog(null, "STOCK FLORES MODIFICADOS " ,"Importante!!", JOptionPane.INFORMATION_MESSAGE);
                       }
                       else
						     JOptionPane.showMessageDialog(null, "No hay stock suficiente de la flor color  " + 
								color + " para eliminar: "  +  inCantidad.getValue(),"Importante!!", JOptionPane.ERROR_MESSAGE);	
					}
				}

			}
		}

		private class AgregarFlor implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (Flor f : floreria.getFlores()) {

					if (f.getColor().equals(color)) {
                       
						f.setCantidad(f.getCantidad() + (int) inCantidad.getValue());
						JOptionPane.showMessageDialog(null, "STOCK FLORES MODIFICADOS " ,"Importante!!", JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		}
	}

	class StockDecoracion extends JPanel {

		JSpinner inCantidad;
		JTextField InPrecio;
		ButtonGroup grupo;
		JRadioButton boton1, boton2;

		public StockDecoracion() {

			setLayout(new GridLayout(2, 4, 2, 2));

			JLabel tipo = new JLabel("Tipo de producto");
			tipo.setHorizontalAlignment(SwingConstants.CENTER);
			add(tipo);

			JLabel cantidad = new JLabel("Cantidad");
			cantidad.setHorizontalAlignment(SwingConstants.CENTER);
			add(cantidad);

			JLabel material = new JLabel("Material");
			material.setHorizontalAlignment(SwingConstants.CENTER);
			add(material);
			
			add(new JLabel(""));
			add(new JLabel(""));

			JLabel decoracion = new JLabel("Decoracion");
			decoracion.setHorizontalAlignment(SwingConstants.CENTER);
			decoracion.setBackground(Color.WHITE);
			decoracion.setOpaque(true);			
			add(decoracion);

			inCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			add(inCantidad);

			grupo = new ButtonGroup();
			JPanel gMaterial = new JPanel();
			gMaterial.setBackground(Color.WHITE);
			
			boton1 = new JRadioButton("Madera", false);
			boton2 = new JRadioButton("Plastico", true);
			
			grupo.add(boton1);
			grupo.add(boton2);

			gMaterial.add(boton1);			
			gMaterial.add(boton2);			

			add(gMaterial);

			JButton agregar = new JButton("Agregar ");
			agregar.addActionListener(new AgregarDecoracion());
			add(agregar);
			
			JButton borrar = new JButton("Borrar ");
			borrar.addActionListener(new BorrarDecoracion());
			add(borrar);
		}
		
		private class BorrarDecoracion implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String mat = (boton1.isSelected())?boton1.getText():boton2.getText();
				
				for (Decoracion d : floreria.getDecoraciones()) {

					if (d.getMaterial().equals(mat)) {  
						
						if (d.getCantidad()- (int) inCantidad.getValue() >= 0 ) {
                       
						d.setCantidad(d.getCantidad() - (int) inCantidad.getValue());
						JOptionPane.showMessageDialog(null, "STOCK DECORACION MODIFICADOS " ,"Importante!!", JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "No hay stock suficiente de la decoracion de " + 
									d.getMaterial()+ " para borrar: "+ inCantidad.getValue() ,"Importante!!", JOptionPane.ERROR_MESSAGE);	
					}
				}										
			}			
		}		

		private class AgregarDecoracion implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String mat = (boton1.isSelected())?boton1.getText():boton2.getText();
				
				for (Decoracion d : floreria.getDecoraciones()) {

					if (d.getMaterial().equals(mat)) {                      
                       
						d.setCantidad(d.getCantidad() + (int) inCantidad.getValue());
						JOptionPane.showMessageDialog(null, "STOCK DECORACION MODIFICADOS " ,"Importante!!", JOptionPane.INFORMATION_MESSAGE);
					}
				}										
			}			
		}		
	}		

}

