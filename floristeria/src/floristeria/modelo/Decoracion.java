package floristeria.modelo;

public class Decoracion extends Producto{
	
	private String material;

	public Decoracion( double precio, String material) {
		super( precio);
		this.material = material;
	}
	
	public String getMaterial() {
		return material;
	}
	

}
