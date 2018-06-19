package victor.easyshop.clases;

import android.graphics.Bitmap;

/**
 * Clase que representa la existencia de una Categoría
 * @author Víctor Martín Torres
 */
public class Categoria
{
    private int _iId;
    private String _sNombre;
    private Imagen _imagen;
    private int _iId_Marca;

    /**
     * Constructor
     * @param iId el Id de la categoría
     * @param sNombre el nombre de la categoría
     * @param iId_Marca el Id de la marca
     */
    public Categoria(int iId, String sNombre, int iId_Marca)
    {
        _iId = iId;
        _sNombre = sNombre;
        _iId_Marca = iId_Marca;
    }

    //GET
    public int getId() {return _iId;}
    public String getNombre() {return _sNombre;}
    public Imagen getImagen() {return _imagen;}
    public int getId_Marca() {return _iId_Marca;}

    /**
     * Devuelve un objeto Bitmap de la imagen de la categoría
     * @return la imagen de la categoría
     */
    public Bitmap getImagenBitmap(){return _imagen.getBitmap();}

    //SET
    public void setImagen(Imagen imagen){_imagen = imagen;}
}