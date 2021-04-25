package floristeria.modelo;

import java.util.ArrayList;
import java.util.List;

public class Floreria {
	
	private String nombre;
	private String direccion;
	private String telefono;
	
	private List <Arbol> arboles;
	private List <Flor> flores;
	private List<Decoracion> decoraciones;
	
	private List<Tiquet> tiquets;		
			public Floreria(String nombre, String direccion, String telefono) {
		
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;		
	
		arboles       = new ArrayList<>();
		flores        = new ArrayList<>();
		decoraciones  = new ArrayList<>();
		tiquets       = new ArrayList<>();
	}


	public void agregarArbol(Arbol arbol) {
		arboles.add(arbol);
	}
	
	public void agregarFlor(Flor flor) {
		flores.add(flor);
		
	}
	
	public void agregarDecoracion(Decoracion decoracion) {
		decoraciones.add(decoracion);
		
	}
	
	public void agregarTiquet(Tiquet tiquet) {
		tiquets.add(tiquet);		
	}

	public String getNombre() {
		return nombre;
	}
	

	public String getDireccion() {
		return direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public List<Arbol> getArboles() {
		return arboles;
	}

	public List<Flor> getFlores() {
		return flores;
	}

	public List<Decoracion> getDecoraciones() {
		return decoraciones;
	}

	public List<Tiquet> getTiquets() {
		return tiquets;
	}	
	

}
