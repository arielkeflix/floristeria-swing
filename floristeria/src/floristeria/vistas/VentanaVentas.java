package floristeria.vistas;

import java.awt.Color;
import java.awt.Dimension;
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
	private	ActualizarPrecio actualizarPrecio;
	private double precioTotal ;
	private Tiquet tiquet;
	private PanelDerecho panelDerecho;
	private JTextField JtfPrecioTot;
	private JTextField InPrecioArbol, InPrecioFlor, InPrecioDecoracion;
	private JSpinner inAltura;
	private String material;
	private double precioArbol, precioFlor, precioDecoracion;
	
	public VentanaVentas(Floreria floreria) {
		
		Font miFuente = new Font("Arial", Font.BOLD, 18);

		this.floreria = floreria;
		tiquet = new Tiquet();

		actualizarPrecio = new ActualizarPrecio();

		setLayout(null);
		
		JLabel titulo = new JLabel("VENTAS");
		titulo.setBounds(0, 10, 900, 30);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(miFuente);
		add(titulo);

		PanelIzquierdo panelizquierdo = new PanelIzquierdo();
		panelizquierdo.setBounds(0, 50, 520, 470);		

		panelDerecho = new PanelDerecho();
		panelDerecho.setBounds(530, 50, 300, 470);

		add(panelizquierdo);
		add(panelDerecho);
	}
	public  void recargarPrecios() {
		
		precioArbol      =  buscarPrecio("arbol", inAltura.getValue().toString() ) ;
		precioFlor       =  buscarPrecio("flor", "Rojo" );
	    precioDecoracion =  buscarPrecio("decoracion", material );
	    
		InPrecioArbol.setText(   "€ "+ precioArbol ) ;
		InPrecioFlor.setText(   "€ "+precioFlor ) ;
		InPrecioDecoracion.setText(   "€ "+  precioDecoracion ) ;
	}
	
	class PanelIzquierdo extends JPanel {

		public PanelIzquierdo() {
			
			Font miFuente = new Font("Arial", Font.BOLD, 14);

			setLayout(null);

			JLabel titulo = new JLabel("AÑADIR PRODUCTOS");
			titulo.setFont(miFuente);
			titulo.setBounds(250, 0, 200, 30);
			add(titulo);

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
		private JScrollPane scroll;

		public PanelDerecho() {
			
			Font miFuente = new Font("Arial", Font.BOLD, 14);

			columnas = new String[] { "Tipo", "Cantidad", "Precio  " };

			scroll = new JScrollPane();

			setLayout(null);
			JLabel titulo = new JLabel("TIQUET VENTA");
			titulo.setFont(miFuente);
			titulo.setBounds(90, 0, 100, 35);
			add(titulo);

			scroll.setBounds(1, 40, 280, 300);
			add(scroll);
			cargarTabla();

			JLabel lbPrecioTot = new JLabel("Precio total: ");
			lbPrecioTot.setBounds(100, 360, 80, 30);
			add(lbPrecioTot);

			JtfPrecioTot = new JTextField("€ 0.0 ");
			JtfPrecioTot.setBounds(180, 360, 80, 30);
			add(JtfPrecioTot);

			JButton eliminar = new JButton("Eliminar ultimo registro");  //elimina el ultimo registro y actualiza el stock
			eliminar.setBounds(0, 410, 170, 30);
			eliminar.addActionListener(new EliminarRegistroUltimo());
			add(eliminar);
			
			JButton generar = new JButton("Generar tiquet");
			generar.setBounds(175, 410, 115, 30);
			generar.addActionListener(new GenerarTiquet());
			add(generar);

		}

		public void cargarTabla() {

			String informacion[][] = new String[tiquet.getRegistros().size()][3]; // creo la matriz con el size de la
																					// list en 'x' y las columnas en 'y'

			int x = 0;
			for (Registro r : tiquet.getRegistros()) {
				
				switch (r.getTipo()) {  //formateo la salida segun el tipo si es Arbol, Flor o Decoracion

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

			scroll.setViewportView(table);
		}
	}

	class VentaArbol extends JPanel {
		
		
		JSpinner inCantidad;				

		public VentaArbol() {			
		
			setLayout(new GridLayout(5, 2, 2, 2));
			
			precioArbol = buscarPrecio("arbol", "40" );

			JLabel tipo = new JLabel("          Tipo de producto:   ");		
			add(tipo);
			
			JLabel arboles = new JLabel("Arboles");
			arboles.setHorizontalAlignment(SwingConstants.CENTER);
			arboles.setBackground(Color.WHITE);
			arboles.setOpaque(true);
			add(arboles);				
			
			JLabel cantidad = new JLabel("          Cantidad:   ");		
			add(cantidad);
			
			inCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));	
			add(inCantidad);
			
			JLabel altura = new JLabel("          Altura en cm:   ");		
			add(altura);
			
			inAltura = new JSpinner(new SpinnerNumberModel(40, 40, 200, 10));
			add(inAltura);	
			
			JLabel jlPrecio = new JLabel("          Precio por unidad:   ");		
			add(jlPrecio);
			
			InPrecioArbol = new JTextField(  "€ "+  precioArbol  );
			InPrecioArbol.setEditable(false);
			InPrecioArbol.setBackground(Color.WHITE);
			add(InPrecioArbol);
			
			add(new JLabel());	   //relleno																																													
			
			inAltura.addChangeListener(new ChangeListener() {				
				@Override
				public void stateChanged(ChangeEvent e) {
					
					precioArbol =buscarPrecio("arbol", inAltura.getValue().toString() );
					
					 InPrecioArbol.setText("€ "+ precioArbol );																													
				}
			});	
			
			JButton fillArbol = new JButton("Añadir Arbol");
			fillArbol.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (okStock("arbol", (int) inCantidad.getValue(), inAltura.getValue().toString() )) {
				
						tiquet.agregarRegistro(new Registro( "Arbol" ,  inAltura.getValue()+"" ,  (int) inCantidad.getValue(), 
								precioArbol *(int) inCantidad.getValue() ) );								

						panelDerecho.cargarTabla();
						actualizarPrecio.actualizar();
					}
					else JOptionPane.showMessageDialog(null, "No hay stock suficiente del arbol de altura  " + 
																inAltura.getValue().toString()+" cm" ,"Importante!!", JOptionPane.ERROR_MESSAGE);						
				}				
			});			
			add(fillArbol);
		}							
	}
	
	class VentaFlor extends JPanel {

		JSpinner inCantidad;
		
		String color;
		
		
		public VentaFlor() {
			setLayout(new GridLayout(5, 2, 2, 2));
			
			precioFlor = buscarPrecio( "flor","Rojo" );			
			color = "Rojo";

			JLabel tipo = new JLabel("Tipo de producto:");
			tipo.setHorizontalAlignment(SwingConstants.RIGHT);
			add(tipo);
			
			JLabel flores = new JLabel("Flores");
			flores.setHorizontalAlignment(SwingConstants.CENTER);
			flores.setBackground(Color.WHITE);
			flores.setOpaque(true);
			add(flores);			

			JLabel cantidad = new JLabel("          Cantidad:");			
			add(cantidad);

			inCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			add(inCantidad);	
			
			JLabel jlcolor = new JLabel("          Color:");			
			add(jlcolor);
			
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
				precioFlor = buscarPrecio( "flor",color );								
				InPrecioFlor.setText("€ "+ precioFlor );
					
				}
			});			
			comboColor.setBackground(Color.WHITE);
			comboColor.setOpaque(true);
			add(comboColor);			

			JLabel jlPrecio = new JLabel("          Precio por unidad:");			
			add(jlPrecio);
			
			InPrecioFlor = new JTextField("€ "+ precioFlor);
			InPrecioFlor.setEditable(false);
			InPrecioFlor.setBackground(Color.WHITE);
			add(InPrecioFlor);		
			
			add(new JLabel());									
								
			JButton fillFlor = new JButton("Añadir Flor");
			fillFlor.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (okStock("flor", (int) inCantidad.getValue(), color )) {
						
						tiquet.agregarRegistro(new Registro( "Flor" , color ,  (int) inCantidad.getValue(), 
								precioFlor *(int) inCantidad.getValue() ) );
					
						panelDerecho.cargarTabla();
						
						actualizarPrecio.actualizar();						
					}
					else JOptionPane.showMessageDialog(null, "No hay stock suficiente de la flor color  " + 
																color ,"Importante!!", JOptionPane.ERROR_MESSAGE);						
				}				
			});			
			add(fillFlor);
		}
	}
	
	class VentaDecoracion extends JPanel {

		JSpinner inCantidad;
		
		ButtonGroup grupo;
		JRadioButton boton1, boton2;
		
		

		public VentaDecoracion() {

			setLayout(new GridLayout(5, 2, 2, 2));
			
			material ="Plastico";
			
			precioDecoracion = buscarPrecio("decoracion", "Plastico" );

			JLabel tipo = new JLabel("          Tipo de producto:");			
			add(tipo);
			
			JLabel decoracion = new JLabel("Decoracion");
			decoracion.setHorizontalAlignment(SwingConstants.CENTER);
			decoracion.setBackground(Color.WHITE);
			decoracion.setOpaque(true);
			add(decoracion);

			JLabel cantidad = new JLabel("          Cantidad:");			
			add(cantidad);
			
			inCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			add(inCantidad);
			
			JLabel jlMaterial = new JLabel("          Material:");			
			add(jlMaterial);			
			
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
			
			JLabel jlPrecio = new JLabel("          Precio por unidad:");			
			add(jlPrecio);
			
			InPrecioDecoracion = new JTextField("€ " + precioDecoracion);
			InPrecioDecoracion.setEditable(false);
			InPrecioDecoracion.setBackground(Color.WHITE);
			add(InPrecioDecoracion);
			
			add(new JLabel());																									
			
			boton1.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					material = "Madera";					
					precioDecoracion = buscarPrecio("decoracion", "Madera" );					
					InPrecioDecoracion.setText("€ " + precioDecoracion);									
				}
			});
			boton2.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					material = "Plastico";
					precioDecoracion = buscarPrecio("decoracion", "Plastico" );
					InPrecioDecoracion.setText("€ " + precioDecoracion);					
				}
			});

			JButton fillDecoracion = new JButton("Añadir Decoracion"); 
			fillDecoracion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (okStock("decoracion", (int) inCantidad.getValue(), material )) {
						
						tiquet.agregarRegistro(new Registro( "Decoracion",  material ,  (int) inCantidad.getValue(), 
								precioDecoracion *(int) inCantidad.getValue() ) );
						panelDerecho.cargarTabla();
						actualizarPrecio.actualizar();	
						
					}
					else JOptionPane.showMessageDialog(null, "No hay stock suficiente de la decoracion de  " + 
							material ,"Importante!!", JOptionPane.ERROR_MESSAGE);						
				}				
			});			
			add(fillDecoracion);									
		}		
	}
	
	private class EliminarRegistroUltimo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {						

			if (tiquet.getRegistros().size() > 0) {
				
				switch (tiquet.getRegistros().get(tiquet.getRegistros().size() -1 ).getTipo()) {  //formateo la salida segun el tipo si es Arbol, Flor o Decoracion

				case "Arbol": {
					for (int x =0; x < floreria.getArboles().size(); x++) {
						if ( (floreria.getArboles().get(x).getAltura() ) ==   // busco por altura
					        ( Integer.valueOf (  tiquet.getRegistros().get(tiquet.getRegistros().size() -1 ).getCaracteristica() ) )  ) {
					         floreria.getArboles().get(x).setCantidad
					        		 (floreria.getArboles().get(x).getCantidad() + tiquet.getRegistros().get(tiquet.getRegistros().size() -1 ).getCantidad() ) ;
					     }
					        	
					}					
					break;
				}
				case "Flor": {
					for (int x =0; x < floreria.getFlores().size(); x++) {
						if ( (floreria.getFlores().get(x).getColor() ).equals(   
					           tiquet.getRegistros().get(tiquet.getRegistros().size() -1 ).getCaracteristica() ) )   {
					         floreria.getFlores().get(x).setCantidad
					        		 (floreria.getFlores().get(x).getCantidad() + tiquet.getRegistros().get(tiquet.getRegistros().size() -1 ).getCantidad() ) ;
					     }
					}        	
					break;
				}
				case "Decoracion": {
					for (int x =0; x < floreria.getDecoraciones().size(); x++) {
						if ( (floreria.getDecoraciones().get(x).getMaterial() ).equals(   
					           tiquet.getRegistros().get(tiquet.getRegistros().size() -1 ).getCaracteristica() ) )   {
					         floreria.getDecoraciones().get(x).setCantidad
					        		 (floreria.getDecoraciones().get(x).getCantidad() + tiquet.getRegistros().get(tiquet.getRegistros().size() -1 ).getCantidad() ) ;
					     }
					}        	
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " );
				}				
				 tiquet.getRegistros().remove(tiquet.getRegistros().size() -1 );
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
				JtfPrecioTot.setText("€ 0.0");

			} else
				JOptionPane.showMessageDialog(null, "NO SE PUEDE GENERAR EL TIQUET PORQUE  SE ENCUENTRA VACIO : ",
						"Importante!!", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	private class ActualizarPrecio {					

		public void actualizar() {
			
			precioTotal=0;
			for(Registro r: tiquet.getRegistros()) {
				precioTotal += r.getPrecio();
			}			
			JtfPrecioTot.setText( "€ "+precioTotal);			
		}
	}

	private boolean okStock(String tipo, int cantidad, String caracteristica) {

		if (tipo.equals("arbol")) {
			for (Arbol a : floreria.getArboles()) {
				if ((a.getAltura() == Integer.parseInt(caracteristica)) && (a.getCantidad() >= cantidad)) {
					
					a.setCantidad(a.getCantidad() - cantidad);
					return true;
				}
			}
		} else if (tipo.equals("flor")) {
			for (Flor f : floreria.getFlores()) {
				if ((f.getColor().equals(caracteristica)) && (f.getCantidad() >= cantidad)) {
					
					f.setCantidad(f.getCantidad() - cantidad);
					return true;
				}
			}
		}else if (tipo.equals("decoracion")) {
			for (Decoracion d : floreria.getDecoraciones()) {

				if ((d.getMaterial().equals(caracteristica)) && (d.getCantidad() >= cantidad)) {
					
					d.setCantidad(d.getCantidad() - cantidad);
					return true;
				}
			}
		}
		return false;
	}

	private Double buscarPrecio(String tipo, String caracteristica) {		

		if (tipo.equals("arbol")) {
			for (Arbol a : floreria.getArboles()) {
				if (a.getAltura() == Integer.parseInt(caracteristica))
					return a.getPrecio();
			}
		} else if (tipo.equals("flor")) {
			for (Flor f : floreria.getFlores()) {
				if (f.getColor().equals(caracteristica))
					return f.getPrecio();
			}
		} else if (tipo.equals("decoracion")) {
			for (Decoracion d : floreria.getDecoraciones()) {
				if (d.getMaterial().equals(caracteristica))
					return d.getPrecio();
			}
		}
		return null;
	}
	
}

				