package br.com.ppware.files;

import br.com.ppware.Log;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class FtpUploader extends Uploader {

    public FtpUploader(String server, int port, String username, String password) {
        super(server, port, username, password);
    }

    public boolean upload(File file, String remoteDirectory) {
        final FTPClient ftpClient = new FTPClient();
        Log.info("Tentando conexão FTP...");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            ftpClient.connect(getServer(), getPort());
            ftpClient.login(getUsername(), getPassword());
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory(remoteDirectory);
            ftpClient.storeFile(file.getName(), inputStream);
            ftpClient.logout();
            ftpClient.disconnect();
            return true;

        } catch (IOException e) {
            Log.erro("Erro durante envio SFTP: ", e.getMessage());
            return false;
        }
    }

}
