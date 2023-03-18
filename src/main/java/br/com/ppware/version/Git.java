package br.com.ppware.version;

import br.com.ppware.Propriedades;
import br.com.ppware.exception.GitAusenteException;

import java.io.File;

public class Git {

    public static void gitAtivo() throws GitAusenteException {
        final File gitFolder = new File(Propriedades.ORIGEM, ".git");
        if(gitFolder.exists() && gitFolder.isDirectory()) throw new GitAusenteException();
    }

    //branch nome: git rev-parse --abbrev-ref HEAD
    //hash do primeiro commit na branch atual: git rev-list --max-parents=0 HEAD
    //

}
