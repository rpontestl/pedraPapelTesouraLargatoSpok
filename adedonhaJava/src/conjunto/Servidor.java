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

    public void verificaConexao(int posicao) throws IOException, SQLException {

        Socket cliente = servidor.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        System.out.println("Cliente " + cliente.getInetAddress().getHostAddress() + " adicionado.");

        OutputStream outToServer = cliente.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);


        Random gerador = new Random();
        int nAleatorio = gerador.nextInt(5);


        String jogada = nomes[nAleatorio];
        out.writeBytes(Integer.toString(nAleatorio));
        String msg = br.readLine();
        if (msg.equals("5")){
            System.out.print("O oponente desistiu do jogo\n");
            cliente.close();
            encerraServer();
            return;
        }

        String ans = nomes[Integer.parseInt(msg)];
        Jogar partida = new Jogar(jogada,ans);
        String resultado = partida.compara();
        System.out.printf("\nRodada atual\n-------------------------------------------\nMe: %s Opponent: %s result: %s\n",jogada,ans,resultado);
        System.out.print("Histórico\n");
        banco.printaBD();
        banco.insereResultado(Integer.toString(posicao),jogada,ans,resultado);

        cliente.close();
    }
    public void printarHistorico() throws SQLException {
        banco.printaBD();
    }
    public void encerraServer() throws IOException, SQLException {
        banco.deletaBD();
        s.close();
        servidor.close();
    }
}
