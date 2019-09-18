package online.lucianofelix.visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import online.lucianofelix.adapter.ConstrutorGrafico;
import online.lucianofelix.controle.ControlaPedido;
import online.lucianofelix.controle.ControlaPessoa;
import online.lucianofelix.controle.ControlaProduto;
import online.lucianofelix.util.ModeloListenerArvore;
import online.lucianofelix.util.MontaGrid;

public class AbaServicos extends JPanel implements TreeSelectionListener {
	JPanel painelPrincipal;
	JPanel painelObjetos;
	private JLabel lblTituloTela;
	// Labels e text fields

	// Arvore do Sistema
	public static JTree arvoreSistema;
	private DefaultMutableTreeNode sistema;
	private DefaultMutableTreeNode chamados;
	private DefaultMutableTreeNode venda;
	private DefaultMutableTreeNode vista;
	private DefaultMutableTreeNode prazo;
	private DefaultMutableTreeNode abertos;
	private DefaultMutableTreeNode contatos;
	private DefaultMutableTreeNode servicos;

	private JScrollPane scrArvSistema;
	// Text Fields
	private JTextField txtfSeqOperacao;
	private JTextField txtfIdAtivo;
	private JTextField txtfStartCompra;
	private JTextField txtfOrdCompra;
	private JTextField txtfStartVenda;
	private JTextField txtfOrdVenda;
	private JTextField txtFTipoSetup;

	// botoes
	private JButton btnProximo;
	private JButton btnAnterior;
	private JButton btnAdiciona;
	private JButton btnSalvar;
	private JButton btnRemove;
	private JButton btnMinerar;
	// Objetos de Controle
	// private Indicadores indicador;
	// private ControlaAtivo cAtv;
	// private Ativo atv;
	// private Operacao op;
	// private List<Ativo> listAtvSis;
	private MontaGrid montaGrid;
	private DefaultTreeModel modArvore;
	private ConstrutorGrafico constG;
	private ControlaPessoa contUsua;
	private ControlaProduto contProd;
	private ControlaPedido contPedi;

	public AbaServicos() {

		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(null);
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPrincipal.setSize(240, 580);
		painelObjetos = new JPanel();
		painelObjetos.setLayout(null);
		painelObjetos.setSize(225, 440);
		getBackground();
		painelObjetos.setBackground(Color.BLACK);

		// TODO +++++ConfiguraÃ§Ã£o dos Labels e text
		// fields++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		lblTituloTela = new JLabel("Serviços");
		lblTituloTela.setBounds(10, 5, 120, 20);
		lblTituloTela.setFont(new Font("Times new roman", Font.BOLD, 18));

		// Configuração da Arvore do sistema;
		sistema = new DefaultMutableTreeNode("Simpro");

		// Pedidos
		chamados = new DefaultMutableTreeNode("Chamados");
		// compra
		abertos = new DefaultMutableTreeNode("Abertos");

		// venda
		vista = new DefaultMutableTreeNode("A Vista");
		prazo = new DefaultMutableTreeNode("A Prazo");
		venda = new DefaultMutableTreeNode("Venda");
		venda.add(vista);
		venda.add(prazo);
		chamados.add(venda);
		chamados.add(abertos);

		// Contatos
		contatos = new DefaultMutableTreeNode("Contatos");

		// produtos
		servicos = new DefaultMutableTreeNode("Serviços");
		// venda

		// montagem da árvore
		sistema.add(chamados);
		sistema.add(contatos);
		sistema.add(servicos);
		modArvore = new DefaultTreeModel(sistema);
		modArvore.addTreeModelListener(new ModeloListenerArvore());
		arvoreSistema = new JTree(modArvore);
		arvoreSistema.setBounds(0, 28, 225, 440);

		// painelObjetos.add(arvoreSistema);
		scrArvSistema = new JScrollPane(arvoreSistema);
		scrArvSistema.setBounds(0, 28, 200, 480);

		// Where the tree is initialized:
		arvoreSistema.getSelectionModel()
				.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Listen for when the selection changes.
		arvoreSistema.addTreeSelectionListener(this);
		arvoreSistema.setCellRenderer(new RenderizarTree());
		arvoreSistema.setShowsRootHandles(true);
		arvoreSistema.setRowHeight(35);

		// TODO Posicionamento e ações dos Botões
		btnAnterior = new JButton("<<<<");
		btnAnterior.setBounds(10, 495, 85, 25);
		btnProximo = new JButton(">>>>");
		btnProximo.setBounds(95, 495, 85, 25);
		btnAdiciona = new JButton("Add");
		btnAdiciona.setBounds(10, 520, 85, 25);
		btnSalvar = new JButton("Salva");
		btnSalvar.setBounds(95, 520, 85, 25);
		btnRemove = new JButton("Rem");
		btnRemove.setBounds(10, 545, 85, 25);
		btnMinerar = new JButton("Minerar");
		btnMinerar.setBounds(95, 545, 85, 25);

		// TODO Botão Adiciona
		btnAdiciona.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				habilitaEdicao();
			}
		});
		// TODO Botão Anterior
		btnAnterior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		// TODO Botão Próximo
		btnProximo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		// TODO Botão Minerar
		btnMinerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		// Botao Salvar
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// .addObject("New Node " + newNodeSuffix++);
				//
				// public DefaultMutableTreeNode addObject(Object child) {
				// DefaultMutableTreeNode parentNode = null;
				// TreePath parentPath = tree.getSelectionPath();
				//
				// if (parentPath == null) {
				// //There is no selection. Default to the root node.
				// parentNode = rootNode;
				// } else {
				// parentNode = (DefaultMutableTreeNode)
				// (parentPath.getLastPathComponent());
				// }
				//
				// return addObject(parentNode, child, true);
				// }
				//
				// public DefaultMutableTreeNode
				// addObject(DefaultMutableTreeNode parent,
				// Object child,
				// boolean shouldBeVisible) {
				// DefaultMutableTreeNode childNode =
				// new DefaultMutableTreeNode(child);
				// ...
				// treeModel.insertNodeInto(childNode, parent,
				// parent.getChildCount());
				//
				// //Make sure the user can see the lovely new node.
				// if (shouldBeVisible) {
				// tree.scrollPathToVisible(new TreePath(childNode.getPath()));
				// }
				// return childNode;
				// }
			}
		});
		// TODO Configuração do Painel principal

		btnProximo.setEnabled(true);
		btnAnterior.setEnabled(true);
		btnAdiciona.setEnabled(false);
		btnSalvar.setEnabled(true);
		btnRemove.setEnabled(false);

		painelPrincipal.add(lblTituloTela);
		painelPrincipal.add(scrArvSistema);
		painelPrincipal.add(btnAnterior);
		painelPrincipal.add(btnProximo);
		painelPrincipal.add(btnAdiciona);
		painelPrincipal.add(btnSalvar);
		painelPrincipal.add(btnRemove);
		painelPrincipal.add(btnMinerar);
		add(painelPrincipal);
	}// TODO Fim do construtor início controle de campos + habilita edição

	public void habilitaEdicao() {
		txtfSeqOperacao.setEnabled(false);
		txtfIdAtivo.setEnabled(true);
		txtfStartCompra.setEnabled(true);
		txtfStartVenda.setEnabled(true);
		txtfOrdCompra.setEnabled(true);
		txtfOrdVenda.setEnabled(true);

		btnProximo.setEnabled(false);
		btnAnterior.setEnabled(false);
		btnAdiciona.setEnabled(false);
		btnSalvar.setEnabled(true);
		btnRemove.setEnabled(true);
	}

	// TODO Desabilita edição
	public void desHabilitaEdicao() {
		txtfSeqOperacao.setEnabled(false);
		txtfIdAtivo.setEnabled(false);
		txtfStartCompra.setEnabled(false);
		txtfStartVenda.setEnabled(false);
		txtfOrdCompra.setEnabled(false);
		txtfOrdVenda.setEnabled(false);

		btnProximo.setEnabled(true);
		btnAnterior.setEnabled(true);
		btnAdiciona.setEnabled(true);
		btnSalvar.setEnabled(false);
		btnRemove.setEnabled(false);
	}

	// TODO Eventos de Mouse
	// arvoreSistema.addMouseListener(new MouseListener() {
	//
	// @Override
	// public void mouseReleased(MouseEvent arg0) {
	// // TODO Ao soltar o botão
	// System.out.println("Mouse Released");
	//
	// }
	//
	// @Override
	// public void mousePressed(MouseEvent arg0) {
	// // TODO Ao pressionar o botão
	// System.out.println("Mouse Pressed");
	// }
	//
	// @Override
	// public void mouseExited(MouseEvent arg0) {
	// // TODO Ao sair de sobre o objeto
	// System.out.println("Mouse Exited");
	// }
	//
	// @Override
	// public void mouseEntered(MouseEvent arg0) {
	// // TODO Ao entrar sobre o objeto
	// System.out.println("Mouse Entered");
	//
	// }
	//
	// @Override
	// public void mouseClicked(MouseEvent arg0) {
	// // TODO Ao clicar
	// System.out.println("Mouse Clicked");
	//
	// }
	// });
	// TODO Renderizar a árvore
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
			if (node.toString().equals("Contatos")) {
				setIcon(new ImageIcon(
						"//home//luciano//SIMPRO//SIMPRO//images//sort_by_groups_32Lime.png"));
			}
			if (node.toString().equals("Serviços")) {
				setIcon(new ImageIcon(
						"//home//luciano//SIMPRO//SIMPRO//images//product_32Lime.png"));
			}
			if (node.toString().equals("Chamados")) {
				setIcon(new ImageIcon(
						"//home//luciano//SIMPRO//SIMPRO//images//shopping_basket_32Lime.png"));
			}
			if (node.toString().equals("Venda")) {
				setIcon(new ImageIcon(
						"//home//luciano//SIMPRO//SIMPRO//images//ic_attach_money_24.png"));
			}
			return this;
		}
	}

	// TODO Capturar seleção de folha
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// Returns the last path element of the selection.
		// This method is useful only when the selection model allows a single
		// selection.
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) arvoreSistema
				.getLastSelectedPathComponent();
		if (node == null) {
			// Nothing is selected.
		}
		// Object nodeInfo = node.getUserObject();
		if (node.isLeaf() & node.isNodeAncestor(chamados)) {
			if (node.getAllowsChildren()) {
				// String idNeg = nodeInfo.toString();
				FrameInicial.getContPedi().iniciar(node.toString());
			}
		}
		if (node.isLeaf() & node.isNodeAncestor(servicos)) {
			if (node.getAllowsChildren()) {
				// String idNeg = nodeInfo.toString();
				FrameInicial.getContServ().iniciar();
			}
		}
	}
}
