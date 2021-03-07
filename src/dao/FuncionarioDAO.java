package dao;

import bean.Funcionario;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import telas.CadastrarFuncionario;
import telas.EditarFuncionario;
import telas.ExcluirFuncionario;

public class FuncionarioDAO implements DAO {

    private List<Funcionario> funcionarios = new ArrayList();

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public void cadastra() {//Chama a tela de cadastro de funcionário

        CadastrarFuncionario novoFuncionario = new CadastrarFuncionario();

    }

    @Override
    public void edita(int itemSelecionado) {//Chama a tela de editar funcionário, passando a linha selecionada por parâmetro

        EditarFuncionario editarFuncionario = new EditarFuncionario(itemSelecionado);

    }

    @Override
    public void exclui(int itemSelecionado) {//Chama classe de excluir funcionário, passando a linha selecionada por parâmetro

        ExcluirFuncionario excluirFuncionario = new ExcluirFuncionario(itemSelecionado);

    }

    @Override
    public List<Funcionario> recupera() {//Abre o arquivo de texto onde os dados estão salvos, passa esses dados para um Array List, e fecha o arquivo.

        try {

            try (FileInputStream arquivoLeitura = new FileInputStream("dados/funcionarios.txt")) {

                InputStreamReader input = new InputStreamReader(arquivoLeitura, "UTF-8");

                try (BufferedReader br = new BufferedReader(input)) {

                    String linha;
                    String[] dados;

                    do {
                        Funcionario f = new Funcionario();
                        linha = br.readLine();
                        if (linha != null) {

                            dados = linha.split("#");

                            f.setNome(dados[0]);
                            f.setIdade(Integer.parseInt(dados[1]));
                            f.setCpf(dados[2]);
                            f.setRg(dados[3]);
                            f.setDepto(dados[4]);
                            f.setSalario(Double.parseDouble(dados[5]));
                            f.setEmail(dados[6]);
                            f.setTelefone(dados[7]);

                            funcionarios.add(f);
                        }

                    } while (linha != null);

                    br.close();

                }

                arquivoLeitura.close();

            }

        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {

            JOptionPane.showMessageDialog(null, "Erro ao recuperar dados de funcionários!\nVerifique o arquivo onde os dados estão armazenados.", "Ops!", 2);
            System.exit(0);

        }

        return funcionarios;
    }

}
