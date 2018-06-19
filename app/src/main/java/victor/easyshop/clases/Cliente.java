package victor.easyshop.clases;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase que se encarga de conectar con el servidor e interactuar con él
 * @author Víctor Martín Torres
 */
public class Cliente
{
    /**
     * Comprobar la conexión con el servidor
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return Devuelve "conectado" si la conexión es correcta
     * @throws IOException error al conectar
     */
    public static String conectar(String sIP_Servidor, String sPuerto) throws IOException
    {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("conectar");
        sRespuesta = socketStream.recibeMensaje();
        socketStream.close();
        return sRespuesta;
    }

    /**
     * Obtener la lista de todas las marcas
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return devuelve una lista con todas las marcas
     * @throws IOException error al conectar
     */
    public static ArrayList<Marca> getMarcas(String sIP_Servidor, String sPuerto) throws IOException
    {
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

    /**
     * Obtener los datos de una marca
     * @param iId_Marca el Id de la marca
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return Objeto de tipo marca
     * @throws IOException error al conectar
     */
    public static Marca getMarca(Integer iId_Marca, String sIP_Servidor, String sPuerto)
            throws IOException
    {
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

    /**
     * Obtener una lista de todas las categorías de una marca
     * @param iId_Marca el Id de la marca
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return lista de todas las categorías de una marca
     * @throws IOException error al conectar
     */
    public static ArrayList<Categoria> getCategorias(Integer iId_Marca, String sIP_Servidor,
                                                     String sPuerto) throws IOException
    {
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

    /**
     * Obtener todos los artículos de una categoría
     * @param iId_Categoria el Id de la categoría
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return lista de todos los artículos de una categoría
     * @throws IOException error al conectar
     */
    public static ArrayList<Articulo> getArticulos(Integer iId_Categoria, String sIP_Servidor,
                                                   String sPuerto) throws IOException
    {
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

    /**
     * Obtener los datos de un artículo
     * @param iId_Articulo el Id del artículo
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return un objeto de tipo Articulo
     * @throws IOException error al conectar
     */
    public static Articulo getUnArticulo(Integer iId_Articulo, String sIP_Servidor, String sPuerto)
            throws IOException
    {
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

    /**
     * Obtener todos los colores asociados a un artículo
     * @param iId_Articulo el Id del artículo
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return lista de colores asociados
     * @throws IOException error al conectar
     */
    public static ArrayList<Articulo_Color> getColoresArticulo(Integer iId_Articulo,
                                                               String sIP_Servidor, String sPuerto)
            throws IOException
    {
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

    /**
     * Obtener todas las tallas asociadas a un artículo
     * @param iId_Articulo el Id del artículo
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return lista de tallas asociadas
     * @throws IOException error al conectar
     */
    public static ArrayList<Articulo_Talla> getTallasArticulo(Integer iId_Articulo,
                                                              String sIP_Servidor, String sPuerto)
            throws IOException
    {
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

    /**
     * Obtener todos los artículos que combnan con un artículo
     * @param iId_Articulo el Id del artículo
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return lista de combinaciones
     * @throws IOException error al conectar
     */
    public static ArrayList<Articulo> getCombinacionesArticulo(Integer iId_Articulo,
                                                               String sIP_Servidor, String sPuerto)
            throws IOException
    {
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
        return aComb;
    }

    /**
     * Enviar ticket de compra
     * @param carrito carrito con todos los artículos elegidos
     * @param sIP_Servidor la IP del servidor
     * @param sPuerto el puerto
     * @return el número del cliente
     * @throws IOException error al conectar
     */
    public static Integer enviaCompra(Carrito carrito, String sIP_Servidor, String sPuerto)
            throws IOException
    {
        String sRespuesta = null;
        int iPuerto = Integer.parseInt(sPuerto);
        SocketStream socketStream = new SocketStream(sIP_Servidor, iPuerto);
        socketStream.setSoTimeout(5000);
        socketStream.enviaMensaje("pedido");

        String mensaje = "";;

        for(Carrito.ArticuloCarrito articulo : carrito.getArticulos()){
            mensaje += articulo.getId_Articulo()+":"+articulo.getId_Color()+":"+articulo.getId_Talla()+"\n";
        }
        mensaje+= "FinTicket";
        System.out.println(mensaje);
        socketStream.enviaMensaje(mensaje);
        int numCliente = Integer.parseInt(socketStream.recibeMensaje());

        socketStream.close();
        return numCliente;
    }
} // fin de class
