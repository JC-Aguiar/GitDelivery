package br.com.ppware.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MensagemUtil {

    public static int tab = 4;

    public static String margem(String textoBase, String textoNovo) {
        String espacos = "";
        for(int i = 0; i < textoBase.length()+tab; i++) {
            espacos += " ";
        }
        return textoBase + espacos + textoNovo;
    }

    public static String margem(String textoBase, String textoNovo, int distanciaChar) {
        String espacos = "";
        for(int i = 0; i < textoBase.length()+distanciaChar; i++) {
            espacos += " ";
        }
        return textoBase + espacos + textoNovo;
    }

    public static List<String> colunas(Map<String, String> textos) {
        return colunas(textos, " ");
    }

    public static List<String> colunas(Map<String, String> textos, String separador) {
        //Multimap para mapear: texto; quantidade de espaços necessários;
        //Obtendo texto com maior tamanho
        int maiorTamanho = 0;
        for(String textoBase : textos.keySet())
            maiorTamanho = Math.max(textoBase.length(), maiorTamanho);
        //Identificando diferença entre textos, ajusta e retorna as mensagens numa lista
        int finalMaiorTamanho = maiorTamanho;
        return textos.keySet()
            .stream()
            .map(textoBase -> {
                final int diferenca = finalMaiorTamanho - textoBase.length() + tab;
                String espacos = "";
                for(int i = 0; i < diferenca; i++) espacos += separador;
                return textoBase + espacos + textos.get(textoBase);
            }).collect(Collectors.toList());
    }


//        final String espacosFinal = espacos;
//        return textos.keys()
//            .stream()
//            .map(k -> {
//                final Stri
//                return k + espacosFinal + textos.get(k);
//            })
//            .collect(Collectors.toList());

}
