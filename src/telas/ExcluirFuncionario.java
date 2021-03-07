package telas;

import dao.FuncionarioDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ExcluirFuncionario {

    FuncionarioDAO dao = new FuncionarioDAO();

    public ExcluirFuncionario(int indice) {

        dao.recupera().remove(indice);//Remove o item selecionado do Array List
        try {
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
                arquivoEscrita.close();
            }
            ListarFuncionarios lf = new ListarFuncionarios();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário!", "Ops!", 2);
        }
    }

}
