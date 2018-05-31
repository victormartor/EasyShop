package victor.easyshop.clases;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase que representa un color de un artículo.
 */
public class Articulo_Color
{
    private int _iId;
    private String _sNombre;
    private ArrayList<Imagen> _aImagenes;

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
