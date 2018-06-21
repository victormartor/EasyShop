package victor.easyshop.clases;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ClienteTest {

    @Test
    public void conectar() throws IOException {
        String expResult = "conectado";
        String result = Cliente.conectar("localhost", "5000");
        assertEquals(expResult, result);
    }

    @Test
    public void getMarcas() throws IOException {
        int expResult = 4;
        int result = Cliente.getMarcas("localhost", "5000").size();
        assertEquals(expResult, result);
    }

    @Test
    public void getMarca() throws IOException {
        Marca expResult = new Marca(1, "ZARA");
        Marca result = Cliente.getMarca(1, "localhost", "5000");
        assertEquals(expResult.getNombre(), result.getNombre());
    }

    @Test
    public void getCategorias() throws IOException {
        int expResult = 4;
        int result = Cliente.getCategorias(1, "localhost", "5000").size();
        assertEquals(expResult, result);
    }

    @Test
    public void getArticulos() throws IOException {
        int expResult = 2;
        int result = Cliente.getArticulos(1, "localhost", "5000").size();
        assertEquals(expResult, result);
    }

    @Test
    public void getUnArticulo() throws IOException {
        Articulo expResult = new Articulo(1, "CAMISETA TEXTO ESTAMPADO", 9.95,
                false);
        Articulo result = Cliente.getUnArticulo(1, "localhost", "5000");
        assertEquals(expResult.getNombre(), result.getNombre());
    }

    @Test
    public void getColoresArticulo() throws IOException {
        int expResult = 3;
        int result = Cliente.getColoresArticulo(1, "localhost", "5000").size();
        assertEquals(expResult, result);
    }

    @Test
    public void getTallasArticulo() throws IOException {
        int expResult = 4;
        int result = Cliente.getTallasArticulo(1, "localhost", "5000").size();
        assertEquals(expResult, result);
    }

    @Test
    public void getCombinacionesArticulo() throws IOException {
        int expResult = 3;
        int result = Cliente.getCombinacionesArticulo(1, "localhost", "5000").size();
        assertEquals(expResult, result);
    }

    @Test
    public void enviaCompra() throws IOException {
        int expResult = 1;
        int result = Cliente.enviaCompra(new Carrito(), "localhost", "5000");
        assertEquals(expResult, result);
    }
}