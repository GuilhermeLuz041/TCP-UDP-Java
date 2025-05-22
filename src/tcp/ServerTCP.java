package tcp;

import java.io.*;
import java.net.*;

public class ServerTCP {
    public static void main(String[] args) {
        int PORT = 8000;

        try (ServerSocket servidor = new ServerSocket(PORT)) {
            System.out.println("Servidor rodando na porta " + PORT);

            while (true) {
                Socket client = servidor.accept();
                System.out.println("Novo cliente conectado: " + client.getInetAddress());

                Thread thread = new Thread(() -> {
                    try (
                        BufferedReader entrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    ) {
                        String mensagem;
                        while ((mensagem = entrada.readLine()) != null) {
                            System.out.println("Recebido do cliente: " + mensagem);
                        }
                    } catch (IOException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } finally {
                        try {
                            client.close();
                            System.out.println("Cliente desconectado.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}