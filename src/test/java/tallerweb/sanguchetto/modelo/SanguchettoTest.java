package tallerweb.sanguchetto.modelo;

import java.util.ArrayList;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;


import tallerweb.sangucheto.modelo.Ingrediente;
import tallerweb.sangucheto.modelo.Sanguchetto;
import tallerweb.sangucheto.modelo.Stock;
import tallerweb.sangucheto.modelo.TipoIngrediente;


public class SanguchettoTest {


	@Test
    public void testVaciar() {
        // Implementar
		Stock st=Stock.getInstance();
		Sanguchetto sang=new Sanguchetto();
		
		
		Ingrediente queso = new Ingrediente("queso", 7.5, TipoIngrediente.CONDIMENTO);
		Ingrediente tomate = new Ingrediente("tomate", 7.5, TipoIngrediente.INGREDIENTE);
		Ingrediente lechuga = new Ingrediente("lechuga", 7.5, TipoIngrediente.CONDIMENTO);
		
		
		st.agregarStock(queso, 1);
		st.agregarStock(tomate, 1);
		st.agregarStock(lechuga, 1);
		
    	
		sang.agregarIngrediente(queso);
		sang.agregarIngrediente(tomate);
		sang.agregarIngrediente(lechuga);
		
		sang.vaciar();
		
		Assert.assertEquals(0, (Integer)sang.verCondimentos().size() + (Integer)sang.verIngredientes().size());

		
    }

    
    @Test
    public void testAgregarIngrediente() {

		Stock st=Stock.getInstance();
		Sanguchetto sang=new Sanguchetto();
		List<Ingrediente> li=new ArrayList<Ingrediente>();
		
		Ingrediente queso = new Ingrediente("queso", 1.0, TipoIngrediente.INGREDIENTE);
		
		st.agregarStock(queso, 1);
		
		sang.agregarIngrediente(queso);
		
		li = sang.verIngredientes();
		
		Assert.assertEquals(queso.getNombre(), li.get(0).getNombre() );
    	
    	
    }
    

	@Test
    public void testVerIngredientes() {
        // Implementar
		Stock st=Stock.getInstance();
		Sanguchetto sang=new Sanguchetto();
		
		Ingrediente queso = new Ingrediente("queso", 1.0, TipoIngrediente.INGREDIENTE);
		
		st.agregarStock(queso, 1);
		
		sang.agregarIngrediente(queso);
		
		Ingrediente ingg = sang.verIngredientes().get(0);
		
		Assert.assertEquals(queso.getNombre(), ingg.getNombre());
		
    }

    @Test
    public void testVerCondimentos() {
        // Implementar
		Stock st=Stock.getInstance();
		Sanguchetto sang=new Sanguchetto();
		
		Ingrediente queso = new Ingrediente("queso", 1.0, TipoIngrediente.CONDIMENTO);
		
		st.agregarStock(queso, 1);
		
		sang.agregarIngrediente(queso);
		
		Ingrediente ingg = sang.verCondimentos().get(0);
		
		Assert.assertEquals(queso.getNombre(), ingg.getNombre());
    	
    }

   @Test
    public void testGetPrecio() {
        // Implementar
		Stock st=Stock.getInstance();
		Sanguchetto sang=new Sanguchetto();
		
		sang.vaciar();
		Ingrediente tomate = new Ingrediente("tomate", 2.0, TipoIngrediente.INGREDIENTE);
		
		st.agregarStock(tomate, 1);
   	
		sang.agregarIngrediente(tomate);
		
		Double valorEsperado = tomate.getPrecio();
		
		Assert.assertEquals( valorEsperado, sang.getPrecio());

    }
}
