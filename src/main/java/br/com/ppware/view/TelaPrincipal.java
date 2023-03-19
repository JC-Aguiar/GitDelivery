package br.com.ppware.view;

import br.com.ppware.Log;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelaPrincipal extends JFrame {
    private JLabel lbDiretorioLocal;
    private JTextField tfDiretorioLocal;
    private JLabel lbServidorIp;
    private JTextField tfServidorIp;
    private JLabel lbServidorPorta;
    private JTextField tfServidorPorta;
    private JLabel lbUsuario;
    private JTextField tfUsuario;
    private JLabel lbSenha;
    private JPasswordField pfSenha;
    private JLabel lbDiretorioEntrega;
    private JTextField tfDiretorioEntrega;
    private JButton btnSalvar;
    private JButton btnCancelar;

        public TelaPrincipal() {
            initComponents();
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);

            // Linha 1
            lbDiretorioLocal.setHorizontalAlignment(SwingConstants.RIGHT);
            gbc.anchor = GridBagConstraints.EAST;
            add(lbDiretorioLocal, gbc);
            gbc.gridx++;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            add(tfDiretorioLocal, gbc);

            // Linha 2
            gbc.gridx = 0;
            gbc.gridy++;
            lbServidorIp.setHorizontalAlignment(SwingConstants.RIGHT);
            gbc.anchor = GridBagConstraints.EAST;
            add(lbServidorIp, gbc);
            gbc.gridx++;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            add(tfServidorIp, gbc);

            // Linha 3
            gbc.gridx = 0;
            gbc.gridy++;
            lbUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
            gbc.anchor = GridBagConstraints.EAST;
            add(lbUsuario, gbc);
            gbc.gridx++;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            add(tfUsuario, gbc);

            // Linha 4
            gbc.gridx = 0;
            gbc.gridy++;
            lbSenha.setHorizontalAlignment(SwingConstants.RIGHT);
            gbc.anchor = GridBagConstraints.EAST;
            add(lbSenha, gbc);
            gbc.gridx++;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            add(pfSenha, gbc);

            // Linha 5
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            add(btnSalvar, gbc);
            gbc.gridx++;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(btnCancelar, gbc);

            setTitle("Formulário");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
        }

    private void initComponents() {
        lbDiretorioLocal = new JLabel("Diretório do projeto:");
        tfDiretorioLocal = new JTextField(20);
        lbServidorIp = new JLabel("IP:");
        tfServidorIp = new JTextField(20);
        lbServidorPorta = new JLabel("IP:");
        tfServidorPorta = new JTextField(20);
        lbUsuario = new JLabel("Usuário:");
        tfUsuario = new JTextField(20);
        lbSenha = new JLabel("Senha:");
        pfSenha = new JPasswordField(20);
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        setVisible(true);
    }

    private void envioHandler(ActionEvent actionEvent) {
            System.out.println();
        Log.info("TESTE: ENVIO REALIZADO!");
    }

    private void configurar() {

        //---------------------------------
        //Layout: área local
//        panel.add(dirLocalLabel);
//        panel.add(localDiretorio);
        //Layout: área do servidor
//        panel.add(areaServidor);
//        panel.add(ipLabel);
//        panel.add(servidorIp);
//        panel.add(portaLabel);
//        panel.add(servidorPorta);
//        panel.add(usuarioLabel);
//        panel.add(servidorUsuario);
//        panel.add(senhaLabel);
//        panel.add(servidorSenha);
//        panel.add(servidorDiretorio);
//        panel.add(dirServidorLabel);
        //---------------------------------
        // Adicionando LocalDiretorio

    }

}

