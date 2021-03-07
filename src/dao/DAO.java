package dao;

import bean.Funcionario;
import java.util.List;

public interface DAO {

    public void cadastra();//Método responsável por inserir um novo funcionário

    public void edita(int itemSelecionado);//Método responsável por editar um funcionário selecionado

    public void exclui(int itemSelecionado);//Método responsável por excluir um funcionário selecionado

    public List<Funcionario> recupera();//Método responsável por recuperar os dados cadastrados

}
