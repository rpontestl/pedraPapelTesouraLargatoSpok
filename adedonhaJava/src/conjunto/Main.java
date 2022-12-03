package conjunto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        String port;
        System.out.print("Defina o número da porta: ");
        port = input.nextLine();
        Servidor servidor = new Servidor(Integer.parseInt(port));
        String opcao = "0";
        do{
            System.out.print("Aguardando jogada de oponente...\n");
            servidor.verificaConexao();
            servidor.printarHistorico();
            System.out.print("Sair do jogo tecle [e]: ");
            opcao = input.nextLine();
        }while(!opcao.equals("e"));

        servidor.encerraServer();
        System.out.println("Jogo Concluído!");
    }
}
