package br.com.ppware.version;

import br.com.ppware.Propriedades;
import br.com.ppware.exception.GitAusenteException;

import java.io.File;

public class Git {

    public static void gitAtivo() throws GitAusenteException {
        final File gitFolder = new File(Propriedades.ORIGEM, ".git");
        if(gitFolder.exists() && gitFolder.isDirectory()) throw new GitAusenteException();
    }

    /**
     * ETAPAS DO PROCESSO:
     * 1) Valida Git Remoto e Servidor Destino:
     *      1.1) Se o diretório do projeto possui Git iniciado
     *      1.1) Se é possível conectar ao repositório Git
     *      1.2) Se é possível conectar ao servidor de entrega
     *      1.3) Se o diretório informado no servidor de entrega permite criar diretórios e arquivos
     * 2) Valida Git Local:
     *      2.1) Realiza comando `git status -uall --porcelain`
     *      2.2) Tratar a resposta, para obter somente arquivos novos ("A "), modificados (" M") e não rastreados ("??")
     *      2.3) Havendo arquivos, solicita mensagem para novo commit (opcional)
     *      2.4) Não havendo arquivos, segue
     * 3) Valida conflitos:
     *      3.1) Realiza comando `git pull`
     *      3.2) Trata a mensagem retornada, buscando por "CONFLICT"
     *          - Se identificado, realiza o comando `git merge --abort` e aborta
 *              - Se não identificado, segue
     * 4) Coleta:
     *      4.1) Nome do branch atual
     *      4.2) Primeiro commit da branch atual
     *      4.3) Exibe todas as tags usadas no projeto, dando ênfase a mais recente
     *      4.4) Solicita nome da nova tag a ser utilizada (interação obrigatória)
     * 5) Executa:
     *      5.1) Criação da nova pasta release (no GitReleaser) e obtêm seu diretório absoluto
     *      5.2) Comando git dif
     *      5.3) Coleta de todos os diretórios/arquivos diferentes entre os commits
     *      5.4) Armazena cada diretório/arquivo na nova pasta release (no GitReleaser)
     *      5.5) Adiciona o arquivo entrega.txt, contendo: data da entrega, usuário git que fez a entrega
     * 6) Executa envio ao servidor:
     *      6.1) Caso algum arquivo falhe, apagar a nova pasta release (no GitReleaser) e abortar tudo
     * 7) Finaliza:
     *      7.1) Em caso de sucesso:
     *          - Abre o arquivo contatos.txt (no GitReleaser)
     *          - Exibe os contatos disponíveis
     *          - Solicita qual e-mail deverá ser notificado do sucesso (optional)
     *      7.1) Caso erro:
     *          - Obtêm o log do processo com erro
     *          - Envia o log ao e-mail 'joao.aguair@ppware.com.br', sob assunto "Falha GitReleaser ${data}: ${git-user}"
     * COMANDOS:
     * Branch nome: git rev-parse --abbrev-ref HEAD
     * Hash do primeiro commit na branch atual: git rev-list --max-parents=0 HEAD
     *
     * ESTRUTURA INTERNA:
     * Nomenclatura das pastas release: release/projetos/${projeto}/${branch}
     * Nomenclatura das subpastas do projeto: ${data}${hora}_${commit} ou ${data}${hora}_${tag}
     */

}
