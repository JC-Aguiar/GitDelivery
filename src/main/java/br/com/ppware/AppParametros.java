package br.com.ppware;

import br.com.ppware.util.MensagemUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum AppParametros {

    PROTOCOLO("-p", "--protocolo",
          "Informe qual o protocolo de envio (padrão: SFTP)", true),
    ENVIO("-d", "--destino",
             "Informe o destinatário do pacote release no formato: <ip>':'<porta>", true),
    LOGIN("-l", "--login",
          "Informe as credenciais para envio no formato: <usuario>'/'<senha>", true),
    TESTE("-t", "--teste",
          "A aplicação não alterará em nada no banco", false),
    HELP("-h", "--help",
         "Exibe todas as opção e suas descrições", false);

    public final String sigla;
    public final String nome;
    public final String descricao;
    public final boolean argumento;

    AppParametros(String sigla, String nome, String descricao, boolean argumento) {
        this.sigla = sigla;
        this.nome = nome;
        this.descricao = descricao;
        this.argumento = argumento;
    }

    public static boolean compare(AppParametros parametro, String comando) {
        return comando.equals(parametro.sigla) || comando.equals(parametro.nome);
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
