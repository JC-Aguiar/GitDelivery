package br.com.ppware;

import br.com.ppware.files.SftpUploader;

import java.io.File;
import java.util.Arrays;

import static br.com.ppware.Propriedades.PARAMETROS;

/**
 * Hello world!
 *
 */
public class App {

    //Operações a serem executadas imediatamente ao iniciar a classe
    static {
        System.out.println(Propriedades.TITULO_GRANDE);
        Log.info(Propriedades.LINHA_HORINZONTAL);
        Log.info(Propriedades.DESCRICAO);
        Arrays.stream(AppParametros.values()).forEach(p -> PARAMETROS.put(p, null));
    }

    public static void main( String[] args ) throws Exception {
        enviar();
    }

    public static void enviar() throws Exception {
        Log.info( "Iniciando novo SFTP Uploader" );
        Log.info( "Host para envio: " + Propriedades.getHost());
        Log.info( "Diretório de destino: " + Propriedades.DESTINO);
        Log.info( "Total de {} arquivos para envio:", Propriedades.ARQUIVOS.size());
        Propriedades.getArquivosPath().forEach(Log::info);
        //Realizando conexão
        final SftpUploader sftp = new SftpUploader(
            Propriedades.SERVIDOR,
            Propriedades.PORTA,
            Propriedades.USUARIO,
            Propriedades.SENHA
        );
        final Long sucessos = Propriedades.getArquivosPath()
            .stream()
            .filter(path -> sftp.upload(new File(path), Propriedades.DESTINO))
            .count();
        //Sucesso
        Log.info( "Total de {} enviados com sucesso", sucessos);
        Log.info( "SFTP Uploader finalizado" );
    }
}
