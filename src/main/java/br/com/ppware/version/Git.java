package br.com.ppware.version;

import br.com.ppware.Propriedades;
import br.com.ppware.exception.GitAusenteException;
import br.com.ppware.net.Internet;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Git {

    private static List<String> executar(String command, File directory) throws IOException, InterruptedException {
        final Process process = Runtime.getRuntime().exec(command, null, directory);
        process.waitFor();
        final List<String> resultado = new ArrayList<>();
        byte[] buffer = new byte[1024];
        int bytesRead = process.getInputStream().read(buffer);
        while (bytesRead != -1) {
            resultado.add(new String(buffer, 0, bytesRead).trim());
            bytesRead = process.getInputStream().read(buffer);
        }
        if (process.exitValue() != 0) {
            throw new IOException(process.getErrorStream().toString().trim());
        }
        return resultado;
    }

    public static void validaGitAtivo() throws GitAusenteException {
        final File gitFolder = new File(Propriedades.origem, ".git");
        if(gitFolder.exists() && gitFolder.isDirectory()) throw new GitAusenteException();
    }

    public static Map<String, String> validaGitRemotes() throws IOException, InterruptedException {
        final List<String> resultado = executar("git remote -v", Propriedades.origem);
        final Map<String, String> remotes = new HashMap<>();
        resultado.forEach(r -> {
            final String[] opcoes = r.split(" ");
            if(opcoes.length <= 1) return;
            if(Internet.testaConexao(opcoes[1]))
                remotes.put(opcoes[0], opcoes[1]);
        });
        if(remotes.isEmpty())
            throw new RuntimeException("Não foi possível se conectar em nenhum dos repositórios remotos.");
        return remotes;
    }

    public static Map<String, String> validaGitStatus() throws IOException, InterruptedException {
        final List<String> resultado = executar("git status -uall --porcelain", Propriedades.origem);
        final Map<String, String> status = new HashMap<>();
        resultado.forEach(linha -> {
            final String sinal = linha.substring(0, 1);
            final String arquivo = linha.substring(2, linha.length()-1);
            if(sinal.equals("A ") || sinal.equals(" M") || sinal.equals("??"))
                status.put(sinal, arquivo);
        });
        return status;
    }

    public static void validaGitPull() throws IOException, InterruptedException {
        final String comandoGit = "git pull " + Propriedades.gitRemote;
        final List<String> resultado = executar(comandoGit, Propriedades.origem);
        resultado.stream()
            .filter(linha -> linha.contains("CONFLICT"))
            .findAny()
            .ifPresent(linha -> {
                try {
                    executar("git merge --abort", Propriedades.origem);
                } catch (Exception e) {
                    throw new RuntimeException("Não foi possível desfazer o merge: Realize operação manualmente");
                }
                throw new RuntimeException("Conflitos encontrados");
            });
    }

}
