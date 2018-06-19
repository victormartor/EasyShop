package victor.easyshop.clases;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Clase que representa un color de un artículo.
 * @author Víctor Martín Torres
 */
public class Articulo_Color
{
    private int _iId;
    private String _sNombre;
    private ArrayList<Imagen> _aImagenes;

    /**
     * Constructor
     * @param iId el Id del color
     * @param sNombre el nombre del color
     * @param aImagenes las imágenes relacionadas con ese color
     */
    public Articulo_Color(int iId, String sNombre, ArrayList<Imagen> aImagenes)
    {
        _iId = iId;
        _sNombre = sNombre;
        _aImagenes = aImagenes;
    }

    //GET
    public int getId() {return _iId;}
    public String getNombre() {return _sNombre;}
    public ArrayList<Imagen> getImagenes() {return _aImagenes;}
}
