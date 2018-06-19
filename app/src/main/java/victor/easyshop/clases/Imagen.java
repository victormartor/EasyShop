package victor.easyshop.clases;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import victor.easyshop.data.EasyShop;

/**
 * Clase que representa una Imagen.
 * @author Víctor Martín Torres
 */
public class Imagen
{
    private String url;
    private Bitmap bitmap;

    /**
     * Constructor
     * @param url la URL de la imagen en el servidor
     */
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

    /**
     * Función para cargar la imagen de internet desde la URL
     * @throws IOException error al conectar con el servidor
     */
    public void cargarImagen() throws IOException
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
            {throw e;}
        }
    }

    /**
     * Función para cargar la imagen de internet desde la URL en HD
     * @throws IOException error al conectar con el servidor
     */
    public void cargarImagenHD() throws IOException
    {
        if (bitmap == null) {
            try {
                URL imageUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();

                bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            } catch (IOException e) {throw e;}
        }
    }
}
