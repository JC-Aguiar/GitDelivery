package br.com.ppware.files;

import br.com.ppware.Log;
import com.jcraft.jsch.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.File;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SftpUploader extends Uploader {

    public SftpUploader(String server, int port, String username, String password) {
        super(server, port, username, password);
    }

    public boolean upload(File file, String remoteDirectory) {
        String sourceFilePath = file.getAbsolutePath();
        JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        ChannelSftp sftpChannel = null;
        Log.info("Tentando conexão SFTP...");
        try {
            //Preparando para realizar conexão
            //StrictHostKeyChecking:
            session = jsch.getSession(getUsername(), getServer(), getPort());
            session.setPassword(getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            //Iniciando canal de comunicação SFTP
            channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) channel;
            //Enviando arquivo
            sftpChannel.put(sourceFilePath, remoteDirectory);
            return true;

        } catch (JSchException | SftpException e) {
            Log.erro("Erro durante envio SFTP: ", e.getMessage());
            return false;

        } finally {
            if(sftpChannel != null) sftpChannel.exit();
            if(channel != null) channel.disconnect();
            if(session != null) session.disconnect();
        }
    }

}
