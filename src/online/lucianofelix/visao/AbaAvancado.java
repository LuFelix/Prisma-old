package online.lucianofelix.visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import online.lucianofelix.adapter.ConstrutorGrafico;
import online.lucianofelix.util.ModeloListenerArvore;
import online.lucianofelix.util.MontaGrid;

public class AbaAvancado extends JPanel implements TreeSelectionListener {
	JPanel painelPrincipal;
	JPanel painelObjetos;
	private JLabel lblTituloTela;
	// Labels e text fields

	// Arvore do Sistema
	private JTree arvoreSistema;
	private DefaultMutableTreeNode sistema;
	private DefaultMutableTreeNode usuario;
	private DefaultMutableTreeNode carteiras;
	private DefaultMutableTreeNode ativosOperados;
	private DefaultMutableTreeNode dinheiro;
	private DefaultMutableTreeNode operacoes;
	private JScrollPane scrMineracao;
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
	public AbaAvancado() {

		// TODO +++++Construtor da Tela de
		// Operacaos+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		UIManager.put("TextField.font",
				new Font("times new roman", Font.BOLD, 13));
		UIManager.put("Label.font", new Font("times new roman", Font.BOLD, 13));
		UIManager.put("Button.font",
				new Font("times new roman", Font.BOLD, 12));

		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(null);
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPrincipal.setSize(240, 600);
		painelObjetos = new JPanel();
		painelObjetos.setLayout(null);
		painelObjetos.setSize(225, 440);

		painelObjetos.setBackground(getBackground().BLACK);

		// TODO +++++Configura��o dos Labels e text
		// fields++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		lblTituloTela = new JLabel("Produtos:");
		lblTituloTela.setBounds(10, 10, 120, 20);
		lblTituloTela.setFont(new Font("Times new roman", Font.BOLD, 16));
		// Configura��o da Arvore do sistema;
		sistema = new DefaultMutableTreeNode("Simpro Vendas");
		usuario = new DefaultMutableTreeNode("Contatos");

		// opera��es
		operacoes = new DefaultMutableTreeNode("Fornecedores");
		// Carteiras
		ativosOperados = new DefaultMutableTreeNode("Iniciados");
		dinheiro = new DefaultMutableTreeNode("Vendas");
		carteiras = new DefaultMutableTreeNode("Clientes");
		carteiras.add(ativosOperados);
		carteiras.add(dinheiro);

		// Usuario
		usuario.add(carteiras);
		usuario.add(operacoes);

		// montagem da �rvore
		sistema.add(usuario);
		modArvore = new DefaultTreeModel(sistema);
		modArvore.addTreeModelListener(new ModeloListenerArvore());
		arvoreSistema = new JTree(modArvore);
		arvoreSistema.setBounds(5, 5, 225, 440);

		// painelObjetos.add(arvoreSistema);
		scrMineracao = new JScrollPane(arvoreSistema);
		scrMineracao.setBounds(5, 40, 225, 440);

		// Where the tree is initialized:
		arvoreSistema.getSelectionModel().setSelectionMode(
				TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

		// Listen for when the selection changes.
		arvoreSistema.addTreeSelectionListener(this);
		arvoreSistema.setShowsRootHandles(true);

		// TODO +++++Posicionamento dos Bot�es
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// A��o Radio Buttons++++++++++++++++

		// +++ Jbuttons+++++++++++++++++++++++
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

		// TODO +++++ A�oes dos
		// Botoes+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// +++++ Bot�o Adiciona ++++++++++++++++++++++++
		btnAdiciona.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				habilitaEdicao();
			}
		});

		// TODO+++++ Bot�o Anterior ++++++++++++++++++++++++

		btnAnterior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// TODO+++++ Bot�o Pr�ximo ++++++++++++++++++++++++

		btnProximo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		// TODO+++++ Botao Salvar +++++++++++++++++++++++++

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

		// TODO+++++ Botao Minerar +++++++++++++++++++++++++
		btnMinerar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		// TODO +++++A��es de foco dos Text
		// Fields++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// TODO +++++A��o de foco txtFCodigo++++++++++++++++++

		// TODO +++++Configura��o do Painel
		// principal++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// Configura��es iniciais
		// txtfSeqOperacao.setEnabled(false);
		// txtfIdAtivo.setEnabled(true);
		// txtfStartCompra.setEnabled(true);
		// txtfStartVenda.setEnabled(true);
		// txtfOrdCompra.setEnabled(true);
		// txtfOrdVenda.setEnabled(true);
		//
		btnProximo.setEnabled(true);
		btnAnterior.setEnabled(true);
		btnAdiciona.setEnabled(false);
		btnSalvar.setEnabled(true);
		btnRemove.setEnabled(false);

		painelPrincipal.add(lblTituloTela);
		painelPrincipal.add(scrMineracao);
		// painelPrincipal.add(lblSeqOperacao);
		// painelPrincipal.add(txtfSeqOperacao);
		// painelPrincipal.add(lblIdAtivo);
		// painelPrincipal.add(txtfIdAtivo);
		// painelPrincipal.add(lblStartCompra);
		// painelPrincipal.add(txtfStartCompra);
		// painelPrincipal.add(lblStartVenda);
		// painelPrincipal.add(txtfStartVenda);
		// painelPrincipal.add(lblOrdCompra);
		// painelPrincipal.add(txtfOrdCompra);
		// painelPrincipal.add(lblOrdVenda);
		// painelPrincipal.add(txtfOrdVenda);
		// painelPrincipal.add(lblQtdAcoes);
		// painelPrincipal.add(txtfQtdAcoes);
		// painelPrincipal.add(lblStopLoss);
		// painelPrincipal.add(txtfStopLoss);
		// painelPrincipal.add(lblDataInicio);
		// painelPrincipal.add(dataInicio);
		// painelPrincipal.add(lblDataFim);
		// painelPrincipal.add(dataFim);
		// painelPrincipal.add(lblTipoSetup);
		// painelPrincipal.add(txtFTipoSetup);
		// painelPrincipal.add(lblFiltroIndicador);
		// painelPrincipal.add(jcbexecutou);
		// painelPrincipal.add(jcbcontraTendencia);
		painelPrincipal.add(btnAnterior);
		painelPrincipal.add(btnProximo);
		painelPrincipal.add(btnAdiciona);
		painelPrincipal.add(btnSalvar);
		painelPrincipal.add(btnRemove);
		painelPrincipal.add(btnMinerar);
		add(painelPrincipal);

	}// TODO +++++++Fim do
		// construtor+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// TODO +++++ Habilita/Desabilita/Carrega/Le/Limpa os Campos
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// public Operacao leCampos(){
	//
	// op = new Operacao();
	// op.setSeqOperacao(Integer.parseInt(txtfSeqOperacao.getText()));
	// op.setIdAtivo(txtfIdAtivo.getText());
	// op.setStartCompra(Float.parseFloat(txtfStartCompra.getText()));
	// op.setStartVenda(Float.parseFloat(txtfStartVenda.getText()));
	// op.setOrdCompra(Float.parseFloat(txtfOrdCompra.getText()));
	// op.setOrdVenda(Float.parseFloat(txtfOrdVenda.getText()));
	// op.setTipoSetup(txtFTipoSetup.getText());
	//
	// return op;
	// }

	public void valueChanged(TreeSelectionEvent e) {
		// Returns the last path element of the selection.
		// This method is useful only when the selection model allows a single
		// selection.
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) arvoreSistema
				.getLastSelectedPathComponent();

		if (node == null)
			// Nothing is selected.
			return;

		Object nodeInfo = node.getUserObject();

		if ((node.isLeaf()) & (node.isNodeAncestor(usuario))) {
			// Indicadores.selecionaIndicador(nodeInfo.toString());
			// indicador.setNome(nodeInfo.toString());
			if (node.getAllowsChildren()) {
				// String idNeg = nodeInfo.toString();
				// cAtv = new ControlaAtivo();
				// montaGrid = new MontaGrid();
				// ArrayList<Object> col = new ArrayList<>();
				// col.add("Abe");
				// col.add("Fec");
				// col.add("Max");
				// col.add("Min");
				// col.add("Data");
				// atv = new Ativo();
				// atv = cAtv.procurarIdNeg(idNeg);
				// FrameInicial.setTblPapeis(montaGrid.montaTabelaCotacao(col,
				// atv.getListCot()));
				// FrameInicial.getTblPapeis().setBackground(getBackground().WHITE);
				// FrameInicial.scrLista.setViewportView(FrameInicial.getTblPapeis());
				// FrameInicial.setPainelDetalhes(new ConstrutorGrafico(idNeg));
				// FrameInicial.scrVisualiza.setViewportView(FrameInicial.getPainelDetalhes());

			}
		} else {
			// displayURL(helpURL);
		}
	}

	// public void carregarCampos(Operacao op){
	// txtfSeqOperacao.setText(String.valueOf((op.getSeqOperacao())));
	// txtfIdAtivo.setText(String.valueOf(op.getIdAtivo()));
	// txtfOrdCompra.setText(String.valueOf(op.getOrdCompra()));
	// txtfOrdVenda.setText(String.valueOf(op.getOrdVenda()));
	// txtfStartCompra.setText(String.valueOf(op.getStartCompra()));
	// txtfStartVenda.setText(String.valueOf(op.getStartVenda()));
	//
	// }

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

}
