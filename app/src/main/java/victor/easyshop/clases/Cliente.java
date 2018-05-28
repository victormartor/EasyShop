package victor.easyshop.clases;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase que se encarga de enviar los datos del pedido a la aplicación para PC
 */
public class Cliente
{
    /*
    public static void conectar(String nombreMaquina, String numPuerto, Carro carro) throws Exception
    {
        int puertoServidor = Integer.parseInt(numPuerto);
        MiSocketStream miSocket = new MiSocketStream(nombreMaquina, puertoServidor);
        String mensaje ="";

        //Columna con la lista de precios
        float total = 0;

        //mensaje += "NUMERO DE CLIENTE:\n";
        mensaje += numCliente+"\n-\n";
        for(Carro.ArticuloCarrito a : carro.getArticulos())
        {
            mensaje +=
                    a.getMarca()+"\n"+
                            a.getNombre()+"\n"+
                            a.getReferencia()+ "-"+a.getColor().hashCode()+"-"+a.getTalla()+"\n"+
                            String.format(Locale.ENGLISH,"%.2f",a.getPrecio())+"\n"+
                            "-\n";

            total += a.getPrecio();
        }

        mensaje += "$\n";
        mensaje += String.format(Locale.ENGLISH,"%.2f",total)+"\n";
        mensaje += "FinTicket\n";
        miSocket.enviaMensaje(mensaje);

        miSocket.close( ); // implica la desconexión
    } // fin
    */

    public static String conectar(String sIP_Servidor, String sPuerto) throws IOException {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("conectar");
        sRespuesta = socketStream.recibeMensaje();
        socketStream.close();
        return sRespuesta;
    }

    public static ArrayList<Marca> getMarcas(String sIP_Servidor, String sPuerto) throws IOException {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("marcas");

        ArrayList<Marca> aMarcas = new ArrayList<>();
        sRespuesta = socketStream.recibeMensaje();
        while(!sRespuesta.equals("FinMarcas")){
            String[] sMarca = sRespuesta.split(":");
            Marca marca = new Marca(Integer.parseInt(sMarca[0]),sMarca[1]);

            sRespuesta = socketStream.recibeMensaje();
            Imagen imagen = new Imagen("http://"+sIP_Servidor+"/EasyShop/Imagenes/"+sRespuesta);
            System.out.println(imagen.getUrl());
            marca.setImagen(imagen);

            aMarcas.add(marca);
            sRespuesta = socketStream.recibeMensaje();
        }

        socketStream.close();
        return aMarcas;
    }
} // fin de class