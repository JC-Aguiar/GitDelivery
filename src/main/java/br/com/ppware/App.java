package br.com.ppware;

import br.com.ppware.files.SftpUploader;
import br.com.ppware.version.Git;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import static br.com.ppware.Propriedades.PARAMETROS;

/**
 * Hello world!
 *
 */
public class App {

    /**
     * ETAPAS DO PROCESSO:
     * 1) Valida Git local:
     *      1.1) Se o diretório do projeto possui Git iniciado
     *      1.2) Realiza comando `git status -uall --porcelain`
     *      1.3) Tratar a resposta, para obter somente arquivos novos ("A "), modificados (" M") e não rastreados ("??")
     *      1.4) Havendo arquivos, solicita mensagem para novo commit (opcional)
     *      1.5) Não havendo arquivos, segue
     * 2) Valida Git remoto:
     *      2.1) Identifica quais os repositórios remotos Git
     *      2.2) Se é possível conectar a algum dos repositórios
     *      2.3) Se mais de um válido, listar na tela
     *      2.4) Solicitar se quer usar um dos repositórios identificados ou tentar um novo (somente na nova versão)
     * 3) Valida conflitos:
     *      3.1) Realiza comando `git pull`
     *      3.2) Trata a mensagem retornada, buscando por "CONFLICT"
     *          - Se identificado, realiza o comando `git merge --abort` e aborta
     *          - Se não identificado, segue
     * 4) Valida servidor de entrega:
     *      4.2) Se é possível conectar ao servidor de entrega
     *      4.3) Se o diretório informado no servidor de entrega permite criar diretórios e arquivos
     * 5) Coleta dados:
     *      5.1) Nome do branch atual
     *      5.2) Primeiro commit da branch atual
     *      5.3) Exibe todas as tags usadas no projeto, dando ênfase a mais recente
     *      5.4) Solicita nome da nova tag a ser utilizada (interação obrigatória)
     * 6) Executa arquivos:
     *      6.1) Criação da nova pasta release (no GitReleaser) e obtêm seu diretório absoluto
     *      6.2) Comando `git diff --name-only ${primeiroCommitHash} HEAD`
     *      6.3) Coleta de todos os diretórios/arquivos exibidos (diferença entre os commits)
     *      6.4) Armazena cada diretório/arquivo na nova pasta release (no GitReleaser)
     *      6.5) Adiciona o arquivo `entrega.txt`, contendo: data e usuário (Git) da entrega
     * 7) Executa envio ao servidor:
     *      7.1) Caso algum arquivo falhe, apagar a nova pasta release (no GitReleaser) e abortar tudo
     * 8) Finaliza:
     *      8.1) Em caso de sucesso:
     *          - Abre o arquivo contatos.txt (no GitReleaser)
     *          - Exibe os contatos disponíveis
     *          - Solicita qual e-mail deverá ser notificado do sucesso (optional)
     *      8.1) Caso erro:
     *          - Obtêm o log do processo com erro
     *          - Envia o log ao e-mail 'joao.aguair@ppware.com.br', sob assunto "Falha GitReleaser ${data}: ${git-user}"
     * COMANDOS:
     * Branch nome: git rev-parse --abbrev-ref HEAD
     * Hash do primeiro commit na branch atual: git rev-list --max-parents=0 HEAD
     * Comparando commits: git diff --name-only ${primeiroCommitHash} HEAD
     *      Exemplo: git diff --name-only 39975e4c22ab85032f84efef10b5eb66b127c21b HEAD
     *
     * ESTRUTURA INTERNA:
     * Nomenclatura das pastas release: release/projetos/${projeto}/${branch}
     * Nomenclatura das subpastas do projeto: ${data}${hora}_${commit} ou ${data}${hora}_${tag}
     */

    //Operações a serem executadas imediatamente ao iniciar a classe
    static {
        System.out.println(Propriedades.TITULO_GRANDE);
        Log.info(Propriedades.LINHA_HORINZONTAL);
        Log.info(Propriedades.DESCRICAO);
        Arrays.stream(AppParametros.values()).forEach(p -> PARAMETROS.put(p, null));
    }

    public static void main( String[] args ) throws Exception {
        //1) Valida Git local
        Git.validaGitAtivo();
        final Map<String, String> arquivos = Git.validaGitStatus();
        if(!arquivos.isEmpty()) novoCommit(arquivos);
        //2) Valida Git remoto
        definindoRepositorio(Git.validaGitRemotes());
        //3) Valida conflitos
        Git.validaGitPull();
        //4) Valida servidor de entrega:
        final SftpUploader sftp = new SftpUploader(
            Propriedades.servidor,
            Propriedades.porta,
            Propriedades.usuario,
            Propriedades.senha);
        sftp.validarPermissoes(Propriedades.destino.getAbsolutePath());
        //5) Coleta dados

//        enviar();
    }

    private static void novoCommit(Map<String, String> arquivos) {
    }

    private static void definindoRepositorio(Map<String, String> validaGitRemotes) {
    }

    public static void teste() throws Exception {
        Log.info( "Iniciando novo SFTP Uploader" );
        Log.info( "Host para envio: " + Propriedades.getHost());
        Log.info( "Diretório de destino: " + Propriedades.destino);
        Log.info( "Total de {} arquivos para envio:", Propriedades.ARQUIVOS.size());
        Propriedades.getArquivosPath().forEach(Log::info);
        //Realizando conexão
        final SftpUploader sftp = new SftpUploader(
            Propriedades.servidor,
            Propriedades.porta,
            Propriedades.usuario,
            Propriedades.senha
        );
        final Long sucessos = Propriedades.getArquivosPath()
            .stream()
            .filter(path -> sftp.upload(new File(path), Propriedades.destino.getAbsolutePath()))
            .count();
        //Sucesso
        Log.info( "Total de {} enviados com sucesso", sucessos);
        Log.info( "SFTP Uploader finalizado" );
    }
}
