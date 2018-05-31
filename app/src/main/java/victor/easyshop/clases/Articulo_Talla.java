package victor.easyshop.clases;

public class Articulo_Talla {
    private int _iId;
    private String _sNombre;

    public Articulo_Talla(int iId, String sNombre){
        _iId = iId;
        _sNombre = sNombre;
    }

    //GET
    public int getId() {return _iId; }
    public String getNombre() { return _sNombre; }
}
