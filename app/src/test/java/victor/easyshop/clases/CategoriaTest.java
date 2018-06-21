package victor.easyshop.clases;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CategoriaTest {

    @Test
    public void getId() {
        Categoria instance = new Categoria(1, "Camisetas hombre", 2);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void getNombre() {
        Categoria instance = new Categoria(1, "Camisetas hombre", 2);
        String expResult = "Camisetas hombre";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    @Test
    public void getImagen() {
        Categoria instance = new Categoria(1, "Camisetas hombre", 2);
        Imagen imagen = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        instance.setImagen(imagen);
        assertEquals(imagen, instance.getImagen());
    }

    @Test
    public void getId_Marca() {
        Categoria instance = new Categoria(1, "Camisetas hombre", 2);
        int expResult = 2;
        int result = instance.getId_Marca();
        assertEquals(expResult, result);
    }

    @Test
    public void getImagenBitmap() throws IOException {
        Categoria instance = new Categoria(1, "Camisetas hombre", 2);
        Imagen imagen = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        imagen.cargarImagen();
        instance.setImagen(imagen);
        assertEquals(imagen.getBitmap(), instance.getImagenBitmap());
    }

    @Test
    public void setImagen() {
        Categoria instance = new Categoria(1, "Camisetas hombre", 2);
        Imagen imagen = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        instance.setImagen(imagen);
        assertEquals(imagen, instance.getImagen());
    }
}