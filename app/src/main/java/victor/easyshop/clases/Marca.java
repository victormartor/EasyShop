package victor.easyshop.clases;

import android.graphics.Bitmap;

/**
 * Clase que representa la existencia de una Marca
 * @author Víctor Martín Torres
 */
public class Marca
{
    private int _iId;
    private String _sNombre;
    private Imagen _imagen;

    /**
     * Constructor
     * @param iId el Id de la marca
     * @param sNombre el nombre de la marca
     */
    public Marca(int iId, String sNombre)
    {
        _iId = iId;
        _sNombre = sNombre;
    }

    //GET
    public int getId() {return _iId;}
    public String getNombre() {return _sNombre;}
    public Imagen getImagen() {return _imagen;}

    /**
     * Devuelve un objeto Bitmap de la imagen de la marca
     * @return
     */
    public Bitmap getImagenBitmap(){return _imagen.getBitmap();}

    //SET
    public void setImagen(Imagen imagen){_imagen = imagen;}
}
