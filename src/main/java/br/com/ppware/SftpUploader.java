package br.com.ppware;

import com.jcraft.jsch.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.File;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SftpUploader extends Uploader {

    public SftpUploader(String server, int port, String username, String password) {
        super(server, port, username, password);
    }

    public void upload(File file, String remoteDirectory) throws Exception {
        String sourceFilePath = file.getAbsolutePath();
        JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        ChannelSftp sftpChannel = null;
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

        } catch (JSchException | SftpException e) {
            throw new RuntimeException(e);
//            e.printStackTrace();

        } finally {
            if(sftpChannel != null) sftpChannel.exit();
            if(channel != null) channel.disconnect();
            if(session != null) session.disconnect();
        }
    }

}
