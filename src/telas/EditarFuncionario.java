package telas;

import dao.FuncionarioDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class EditarFuncionario extends JFrame {

    JPanel camposForm, painelBotoes, painelNome, painelIdade, painelCpf, painelRg, painelDepto, painelSalario, painelEmail, painelTelefone;
    JTextField txtNome, txtIdade, txtCpf, txtRg, txtDepto, txtSalario, txtEmail, txtTelefone;
    JButton btnSalvar, btnCancelar;

    private final FuncionarioDAO dao = new FuncionarioDAO();

    public EditarFuncionario(int indice) {
        criaComponetes();
        preparaComponentes();
        carregaCampos(indice);
        preparaJanela();
        acoesBotoes(indice);
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

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
    }

    public void carregaCampos(int indice) {

        dao.setFuncionarios(dao.recupera());

        txtNome.setText(dao.getFuncionarios().get(indice).getNome());
        txtIdade.setText("" + dao.getFuncionarios().get(indice).getIdade());
        txtCpf.setText(dao.getFuncionarios().get(indice).getCpf());
        txtRg.setText(dao.getFuncionarios().get(indice).getRg());
        txtDepto.setText(dao.getFuncionarios().get(indice).getDepto());
        txtSalario.setText("" + dao.getFuncionarios().get(indice).getSalario());
        txtEmail.setText(dao.getFuncionarios().get(indice).getEmail());
        txtTelefone.setText(dao.getFuncionarios().get(indice).getTelefone());

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
        camposForm.setLayout(new GridLayout(8, 1));

        //Alinhar paineis do formulário à esquerda
        ((FlowLayout) painelNome.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelIdade.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelCpf.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelRg.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelDepto.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelSalario.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelEmail.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) painelTelefone.getLayout()).setAlignment(FlowLayout.LEFT);

        camposForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);
    }

    public void preparaJanela() {

        getContentPane().add(camposForm, BorderLayout.NORTH);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);

        setTitle("Editar funcionário");
        setLayout(new FlowLayout());
        setSize(250, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.NORMAL);

        setVisible(true);
    }

    public void acoesBotoes(int indice) {

        btnSalvar.addActionListener((ActionEvent ae) -> {
            try {
                try {
                    //Atualiza o Array List com os dados inseridos
                    dao.getFuncionarios().get(indice).setNome(txtNome.getText());
                    dao.getFuncionarios().get(indice).setIdade(Integer.parseInt(txtIdade.getText()));
                    dao.getFuncionarios().get(indice).setCpf(txtCpf.getText());
                    dao.getFuncionarios().get(indice).setRg(txtRg.getText());
                    dao.getFuncionarios().get(indice).setDepto(txtDepto.getText());
                    dao.getFuncionarios().get(indice).setSalario(Double.parseDouble(txtSalario.getText()));
                    dao.getFuncionarios().get(indice).setEmail(txtEmail.getText());
                    dao.getFuncionarios().get(indice).setTelefone(txtTelefone.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ops! Houve algum problema com os dados digitados! Tente novamente.", "Ops!", 1);
                    dispose();
                }
                //Abre o arquivo, limpando todos os dados e preenchendo de novo com o Array List atualizado, e fecha o arquivo
                try (FileOutputStream arquivoEscrita = new FileOutputStream("dados/funcionarios.txt"); PrintWriter pr = new PrintWriter(arquivoEscrita)) {
                    for (int i = 0; i < dao.getFuncionarios().size(); i++) {
                        pr.print(dao.getFuncionarios().get(i).getNome() + "#");
                        pr.print(dao.getFuncionarios().get(i).getIdade() + "#");
                        pr.print(dao.getFuncionarios().get(i).getCpf() + "#");
                        pr.print(dao.getFuncionarios().get(i).getRg() + "#");
                        pr.print(dao.getFuncionarios().get(i).getDepto() + "#");
                        pr.print(dao.getFuncionarios().get(i).getSalario() + "#");
                        pr.print(dao.getFuncionarios().get(i).getEmail() + "#");
                        pr.println(dao.getFuncionarios().get(i).getTelefone());
                    }

                    pr.close();

                }
            } catch (HeadlessException | IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao editar funcionário!", "Ops!", 2);
            }

            dispose();
            ListarFuncionarios lf = new ListarFuncionarios();
        });

        btnCancelar.addActionListener((ActionEvent ae) -> {
            dispose();
            ListarFuncionarios lf = new ListarFuncionarios();
        });
    }
}
