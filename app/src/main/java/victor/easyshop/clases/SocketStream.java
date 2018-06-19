package victor.easyshop.clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Clase que se encarga de realizar la conexión con la aplicación de PC
 * @author Víctor Martín Torres
 */
public class SocketStream extends Socket
{
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;

    /**
     * Constructor encargado de crear el socket y establecer los flujos de entrada y salida
     * @param maquinaAceptadora IP del Servidor
     * @param puertoAceptador puerto
     * @throws SocketException Error al crear el socket
     * @throws IOException error al establecer los flujos
     */
    SocketStream(String maquinaAceptadora,int puertoAceptador ) throws SocketException,IOException
    {
        socket = new Socket();
        socket.connect(new InetSocketAddress(maquinaAceptadora, puertoAceptador), 5000);
        establecerFlujos( );
    }

    /**
     * Constructor que copia un socket ya creado
     * @param socket el socket ya creado
     * @throws IOException error al establecer los flujos
     */
    SocketStream(Socket socket) throws IOException
    {
        this.socket = socket;
        establecerFlujos( );
    }

    /**
     * Establece los flujos de entrada y salida
     * @throws IOException error al establecer los flujos
     */
    private void establecerFlujos( ) throws IOException
    {
        // obtiene un flujo de salida para leer del socket de datos
        InputStream flujoEntrada = socket.getInputStream();
        entrada = new BufferedReader(new InputStreamReader(flujoEntrada));
        OutputStream flujoSalida = socket.getOutputStream();
        // crea un objeto PrintWriter para salida en modo carácter
        salida = new PrintWriter(new OutputStreamWriter(flujoSalida));
    }

    /**
     * Envía un mensaje al servidor de tipo String
     * @param mensaje el mensaje al enviar
     * @throws IOException error al conectar con el servidor
     */
    public void enviaMensaje(String mensaje) throws IOException
    {
        salida.println(mensaje);
        // La subsiguiente llamada al método flush es necesaria para que
        // los datos se escriban en el flujo de datos del socket antes
        // de que se cierre el socket.
        salida.flush();
    } // fin de enviaMensaje

    /**
     * Recibe un mensaje del servidor de tipo String
     * @return el mensaje recibido
     * @throws IOException error al conectar con el servidor
     */
    public String recibeMensaje( )  throws IOException
    {
        // lee una línea del flujo de datos
        String mensaje = entrada.readLine();
        return mensaje;
    } // fin de recibeMensaje

} //fin de class
