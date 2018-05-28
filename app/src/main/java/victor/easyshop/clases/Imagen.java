package victor.easyshop.clases;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import victor.easyshop.data.EasyShop;

/**
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase que representa una Imagen.
 */
public class Imagen
{
    private String url;
    private Bitmap bitmap;

    public Imagen(String url)
    {
        this.url = url;
    }

    //GET
    public String getUrl() {return url;}
    public Bitmap getBitmap() {return bitmap;}

    //SET
    public void setUrl(String url) {
        this.url = url;
    }
    public void setBitmap(Bitmap bitmap) {this.bitmap = bitmap;}

    //Función para cargar la imagen de internet desde la URL
    public void cargarImagen()
    {
        if (bitmap == null) {
            try {
                URL imageUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2; // el factor de escala a minimizar la imagen, siempre es potencia de 2

                bitmap = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);
            } catch (IOException e)
            {e.printStackTrace();}
        }
    }

    //Función para cargar la imagen de internet desde la URL en HD
    public void cargarImagenHD()
    {
        if (bitmap == null) {
            try {
                URL imageUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();

                bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}
