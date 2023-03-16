package br.com.ppware;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Log {
    private static final String FORMATO = "%s [%s] %s: ";
    private static final String DATA_FORMATO = "HH:mm:ss";

    public enum Level {
        INFO("INFO "),
        WARN("WARN "),
        ERRO("FATAL");
        public final String print;
        Level(String print) {
            this.print = print;
        }
    }

    public static void log(Level level, String texto, Object... values) {
        switch (level) {
            case ERRO:
                erro(texto, values);
                break;
            case WARN:
                warn(texto, values);
                break;
            case INFO:
                info(texto, values);
                break;
        }
    }

    public static void info(String texto, Object... values) {
        final String dateTime = getDateTimeString();
        final String threadName = getThreadName();
        final String mensagem = String.format(FORMATO, dateTime, threadName, Level.INFO.print);
        System.out.println(mensagem + montarMensagem(texto, values));
    }

    public static void warn(String texto, Object... values) {
        final String dateTime = getDateTimeString();
        final String threadName = getThreadName();
        final String mensagem = String.format(FORMATO, dateTime, threadName, Level.WARN.print);
        System.out.println(mensagem + montarMensagem(texto, values));
    }

    public static void erro(String texto, Object... values) {
        final String dateTime = getDateTimeString();
        final String threadName = getThreadName();
        final String mensagem = String.format(FORMATO, dateTime, threadName, Level.ERRO.print);
        System.out.println(mensagem + montarMensagem(texto, values));
    }

    private static String getThreadName() {
        final StringBuilder thread = new StringBuilder(Thread.currentThread().getName());
        while (thread.length() < 20) {
            thread.append(" ");
        }
        return thread.length() > 20 ? thread.substring(0, 20) : thread.toString();
    }

    private static String getDateTimeString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATA_FORMATO));
    }

    private static String montarMensagem(String texto, Object... values) {
        //removendo sintaxe de formatação previamente enviada
        texto = texto.replaceAll("%", "");
        //lopping para cada ALVO identificado: trocar por '%s' e adicionar Objeto no texto formatado
        final int count = StringUtils.countMatches(texto, "{}");
        final String[] valuesArray = Arrays.stream(values)
            .map(String::valueOf)
            .toArray(String[]::new);
        for (int i = 0; i < count; i++) {
            texto = texto.replaceFirst("\\{}", "%s");
            texto = String.format(texto, valuesArray[i]);
        }
        return texto;
    }
}
