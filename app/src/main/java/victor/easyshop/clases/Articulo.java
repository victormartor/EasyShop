package victor.easyshop.clases;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase que representa la existencia de un Artículo
 */
public class Articulo
{
    private int _iId;
    private String _sNombre;
    private double _dPVP;
    private boolean _bTalla_Es_Numero;

    private Imagen _imagen;

    //Constructor para obtener lo minimo para la lista de artículos
    public Articulo(int iId, double dPVP){
        _iId = iId;
        _dPVP = dPVP;
    }

    //Constructor completo
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

    public Imagen getImagen() { return _imagen; }
    public Bitmap getImagenBitmap(){return _imagen.getBitmap();}

    //SET
    public void setImagen(Imagen imagen){_imagen = imagen;}


    //METODOS
    /*
    public void insertarCategoria(Categoria cat) { categorias.add(cat);}

    public Categoria[] getCategoriasVector()
    {
        int n = categorias.size();
        Categoria[] vector = new Categoria[n];

        for (int i=0; i<n; i++)
        {
            vector[i] = categorias.get(i);
        }

        return  vector;
    }

    @Nullable
    public static Marca getMarcadeLista(Marca[] lista, int id)
    {
        for (Marca item : lista) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    */
}
