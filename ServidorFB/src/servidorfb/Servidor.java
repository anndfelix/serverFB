package servidorfb;

import Exception.DAOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import net.sf.json.JSONObject;
import dao.UsuarioDAO;
import java.util.Date;
import objectosNegocio.Sexo;
import objectosNegocio.Usuario;

public class Servidor {

    public static void main(String[] args) throws DAOException {

        UsuarioDAO udao = new UsuarioDAO();

        try {

            InputStreamReader isr;
            BufferedReader br;

            ServerSocket serverSocket = new ServerSocket(5000);
            Socket socket = serverSocket.accept();
            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);
            String str = br.readLine();
            JSONObject object = JSONObject.fromObject(str);

            System.out.println("Name:" + object.getString("name"));
            System.out.println("password:" + object.getString("password"));
            System.out.println("fecha:" + object.getString("fecha"));
            System.out.println("email:" + object.getString("email"));
            System.out.println("edad:" + object.getString("edad"));
            System.out.println("numero:" + object.getString("numero"));
            System.out.println("sexo:" + object.getString("sexo"));

            Usuario usuario = new Usuario();

            usuario.setNombre(object.getString("name"));
            usuario.setFecha(new Date(100, 12, 12));
            usuario.setContraseña(object.getString("password"));
            usuario.setEmail(object.getString("email"));
            usuario.setNumeroCelular(object.getString("numero"));
            usuario.setSexo(Sexo.valueOf(object.getString("sexo")));
            usuario.setEdad(Integer.parseInt(object.getString("edad")));

            udao.insertar(usuario);

            br.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
