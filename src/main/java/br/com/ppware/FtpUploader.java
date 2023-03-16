package br.com.ppware;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class FtpUploader extends Uploader {

    public FtpUploader(String server, int port, String username, String password) {
        super(server, port, username, password);
    }

    public void upload(File file, String remoteDirectory) throws Exception {
        final FTPClient ftpClient = new FTPClient();
        try (FileInputStream inputStream = new FileInputStream(file)) {
            ftpClient.connect(getServer(), getPort());
            ftpClient.login(getUsername(), getPassword());
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory(remoteDirectory);
            ftpClient.storeFile(file.getName(), inputStream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

}
