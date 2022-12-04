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

    public void verificaConexao(int posicao) throws IOException {

        Socket cliente = servidor.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        System.out.println("Cliente " + cliente.getInetAddress().getHostAddress() + " adicionado.");

        OutputStream outToServer = cliente.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);


        Random gerador = new Random();
        int nAleatorio = gerador.nextInt(5);
        out.writeUTF(Integer.toString(nAleatorio));

        String jogada = nomes[nAleatorio];

        String msg = br.readLine();
        if (msg.equals("5")) return;
        System.out.printf("\nMensagem recebida %s - %s\n",msg,nomes[Integer.parseInt(msg)]);
        /*String ans = nomes[Integer.parseInt(msg)];
        Jogar partida = new Jogar(jogada,ans);
        String resultado = partida.compara();
        System.out.printf("\nRodada atual\n-------------------------------------------\nMe: %s Opponent: %s result: %s\n",jogada,ans,resultado);
        System.out.print("Histórico\n");
        banco.printaBD();
        banco.insereResultado(posicao,jogada,ans,resultado);
        */

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
