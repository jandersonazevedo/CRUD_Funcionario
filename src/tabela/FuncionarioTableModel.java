package tabela;

import bean.Funcionario;
import dao.FuncionarioDAO;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FuncionarioTableModel extends AbstractTableModel {

    private final List<Funcionario> funcionarios;
    private final List<String> titulosColunas;
    private final FuncionarioDAO dao;

    NumberFormat salarioFormat = NumberFormat.getCurrencyInstance();//Formata o salário pra o formato de dinheiro em reais (R$)

    public FuncionarioTableModel(FuncionarioDAO dao) {
        this.dao = dao;
        this.funcionarios = dao.recupera();
        titulosColunas = Arrays.asList("Nome", "Idade", "CPF", "RG", "Departamento", "Salário", "E-mail", "Telefone");
    }

    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return titulosColunas.size();
    }

    @Override
    public String getColumnName(int i) {
        return titulosColunas.get(i);
    }

    @Override
    public Object getValueAt(int idLinha, int idColuna) {
        Funcionario funcionario = funcionarios.get(idLinha);

        switch (idColuna) {
            case 0:
                return funcionario.getNome();
            case 1:
                return funcionario.getIdade();
            case 2:
                return funcionario.getCpf();
            case 3:
                return funcionario.getRg();
            case 4:
                return funcionario.getDepto();
            case 5:
                return salarioFormat.format(funcionario.getSalario());
            case 6:
                return funcionario.getEmail();
            case 7:
                return funcionario.getTelefone();
        }
        return funcionarios;
    }

    @Override
    public void setValueAt(Object aValue, int idLinha, int idColuna) {
        Funcionario funcionario = funcionarios.get(idLinha);

        switch (idColuna) {
            case 0:
                funcionario.setNome((String) aValue);
                break;
            case 1:
                funcionario.setIdade((int) aValue);
                break;
            case 2:
                funcionario.setCpf((String) aValue);
                break;
            case 3:
                funcionario.setRg((String) aValue);
                break;
            case 4:
                funcionario.setDepto((String) aValue);
                break;
            case 5:
                funcionario.setSalario((double) aValue);
                break;
            case 6:
                funcionario.setTelefone((String) aValue);
                break;
            case 7:
                funcionario.setEmail((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("Índice exedido!");
        }
    }

}
