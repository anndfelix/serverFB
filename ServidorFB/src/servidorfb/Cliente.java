package servidorfb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import net.sf.json.JSONObject;
import objectosNegocio.Sexo;
import objectosNegocio.Usuario;

public class Cliente {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        InputStreamReader isr;
        BufferedReader br;
        OutputStreamWriter osw;
        BufferedWriter rw;

        try {

            Socket socket = new Socket("localhost", 5000);
            osw = new OutputStreamWriter(socket.getOutputStream());
            rw = new BufferedWriter(osw);

            Usuario usuario = new Usuario("andy", "andy@gmail.com", Sexo.O, "6441714360", "123456a", 20, new Date(100, 12, 12));

            JSONObject obj = JSONObject.fromObject(usuario);

//            Usuario usuario = udao.consultarID(14L);
//            List<String> etiquetas = new ArrayList<>();
//
//            etiquetas.add("#Hola");
//
//            publicacion.setContenido("Hola hola");
//            publicacion.setEtiquetas(etiquetas);
//            publicacion.setUsuarioCreador(usuario);
//            publicacion.setFechaHoraCreacion(new GregorianCalendar(fecha.getYear(), fecha.getMonthValue() - 1, fecha.getDayOfMonth(), fecha.getHour(), fecha.getMinute(), fecha.getSecond()));
//            Usuario usuario = new Usuario("Elizabeth", "eli@gmail.com", Sexo.O, "6441714360", "coco", 20, new Date(121, 10, 13));
//
            obj.put("name", usuario.getNombre());
            obj.put("password", usuario.getContraseña());
            obj.put("fecha", usuario.getFecha());
            obj.put("email", usuario.getEmail());
            obj.put("edad", usuario.getEdad());
            obj.put("numero", usuario.getNumeroCelular());
            obj.put("sexo", usuario.getSexo());

            rw.write(obj.toString() + "\n");
            rw.close();
            socket.close();

        } catch (Exception e) {

        }
    }
}
