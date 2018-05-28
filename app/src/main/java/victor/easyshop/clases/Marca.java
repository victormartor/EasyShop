package victor.easyshop.clases;

import android.graphics.Bitmap;

/**
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase que representa la existencia de una Marca
 */
public class Marca
{
    private int _iId;
    private String _sNombre;
    private Imagen _imagen;

    public Marca(int iId, String sNombre)
    {
        _iId = iId;
        _sNombre = sNombre;
    }

    //GET
    public int getId() {return _iId;}
    public String getNombre() {return _sNombre;}
    public Imagen getImagen() {return _imagen;}

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
