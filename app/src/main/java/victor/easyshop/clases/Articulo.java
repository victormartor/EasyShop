package victor.easyshop.clases;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Clase que representa la existencia de un Artículo
 * @author Víctor Martín Torres
 */
public class Articulo
{
    private int _iId;
    private String _sNombre;
    private double _dPVP;
    private boolean _bTalla_Es_Numero;
    private int _iId_Marca;
    private int _iId_Categoria;

    private Imagen _imagen;

    /**
     * Constructor para obtener lo minimo para la lista de artículos
     * @param iId el Id del artículo
     * @param dPVP el precio del artículo
     */
    public Articulo(int iId, double dPVP)
    {
        _iId = iId;
        _dPVP = dPVP;
    }

    /**
     * Constructor para obtener lo minimo para la lista de combinaciones
     * @param iId el Id del artículo
     * @param iId_Marca el Id de la marca
     * @param iId_Categoria el Id de la Categoría
     */
    public Articulo(int iId, int iId_Marca, int iId_Categoria)
    {
        _iId = iId;
        _iId_Marca = iId_Marca;
        _iId_Categoria = iId_Categoria;
    }

    /**
     * Constructor completo
     * @param iId el Id del artículo
     * @param sNombre el nombre del artículo
     * @param dPVP el precio del artículo
     * @param bTalla_Es_Numero indica si las tallas son números
     */
    public Articulo(int iId, String sNombre, double dPVP, boolean bTalla_Es_Numero)
    {
        _iId = iId;
        _sNombre = sNombre;
        _dPVP = dPVP;
        _bTalla_Es_Numero = bTalla_Es_Numero;
    }


    //GET
    public int getId() {return _iId; }
    public String getNombre() { return _sNombre; }
    public double getPVP() { return _dPVP; }
    public boolean getTalla_Es_Numero() { return _bTalla_Es_Numero; }
    public int getId_Marca() {return _iId_Marca;}
    public int getId_Categoria() {return _iId_Categoria;}

    public Imagen getImagen() { return _imagen; }

    /**
     * devuelve un objeto Bitmap de la imagen característica del artículo
     * @return
     */
    public Bitmap getImagenBitmap(){return _imagen.getBitmap();}

    //SET
    public void setImagen(Imagen imagen){_imagen = imagen;}
}
