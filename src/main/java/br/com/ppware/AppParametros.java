package br.com.ppware;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum AppParametros {

    PAUSE("-p", "--pause",
          "Informe quantos milissegundos de intervalo terá entre as execuções", true, ""),
    ONE_SHOT("-o", "--one-shot",
             "Executa a aplicação somente uma vez", false, ""),
    TESTE("-t", "--teste",
          "A aplicação não alterará em nada no banco", false, ""),
    DRIVE("-d", "--drive",
          "Informe o drive de conexão Java ao banco", true, ""),
    LOGIN("-l", "--login",
          "Informe as credenciais de conexão ao banco no formato: <usuario>'/'<senha>", true, ""),
    HELP("-h", "--help",
         "Exibe todas as opção e suas descrições", false, "");

    public final String sigla;
    public final String nome;
    public final String descricao;
    public final boolean argumento;
    @Setter
    @Getter
    private String valor;

    AppParametros(String sigla, String nome, String descricao, boolean argumento, String valor) {
        this.sigla = sigla;
        this.nome = nome;
        this.descricao = descricao;
        this.argumento = argumento;
        this.valor = valor;
    }

    public static boolean compare(AppParametros parametro, String comando) {
        return comando.equals(parametro.sigla) || comando.equals(parametro.nome);
    }

    public static void setValor(AppParametros parametro, String valor) {
        parametro.valor = valor;
    }

    public static void exibirParametros() {
        Log.info("Parâmetros opcionais:");
        final Map<String, String> mensagens = new HashMap<>();
        Arrays.stream(AppParametros.values())
            .forEach(p -> {
                final String textoBase = String.format("\t'%s' ou '%s'", p.sigla, p.nome);
                mensagens.put(textoBase, p.descricao);
            });
        MensagemUtil.colunas(mensagens).forEach(Log::info);
    }

}
