package victor.easyshop.data;

import android.app.Application;

public class EasyShop extends Application {
    private String _sIP_Servidor;

    public String getIP_Servidor() {
        return _sIP_Servidor;
    }

    public void setIP_Servidor(String sIP_Servidor) {
        _sIP_Servidor = sIP_Servidor;
    }
}
