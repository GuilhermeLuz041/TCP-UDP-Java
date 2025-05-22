package tcp;

import java.io.*;
import java.net.*;

public class ClientTCP {
    public static void main(String[] args) {

        String HOST = "localhost";
        int PORT = 8000;

        try (
            Socket socket = new Socket(HOST, PORT);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter saidaServidor = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("Conectado ao servidor. Escreva uma mensagem:");

            String linha;
            while ((linha = teclado.readLine()) != null) {
                saidaServidor.println(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
