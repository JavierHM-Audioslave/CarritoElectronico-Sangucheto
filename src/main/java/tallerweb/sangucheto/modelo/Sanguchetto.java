package tallerweb.sangucheto.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Sanguchetto {

	private List<Ingrediente> ingredientes = new LinkedList<Ingrediente>();
	
	public Sanguchetto(){}
	
	/**
	 * Elimina todos los ingredientes del sanguchetto.<br>
	 */
	public void vaciar(){
		ingredientes.clear();
	}
	
	/**
	 * Agrega un ingrediente al carrito.<br>
	 * @param ingrediente
	 */
	public void agregarIngrediente(Ingrediente ingrediente){
		ingredientes.add(ingrediente);
	}
	
	/**
	 * Lista todos los ingredientes que forman parte del sanguchetto.<br>
	 * @return
	 */
		public List<Ingrediente> verIngredientes(){			
			
			List<Ingrediente> li=new ArrayList<Ingrediente>();
			for(Ingrediente i: ingredientes)
			{
				if(i.getTipo().equals(TipoIngrediente.INGREDIENTE))
				{
					li.add(i);
				}
			}
			return li;		
	}
	
	/**
     * Lista todos los condimentos que forman parte del sanguchetto.<br>
     * @return
     */
    public List<Ingrediente> verCondimentos(){
    	
    	List<Ingrediente> li=new ArrayList<Ingrediente>();
    	for(Ingrediente i: ingredientes)
    	{
    		if(i.getTipo().equals(TipoIngrediente.CONDIMENTO))
    		{
    			li.add(i);
    		}
    	}
    	return li;   	 	 
    }
	
	/**
	 * Devuelve el precio total del sanguchetto.<br>
	 * @return
	 */
	public Double getPrecio(){
		Double suma=(double) 0;
		for(Ingrediente i:ingredientes)
		{
			suma+=i.getPrecio();
		}
		return suma;
	}
}
