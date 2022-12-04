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
        int i = 1;
        int nVitoria = 0 ,nDerrota = 0;
        String result ="";
        do{
            System.out.print("Aguardando jogada de oponente...\n");
            result = servidor.verificaConexao(i);
            i+=1;
            if(result.equals("Vitoria")) nVitoria+=1;
            if(result.equals("Derrota")) nDerrota+=1;
            System.out.printf("\nPlacar: %s X %s\n",nVitoria,nDerrota);
        }while(i<=15);

        System.out.print("\nFim de jogo\n");
        if (nVitoria < nDerrota)
            System.out.print("Python player win\n");
        else if(nVitoria == nDerrota)
            System.out.print("Empate\n");
        else
            System.out.print("Java player win");

        servidor.encerraServer();
        System.out.println("Jogo Concluído!");
    }
}
