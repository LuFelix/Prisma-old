package online.lucianofelix.util;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.dao.ConfigS;
import online.lucianofelix.dao.DAOProdutoImagem;
import online.lucianofelix.visao.FrameImagens;
import online.lucianofelix.visao.PainelProdutos;

public class ManipulaImagensProduto extends JFrame {
	private Conexao c;
	private JPanel pnlBotoes;
	private JPanel pnlPrincipal;
	PreparedStatement pstmt;
	private Produto prod;
	static private String newline = "\n";
	private JTextArea log;
	private JFileChooser fc;
	static Image imagem;
	private DAOProdutoImagem daoProdImg;
	private File file;
	private List<File> listFiles;

	public ManipulaImagensProduto() {
		super();
		initComponents();

	}// Fim do Construtor
	public ManipulaImagensProduto(Produto produto) {
		super();
		initComponents();
		this.prod = produto;
	}// Fim do Construtor

	void initComponents() {
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
		daoProdImg = new DAOProdutoImagem();
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		listFiles = new ArrayList<File>();
		JScrollPane logScrollPane = new JScrollPane(log);

		JButton btnAnexa = new JButton("Anexar");
		btnAnexa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abreSelecaoArquivo();

			}
		});

		JButton btnSalva = new JButton("Salvar");
		btnSalva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				salvarImagensSelecionadas();

			}
		});
		JButton btnExibe = new JButton("Exibir");
		btnExibe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameImagens(listFiles.get(listFiles.size() - 1));

			}
		});
		JButton btnRemove = new JButton("Remover");
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		JButton btnCaptura = new JButton("WebCam");
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		pnlBotoes = new JPanel(new GridLayout(1, 5));
		pnlBotoes.add(btnAnexa);
		pnlBotoes.add(btnSalva);
		pnlBotoes.add(btnExibe);
		pnlBotoes.add(btnRemove);
		pnlBotoes.add(btnCaptura);
		pnlPrincipal = new JPanel(new BorderLayout());
		pnlPrincipal.add(pnlBotoes, BorderLayout.PAGE_START);
		pnlPrincipal.add(logScrollPane, BorderLayout.CENTER);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				JFrame frame = new JFrame("Manipula Imagens");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.add(pnlPrincipal);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}
		});

	}
	@Override
	public void paint(Graphics graphics) {
		graphics.drawImage(imagem, 0, 0, null);
	}

	public void ExibirImagem(String url) {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		imagem = toolkit.getImage(url);
		MediaTracker mediaTracker = new MediaTracker(this);
		mediaTracker.addImage(imagem, 0);

		try {
			mediaTracker.waitForID(0);
		} catch (InterruptedException ie) {
			System.err.println(ie);
			System.exit(1);
		}
		setSize(imagem.getWidth(null), imagem.getHeight(null));
		setVisible(true);
	}
	public void ExibirImagem(File arquivo) {
		JFrame frameImagem = new JFrame(arquivo.getName());
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		imagem = toolkit.getImage(arquivo.getAbsolutePath());
		MediaTracker mediaTracker = new MediaTracker(frameImagem);
		mediaTracker.addImage(imagem, 0);

		try {
			mediaTracker.waitForID(0);

		} catch (InterruptedException ie) {
			System.err.println(ie);
			System.exit(1);
		}

		frameImagem.setSize(imagem.getWidth(null), imagem.getHeight(null));
		frameImagem.repaint();
		frameImagem.setVisible(true);

	}

	void salvarImagensSelecionadas() {
		for (int j = 0; j < listFiles.size(); j++) {
			daoProdImg.cadastrarImagem(prod, listFiles.get(j));
		}
		PainelProdutos.carregarImagensProd(prod);
	}

	void abreSelecaoArquivo() {

		// Set up the file chooser.
		if (fc == null) {
			UIManager.put("FileChooser.lookInLabelMnemonic", "E");
			UIManager.put("FileChooser.lookInLabelText", "Examinar em");

			UIManager.put("FileChooser.saveInLabelMnemonic", "S");
			UIManager.put("FileChooser.saveInLabelText", "Salvar em");

			UIManager.put("FileChooser.upFolderToolTipText", "Um nível acima");
			UIManager.put("FileChooser.upFolderAccessibleName",
					"Um nível acima");

			UIManager.put("FileChooser.homeFolderToolTipText", "Desktop");
			UIManager.put("FileChooser.homeFolderAccessibleName", "Desktop");

			UIManager.put("FileChooser.newFolderToolTipText",
					"Criar nova pasta");
			UIManager.put("FileChooser.newFolderAccessibleName",
					"Criar nova pasta");

			UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
			UIManager.put("FileChooser.listViewButtonAccessibleName", "Lista");

			UIManager.put("FileChooser.detailsViewButtonToolTipText",
					"Detalhes");
			UIManager.put("FileChooser.detailsViewButtonAccessibleName",
					"Detalhes");

			UIManager.put("FileChooser.fileNameLabelMnemonic", "N");
			UIManager.put("FileChooser.fileNameLabelText", "Nome do arquivo");

			UIManager.put("FileChooser.filesOfTypeLabelMnemonic", "A");
			UIManager.put("FileChooser.filesOfTypeLabelText",
					"Arquivos do tipo");

			UIManager.put("FileChooser.fileNameHeaderText", "Nome");
			UIManager.put("FileChooser.fileSizeHeaderText", "Tamanho");
			UIManager.put("FileChooser.fileTypeHeaderText", "Tipo");
			UIManager.put("FileChooser.fileDateHeaderText", "Data");
			UIManager.put("FileChooser.fileAttrHeaderText", "Atributos");

			UIManager.put("FileChooser.cancelButtonText", "Cancelar");
			UIManager.put("FileChooser.cancelButtonMnemonic", "C");
			UIManager.put("FileChooser.cancelButtonToolTipText", "Cancelar");

			UIManager.put("FileChooser.openButtonText", "Abrir / Salvar");
			UIManager.put("FileChooser.openButtonMnemonic", "A");
			UIManager.put("FileChooser.openButtonToolTipText", "Abrir");

			UIManager.put("FileChooser.saveButtonText", "Salvar =]");
			UIManager.put("FileChooser.saveButtonToolTipText", "S");
			UIManager.put("FileChooser.saveButtonToolTipText", "Salvar");

			UIManager.put("FileChooser.updateButtonText", "Alterar");
			UIManager.put("FileChooser.updateButtonToolTipText", "A");
			UIManager.put("FileChooser.updateButtonToolTipText", "Alterar");

			UIManager.put("FileChooser.helpButtonText", "Ajuda");
			UIManager.put("FileChooser.helpButtonToolTipText", "A");
			UIManager.put("FileChooser.helpButtonToolTipText", "Ajuda");

			UIManager.put("FileChooser.acceptAllFileFilterText",
					"Todos os arquivos");
			fc = new JFileChooser("c:\\");

			// Add a custom file filter and disable the default
			// (Accept All) file filter.
			fc.addChoosableFileFilter(new ImageFilter());
			fc.setAcceptAllFileFilterUsed(false);

			// Add custom icons for file types.
			fc.setFileView(new ImageFileView());

			// Add the preview pane.
			fc.setAccessory(new ImagePreview(fc));
		}

		// Show it.
		int returnVal = fc.showDialog(ManipulaImagensProduto.this, "Adicionar");

		// Process the results.
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			listFiles.add(fc.getSelectedFile());
			log.append("Adicionando arquivo: " + fc.getSelectedFile().getName()
					+ "." + newline);

		} else {
			log.append("Adição cancelada." + newline);
		}
		log.setCaretPosition(log.getDocument().getLength());

		// Reset the file chooser for the next time it's shown.
		fc.setSelectedFile(null);
	}

}
