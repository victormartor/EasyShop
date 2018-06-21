package victor.easyshop.clases;

import org.junit.Test;

import static org.junit.Assert.*;

public class Articulo_TallaTest {

    @Test
    public void getId() {
        Articulo_Talla instance = new Articulo_Talla(3, "S");
        int expResult = 3;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void getNombre() {
        Articulo_Talla instance = new Articulo_Talla(3, "S");
        String expResult = "S";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }
}