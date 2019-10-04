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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import online.lucianofelix.dao.ConfigS;
import online.lucianofelix.dao.DAOProdutoPrepSTM;

public class ManipulaImagens extends JPanel {
	private Conexao c;
	private JPanel pnlBotoes;
	PreparedStatement pstmt;
	static private String newline = "\n";
	private JTextArea log;
	private JFileChooser fc;
	static Image imagem;
	private DAOProdutoPrepSTM daoProd;

	public ManipulaImagens() {
		super(new BorderLayout());
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
		daoProd = new DAOProdutoPrepSTM();
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);

		JScrollPane logScrollPane = new JScrollPane(log);
		JButton btnAnexa = new JButton("Anexar...");
		btnAnexa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abreSelecaoArquivo();

			}
		});
		JButton btnSalva = new JButton("Salvar");
		pnlBotoes = new JPanel(new GridLayout(1, 2));
		pnlBotoes.add(btnAnexa);
		pnlBotoes.add(btnSalva);
		add(logScrollPane, BorderLayout.CENTER);
		add(pnlBotoes, BorderLayout.PAGE_END);

	}// Fim do Construtor

	public boolean salvarFuncionarioFotoCorpo(String imagem) {
		String insertRow = "UPDATE cfuncionario SET fotocorpo=? WHERE id = ;";
		try {
			pstmt = c.getCon().prepareStatement(insertRow);
			File imagemFile = new File(imagem);
			byte[] imagemArray = new byte[(int) imagemFile.length()];
			DataInputStream imagemStream = new DataInputStream(
					new FileInputStream(imagemFile));
			imagemStream.readFully(imagemArray);
			imagemStream.close();
			pstmt.setBytes(1, imagemArray);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public Image capturaImagemRosto() {
		byte[] imageByte = null;
		Image image = null;
		String sql = "SELECT fotorosto FROM cfuncionario WHERE id = ";
		try {
			Statement stm = c.getCon().createStatement();
			ResultSet rset = stm.executeQuery(sql);

			while (rset.next()) {
				imageByte = rset.getBytes("fotorosto");
				ImageIcon icon = new ImageIcon(imageByte);
				return icon.getImage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	public void carregarImagem(String nome, int tipo) {

		UIManager.put("FileChooser.lookInLabelMnemonic", "E");
		UIManager.put("FileChooser.lookInLabelText", "Examinar em");

		UIManager.put("FileChooser.saveInLabelMnemonic", "S");
		UIManager.put("FileChooser.saveInLabelText", "Salvar em");

		UIManager.put("FileChooser.upFolderToolTipText", "Um nível acima");
		UIManager.put("FileChooser.upFolderAccessibleName", "Um nível acima");

		UIManager.put("FileChooser.homeFolderToolTipText", "Desktop");
		UIManager.put("FileChooser.homeFolderAccessibleName", "Desktop");

		UIManager.put("FileChooser.newFolderToolTipText", "Criar nova pasta");
		UIManager.put("FileChooser.newFolderAccessibleName",
				"Criar nova pasta");

		UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
		UIManager.put("FileChooser.listViewButtonAccessibleName", "Lista");

		UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalhes");
		UIManager.put("FileChooser.detailsViewButtonAccessibleName",
				"Detalhes");

		UIManager.put("FileChooser.fileNameLabelMnemonic", "N");
		UIManager.put("FileChooser.fileNameLabelText", "Nome do arquivo");

		UIManager.put("FileChooser.filesOfTypeLabelMnemonic", "A");
		UIManager.put("FileChooser.filesOfTypeLabelText", "Arquivos do tipo");

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
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileView(new ImageFileView());
		fc.setAccessory(new ImagePreview(fc));
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int res = fc.showDialog(null, "Abrir");

		if (res == JFileChooser.APPROVE_OPTION) {
			File arquivo = fc.getSelectedFile();
			try {
				BufferedReader in = new BufferedReader(new FileReader(arquivo));
				String minhaImagem = arquivo.getAbsolutePath();
				in.close();
				if (tipo == 1) {
					// daoProd.salvarFoto(minhaImagem);
					System.out.println(minhaImagem);

					ExibirImagem(minhaImagem);

					JOptionPane.showMessageDialog(null,
							"Foto do Perfil Salva com Sucesso!.",
							"SIMPRO - STIMAGAZINE",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					// daoProd.salvarFoto(minhaImagem);
					JOptionPane.showMessageDialog(null,
							"Foto Adicionada com Sucesso!.",
							"SIMPRO - STIMAGAZINE",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}
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
		int returnVal = fc.showDialog(ManipulaImagens.this, "Adicionar");

		// Process the results.
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			log.append(
					"Adicionando arquivo: " + file.getName() + "." + newline);

		} else {
			log.append("Adição cancelada." + newline);
		}
		log.setCaretPosition(log.getDocument().getLength());

		// Reset the file chooser for the next time it's shown.
		fc.setSelectedFile(null);
	}

}
