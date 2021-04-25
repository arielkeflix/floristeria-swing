package floristeria.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import floristeria.modelo.Arbol;
import floristeria.modelo.Decoracion;
import floristeria.modelo.Flor;
import floristeria.modelo.Floreria;
import floristeria.vistas.VentanaVentas.PanelDerecho;
import floristeria.vistas.VentanaVentas.PanelIzquierdo;


public class VentanaPrincipal extends JFrame {
	
	private Floreria floreria;	
	
	private VentanaModificarStock 	stockFill;	
	private VentanaListarStock  	stockList;	
	private VentanaVentas  			panelVentas;	
	private VentanaListarTiquets 	ticketsList;
	private VentanaModificarPrecio  ventanaModificarPrecio;
	private VentanaContacto 		ventanaContacto ;	
	private VentanaHelp			    ventanaHelp ;
	private VentanaWelcome			ventanaWelcome;
	
	private  BarMenu menuBar= new BarMenu(); //necesito crearlo aqui para que lo vean creado desde todas las clases internas tantoPrincipal como inicio
	
	JScrollPane scroll;	

	public VentanaPrincipal() {			
		
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		
		Dimension tamañoPantalla = miPantalla.getScreenSize();

		int alturaPantalla = tamañoPantalla.height;

		int anchoPantalla = tamañoPantalla.width;

		setSize(900, 600); 

		setLocation( (anchoPantalla - 900) /2 , (alturaPantalla  - 600)/2);				
		
		Principal p = new Principal();
		add(p);
		
	}	
	class Principal extends JPanel{					
		
		public Principal () {
			
			setLayout(new BorderLayout());								
			
			add(menuBar,BorderLayout.NORTH);	
			
			menuBar.setVisible(false);
			
			VentanaInicio ventanaInicio= new VentanaInicio();
			
			scroll = new JScrollPane();						

			add(scroll, BorderLayout.CENTER);
			
			scroll.setViewportView(ventanaInicio);
		}		
	}
	class BarMenu extends JMenuBar {					
		
		public BarMenu () {							
			
			JButton btStock			 = new JButton("MODIFICAR STOCK");
			JButton btVentas		 = new JButton("VENTAS");
			JButton btListarStock 	 = new JButton("LISTAR STOCK");
			JButton btListarTiquets  = new JButton("LISTAR TIQUETS");
			JButton btPrecios        = new JButton("MODIFICAR PRECIOS");
			JButton btContacto		 = new JButton("CONTACTO");
			JButton btAyuda 		 = new JButton("HELP");
			
			btPrecios.addActionListener( new  ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {					
					scroll.setViewportView(ventanaModificarPrecio);														
				}
			});
			
			btAyuda.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {					
									
					scroll.setViewportView(ventanaHelp);														
				}
			});			
			btContacto.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {					
									
					scroll.setViewportView(ventanaContacto);														
				}
			});			
			btListarStock.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {					
					stockList.generarListado();					
					scroll.setViewportView(stockList);														
				}
			});
			btListarTiquets.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {					
						
					ticketsList.cargarTablas(0);  //inicio desde el 1er tiquets 
					scroll.setViewportView(ticketsList);														
				}
			});						
			btStock.addActionListener( new  ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {					
					scroll.setViewportView(stockFill);														
				}
			});
			
			btVentas.addActionListener( new  ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {					
					scroll.setViewportView(panelVentas);														
				}
			});													
			add(btStock);		
			add(btVentas);
			add(btListarStock);
			add(btListarTiquets);
			add(btPrecios);
			add(btContacto );
			add(btAyuda);												
		}		
	}
	
	class VentanaInicio extends JPanel {
		
		private JTextField inNombre, inArbol,inFlor,inDecoracionM, inDecoracionP,inDireccion, inTelefono  ;	
		
		public VentanaInicio() {										
			
			Font miFuente = new Font("Arial", Font.BOLD, 18);
			
			setLayout(null);

			PanelIzquierdo panelizquierdo = new PanelIzquierdo();
			panelizquierdo.setBounds(0, 50, 400, 350);			

			PanelDerecho panelDerecho = new PanelDerecho();
			panelDerecho.setBounds(450, 50, 450, 350);

			add(panelizquierdo);
			add(panelDerecho);
			
			JLabel titulo = new JLabel("FORMULARIO DE INICIO OPERACIONES FLORISTERIA");
			titulo.setFont(miFuente);
			titulo.setBounds(200, 10, 500, 40);
			add(titulo);				
			
			JButton crear  = new JButton ("Crear floristeria");
			crear.setBounds(300, 420, 150, 40);
			add(crear);
			crear.addActionListener(new CrearFloristeria());			
		}
		
		private class PanelIzquierdo extends JPanel{			
			
			public PanelIzquierdo() {
				
				Font miFuente = new Font("Arial", Font.BOLD, 14);
				
				setLayout(null);
				
				JLabel lTitulo = new JLabel("Rellene los datos de la floreria ");
				lTitulo.setFont(miFuente);
				lTitulo.setBounds(100, 20, 250, 30);
				add(lTitulo);								
				
				JLabel lNombre = new JLabel("Nombre de la floreria: ");
				lNombre.setBounds(20, 90, 150, 30);
				add(lNombre);
				
				inNombre = new JTextField();
				inNombre.setBounds(170, 90, 150, 30);				
				inNombre.setBackground(Color.WHITE);
				add(inNombre);
				
				JLabel lDireccion = new JLabel("Direccion de la floreria: ");
				lDireccion.setBounds(20, 130, 150, 30);				
				add(lDireccion);
				
				inDireccion = new JTextField();
				inDireccion.setBounds(170, 130, 150, 30);				
				inDireccion.setBackground(Color.WHITE);
				add(inDireccion);
				
				JLabel lTelefono = new JLabel("Telefono de la floreria: ");
				lTelefono.setBounds(20, 170, 150, 30);
				add(lTelefono);
				
				inTelefono = new JTextField();
				inTelefono.setBounds(170, 170, 150, 30);				
				inTelefono.setBackground(Color.WHITE);
				add(inTelefono);
			}
		}
		
		private class PanelDerecho extends JPanel{
			
			
			public PanelDerecho() {
				
				Font miFuente = new Font("Arial", Font.BOLD, 14);
				
				setLayout(null);
				
				JLabel titulo = new JLabel("Rellene los precios por unidad");
				 titulo.setFont(miFuente);
				titulo.setBounds(50, 20, 350, 30);
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
			
			}
		}

		private class CrearFloristeria implements ActionListener {
			private Double aux = 0.0;

			@Override
			public void actionPerformed(ActionEvent e) {

				if (validar()) {								

					String colores[] = { "Rojo", "Amarillo", "Blanco", "Azul" };

					floreria = new Floreria(inNombre.getText(), inDireccion.getText(), inTelefono.getText());

					VentanaPrincipal.super.setTitle("Floreria: " + floreria.getNombre());

					for (int x = 10; x < 210; x += 10) {

						floreria.agregarArbol(new Arbol( x * Double.parseDouble(inArbol.getText()) / 10, x));
					}
					for (int x = 0; x < colores.length; x++) {
						floreria.agregarFlor(new Flor( Double.parseDouble(inFlor.getText()), colores[x]));
					}
					floreria.agregarDecoracion(	new Decoracion( Double.parseDouble(inDecoracionM.getText()), "Madera"));

					floreria.agregarDecoracion(	new Decoracion( Double.parseDouble(inDecoracionP.getText()), "Plastico"));

					stockFill = new VentanaModificarStock(floreria);

					stockList = new VentanaListarStock(floreria);

					ticketsList = new VentanaListarTiquets(floreria);

					panelVentas = new VentanaVentas(floreria);
					
					ventanaModificarPrecio = new VentanaModificarPrecio(floreria, panelVentas);

					ventanaContacto = new VentanaContacto(floreria);

					ventanaHelp = new VentanaHelp(floreria);	
					
					ventanaWelcome = new VentanaWelcome(floreria);
					
					menuBar.setVisible(true);

					scroll.setViewportView(ventanaWelcome);
				}
			}

			private boolean validar() {
				
				if ( inNombre.getText().isEmpty() || inDireccion.getText().isEmpty() || inTelefono.getText().isEmpty() ){
					
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campo  de la floreria  ",
							"Importante!!", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				
				if ( validarDouble( inArbol, "Precio arbol" )&& validarDouble( inFlor, "Precio flor")  && 
						validarDouble( inDecoracionM, "Precio decoracion de madera") && validarDouble( inDecoracionP, "Precio decoracion de plastico") )
					  return  true ;								
				 
				else return false;
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



