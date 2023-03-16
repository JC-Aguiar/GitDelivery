package br.com.ppware;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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
    public static final String DESCRICAO = "Aplicação para automatizar pacotes release.\n" +
        "O objetivo é entregar ao cliente os mesmos artefatos que estarão sendo enviados ao repositório remoto.";
    public static final String LINHA_HORINZONTAL = "******************************";

    //FORMATOS GERAIS --------------------------------------------------------------------------------
    public static final String DATA_FORMATO = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    //CONTROLE DOS PROCESSOS --------------------------------------------------------------------------------
    public static Long intervaloMilissegundos = 900000L; // 15 minutos
    public static final Map<AppParametros, Boolean> PARAMETROS = new HashMap();
    public static long duracaoTotal;

    //ATRIBUTOS DO BANCO  --------------------------------------------------------------------------------
    public static java.util.Date ifxdate = Date.from(Instant.now());
    public static String diretorioOrigem;
    public static String diretorioDestino;
    public static String usuario;
    public static String senha;

}
