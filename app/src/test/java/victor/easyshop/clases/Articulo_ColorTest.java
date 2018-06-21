package victor.easyshop.clases;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Articulo_ColorTest {

    @Test
    public void getId() {
        Articulo_Color instance = new Articulo_Color(1, "Blanco", null);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void getNombre() {
        Articulo_Color instance = new Articulo_Color(1, "Blanco", null);
        String expResult = "Blanco";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    @Test
    public void getImagenes() {
        ArrayList<Imagen> aImagenes = new ArrayList<>();
        aImagenes.add(new Imagen("http://localhost/EasyShop/Imagenes/0x0.png"));
        Articulo_Color instance = new Articulo_Color(1, "Blanco", aImagenes);
        ArrayList<Imagen> result = instance.getImagenes();
        assertEquals(aImagenes, result);
    }
}