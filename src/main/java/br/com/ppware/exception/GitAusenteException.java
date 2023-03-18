package br.com.ppware.exception;

public class GitAusenteException extends Exception {

    public static final String MENSAGEM = "Não existe versionamento Git no diretório informado";

    public GitAusenteException() {
        super(MENSAGEM);
    }

}
