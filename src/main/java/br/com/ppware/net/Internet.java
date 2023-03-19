package br.com.ppware.net;

import java.net.HttpURLConnection;
import java.net.URL;

public class Internet {
    public static boolean testaConexao(String urlStr) {
        try {
            final URL url = new URL(urlStr);
            final HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("HEAD");
            int resposta = conexao.getResponseCode();
            if (resposta == HttpURLConnection.HTTP_OK) {
                System.out.println("Conexão bem-sucedida.");
                return true;
            }
            System.out.println("Não foi possível estabelecer conexão.");
            return false;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao testar a conexão: " + e.getMessage());
            return false;
        }
    }
}
