package victor.easyshop.clases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarritoTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getArticulos() {
        Carrito instance = new Carrito();
        instance.insertarArticulo(1, "Camiseta", 10, 2,
                "Negro", 3, "S", "http://localhost/EasyShop/Imagenes/0x0.png",
                4, "H&M", 5);
        int expResult = 1;
        int result = instance.getArticulos().size();
        assertEquals(expResult, result);
    }

    @Test
    public void getNumArticulos() {
        Carrito instance = new Carrito();
        instance.insertarArticulo(1, "Camiseta", 10, 2,
                "Negro", 3, "S", "http://localhost/EasyShop/Imagenes/0x0.png",
                4, "H&M", 5);
        int expResult = 1;
        int result = instance.getNumArticulos();
        assertEquals(expResult, result);
    }

    @Test
    public void vacio() {
        Carrito instance = new Carrito();
        boolean expResult = true;
        boolean result = instance.vacio();
        assertEquals(expResult, result);
    }

    @Test
    public void insertarArticulo() {
        Carrito instance = new Carrito();
        instance.insertarArticulo(1, "Camiseta", 10, 2,
                "Negro", 3, "S", "http://localhost/EasyShop/Imagenes/0x0.png",
                4, "H&M", 5);
        assertEquals(1, instance.getArticulos().get(0).getId_Articulo());
        assertEquals("Camiseta", instance.getArticulos().get(0).getNombre());
        assertEquals(10, instance.getArticulos().get(0).getPVP(), 0.0);
        assertEquals(2, instance.getArticulos().get(0).getId_Color());
        assertEquals("Negro", instance.getArticulos().get(0).getColor());
        assertEquals(3, instance.getArticulos().get(0).getId_Talla());
        assertEquals("S", instance.getArticulos().get(0).getTalla());
        assertEquals("http://localhost/EasyShop/Imagenes/0x0.png",
                instance.getArticulos().get(0).getImagenURL());
        assertEquals(4, instance.getArticulos().get(0).getId_Marca());
        assertEquals("H&M", instance.getArticulos().get(0).getMarca());
        assertEquals(5, instance.getArticulos().get(0).getId_Categoria());
    }

    @Test
    public void eliminarArticulo() {
        Carrito instance = new Carrito();
        instance.insertarArticulo(1, "Camiseta", 10, 2,
                "Negro", 3, "S", "http://localhost/EasyShop/Imagenes/0x0.png",
                4, "H&M", 5);
        assertEquals(1, instance.getNumArticulos());
        instance.eliminarArticulo(instance.getArticulos().get(0));
        assertEquals(0, instance.getNumArticulos());
    }

    @Test
    public void vaciarCarro() {
        Carrito instance = new Carrito();
        instance.insertarArticulo(1, "Camiseta", 10, 2,
                "Negro", 3, "S", "http://localhost/EasyShop/Imagenes/0x0.png",
                4, "H&M", 5);
        assertEquals(false, instance.vacio());
        instance.vaciarCarro();
        assertEquals(true, instance.vacio());
    }
}