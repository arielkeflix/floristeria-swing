package floristeria.modelo;

public class Arbol extends Producto {
	
	private int altura;

	public Arbol( double precio, int altura) {
		super(precio);
		this.altura = altura;
	}
	
	public int getAltura() {
		
		return altura;
	}
	
	
	

}
