package floristeria.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tiquet {
	
	private List<Registro>registros;
	private int nroTiquet;
	private static int contador = 0;
	
	 
	public  Tiquet() {
		registros = new ArrayList<>();
		contador++;
		nroTiquet = contador;
	}
	
	public void agregarRegistro(Registro registro) {
		registros.add(registro);
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public int getNroTiquet() {
		return nroTiquet;
	}
	
	

}
