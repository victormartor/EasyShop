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
            marca.setImagen(imagen);

            aMarcas.add(marca);
            sRespuesta = socketStream.recibeMensaje();
        }

        socketStream.close();
        return aMarcas;
    }

    public static Marca getMarca(Integer iId_Marca, String sIP_Servidor, String sPuerto) throws IOException {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("marca");
        socketStream.enviaMensaje(iId_Marca.toString());
        sRespuesta = socketStream.recibeMensaje();

        String[] sMarca = sRespuesta.split(":");
        Marca marca = new Marca(Integer.parseInt(sMarca[0]),sMarca[1]);

        sRespuesta = socketStream.recibeMensaje();
        Imagen imagen = new Imagen("http://"+sIP_Servidor+"/EasyShop/Imagenes/"+sRespuesta);
        marca.setImagen(imagen);

        socketStream.close();
        return marca;
    }

    public static ArrayList<Categoria> getCategorias(Integer iId_Marca, String sIP_Servidor, String sPuerto) throws IOException {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("categorias");
        socketStream.enviaMensaje(iId_Marca.toString());

        ArrayList<Categoria> aCategorias = new ArrayList<>();
        sRespuesta = socketStream.recibeMensaje();
        while(!sRespuesta.equals("FinCategorias")){
            String[] sCategoria = sRespuesta.split(":");
            Categoria categoria = new Categoria(Integer.parseInt(sCategoria[0]), sCategoria[1],
                    Integer.parseInt(sCategoria[3]));

            sRespuesta = socketStream.recibeMensaje();
            Imagen imagen = new Imagen("http://"+sIP_Servidor+"/EasyShop/Imagenes/"+sRespuesta);
            categoria.setImagen(imagen);

            aCategorias.add(categoria);
            sRespuesta = socketStream.recibeMensaje();
        }

        socketStream.close();
        return aCategorias;
    }

    public static ArrayList<Articulo> getArticulos(Integer iId_Categoria, String sIP_Servidor, String sPuerto) throws IOException {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("articulos");
        socketStream.enviaMensaje(iId_Categoria.toString());

        ArrayList<Articulo> aArticulos = new ArrayList<>();
        sRespuesta = socketStream.recibeMensaje();
        while(!sRespuesta.equals("FinArticulos")){
            String[] sArticulo = sRespuesta.split(":");
            Articulo articulo = new Articulo(Integer.parseInt(sArticulo[0]),Double.parseDouble(sArticulo[3]));

            sRespuesta = socketStream.recibeMensaje();
            Imagen imagen = new Imagen("http://"+sIP_Servidor+"/EasyShop/Imagenes/"+sRespuesta);
            articulo.setImagen(imagen);

            aArticulos.add(articulo);
            sRespuesta = socketStream.recibeMensaje();
        }

        socketStream.close();
        return aArticulos;
    }

    public static Articulo getUnArticulo(Integer iId_Articulo, String sIP_Servidor, String sPuerto) throws IOException {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("articulo");
        socketStream.enviaMensaje(iId_Articulo.toString());

        sRespuesta = socketStream.recibeMensaje();

        String[] sArticulo = sRespuesta.split(":");
        Articulo articulo = new Articulo(
                Integer.parseInt(sArticulo[0]),
                sArticulo[1],
                Double.parseDouble(sArticulo[3]),
                Boolean.parseBoolean(sArticulo[4]));

        socketStream.close();
        return articulo;
    }

    public static ArrayList<Articulo_Color> getColoresArticulo(Integer iId_Articulo, String sIP_Servidor, String sPuerto) throws IOException {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("colores");
        socketStream.enviaMensaje(iId_Articulo.toString());

        ArrayList<Articulo_Color> aColores = new ArrayList<>();
        sRespuesta = socketStream.recibeMensaje();
        while(!sRespuesta.equals("FinColores")){
            String[] sColor = sRespuesta.split(":");

            int id = Integer.parseInt(sColor[0]);
            String nombre = sColor[1];
            ArrayList<Imagen> aImagenes = new ArrayList<>();

            sRespuesta = socketStream.recibeMensaje();
            while(!sRespuesta.equals("FinImagenes")){
                Imagen imagen = new Imagen("http://"+sIP_Servidor+"/EasyShop/Imagenes/"+sRespuesta);
                aImagenes.add(imagen);
                sRespuesta = socketStream.recibeMensaje();
            }
            Articulo_Color color = new Articulo_Color(id, nombre, aImagenes);

            aColores.add(color);
            sRespuesta = socketStream.recibeMensaje();
        }

        socketStream.close();
        return aColores;
    }

    public static ArrayList<Articulo_Talla> getTallasArticulo(Integer iId_Articulo, String sIP_Servidor, String sPuerto) throws IOException {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("tallas");
        socketStream.enviaMensaje(iId_Articulo.toString());

        ArrayList<Articulo_Talla> aTallas = new ArrayList<>();
        sRespuesta = socketStream.recibeMensaje();
        while(!sRespuesta.equals("FinTallas")){
            String[] sTalla = sRespuesta.split(":");
            Articulo_Talla articulo_talla = new Articulo_Talla(Integer.parseInt(sTalla[0]), sTalla[1]);
            aTallas.add(articulo_talla);
            sRespuesta = socketStream.recibeMensaje();
        }
        socketStream.close();
        return aTallas;
    }

    public static ArrayList<Articulo> getCombinacionesArticulo(Integer iId_Articulo, String sIP_Servidor, String sPuerto) throws IOException {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("combinaciones");
        socketStream.enviaMensaje(iId_Articulo.toString());

        ArrayList<Articulo> aComb = new ArrayList<>();
        sRespuesta = socketStream.recibeMensaje();
        while(!sRespuesta.equals("FinComb")){
            String[] sArticulo = sRespuesta.split(":");
            Articulo articulo = new Articulo(Integer.parseInt(sArticulo[0]),Integer.parseInt(sArticulo[5]),
                    Integer.parseInt(sArticulo[6]));

            sRespuesta = socketStream.recibeMensaje();
            Imagen imagen = new Imagen("http://"+sIP_Servidor+"/EasyShop/Imagenes/"+sRespuesta);
            articulo.setImagen(imagen);

            aComb.add(articulo);
            sRespuesta = socketStream.recibeMensaje();;
        }
        socketStream.close();
        System.out.println("combinaciones recibidas");
        return aComb;
    }
} // fin de class
