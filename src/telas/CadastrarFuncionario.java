package telas;

import dao.FuncionarioDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class CadastrarFuncionario extends JFrame {

    JPanel camposForm, painelBotoes, painelNome, painelIdade, painelCpf, painelRg, painelDepto, painelSalario, painelEmail, painelTelefone;
    JTextField txtNome, txtIdade, txtCpf, txtRg, txtDepto, txtSalario, txtEmail, txtTelefone;
    JButton btnCadastrar, btnCancelar;

    private final FuncionarioDAO dao = new FuncionarioDAO();

    public CadastrarFuncionario() {//
        criaComponetes();
        preparaComponentes();
        preparaJanela();
        acoesBotoes();
    }

    public void criaComponetes() {

        camposForm = new JPanel();

        painelNome = new JPanel();
        painelIdade = new JPanel();
        painelCpf = new JPanel();
        painelRg = new JPanel();
        painelDepto = new JPanel();
        painelSalario = new JPanel();
        painelEmail = new JPanel();
        painelTelefone = new JPanel();

        txtNome = new JTextField(15);
        txtIdade = new JTextField(3);
        txtCpf = new JTextField(10);
        txtRg = new JTextField(9);
        txtDepto = new JTextField(10);
        txtSalario = new JTextField(6);
        txtEmail = new JTextField(15);
        txtTelefone = new JTextField(10);

        painelBotoes = new JPanel();

        btnCadastrar = new JButton("Cadastrar");
        btnCancelar = new JButton("Cancelar");
    }

    public void preparaComponentes() {

        painelNome.add(new JLabel("Nome:"));
        painelNome.add(txtNome);

        painelIdade.add(new JLabel("Idade:"));
        painelIdade.add(txtIdade);

        painelCpf.add(new JLabel("CPF:"));
        painelCpf.add(txtCpf);

        painelRg.add(new JLabel("RG:"));
        painelRg.add(txtRg);

        painelDepto.add(new JLabel("Departamendo:"));
        painelDepto.add(txtDepto);

        painelSalario.add(new JLabel("Salário:"));
        painelSalario.add(txtSalario);

        painelEmail.add(new JLabel("E-mail:"));
        painelEmail.add(txtEmail);

        painelTelefone.add(new JLabel("Telefone:"));
        painelTelefone.add(txtTelefone);

        camposForm.add(painelNome);
        camposForm.add(painelIdade);
        camposForm.add(painelCpf);
        camposForm.add(painelRg);
        camposForm.add(painelDepto);
        camposForm.add(painelSalario);
        camposForm.add(painelEmail);
        camposForm.add(painelTelefone);

        camposForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        //Alinhar paineis do formulário à esquerda
        ((FlowLayout) painelNome.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelIdade.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelCpf.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelRg.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelDepto.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelSalario.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelEmail.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelTelefone.getLayout()).setAlignment(FlowLayout.LEFT);

        camposForm.setLayout(new GridLayout(8, 1));
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnCadastrar);
    }

    public void preparaJanela() {

        getContentPane().add(camposForm, BorderLayout.NORTH);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);

        setTitle("Cadastrar funcionário");
        setLayout(new FlowLayout());
        setSize(250, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.NORMAL);
        setVisible(true);

    }

    public void acoesBotoes() {

        btnCadastrar.addActionListener((ActionEvent ae) -> {
            try {
                String novoFunc;//Cria a variável responsável por armazenar a linha com os dados inseridos

                try {
                    //Concatena todos os dados inseridos, separando-os com o caractere escolhido "#", e atribui à variável
                    novoFunc = txtNome.getText() + "#" + Integer.parseInt(txtIdade.getText()) + "#" + txtCpf.getText() + "#" + txtRg.getText() + "#" + txtDepto.getText() + "#" + Double.parseDouble(txtSalario.getText()) + "#" + txtEmail.getText() + "#" + txtTelefone.getText() + "\n";

                } catch (NumberFormatException e) {
                    //Caso haja algum problema com os dados inseridos, a variável recebe valor vazio, para não inserir dados indesejados no arquivo
                    novoFunc = "";
                    JOptionPane.showMessageDialog(null, "Ops! Houve algum problema com os dados digitados! Tente novamente.", "Ops!", 1);

                }

                try (FileWriter arquivoEscrita = new FileWriter("dados/funcionarios.txt", true)) {
                    //Insere os dados depois da última linha do arquivo
                    arquivoEscrita.write(novoFunc);

                    arquivoEscrita.close();
                }
                //Fecha a tela de cadastro
                dispose();
                //Abre novamente a tela principal
                ListarFuncionarios lf = new ListarFuncionarios();

            } catch (HeadlessException | IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário!", "Ops!", 2);
            }
        });

        btnCancelar.addActionListener((ActionEvent ae) -> {
            //Fecha a tela de cadastro
            dispose();
            //Abre novamente a tela principal
            ListarFuncionarios lf = new ListarFuncionarios();

        });
    }
}
