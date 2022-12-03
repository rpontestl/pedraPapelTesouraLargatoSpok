package conjunto;


import sun.security.mscapi.CPublicKey;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Servidor {

    OutputStream output;

    ServerSocket servidor;

    bancoDados banco;
    Scanner s;
    String[] nomes = new String[5];
    public Servidor(int port) throws IOException, SQLException {

        servidor = new ServerSocket(port);
        System.out.println("Porta " + port + " aberta!");
        banco = new bancoDados();

        nomes[0] = "pedra";
        nomes[1] = "spock";
        nomes[2] = "papel";
        nomes[3] = "lagarto";
        nomes[4] = "tesoura";

    }

    public void verificaConexao() throws IOException {
        Socket cliente = servidor.accept();
        System.out.println("Cliente " +
                cliente.getInetAddress().getHostAddress() + " adicionado."
        );
        s = new Scanner(cliente.getInputStream());
        while (s.hasNextLine()) {
            //String jogada = nomes[sendAmove()];

            String msg = s.nextLine();
            if (msg.equals("5")) break;
            System.out.printf("\nMensagem recebida %s - %s\n",msg,nomes[Integer.parseInt(msg)]);
            /*String ans = nomes[Integer.parseInt(msg)];
            Jogar partida = new Jogar(jogada,ans);
            String resultado = partida.compara();
            System.out.printf("\nRodada atual\n-------------------------------------------\nMe: %s Opponent: %s result: %s\n",jogada,ans,resultado);
            System.out.print("Histórico\n");
            banco.printaBD();
            banco.insereResultado(Integer.toString(i),jogada,ans,resultado);
            i=i+1;*/
        }
        cliente.close();

    }
    public void printarHistorico() throws SQLException {
        banco.printaBD();
    }
    public void encerraServer() throws IOException, SQLException {
        banco.deletaBD();
        s.close();
        servidor.close();
    }/*
    public int sendAmove() throws IOException {
        System.out.print("Entrou");
        Random gerador = new Random();
        int nAleatorio = gerador.nextInt(5);
        String msg = Integer.toString(nAleatorio);
        System.out.println(msg);
        ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
        saida.writeUTF(msg);
        return nAleatorio;
    }*/

}
