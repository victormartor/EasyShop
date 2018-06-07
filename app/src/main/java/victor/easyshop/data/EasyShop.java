package victor.easyshop.data;

import android.app.Application;

import victor.easyshop.clases.Carrito;
import victor.easyshop.clases.Inactividad;

public class EasyShop extends Application {
    //IP SERVIDOR
    private String _sIP_Servidor;
    public String getIP_Servidor() {
        return _sIP_Servidor;
    }
    public void setIP_Servidor(String sIP_Servidor) {
        _sIP_Servidor = sIP_Servidor;
    }

    //CARRITO
    private Carrito _carrito;
    public Carrito getCarrito() { return _carrito; }
    public void setCarrito(Carrito carrito) {_carrito = carrito;}

    //INACTIVIDAD
    private Inactividad _inactividad;
    public Inactividad getInactividad(){return _inactividad;}
    public void setInactividad(Inactividad inactividad) {_inactividad = inactividad;}
}
