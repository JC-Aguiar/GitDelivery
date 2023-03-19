package br.com.ppware.files;

import br.com.ppware.Log;
import com.jcraft.jsch.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SftpUploader extends Uploader {

    final JSch jsch = new JSch();
    Session session = null;
    Channel channel = null;
    ChannelSftp sftpChannel = null;

    public SftpUploader(String server, int port, String username, String password) throws JSchException {
        super(server, port, username, password);
        Log.info("Tentando conexão SFTP...");
        try {
            //Preparando para realizar conexão
            session = jsch.getSession(getUsername(), getServer(), getPort());
            session.setPassword(getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            //Iniciando canal de comunicação SFTP
            channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) channel;

        } catch (JSchException e) {
            Log.erro("Erro durante conexão SFTP: ", e.getMessage());
            throw e;

        } finally {
            if(sftpChannel != null) sftpChannel.exit();
            if(channel != null) channel.disconnect();
            if(session != null) session.disconnect();
        }
    }

    public void validarPermissoes(String diretorioRemoto) throws SftpException {
        final SftpATTRS directoryAttributes = ((ChannelSftp) channel).lstat(diretorioRemoto);
        int permissoes = directoryAttributes.getPermissions();
        // Verifica se as permissões têm pelo menos as configurações mínimas
        if ((permissoes & 0700) != 0700 || (permissoes & 0004) != 0004 || (permissoes & 0001) != 0001) {
            throw new RuntimeException("O diretório não tem as permissões corretas para envio SFTP.");
        } else {
            Log.info("O diretório tem as permissões corretas para envio SFTP.");
        }
    }

    public boolean upload(File file, String diretorioRemoto) {
        String sourceFilePath = file.getAbsolutePath();
        try {
            sftpChannel.put(sourceFilePath, diretorioRemoto);
            return true;
        } catch (SftpException e) {
            Log.erro("Erro durante envio SFTP: ", e.getMessage());
            return false;
        }
    }

}
