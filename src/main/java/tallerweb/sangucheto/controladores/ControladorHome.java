package tallerweb.sangucheto.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import tallerweb.sangucheto.modelo.BaseDeDatos;
import tallerweb.sangucheto.modelo.Ingrediente;
import tallerweb.sangucheto.modelo.Sanguchetto;
import tallerweb.sangucheto.modelo.Stock;
import tallerweb.sangucheto.modelo.TipoIngrediente;

@Controller
@SessionAttributes({"sanguchitoDeUsuario", "usuario"}) // Identifico a un "usuario" con sólo su id. //
public class ControladorHome {

	@RequestMapping(path="/")
	public ModelAndView loguearUsuario(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(path="verHome", method = RequestMethod.POST)
	public ModelAndView visualizarHome()
	{
		return new ModelAndView("home");
	}
	
	
	@RequestMapping(path="logueo", method = RequestMethod.POST)
	public ModelAndView conectarABD(HttpServletRequest request, @ModelAttribute("documento") String dni, @ModelAttribute("password") String pass) // NOTA: Obviamente que lo que estoy haciendo dentro de este metodo va a cambiar bastante cuando meta la BD. Como todavia no hay BD, para cada usuario que ingresa, sólo grabo su "id" y la registro en la variable de sesión "usuario". //
	{
		BaseDeDatos bd = new BaseDeDatos();
		bd.conectar();
		request.getSession().setAttribute("sanguchitoDeUsuario", null);
		if(bd.loguear(dni, pass))
		{
			request.getSession().setAttribute("usuario", dni);
			return new ModelAndView("home");
		}
		else
		{
			request.getSession().setAttribute("usuario", null);
			return new ModelAndView("login");
		}
	}
	
	
	@RequestMapping(path = "alta", method = RequestMethod.POST)
	public ModelAndView darAlta(@ModelAttribute("documento") String dni, @ModelAttribute("password") String pass)
	{
		BaseDeDatos bd = new BaseDeDatos();
		bd.conectar();
		if(!bd.comprobarExistencia(dni))
		{
			bd.darAlta(dni, pass);
		}
		
		return new ModelAndView("login"); // NOTA 1: Lo redirigo a "login" porque darse de alta no es loguearse. NOTA 2: Si lo quisiera hacer lindo, la pagina devuelta deberia de tener un cartelito que diga si el alta fue exitosa o no. Pero la idea es apuntar al back-end.//
	}
	
	
	@RequestMapping(path = "modificacion", method = RequestMethod.POST)
	public ModelAndView modificarRegistro(@ModelAttribute("documento") String dni, @ModelAttribute("passwordVieja") String passVieja, @ModelAttribute("passwordNueva") String passNueva)
	{
		BaseDeDatos bd = new BaseDeDatos();
		bd.conectar();
		if(bd.comprobarExistenciaYPass(dni, passVieja))
		{
			bd.actualizarPassword(dni, passNueva);
		}
		
		return new ModelAndView("login");
	}
	
	
	@RequestMapping(path="agregarProducto",  method = RequestMethod.POST)
	public ModelAndView agregarProductoPaso1()
	{
		Sanguchetto sang=new Sanguchetto();
		ArrayList<Ingrediente> prodsIngr=(ArrayList<Ingrediente>)sang.verIngredientes();
		ArrayList<Ingrediente> prodsCond=(ArrayList<Ingrediente>)sang.verCondimentos();
		
		ModelMap model=new ModelMap();
		model.put("prodsIngr", prodsIngr);
		model.put("prodsCond", prodsCond);
		model.put("precio", sang.getPrecio());
		model.put("precioConDesc",0);
		model.put("ahorro", 0);
		model.put("descuento", 0);
		
		Stock st=Stock.getInstance();
		HashMap<Ingrediente,Integer> ingrs=new HashMap<Ingrediente,Integer>(); // Podria haber usado una List en vez de un Map ya que el value no lo uso en la vista. //
		for(Map.Entry<Ingrediente,Integer> i:st.obtenerStock().entrySet())
		{
			if(i.getKey().getTipo().equals(TipoIngrediente.INGREDIENTE)) //Con esto obtengo los productos (y sus correspondientes stocks) de tipo "INGREDIENTE" para poder ser facilmente presentados en la view. // 
			{
				ingrs.put(i.getKey(),i.getValue());
			}
		}		
		model.put("stockDeProds", ingrs);
		
		
		HashMap<Ingrediente,Integer> conds=new HashMap<Ingrediente,Integer>();
		for(Map.Entry<Ingrediente,Integer> i:st.obtenerStock().entrySet())
		{
			if(i.getKey().getTipo().equals(TipoIngrediente.CONDIMENTO)) //Con esto obtengo los productos (y sus correspondientes stocks) de tipo "CONDIMENTO" para poder ser facilmente presentados en la view. // 
			{
				conds.put(i.getKey(),i.getValue());
			}
		}
		model.put("stockDeConds", conds);
		
		String mensaje=new String();
		model.put("error", mensaje);
		
		return new ModelAndView("AgregarACarrito-Paso1",model);
	}
	
	
	@RequestMapping(path="analizandoIngreso",  method = RequestMethod.POST)
	public ModelAndView analizarValidezDeLaCantidadIngresada(HttpServletRequest request, @ModelAttribute("combobox") String nomDeProd, @ModelAttribute("cant") String cant, @ModelAttribute("descuentoAAplicar") String desc)
	{
		if(request.getSession().getAttribute("usuario") == null)
		{
			return new ModelAndView("/");
		}
		
		
		Sanguchetto sang = null;
		if(request.getSession().getAttribute("sanguchitoDeUsuario") == null)
		{
			sang = new Sanguchetto();
			request.getSession().setAttribute("sanguchitoDeUsuario", sang);
		}
		else
		{
			sang = (Sanguchetto) request.getSession().getAttribute("sanguchitoDeUsuario");
		}
		
		Stock st=Stock.getInstance();
		Boolean agregadoCorrecto=false;
		Integer cantidad=Integer.parseInt(cant);
		HashMap<Ingrediente,Integer> hm=(HashMap<Ingrediente,Integer>)Stock.getInstance().obtenerStock();
		for(Map.Entry<Ingrediente,Integer> me:hm.entrySet())
		{
			if(me.getKey().getNombre().equals(nomDeProd))
			{
				if(st.comprarIngrediente(me.getKey(), cantidad))
				{
					for(int i = 0; i<cantidad; i++)
					{
						sang.agregarIngrediente(me.getKey()); //Entro a este for porque, en el caso de querer ingresar más de un producto, no se podria ya que el metodo agregarIngrediente esta modelado para que se ingrese de a un producto. NOTA: Después modificar el modelo para que en una corrida del metodo se puedan agregar la cantidad n que quisiera. //
					}
					agregadoCorrecto=true;
				}
				break;
			}			
		}			
		ArrayList<Ingrediente> prodsIngr=(ArrayList<Ingrediente>)sang.verIngredientes();
		ArrayList<Ingrediente> prodsCond=(ArrayList<Ingrediente>)sang.verCondimentos();
		
		ModelMap model=new ModelMap();
		model.put("prodsIngr", prodsIngr);
		model.put("prodsCond", prodsCond);
		model.put("precioTotal", sang.getPrecio());
		Double descuento=Integer.parseInt(desc)*sang.getPrecio()/100;
		model.put("precioConDesc",descuento);
		
		if(descuento==0)
		{
			model.put("ahorro", 0);
		}
		else
		{
			model.put("ahorro", sang.getPrecio()-descuento);
		}
		
		if(desc == null || desc.equals(""))
		{
			model.put("descuento", 0);
		}
		else
		{
			model.put("descuento", desc);
		}		
		
		HashMap<Ingrediente,Integer> ingrs=new HashMap<Ingrediente,Integer>(); // Podria haber usado una List en vez de un Map ya que el value no lo uso en la vista. //
		for(Map.Entry<Ingrediente,Integer> i:st.obtenerStock().entrySet())
		{
			if(i.getKey().getTipo().equals(TipoIngrediente.INGREDIENTE)) //Con esto obtengo los productos (y sus correspondientes stocks) de tipo "INGREDIENTE" para poder ser facilmente presentados en la view. // 
			{
				ingrs.put(i.getKey(),i.getValue());
			}
		}		
		model.put("stockDeProds", ingrs);		
		
		HashMap<Ingrediente,Integer> conds=new HashMap<Ingrediente,Integer>();
		for(Map.Entry<Ingrediente,Integer> i:st.obtenerStock().entrySet())
		{
			if(i.getKey().getTipo().equals(TipoIngrediente.CONDIMENTO)) //Con esto obtengo los productos (y sus correspondientes stocks) de tipo "CONDIMENTO" para poder ser facilmente presentados en la view. // 
			{
				conds.put(i.getKey(),i.getValue());
			}
		}
		model.put("stockDeConds", conds);
		
		String mensaje=new String();
		if(agregadoCorrecto==false)
		{
			mensaje="El ultimo ingreso no fue posible dado que superó las unidades en stock!";
		}
		model.put("error", mensaje);
		model.put("usuario", request.getSession().getAttribute("usuario"));
		
		request.getSession().setAttribute("sanguchitoDeUsuario", sang);
		
		return new ModelAndView("AgregarACarrito-Paso1",model);		
	}
	
	
	@RequestMapping(path="descuento",  method = RequestMethod.POST)
	public ModelAndView obtenerTotalesConDescuento(HttpServletRequest request, @ModelAttribute("valorDescuento") String valDesc)
	{
		Sanguchetto sang = (Sanguchetto) request.getSession().getAttribute("sanguchitoDeUsuario");
		if(sang == null) // Si se pide calcular el descuento de un sanguchito sin nada, lo redirijo a la vista "AgregarACarrito-Paso1" hasta que el cajero ingrese al menos un ingrediente. //
		{
			return new ModelAndView("AgregarACarrito-Paso1");
		}
		
		ModelMap model=new ModelMap();
		model.put("descuento", valDesc);
		
		Stock st=Stock.getInstance();
		
		ArrayList<Ingrediente> prodsIngr=(ArrayList<Ingrediente>)sang.verIngredientes();
		ArrayList<Ingrediente> prodsCond=(ArrayList<Ingrediente>)sang.verCondimentos();
		
		model.put("prodsIngr", prodsIngr);
		model.put("prodsCond", prodsCond);
		model.put("precioTotal", sang.getPrecio());
		Double descuento=Integer.parseInt(valDesc)*sang.getPrecio()/100;
		model.put("precioConDesc",descuento);
		if(descuento==0)
		{
			model.put("ahorro", 0);
		}
		else
		{
			model.put("ahorro", sang.getPrecio()-descuento);
		}

		if(valDesc == null || valDesc.equals(""))
		{
			model.put("descuento", 0);
		}
		else
		{
			model.put("descuento", valDesc);
		}		
		
		HashMap<Ingrediente,Integer> ingrs=new HashMap<Ingrediente,Integer>(); // Podria haber usado una List en vez de un Map ya que el value no lo uso en la vista. //
		for(Map.Entry<Ingrediente,Integer> i:st.obtenerStock().entrySet())
		{
			if(i.getKey().getTipo().equals(TipoIngrediente.INGREDIENTE)) //Con esto obtengo los productos (y sus correspondientes stocks) de tipo "INGREDIENTE" para poder ser facilmente presentados en la view. // 
			{
				ingrs.put(i.getKey(),i.getValue());
			}
		}		
		model.put("stockDeProds", ingrs);		
		
		HashMap<Ingrediente,Integer> conds=new HashMap<Ingrediente,Integer>();
		for(Map.Entry<Ingrediente,Integer> i:st.obtenerStock().entrySet())
		{
			if(i.getKey().getTipo().equals(TipoIngrediente.CONDIMENTO)) //Con esto obtengo los productos (y sus correspondientes stocks) de tipo "CONDIMENTO" para poder ser facilmente presentados en la view. // 
			{
				conds.put(i.getKey(),i.getValue());
			}
		}
		model.put("stockDeConds", conds);
		
		
		return new ModelAndView("AgregarACarrito-Paso1",model);
	}
	
	
	
	@RequestMapping(path="vaciarCarrito", method=RequestMethod.POST)
	public ModelAndView vaciarTodoElCarroYDevolverStock(HttpServletRequest request)
	{
		Sanguchetto sang= (Sanguchetto) request.getSession().getAttribute("sanguchitoDeUsuario");
		Stock st=Stock.getInstance();
		
		for(Ingrediente ing:(ArrayList<Ingrediente>)sang.verIngredientes())
		{
			st.agregarStock(ing, 1);
		}
		
		for(Ingrediente ing:(ArrayList<Ingrediente>)sang.verCondimentos())
		{
			st.agregarStock(ing, 1);
		}		
		sang.vaciar();
		request.getSession().setAttribute("sanguchitoDeUsuario", null);
		
		return new ModelAndView("home");
	}
	

	
	
	
	
	@RequestMapping(path="/altaProducto", method=RequestMethod.POST)
	public ModelAndView darAltaAUnProductoEnElStock()
	{
		return new ModelAndView("darAltaAUnProducto");
	}
	
	@RequestMapping(path="alta/eleccion", method=RequestMethod.POST)
	public ModelAndView productoFehacientementeAdicionadoEnElStock(@ModelAttribute("Nombre") String nom, @ModelAttribute("Precio") String prec, @ModelAttribute("TipoProd") String tp)
	{
		Stock st=Stock.getInstance();
		if(tp.equals("INGREDIENTE"))
		{
			st.agregarIngrediente(new Ingrediente(nom,Double.parseDouble(prec),TipoIngrediente.INGREDIENTE));
		}
		else
		{
			st.agregarIngrediente(new Ingrediente(nom,Double.parseDouble(prec),TipoIngrediente.CONDIMENTO));
		}		
		return new ModelAndView("home");
	}
	
	
	
	
	
	@RequestMapping(path="agregarStock", method=RequestMethod.POST)
	public ModelAndView agregarStockAUnProducto()
	{
		HashMap<Ingrediente,Integer> hm=(HashMap<Ingrediente, Integer>) Stock.getInstance().obtenerStock();
		ModelMap model=new ModelMap();
		model.put("stock",hm);		
		return new ModelAndView("agregarStockAUnProducto",model);
	}
	
	@RequestMapping(path="agregandoStock", method=RequestMethod.POST)
	public ModelAndView agregandoUnaUnidadDeStockAUnProducto(@ModelAttribute("comboboxStock") String nomDeProd)
	{
		Stock st=Stock.getInstance();
		for(Map.Entry<Ingrediente,Integer> ing:st.obtenerStock().entrySet())
		{
			if(ing.getKey().getNombre().equals(nomDeProd))
			{
				st.agregarStock(ing.getKey(),1);
				break;
			}
		}		
		return new ModelAndView("home");
	}
	
	
	
	
	@RequestMapping(path="borrarStock", method=RequestMethod.POST)
	public ModelAndView eliminarStockAUnProducto()
	{
		Stock st=Stock.getInstance();
		HashMap<Ingrediente,Integer> hm=(HashMap<Ingrediente,Integer>)st.obtenerStock();
		ModelMap model=new ModelMap();
		model.put("stock",hm);		
		return new ModelAndView("borrarStockAUnProducto",model);
	}
	
	@RequestMapping(path="borrandoStock", method=RequestMethod.POST)
	public ModelAndView eliminarStockAUnProducto(@ModelAttribute("comboboxStock") String nomDeProd)
	{
		Stock st=Stock.getInstance();
		for(Map.Entry<Ingrediente,Integer> ing:st.obtenerStock().entrySet())
		{
			if(ing.getKey().getNombre().equals(nomDeProd))
			{
				st.eliminarIngrediente(ing.getKey());
				break;
			}
		}		
		return new ModelAndView("home");
	}
	
	
	
	
	@RequestMapping(path="muestraStock", method=RequestMethod.POST)
	public ModelAndView muestraStockDeLosProductos(HttpServletRequest request)
	{
		Stock st=Stock.getInstance();
		HashMap<Ingrediente,Integer> hm=(HashMap<Ingrediente,Integer>)st.obtenerStock();
		ModelMap model=new ModelMap();
		model.put("stock",hm);
		return new ModelAndView("mostrarStockDeLosProductos",model);
	}	
}
