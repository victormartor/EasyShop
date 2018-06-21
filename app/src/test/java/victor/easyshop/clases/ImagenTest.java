package victor.easyshop.clases;

import android.graphics.Bitmap;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ImagenTest {

    @Test
    public void getUrl() {
        Imagen instance = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        String expResult = "http://localhost/EasyShop/Imagenes/0x0.png";
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void getBitmap() {
        Imagen instance = new Imagen("http://localhost/EasyShop/Imagenes/0x0.png");
        Bitmap expResult = null;
        Bitmap result = instance.getBitmap();
        assertEquals(expResult, result);
    }

    @Test
    public void setUrl() {
        Imagen instance = new Imagen(null);
        instance.setUrl("http://localhost/EasyShop/Imagenes/0x0.png");
        String expResult = "http://localhost/EasyShop/Imagenes/0x0.png";
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }
}