package victor.easyshop.clases;

import android.graphics.Bitmap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ArticuloTest {

    @Test
    public void getId() {
        Articulo articulo = new Articulo(1, 10);
        int expResult = 1;
        int result = articulo.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void getNombre() {
        Articulo articulo = new Articulo(1, "Prueba", 10, false);
        String expResult = "Prueba";
        String result = articulo.getNombre();
        assertEquals(expResult, result);
    }

    @Test
    public void getPVP() {
        Articulo articulo = new Articulo(1, 10);
        double expResult = 10;
        double result = articulo.getPVP();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void getTalla_Es_Numero() {
        Articulo articulo = new Articulo(1, "Prueba", 10, false);
        boolean expResult = false;
        boolean result = articulo.getTalla_Es_Numero();
        assertEquals(expResult, result);
    }

    @Test
    public void getId_Marca() {
        Articulo articulo = new Articulo(1, 2, 3);
        int expResult = 2;
        int result = articulo.getId_Marca();
        assertEquals(expResult, result);
    }

    @Test
    public void getId_Categoria() {
        Articulo articulo = new Articulo(1, 2, 3);
        int expResult = 3;
        int result = articulo.getId_Categoria();
        assertEquals(expResult, result);
    }

    @Test
    public void getImagen() {
        Articulo articulo = new Articulo(1, 10);
        Imagen imagen = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        articulo.setImagen(imagen);
        Imagen result = articulo.getImagen();
        assertEquals(imagen, result);
    }

    @Test
    public void getImagenBitmap() throws IOException {
        Articulo articulo = new Articulo(1, 10);
        Imagen imagen = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        imagen.cargarImagen();
        articulo.setImagen(imagen);
        Bitmap result = articulo.getImagenBitmap();
        assertEquals(imagen.getBitmap(), result);
    }

    @Test
    public void setImagen() {
        Articulo articulo = new Articulo(1, 10);
        Imagen imagen = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        articulo.setImagen(imagen);
        Imagen result = articulo.getImagen();
        assertEquals(imagen, result);
    }
}