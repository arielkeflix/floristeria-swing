package floristeria.modelo;

public class Flor extends Producto{
	
	private String color;

	public Flor( double precio, String color) { 
		super(precio);  
		this.color = color;
	}

	public String getColor() {
		return color;
	}		
	
}
