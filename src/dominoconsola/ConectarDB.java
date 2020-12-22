package dominoconsola;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class ConectarDB {
    
    Principal domino;
    public void ConectarDB(String jugador1,String jugador2, LocalDateTime inicio_juego, LocalDateTime fin_juego, int puntos_jugador1, int puntos_jugador2){
        
        
        try{
            //1- Crear conexion
            Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/partidasdomino?serverTimezone=UTC", "root", "Admin123");
            //2- crear objeto STATEMENT
            Statement miStatement = miConexion.createStatement();
            //3-Ejecutar la instruccion SQL
            String instruccionSQL = "insert into partidas(jugador1, jugador2, inicio_partida, fin_partida, puntos_jugador1,puntos_jugador2) values ('"+jugador1+"','"+jugador2+"','"+inicio_juego+"','"+fin_juego+"','"+puntos_jugador1+"','"+puntos_jugador2+"')";
            miStatement.executeUpdate(instruccionSQL);
            //4-Recorrer el ResultSet
            String Query = "SELECT jugador1, jugador2, inicio_partida, fin_partida, puntos_jugador1, puntos_jugador2,\n" +
                            "if(puntos_jugador1 < puntos_jugador2, concat(\"ganó \", jugador1), concat(\"ganó \", jugador2)) as Resultado\n" +
                            "FROM partidasdomino.partidas order by inicio_partida desc\n" +
                            "LIMIT 10;";
            ResultSet miResultSet = miStatement.executeQuery(Query);  
            
            System.out.println();
            System.out.println("                                                 Juego del Dominó - Resultado de las últimas diez partidas jugadas");
            System.out.println("__________________________________________________________________________________________________________________________________________________________________________________________________________________________");
            System.out.println(String.format("%10s %20s %25s %25s %20s %20s %10s", "\nJugador1","Jugador2", "Inicio Partida","Fin Partida","Puntaje Jug 1","Puntaje Jug 2","Ganador"));
            System.out.println("__________________________________________________________________________________________________________________________________________________________________________________________________________________________");

            while(miResultSet.next()){
                System.out.println(String.format("%10s %20s %25s %25s %10s %20s %20s", miResultSet.getString("jugador1"),miResultSet.getString("jugador2"),miResultSet.getString("inicio_partida"),miResultSet.getString("fin_partida"),miResultSet.getString("puntos_jugador1"),miResultSet.getString("puntos_jugador2"),miResultSet.getString("Resultado")));
            }
            
            miResultSet.close();
            
        }catch(Exception e) {
            System.out.println("No conecta la base de datos");
            e.printStackTrace();
        }
    }
    
    
}
