package floristeria.modelo;

public class Registro {
	
	private String tipo;
	private String caracteristica;
	private int cantidad;
	private double precio;
	
	public Registro(String tipo,String caracteristica,  int cantidad, double precio) {
		
		this.tipo = tipo;
		this.caracteristica = caracteristica;
		
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	
	public String getTipo() {
		return tipo;
	}


	public String getCaracteristica() {
		return caracteristica;
	}


	public int getCantidad() {
		return cantidad;
	}

	public double getPrecio() {
		return precio;
	}			
}
