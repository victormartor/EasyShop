package victor.easyshop.clases;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase que representa la existencia del Carrito
 */
public class Carrito
{
    //clase para un articulo dentro del carrito
    public class ArticuloCarrito
    {
        private int _iId_Articulo;
        private String _sNombre;
        private double _dPVP;

        private int _iId_Color;
        private String _sColor;

        private int _iId_Talla;
        private String _sTalla;

        private String _sImagenURL;
        private Bitmap _bitmap;

        private int _iId_Marca;
        private String _sMarca;
        private int _iId_Categoria;

        public ArticuloCarrito(int iId_Articulo, String sNombre, double dPVP, int iId_Color, String sColor,
                               int iId_Talla, String sTalla, String sImagenURL,
                               int iId_Marca, String sMarca, int iId_Categoria) {
            _iId_Articulo = iId_Articulo;
            _sNombre = sNombre;
            _dPVP = dPVP;
            _iId_Color = iId_Color;
            _sColor = sColor;
            _iId_Talla = iId_Talla;
            _sTalla = sTalla;
            _sImagenURL = sImagenURL;
            _iId_Marca = iId_Marca;
            _sMarca = sMarca;
            _iId_Categoria = iId_Categoria;
        }

        //GET
        public int getId_Articulo() {return _iId_Articulo;}
        public String getNombre() {return _sNombre;}
        public double getPVP() {return _dPVP;}
        public int getId_Color() {return _iId_Color;}
        public String getColor() {return _sColor;}
        public int getId_Talla() {return _iId_Talla;}
        public String getTalla() {return _sTalla;}
        public String getImagenURL() {return _sImagenURL;}
        public Bitmap getBitmap() {return _bitmap;}

        public int getId_Marca() {return _iId_Marca;}
        public String getMarca() {return _sMarca;}
        public int getId_Categoria() {return _iId_Categoria;}

        //Función para cargar la imagen de internet desde la URL
        public void cargarImagen() throws IOException
        {
            if (_bitmap == null) {
                URL imageUrl = new URL(_sImagenURL);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4; // el factor de escala a minimizar la imagen, siempre es potencia de 2

                _bitmap = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);
            }
        }
    }

    private ArrayList<ArticuloCarrito> _aArticulos;

    public Carrito()
    {
        _aArticulos = new ArrayList<>();
    }

    //GET
    public ArrayList<ArticuloCarrito> getArticulos() { return  _aArticulos; }
    public int getNumArticulos() {return _aArticulos.size();}

    //METODOS

    public boolean vacio() {return _aArticulos.isEmpty();}

    public void insertarArticulo(int iId_Articulo, String sNombre, double dPVP, int iId_Color, String sColor,
                                 int iId_Talla, String sTalla, String sImagenURL,
                                 int iId_Marca, String sMarca, int iId_Categoria)
    {
        ArticuloCarrito articulo = new ArticuloCarrito(
                iId_Articulo, sNombre, dPVP, iId_Color, sColor,
                iId_Talla, sTalla, sImagenURL, iId_Marca, sMarca, iId_Categoria
        );
        _aArticulos.add(articulo);
    }

    public void eliminarArticulo(ArticuloCarrito articulo){
        _aArticulos.remove(articulo);
    }

    public void vaciarCarro()
    {
        if(!_aArticulos.isEmpty())
        {
            _aArticulos.clear();
        }
    }
}

