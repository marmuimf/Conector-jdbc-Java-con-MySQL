//CONEXION A BD: java-mySQL
//descargo conector y lo agrego al proyecto, intermediario
package conector;

import java.sql.*; //importo toda la libreria SQL

public class Conector {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/pruebaconector"; //ruta impuesta por el conector, servidor bd+puerto
        String usuario = "pruebaconector2";
        String contrasena = "pruebaconector2";

        try {//imprescindible para el conector jbdc
            Class.forName("com.mysql.cj.jdbc.Driver");//importo la clase correspondiente a la libreria
            Connection miconexion = DriverManager.getConnection(url, usuario, contrasena);

            //Primera consulta
            Statement peticion1 = miconexion.createStatement();
            String cadena = "SELECT * FROM personas";
            ResultSet resultado1 = peticion1.executeQuery(cadena);

            while (resultado1.next()) {//mientras que tenga un siguiente registro
                String nombre = resultado1.getString("nombre");
                String apellidos = resultado1.getString("apellidos");
                String email = resultado1.getString("email");
                String edad = resultado1.getString("edad");
                System.out.println(nombre + " " + apellidos + ", " + email + ", " + edad);
            }
            resultado1.close();
            peticion1.close();

            System.out.println("------------------------------------------");

            // Segunda consulta
            Statement peticion2 = miconexion.createStatement();
            String cadena2 = "SELECT * FROM personas WHERE edad < 89";
            ResultSet resultado2 = peticion2.executeQuery(cadena2);
            while (resultado2.next()) {
                String nombre = resultado2.getString("nombre");
                String apellidos = resultado2.getString("apellidos");
                String email = resultado2.getString("email");
                String edad = resultado2.getString("edad");
                System.out.println(nombre + " " + apellidos + ", " + email + ", " + edad);
            }
            resultado2.close();
            peticion2.close();

            System.out.println("------------------------------------------");

            // tercera consulta
            Statement peticion3 = miconexion.createStatement();
            String cadena3 = "SELECT nombre FROM personas WHERE nombre LIKE 'M%'";
            ResultSet resultado3 = peticion3.executeQuery(cadena3);
            while (resultado3.next()) {
                String nombre = resultado3.getString("nombre");
                System.out.println(nombre);
            }
            resultado3.close();
            peticion3.close();

            miconexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
