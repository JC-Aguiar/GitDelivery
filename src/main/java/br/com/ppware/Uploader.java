package br.com.ppware;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.File;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class Uploader {
    String server;
    int port;
    String username;
    String password;

    public abstract void upload(File file, String remoteDirectory) throws Exception;
}
