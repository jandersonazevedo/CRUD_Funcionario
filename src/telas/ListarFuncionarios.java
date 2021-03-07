package telas;

import dao.FuncionarioDAO;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import tabela.FuncionarioTableModel;

public final class ListarFuncionarios extends JFrame {

    JPanel painelPrincipal, painelBotoes;
    private JTable tabela;
    JScrollPane barraRolagem;
    private JButton btnNovo, btnEditar, btnExcluir, btnSair;
    private FuncionarioDAO dao = new FuncionarioDAO();
    private FuncionarioTableModel tm = new FuncionarioTableModel(dao);
    private int itemSelecionado;

    public int getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(int itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }

    public void setPainelPrincipal(JPanel painelPrincipal) {
        this.painelPrincipal = painelPrincipal;
    }

    public JPanel getPainelBotoes() {
        return painelBotoes;
    }

    public void setPainelBotoes(JPanel painelBotoes) {
        this.painelBotoes = painelBotoes;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    public JScrollPane getBarraRolagem() {
        return barraRolagem;
    }

    public void setBarraRolagem(JScrollPane barraRolagem) {
        this.barraRolagem = barraRolagem;
    }

    public JButton getBtnNovo() {
        return btnNovo;
    }

    public void setBtnNovo(JButton btnNovo) {
        this.btnNovo = btnNovo;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public void setBtnEditar(JButton btnEditar) {
        this.btnEditar = btnEditar;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public void setBtnExcluir(JButton btnExcluir) {
        this.btnExcluir = btnExcluir;
    }

    public JButton getBtnSair() {
        return btnSair;
    }

    public void setBtnSair(JButton btnSair) {
        this.btnSair = btnSair;
    }

    public FuncionarioDAO getDao() {
        return dao;
    }

    public void setDao(FuncionarioDAO dao) {
        this.dao = dao;
    }

    public FuncionarioTableModel getTm() {
        return tm;
    }

    public void setTm(FuncionarioTableModel tm) {
        this.tm = tm;
    }

    public ListarFuncionarios() {
        criaComponetes();
        preparaComponentes();
        preparaJanela();
        acoesBotoes();
    }

    public void criaComponetes() {

        painelPrincipal = new JPanel();
        painelBotoes = new JPanel();

        this.tabela = new JTable(tm);

        barraRolagem = new JScrollPane(this.tabela);

        this.btnNovo = new JButton("Novo");
        this.btnEditar = new JButton("Editar");
        this.btnExcluir = new JButton("Excluir");
        this.btnSair = new JButton("Sair");

    }

    public void preparaComponentes() {

        painelPrincipal.setLayout(new GridLayout());
        //Prepara para centralizar célula
        DefaultTableCellRenderer centralizaCelula = new DefaultTableCellRenderer();
	centralizaCelula.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Redimenciona a largura da coluna "Nome" para 200 pixels
        TableColumn col = tabela.getColumnModel().getColumn(0);
        col.setPreferredWidth(200);
        //Redimenciona a largura da coluna "Idade" para 10 pixels e centraliza
        tabela.getColumnModel().getColumn(1).setCellRenderer(centralizaCelula);   
        col = tabela.getColumnModel().getColumn(1);
        col.setPreferredWidth(10);
        //Redimenciona a largura da coluna "CPF" para 60 pixels e centraliza
        tabela.getColumnModel().getColumn(2).setCellRenderer(centralizaCelula); 
        col = tabela.getColumnModel().getColumn(2);
        col.setPreferredWidth(60);
        //Redimenciona a largura da coluna "RG" para 35 pixels e centraliza
        tabela.getColumnModel().getColumn(3).setCellRenderer(centralizaCelula); 
        col = tabela.getColumnModel().getColumn(3);
        col.setPreferredWidth(35);
        //Redimenciona a largura da coluna "Departamento" para 30 pixels
        col = tabela.getColumnModel().getColumn(4);
        col.setPreferredWidth(30);
        //Redimenciona a largura da coluna "Salário" para 40 pixels e centraliza
        tabela.getColumnModel().getColumn(5).setCellRenderer(centralizaCelula); 
        col = tabela.getColumnModel().getColumn(5);
        col.setPreferredWidth(40);
        //Redimenciona a largura da coluna "E-mail" para 120 pixels
        col = tabela.getColumnModel().getColumn(6);
        col.setPreferredWidth(120);
        //Redimenciona a largura da coluna "Telefone" para 50 pixels e centraliza
        tabela.getColumnModel().getColumn(7).setCellRenderer(centralizaCelula); 
        col = tabela.getColumnModel().getColumn(7);
        col.setPreferredWidth(50);
        
        painelBotoes.add(this.btnSair);
        painelBotoes.add(this.btnExcluir);
        painelBotoes.add(this.btnEditar);
        painelBotoes.add(this.btnNovo);

        painelPrincipal.add(barraRolagem);

    }

    public void preparaJanela() {

        setTitle("Gerenciamento de Funcionários");
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);
        getContentPane().add(painelPrincipal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 400);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void acoesBotoes() {

        btnSair.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });

        btnNovo.addActionListener((ActionEvent ae) -> {
            dispose();
            dao.cadastra();
        });

        btnEditar.addActionListener((ActionEvent ae) -> {
            itemSelecionado = tabela.getSelectedRow();//Atribui o índice da linha selecionada para a variável
            if (itemSelecionado != -1) {//Se algum item for selecionado
                dispose();
                dao.edita(itemSelecionado);
            } else {//Se nenhum item for selcionado
                JOptionPane.showMessageDialog(null, "Nenhum item selecionado! Escolha um item para editar.", "Nenhum item selecionado", 1);
            }
        });

        btnExcluir.addActionListener((ActionEvent ae) -> {
            itemSelecionado = tabela.getSelectedRow();
            if (itemSelecionado != -1) {
                setVisible(false);
                int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que desaja excluir " + dao.getFuncionarios().get(itemSelecionado).getNome() + "?", "Excluir funcionário", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {//Se o usuário clicar em "Sim"
                    dao.exclui(itemSelecionado);
                } else {//Se o usuário não clicar em "Sim"
                    setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum item selecionado! Escolha um item para excluir.", "Nenhum item selecionado", 1);
            }
        });
    }

    public static void main(String[] args) {

        ListarFuncionarios lf = new ListarFuncionarios();

    }
}
