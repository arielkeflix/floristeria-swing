package floristeria.vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import floristeria.modelo.Floreria;
import floristeria.modelo.Registro;
import floristeria.modelo.Tiquet;

public class VentanaListarTiquets extends JPanel {

	private Floreria floreria;
	private JScrollPane scroll1, scroll2, scroll3, scroll4;
	private JLabel titTabla1, titTabla2, titTabla3, titTabla4, jlTotalVentas;
	private JLabel lbPrecioTotal1, lbPrecioTotal2, lbPrecioTotal3, lbPrecioTotal4;
	private JButton  btnPrevios, btnProximos;
	private int startTable =0;
	
	
	public VentanaListarTiquets(Floreria floreria) {

		this.floreria = floreria;

		Font miFuente = new Font("Arial", Font.BOLD, 18);	
		
		Font fuente = new Font("Arial", Font.BOLD, 16);

		setLayout(null);
		
		jlTotalVentas = new JLabel("Total ventas: € "  );		
		jlTotalVentas.setFont(fuente);
		jlTotalVentas.setBounds(10, 10, 200, 30);
		add(jlTotalVentas);

		JLabel titulo = new JLabel("LISTADO DE TIQUETS DE VENTA");		
		
		titulo.setBounds(0, 10, 900, 30);
		
		titulo.setHorizontalAlignment(SwingConstants.CENTER);

		titulo.setFont(miFuente);		

		add(titulo);
		
		titTabla1 = new JLabel("tiquets nro ");
		titTabla1.setBounds(230, 45, 150, 20);;
		add(titTabla1);
		
		titTabla2 = new JLabel("tiquets nro ");
		titTabla2.setBounds(580, 45, 150, 20);
		add(titTabla2);
		
		titTabla3 = new JLabel("tiquets nro ");
		titTabla3.setBounds(230, 280, 150, 20);
		add(titTabla3);
		
		titTabla4 = new JLabel("tiquets nro ");
		titTabla4.setBounds(580, 280, 150, 20);
		add(titTabla4);
		
		lbPrecioTotal1 = new JLabel("precioTotal ");
		lbPrecioTotal1.setBounds(130, 260, 150, 20);
		add(lbPrecioTotal1);
		
		lbPrecioTotal2 = new JLabel("precioTotal ");
		lbPrecioTotal2.setBounds(480, 260, 150, 20);
		add(lbPrecioTotal2);
		
		lbPrecioTotal3 = new JLabel("precioTotal ");
		lbPrecioTotal3.setBounds(130, 495, 150, 20);
		add(lbPrecioTotal3);
		
		lbPrecioTotal4 = new JLabel("precioTotal ");
		lbPrecioTotal4.setBounds(480, 495, 150, 20);
		add(lbPrecioTotal4);				

		scroll1 = new JScrollPane();
		scroll1.setBounds(130, 70, 280, 180);		
		add(scroll1);
		
		scroll2 = new JScrollPane();
		scroll2.setBounds(480, 70, 280, 180);		
		add(scroll2);
		
		scroll3 = new JScrollPane();
		scroll3.setBounds(130, 310, 280, 180);		
		add(scroll3);
		
		scroll4 = new JScrollPane();
		scroll4.setBounds(480, 310, 280, 180);		
		add(scroll4);
		
		btnProximos = new JButton("Proximos 4");
		btnProximos.setBounds(775, 270, 100, 30);
		btnProximos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarTablas(4);
				
			}
		});
		add(btnProximos);
		
		btnPrevios = new JButton(" 4 previos");
		btnPrevios.setBounds(10, 270, 100, 30);
		btnPrevios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarTablas(-4);
				
			}
		});
		add(btnPrevios);
	}

	public void cargarTablas(int comienzo) {

		String columnas[] = { "Tipo", "Cantidad", "Precio  " }; // titulo de las columnas de cada tabla

		int scrolls = 1;
		JTable table;
		Tiquet t;
		boolean continuar = true; // cuando llego a interar 4 tiquets paro
		double precioTotal;

		btnProximos.setEnabled(true);
		btnPrevios.setEnabled(true);

		startTable += comienzo; // identifica si es 1era vez 'o' next '+4' o previos '-4'

		if (startTable + 4 >= floreria.getTiquets().size()) { // si no hay mas de 4 para mostrar
			startTable = floreria.getTiquets().size() - 4;
			btnProximos.setEnabled(false);
		}
		if (startTable <= 0) { // si no hay previos, valido tambien que no sea negativo

			startTable = 0;
			btnPrevios.setEnabled(false);
		}

		for (int y = startTable; continuar && y < floreria.getTiquets().size(); y++) {

			t = floreria.getTiquets().get(y);

			int x = 0;
			String informacion[][] = new String[t.getRegistros().size()][3];// creo la matriz con el size de la list en
																			// 'x' y las columnas en 'y'
			precioTotal = 0;

			for (Registro r : t.getRegistros()) {

				switch (r.getTipo()) { // formateo la salida segun el tipo si es Arbol, Flor o Decoracion

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
				precioTotal += r.getPrecio();
				x++;
			}
			table = new JTable(informacion, columnas);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(140);
			table.getColumnModel().getColumn(1).setPreferredWidth(70);
			table.getColumnModel().getColumn(2).setPreferredWidth(67);

			switch (scrolls) {
			case 1:
				scroll1.setViewportView(table);
				titTabla1.setText("Tiquet nro: " + t.getNroTiquet());
				lbPrecioTotal1.setText("Precio total: € " + precioTotal);
				break;
			case 2:
				scroll2.setViewportView(table);
				titTabla2.setText("Tiquet nro: " + t.getNroTiquet());
				lbPrecioTotal2.setText("Precio total: € " + precioTotal);
				break;
			case 3:
				scroll3.setViewportView(table);
				titTabla3.setText("Tiquet nro: " + t.getNroTiquet());
				lbPrecioTotal3.setText("Precio total: € " + precioTotal);
				break;
			case 4:
				scroll4.setViewportView(table);
				titTabla4.setText("Tiquet nro: " + t.getNroTiquet());
				lbPrecioTotal4.setText("Precio total: € " + precioTotal);
				scrolls = 0;
				continuar = false;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + scrolls);
			}
			scrolls++;
		}

		jlTotalVentas.setText("Total ventas: € " + calcularTotal());

	}
	
	private double calcularTotal() {
		double total = 0; 
		for (Tiquet t: floreria.getTiquets()) {			
			for (Registro r: t.getRegistros()) {
				total+= r.getPrecio();				
			}			
		}
		return total;		
	}
}
