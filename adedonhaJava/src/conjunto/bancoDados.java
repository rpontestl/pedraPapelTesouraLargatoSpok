package conjunto;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;

public class bancoDados {
    Connection con;
    Statement statement;

    public bancoDados(){
        try
        {
            // create a mysql database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/competicao","pontes","robozinho123");
            statement = con.createStatement();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void insereResultado(String rodada, String myPlay, String opponentPlay, String result) throws SQLException {
        String query = " insert into javaRounds (Rodada, Me, He, Result)"
                + " values (?, ?, ?, ?)";

        // criando o mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt (1, Integer.parseInt(rodada));
        preparedStmt.setString (2, myPlay);
        preparedStmt.setString (3, opponentPlay);
        preparedStmt.setString  (4, result);
        preparedStmt.execute();
    }
    public void printaBD() throws SQLException{
        ResultSet rs = statement.executeQuery("select * from javaRounds;");

        while(rs.next()){
            System.out.printf("Rodada: %s - Me: %s - Oponente: %s - Resultado: %s",rs.getString("Rodada"),rs.getString("Me"),rs.getString("He"),rs.getString("Result"));
        }
    }
    public void deletaBD() throws SQLException {
        PreparedStatement preparedStmt = con.prepareStatement("truncate javaRounds");
        preparedStmt.execute();
    }

}
