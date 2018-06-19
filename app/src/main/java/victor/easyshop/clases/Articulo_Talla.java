package victor.easyshop.clases;

/**
 * Clase que almacena una talla relacionada con un artículo
 * @author Víctor Martín Torres
 */
public class Articulo_Talla
{
    private int _iId;
    private String _sNombre;

    /**
     * Constructor
     * @param iId el Id del artículo
     * @param sNombre el nombre de la Talla
     */
    public Articulo_Talla(int iId, String sNombre)
    {
        _iId = iId;
        _sNombre = sNombre;
    }

    //GET
    public int getId() {return _iId; }
    public String getNombre() { return _sNombre; }
}
