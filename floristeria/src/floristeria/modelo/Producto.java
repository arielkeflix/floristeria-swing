package floristeria.modelo;

public abstract class Producto {
	
	int cantidad ;
	double precio;
	public Producto( double precio) { 
		
		this.cantidad = 0;
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public double getPrecio() {
		return precio;
	}	
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	

}
