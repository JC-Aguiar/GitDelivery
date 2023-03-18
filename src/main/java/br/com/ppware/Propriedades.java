package br.com.ppware;

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

    //ATRIBUTOS DO BANCO  --------------------------------------------------------------------------------
    public static java.util.Date ifxdate = Date.from(Instant.now());
    public static final String SERVIDOR = "10.129.164.206";
    public static final Integer PORTA = 22;
    public static final String USUARIO = "rcvry";
    public static final String SENHA = "Ppw@1022";

    //ATRIBUTOS DE TRANSFERÊNCIA --------------------------------------------------------------------------------
    public static final List<String> ARQUIVOS = Arrays.asList("teste.txt");
    public static final String ORIGEM = "src/main/resources/";
    public static final String DESTINO = "/app/rcvry/tmp/Teste";

    //UTILIDADE GERAIS --------------------------------------------------------------------------------
    public static String getHost() {
        return SERVIDOR + ":" + PORTA;
    }

    public static List<String> getArquivosPath() {
        return ARQUIVOS.stream()
            .map(arq -> ORIGEM + arq)
            .collect(Collectors.toList());
    }

}
