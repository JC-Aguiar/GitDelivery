package br.com.ppware;

import java.io.File;
import java.sql.Date;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Propriedades {

    //SOBRE A APLICAÇÃO --------------------------------------------------------------------------------
    public static final String TITULO = "GIT RELEASER";
    public static final String TITULO_GRANDE = "\n" +
        "   ______   _  _  _______           __                                    \n" +
        " .' ___  | (_)/ ||_   __ \\         [  |                                   \n" +
        "/ .'   \\_| __`| |-'| |__) |  .---.  | | .---.  ,--.  .--.  .---.  _ .--.  \n" +
        "| |   ____[  || |  |  __ /  / /__\\\\ | |/ /__\\\\`'_\\ :( (`\\]/ /__\\\\[ `/'`\\] \n" +
        "\\ `.___]  || || |,_| |  \\ \\_| \\__., | || \\__.,// | |,`'.'.| \\__., | |     \n" +
        " `._____.'[___]__/____| |___|'.__.'[___]'.__.'\\'-;__[\\__) )'.__.'[___]    ";
    public static final String DESCRICAO = "Aplicação para automatizar pacotes release. " +
        "O objetivo é entregar ao cliente os mesmos artefatos que estarão sendo enviados ao repositório remoto.";
    public static final String LINHA_HORINZONTAL = "******************************";

    //FORMATOS GERAIS --------------------------------------------------------------------------------
    public static final String DATA_FORMATO = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";


    //CONTROLE DOS PROCESSOS --------------------------------------------------------------------------------
    public static Long intervaloMilissegundos = 900000L; // 15 minutos
    public static final Map<AppParametros, String> PARAMETROS = new HashMap();
    public static long duracaoTotal;

    //ATRIBUTOS GERAIS  --------------------------------------------------------------------------------
    public static java.util.Date ifxdate = Date.from(Instant.now());
    public static String gitRemote = "";
    public static String gitUrl = "";

    //ATRIBUTOS DE TRANSFERÊNCIA --------------------------------------------------------------------------------
    public static final List<File> ARQUIVOS = Arrays.asList(new File("teste.txt"));
    public static File origem = new File("src/main/resources/");
    public static File destino = new File("/app/rcvry/tmp/Teste");
    public static String servidor = "10.129.164.206";
    public static Integer porta = 22;
    public static String usuario = "rcvry";
    public static String senha = "Ppw@1022";

    //UTILIDADE GERAIS --------------------------------------------------------------------------------
    public static String getHost() {
        return servidor + ":" + porta;
    }

    public static List<String> getArquivosPath() {
        return ARQUIVOS.stream()
            .map(arq -> origem + arq.getName())
            .collect(Collectors.toList());
    }

    public static void setGitRemote(String nome, String url) {
        gitRemote = nome;
        gitUrl = url;
    }

}
