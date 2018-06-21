package victor.easyshop.clases;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MarcaTest {

    @Test
    public void getId() {
        Marca instance = new Marca(1, "ZARA");
        assertEquals(1, instance.getId());
    }

    @Test
    public void getNombre() {
        Marca instance = new Marca(1, "ZARA");
        assertEquals("ZARA", instance.getNombre());
    }

    @Test
    public void getImagen() {
        Marca instance = new Marca(1, "ZARA");
        Imagen imagen = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        instance.setImagen(imagen);
        assertEquals(imagen, instance.getImagen());
    }

    @Test
    public void getImagenBitmap() throws IOException {
        Marca instance = new Marca(1, "ZARA");
        Imagen imagen = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        imagen.cargarImagen();
        instance.setImagen(imagen);
        assertEquals(imagen.getBitmap(), instance.getImagenBitmap());
    }

    @Test
    public void setImagen() {
        Marca instance = new Marca(1, "ZARA");
        Imagen imagen = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        instance.setImagen(imagen);
        assertEquals(imagen, instance.getImagen());
    }
}