package online.lucianofelix.visao;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import online.lucianofelix.adapter.ConstrutorGrafico;
import online.lucianofelix.beans.Ativo;
import online.lucianofelix.beans.Indicadores;
import online.lucianofelix.beans.Operacao;
import online.lucianofelix.controle.ControlaAtivo;
import online.lucianofelix.util.ModeloListenerArvore;
import online.lucianofelix.util.MontaGrid;

public class AbaStatus extends JPanel implements TreeSelectionListener {
	JPanel painelPrincipal;
	JPanel painelObjetos;
	private JLabel lblTituloTela;
	// Labels e text fields

	// Arvore do Sistema
	private JTree arvoreSistema;
	private DefaultMutableTreeNode sistema;
	private DefaultMutableTreeNode cadastros;
	private DefaultMutableTreeNode formPrecos;

	private DefaultMutableTreeNode tabelasPrecos;
	private DefaultMutableTreeNode tabelasCusto;
	private DefaultMutableTreeNode tabelasVenda;
	private DefaultMutableTreeNode condPagamento;
	private DefaultMutableTreeNode servicos;
	private JScrollPane scrMineracao;

	// Objetos de Controle
	private Indicadores indicador;
	private ControlaAtivo cAtv;
	private Ativo atv;
	private Operacao op;
	private List<Ativo> listAtvSis;
	private MontaGrid montaGrid;
	private DefaultTreeModel modArvore;
	private ConstrutorGrafico constG;

	// TODO +++++Construtor da Tela
	public AbaStatus() {

		UIManager.put("TextField.font",
				new Font("times new roman", Font.BOLD, 13));
		UIManager.put("Label.font", new Font("times new roman", Font.BOLD, 13));
		UIManager.put("Button.font",
				new Font("times new roman", Font.BOLD, 12));

		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(null);
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPrincipal.setSize(240, 580);
		painelObjetos = new JPanel();
		painelObjetos.setLayout(null);
		painelObjetos.setSize(225, 440);

		// TODO Configuração dos Labels e text fields
		lblTituloTela = new JLabel("Status");
		lblTituloTela.setBounds(10, 5, 120, 20);
		lblTituloTela.setFont(new Font("Times new roman", Font.BOLD, 18));

		// Configuração da árvore do sistema;
		sistema = new DefaultMutableTreeNode("Simpro");

		// Tabelas
		condPagamento = new DefaultMutableTreeNode("Cond. Pagamento");
		servicos = new DefaultMutableTreeNode("Serviços");

		tabelasPrecos = new DefaultMutableTreeNode("Tabelas de Preços");
		tabelasCusto = new DefaultMutableTreeNode("Custo");
		tabelasVenda = new DefaultMutableTreeNode("Venda");
		tabelasPrecos.add(tabelasCusto);
		tabelasPrecos.add(tabelasVenda);

		formPrecos = new DefaultMutableTreeNode("Preços");
		formPrecos.add(tabelasPrecos);
		cadastros = new DefaultMutableTreeNode("Cadastros");
		cadastros.add(formPrecos);
		cadastros.add(condPagamento);
		cadastros.add(servicos);

		// montagem da árvore
		sistema.add(cadastros);

		modArvore = new DefaultTreeModel(sistema);
		modArvore.addTreeModelListener(new ModeloListenerArvore());
		arvoreSistema = new JTree(modArvore);
		arvoreSistema.setBounds(5, 28, 225, 480);

		scrMineracao = new JScrollPane(arvoreSistema);
		scrMineracao.setBounds(0, 28, 200, 480);

		// Where the tree is initialized:
		arvoreSistema.getSelectionModel()
				.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Listen for when the selection changes.
		arvoreSistema.addTreeSelectionListener(this);
		arvoreSistema.setCellRenderer(new RenderizarTree());
		arvoreSistema.setShowsRootHandles(true);
		arvoreSistema.setRootVisible(false);
		arvoreSistema.setSelectionRow(0);
		arvoreSistema.setRowHeight(25);
		expandirrArvore(arvoreSistema);

		// TODO Configuração do Painelprincipal
		painelPrincipal.add(lblTituloTela);
		painelPrincipal.add(scrMineracao);
		add(painelPrincipal);

	}

	// Evento de Mouse
	// arvoreSistema.addMouseListener(new MouseListener() {
	//
	// @Override
	// public void mouseReleased(MouseEvent arg0) {
	// // TODO Auto-generated method stub
	// System.out.println("Mouse Released");
	//
	// }
	//
	// @Override
	// public void mousePressed(MouseEvent arg0) {
	// // TODO Auto-generated method stub
	// System.out.println("Mouse Pressed");
	// }
	//
	// @Override
	// public void mouseExited(MouseEvent arg0) {
	// // TODO Auto-generated method stub
	// System.out.println("Mouse Exited");
	// }
	//
	// @Override
	// public void mouseEntered(MouseEvent arg0) {
	// // TODO Auto-generated method stub
	// System.out.println("Mouse Entered");
	//
	// }
	//
	// @Override
	// public void mouseClicked(MouseEvent arg0) {
	// // TODO Auto-generated method stub
	// System.out.println("Mouse Clicked");
	//
	// }
	// });
	// TODO Renderizar Árvore
	private class RenderizarTree extends DefaultTreeCellRenderer
			implements
				TreeCellRenderer {
		private Font plainFont, italicFont;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean selected, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, selected, expanded,
					leaf, row, hasFocus);
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			if (node.toString().equals("Tabelas")) {
				setIcon(new ImageIcon(
						"//home//luciano//SIMPRO//SIMPRO//images//shopping_basket_32Lime.png"));
			}
			if (node.toString().equals("Cond. Pagamento")) {
				setIcon(new ImageIcon(
						"//home//luciano//SIMPRO//SIMPRO//images//shopping_basket_32Lime.png"));
			}
			if (node.toString().equals("Serviços")) {
				setIcon(new ImageIcon(
						"//home//luciano//SIMPRO//SIMPRO//images//sort_by_groups_32Lime.png"));
			}
			return this;
		}
	}

	// TODO Capturar Seleção
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// Returns the last path element of the selection.
		// This method is useful only when the selection model allows a single
		// selection.
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) arvoreSistema
				.getLastSelectedPathComponent();
		Object nodeInfo = node.getUserObject();
		String nomeNo = nodeInfo.toString();
		// System.out.println(nomeNo);
		if (node == null)
			// Nothing is selected.
			return;

		if (node.isLeaf() & nomeNo.equals("Cond. Pagamento")) {
			if (node.getAllowsChildren()) {
				FrameInicial.getContCondPag().iniciar();
			}
		}
		if (node.isLeaf() & nomeNo.equals("Serviços")) {
			if (node.getAllowsChildren()) {
				FrameInicial.getContServ().iniciar();
			}
		}
		if (node.isLeaf() & nomeNo.equals("Custo")) {
			if (node.getAllowsChildren()) {

			}
		}
		if (node.isLeaf() & nomeNo.equals("Venda")) {
			if (node.getAllowsChildren()) {

			}
		}
	}

	public static void expandirrArvore(JTree tree) {
		try {
			for (int row = 0; row <= tree.getRowCount(); row++) {
				tree.expandRow(row);
			}
		} catch (Exception e) {
			// tratar erro
		}
	}
}
