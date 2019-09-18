package online.lucianofelix.zebra;
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//import java.util.zip.ZipOutputStream;
//
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.filechooser.FileFilter;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.time.DateUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.bushe.swing.event.EventBus;
//import org.bushe.swing.event.annotation.AnnotationProcessor;
//import org.bushe.swing.event.annotation.EventTopicSubscriber;
//
//import br.gov.lexml.lexedit.editor.modulo.ArquivoLexEdit;
//import br.gov.lexml.lexedit.editor.modulo.ArquivoLexEdit.DefPropriedade;
//import br.gov.lexml.lexedit.editor.modulo.EditorDocumento;
//import br.gov.lexml.lexedit.editor.modulo.LocalArquivo;
//import br.gov.lexml.lexedit.editor.modulo.ServicoArquivo;
//import br.gov.lexml.lexedit.editor.modulo.TipoDocumento;
//import br.gov.lexml.lexedit.editor.principal.LexEdit;
//import br.gov.lexml.lexedit.editor.salvardocumento.SalvarDocumentoComoControllerImpl;
//import br.gov.lexml.lexedit.editor.util.FileUtil;
//import br.gov.lexml.lexedit.editor.util.exception.OperacaoCanceladaUsuarioException;
//import br.gov.lexml.lexedit.editor.util.swing.DialogoUtil;
//import br.gov.lexml.lexedit.gerenciadorarquivos.modulo.ModuloGerenciadorArquivos;
//import br.gov.lexml.lexedit.gerenciadorarquivos.util.ListaArquivosPdfThreadUtil;
//
///**
// * Gerenciador de arquivos.
// */
//public class GerenciadorArquivosControllerImpl implements GerenciadorArquivosController {
//
//	private static final Log log = LogFactory.getLog(GerenciadorArquivosControllerImpl.class);
//	
//	private static final LexEdit lexedit = LexEdit.getInstance();
//	
//    private static final Pattern PATTERN_AQUIVO_BACKUP = Pattern.compile("lexedit_backup_\\d{6}( \\(\\d+\\))?.zip");
//    
//    private List<ServicoArquivo> listServicoArquivo = new ArrayList<ServicoArquivo>();
//    
//    private Map<TipoDocumento, List<ArquivoLexEdit>> mapListArquivos = new HashMap<TipoDocumento, List<ArquivoLexEdit>>();
//    
//    protected GerenciadorArquivosView view;
//
//    //
//    // -- Construtor
//    //
//    public GerenciadorArquivosControllerImpl() {
//		// Assina eventos
//		AnnotationProcessor.process(this);
//		
//    	List<EditorDocumento> editores = lexedit.getEditores();
//    	
//    	// Inicializa coleções
//    	for(EditorDocumento ed: editores) {
//    		ServicoArquivo sa = ed.getServicoArquivo();
//    		listServicoArquivo.add(sa);
//    		mapListArquivos.put(sa.getTipoDocumento(), new ArrayList<ArquivoLexEdit>());
//    	}
//		
//		atualiza();
//    	
//        view = new GerenciadorArquivosViewImpl(this);
//    }
//
//    /**
//     * Preenche as listas de arquivos com os arquivos do file system. 
//     */
//	private void atualiza() {
//		
//    	for(ServicoArquivo sa: listServicoArquivo) {
//    		
//    		List<ArquivoLexEdit> arquivos = mapListArquivos.get(sa.getTipoDocumento());
//    		arquivos.clear();
//    		
//    		for(LocalArquivo la: sa.getLocaisArquivo()) {
//    			arquivos.addAll(sa.getArquivos(la, true));
//    		}
//    		
//    	}
//	}
//
//    
//    //
//    // -- Implementação de métodos definidos na interface.
//    //
//
//    /**
//     * @see br.gov.lexml.lexedit.gerenciadorarquivos.GerenciadorArquivosController#abrir()
//     */
//    @Override
//    public void abrir() {
//    	List<ArquivoLexEdit> arquivos = view.getArquivosSelecionados();
//        if(arquivos.size() == 1) {
//        	lexedit.abrirDocumento(arquivos.get(0).getFile());
//        }
//        else {
//        	EventBus.publish(LexEdit.CODIGO_MENU, LexEdit.MENU_ABRIR);
//        }
//    }
//    
//    @Override
//    public ArquivoLexEdit getArquivoSelecionado() {
//    	List<ArquivoLexEdit> arquivos = view.getArquivosSelecionados();
//    	if(arquivos.isEmpty()) {
//    		return null;
//    	}
//    	return arquivos.get(0);
//    }
//
//    @Override
//    public List<ArquivoLexEdit> getArquivosSelecionados() {
//    	return view.getArquivosSelecionados();
//    }
//    
//    @Override
//    public boolean excluir() {
//    	List<ArquivoLexEdit> arquivos = view.getArquivosSelecionados();
//    	
//    	if(arquivos.isEmpty()) {
//    		return false;
//    	}
//    	
//    	String msg = null;
//    	if(arquivos.size() > 1) {
//    		msg = "Deseja mesmo excluir os " + arquivos.size() + " arquivos selecionados?";
//    	}
//    	else {
//    		String nomeArquivo = FileUtil.retiraExtensao(arquivos.get(0).getNomeArquivo());
//    		msg = "Deseja mesmo excluir o arquivo \"" + nomeArquivo + "\"?";    		
//    	}
//    	
//        int resposta = DialogoUtil.mostraDialogoConfirmacao(lexedit.getMainFrame(), msg,
//                                                            "Excluir Arquivo", JOptionPane.YES_NO_OPTION);
//
//        if (resposta == JOptionPane.YES_OPTION) {
//        	for(ArquivoLexEdit a: arquivos) {
//        		remove(a);
//        	}
//            view.atualiza();
//            return true;
//        }
//
//        return false;
//    }
//
//    @Override
//    public void enviar() {
//    	
//    	ArquivoLexEdit arquivo = getArquivoSelecionado();
//    	if(arquivo == null) {
//    		return;
//    	}
//    	
//    	EditorDocumento e = lexedit.getEditorByTipoDocumento(arquivo.getTipo());
//    	if(e != null) {
//    		e.enviar(arquivo.getFile());
//    	}
//    	else {
//    		log.error("Editor não encontrado para " + arquivo.getTipo());
//    	}
//    	
//    }
//
//    @Override
//    public void mostraPropriedadesArquivo() {
//    	ArquivoLexEdit arquivo = getArquivoSelecionado();
//    	
//    	if(arquivo == null) {
//    		return;
//    	}
//    	
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("<html>");
//
//        sb.append("<b>Descrição:</b><br />");
//        sb.append(StringUtils.defaultString(arquivo.getDescricao()));
//        sb.append("<br /><br />");
//        
//        ServicoArquivo sa = lexedit.getEditorByTipoDocumento(arquivo.getTipo()).getServicoArquivo();
//        for(DefPropriedade dp: sa.getDefsPropriedade()) {
//            sb.append("<b>");
//            sb.append(dp.getLabel());
//            sb.append("</b><br />");
//            sb.append(arquivo.get(dp));
//            sb.append("<br /><br />");
//        }
//
//        sb.append("<b>Situação:</b><br />");
//        sb.append(StringUtils.defaultString(arquivo.getSituacao()));
//        sb.append("<br /><br />");
//
//        sb.append("<b>Elaborador:</b><br />");
//        sb.append(StringUtils.defaultString(arquivo.getElaborador()));
//        sb.append("<br /><br />");
//
//        sb.append("<p><b>Observação:</b><br />");
//        sb.append(StringUtils.defaultString(arquivo.getObservacao()));
//        sb.append("<br /><br /></p></html>");
//        
//        JOptionPane.showMessageDialog(lexedit.getMainFrame(), sb.toString(),
//        		"Propriedades do Documento", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    /**
//     * @see br.gov.lexml.lexedit.gerenciadorarquivos.GerenciadorArquivosController#imprimir()
//     */
//    @Override
//    public void imprimir() {
//    	
//    	ArquivoLexEdit arquivo = getArquivoSelecionado();
//    	if(arquivo == null) {
//    		return;
//    	}
//    	
//    	EditorDocumento e = lexedit.getEditorByTipoDocumento(arquivo.getTipo());
//    	if(e != null) {
//    		e.imprimir(arquivo.getFile());
//    	}
//    	else {
//    		log.error("Editor não encontrado para " + arquivo.getTipo());
//    	}
//    	
//    }
//
//    /**
//     * @see br.gov.lexml.lexedit.gerenciadorarquivos.GerenciadorArquivosController#importar()
//     */
//    @Override
//    public void importar() {
//        JFileChooser escolheOrigem = new JFileChooser();
//        JFrame janelaPrincipal = lexedit.getMainFrame();
//
//        escolheOrigem.setAcceptAllFileFilterUsed(false);
//        escolheOrigem.setFileFilter(new FileFilterXml());
//        escolheOrigem.setMultiSelectionEnabled(false);
//        escolheOrigem.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//        escolheOrigem.setDialogTitle("Importar Documento");
//        int retorno = escolheOrigem.showOpenDialog(janelaPrincipal);
//
//        try {
//            if (retorno == JFileChooser.APPROVE_OPTION) {
//            	
//                File origem = escolheOrigem.getSelectedFile();
//                
//                EditorDocumento e = lexedit.getEditorByFile(origem);
//                
//                if(e != null) {
//                	SalvarDocumentoComoControllerImpl ctl = new SalvarDocumentoComoControllerImpl(origem.getName());
//                	ctl.setServicoArquivo(e.getServicoArquivo());
//                	
//                	if (ctl.isConfirmado()) {
//                		File destino = ctl.getFile();
//                		
//                		FileUtils.copyFile(origem, destino);
//                		
//                		ArquivoLexEdit arquivo = e.getServicoArquivo().criaArquivo(ctl.getLocalArquivo(), destino, true);   
//                		adiciona(arquivo);
//                		view.atualiza();
//                		view.seleciona(arquivo);
//                	}
//                }
//                
//            }
//        }
//        catch (IOException e) {
//            log.error("Erro no importar", e);
//            DialogoUtil.mostraErro("Ocorreu o seguinte erro:\n\n" + e.getMessage()
//                                   + "\n\nNão foi possível importar o arquivo.\n");
//        }
//        catch (OperacaoCanceladaUsuarioException e) {
//            DialogoUtil.mostraErro("Usuário cancelou a operação.");
//        }
//    }
//
//    @Override
//    public void exportar() {
//    	
//        List<ArquivoLexEdit> arquivos = getArquivosSelecionados();
//        
//        JFrame janelaPrincipal = lexedit.getMainFrame();
//
//        JFileChooser escolheDestino = new JFileChooser();
//        escolheDestino.setAcceptAllFileFilterUsed(true);
//        escolheDestino.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//        escolheDestino.setDialogTitle("Selecione o diretório para gravar os arquivos");
//        int retorno = escolheDestino.showSaveDialog(janelaPrincipal);
//
//        int c = 0;
//        if (retorno == JFileChooser.APPROVE_OPTION) {
//            File dirDestino = escolheDestino.getSelectedFile();
//
//            // Exporta os arquivos
//            for(ArquivoLexEdit a: arquivos) {
//            	File origem = a.getFile();
//            	File destino = new File(dirDestino, origem.getName());
//            	destino = FileUtil.getNomeSemChoque(destino);
//            	try {
//					FileUtil.copia(origem, destino);
//					c++;
//				} catch (IOException e) {
//					log.error("Falha ao criar arquivo " + destino);
//					DialogoUtil.mostraMensagem("Erro", "Falha ao salvar o arquivo " + destino.getName(), e);
//				}
//            }
//        }
//        
//        if(c > 0) {
//        	String msg = "Exportação de " + (c == 1? c + " arquivo": c + " arquivos") + " concluída.";
//        	DialogoUtil.mostraMensagem(janelaPrincipal, msg);
//        }
//    }
//
//    @Override
//    public void backup() {
//    	
//        JFrame janelaPrincipal = lexedit.getMainFrame();
//
//		JFileChooser escolheDestino = new JFileChooser();
//		escolheDestino.setAcceptAllFileFilterUsed(true);
//		escolheDestino.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		escolheDestino.setDialogTitle("Selecione o diretório para a cópia de segurança");
//         
//        int retorno = escolheDestino.showSaveDialog(janelaPrincipal);
//
//        try {
//            if (retorno == JFileChooser.APPROVE_OPTION) {
//            	
//            	// Monta nome do arquivo
//            	File dir = escolheDestino.getSelectedFile();
//            	SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
//            	String data = sdf.format(new Date());
//            	File destino = new File(dir, "lexedit_backup_" + data + ".zip");
//            	destino = FileUtil.getNomeSemChoque(destino);
//            	
//                backup(destino);
//                
//                DialogoUtil.mostraMensagem(janelaPrincipal, "Gravado o arquivo " + destino.getName() + " com sucesso.");
//            }
//        }
//        catch (IOException e) {
//            log.error("Erro ao gravar backup", e);
//            DialogoUtil.mostraMensagem("Erro", "Não foi possível criar a cópia de segurança", e);
//        }
//    }
//
//    @Override
//    public void imprimirListaDeArquivos() {
//
//        try {
//        	List<ArquivoLexEdit> arquivos = getArquivosSelecionados();
//        	String arquivosXml = new ListaArquivosWriter(arquivos).getXml();
//            Thread thread = new ListaArquivosPdfThreadUtil(arquivos.get(0).getTipo(), arquivosXml);
//            thread.start();
//        }
//        catch (Exception e) {
//            log.error(e);
//            DialogoUtil.mostraErro("Não foi possível criar o relatório 'lista de arquivos'.\n" + e.getMessage());
//        }
//        
//    }
//
//    @Override
//    public JPanel getPainel() {
//        return view.getPanel();
//    }
//
//    //
//    // -- Métodos privados
//    //
//
//    protected class FileFilterXml extends FileFilter {
//
//        @Override
//        public boolean accept(final File arquivo) {
//            if (arquivo.isDirectory()) {
//                return true;
//            }
//
//            String extension = FileUtil.getExtensao(arquivo);
//
//            if ("xml".equals(extension)) {
//                return true;
//            }
//
//            return false;
//        }
//
//        @Override
//        public String getDescription() {
//            return "Arquivo XML";
//        }
//    }
//
//    protected class FileFilterZip extends FileFilter {
//
//        @Override
//        public boolean accept(final File arquivo) {
//            if (arquivo.isDirectory()) {
//                return true;
//            }
//
//            if (PATTERN_AQUIVO_BACKUP.matcher(arquivo.getName()).matches()) {
//                return true;
//            }
//
//            return false;
//        }
//
//        @Override
//        public String getDescription() {
//            return "Cópia de segurança 'lexedit_backup_AAMMDD.zip'";
//        }
//    }
//
//    @Override
//    public void restaurar() {
//
//    	JFrame janelaPrincipal = lexedit.getMainFrame();
//
//    	JFileChooser escolheOrigem = new JFileChooser();
//        escolheOrigem.setAcceptAllFileFilterUsed(false);
//        escolheOrigem.setFileFilter(new FileFilterZip());
//        escolheOrigem.setMultiSelectionEnabled(false);
//        escolheOrigem.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//        escolheOrigem.setDialogTitle("Selecione o arquivo com a cópia de segurança");
//        int retorno = escolheOrigem.showOpenDialog(janelaPrincipal);
//
//        try {
//            int count = 0;
//            if (retorno == JFileChooser.APPROVE_OPTION) {
//                File arquivo = escolheOrigem.getSelectedFile();
//                
//                if(!PATTERN_AQUIVO_BACKUP.matcher(arquivo.getName()).matches()) {
//                    throw new IOException("Não se trata de um arquivo de backup válido.");
//                }
//                
//                count = restaurar(arquivo);
//
//                atualiza();
//                
//                view.atualiza();
//                
//                DialogoUtil.mostraAviso(janelaPrincipal, "Foram restaurados " + count + " arquivos.");
//            }
//        }
//        catch (IOException e) {
//            log.error("Erro ao restaurar", e);
//            DialogoUtil.mostraErro("Ocorreu o seguinte erro ao restaurar os arquivos:\n\n" + e.getMessage() + "\n");
//        }
//        
//    }
//
//	@EventTopicSubscriber(topic = ModuloGerenciadorArquivos.CODIGO_EVENTOS)
//	public void trataMenu(String topic, String idEvento) {
//		if(idEvento.equals(ModuloGerenciadorArquivos.EVENTO_SELECAO_ARQUIVO)) {
//			view.habilitaBotoes();
//		}
//    }
//	
//	@EventTopicSubscriber(topic = LexEdit.CODIGO_EVENTOS)
//	public void trataEventosEditor(String topic, String idMenu) {
//
//		if(idMenu.equals(LexEdit.EVENTO_ARQUIVO_SALVO)) {
//			ArquivoLexEdit arquivo = lexedit.getUltimoArquivoSalvo();
//    		adiciona(arquivo);
//    		view.atualiza();
//    		view.seleciona(arquivo);
//		}
//		
//	}
//	
//    
//    @Override
//    public boolean finaliza() {
//    	return true;
//    }
//    
//    public List<ArquivoLexEdit> getArquivos(TipoDocumento tipoDocumento) {
//    	return mapListArquivos.get(tipoDocumento); 
//    }
//
//    private void remove(ArquivoLexEdit arquivo) {
//    	List<ArquivoLexEdit> arquivos = mapListArquivos.get(arquivo.getTipo());
//    	if(arquivos != null) {
//    		if(arquivo.getFile().delete()) {
//    			arquivos.remove(arquivo);
//    		}
//    		else {
//    			DialogoUtil.mostraErro("Ocorreu um erro ao excluir o arquivo.");
//    		}
//    	}
//    }
//
//    private void backup(final File arquivoSaida) throws IOException {
//        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(arquivoSaida));
//        
//        for(EditorDocumento e: lexedit.getEditores()) {
//        	ServicoArquivo sa = e.getServicoArquivo();
//        	
//        	for(LocalArquivo la: sa.getLocaisArquivo()) {
//                zipDir(la.getDiretorio(), sa.getTipoDocumento().getExtensao(), out);
//        	}
//        	
//        }
//        
//        out.close();
//    }
//
//    private void adiciona(ArquivoLexEdit arquivo) {
//    	List<ArquivoLexEdit> arquivos = mapListArquivos.get(arquivo.getTipo());
//    	if(arquivos != null) {
//    		arquivos.remove(arquivo); // remove se já existir
//    		arquivos.add(arquivo);
//    	}
//    }
//
//    private void zipDir(File zipDir, String extensao, final ZipOutputStream zos) throws IOException {
//    	
//        String[] dirList = zipDir.list();
//
//        for (int i = 0; i < dirList.length; i++) {
//            File f = new File(zipDir, dirList[i]);
//            if (f.isDirectory()) {
//                zipDir(f, extensao, zos);
//                continue;
//            }
//            else if(f.getName().toLowerCase().endsWith(extensao)) {
//            	FileInputStream fis = new FileInputStream(f);
//            	ZipEntry anEntry = new ZipEntry(f.getPath());
//            	anEntry.setTime(f.lastModified());
//            	zos.putNextEntry(anEntry);
//            	IOUtils.copy(fis, zos);
//            	zos.flush();
//            	fis.close();
//            }
//        }
//    }
//
//	private int restaurar(File arquivo) throws IOException {
//
//    	JFrame janelaPrincipal = lexedit.getMainFrame();
//
//    	int count = 0;
//		
//        ZipInputStream zip = new ZipInputStream(new FileInputStream(arquivo));
//
//        ZipEntry entry = null;
//
//        while ((entry = zip.getNextEntry()) != null) {
//            String outFilename = entry.getName();
//
//            File file = new File(outFilename);
//
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            
//            if (file.exists()) {
//                Date dtZip = DateUtils.truncate(new Date(entry.getTime()), Calendar.MINUTE);
//                Date dtFile = DateUtils.truncate(new Date(file.lastModified()), Calendar.MINUTE);
//                
//                if (dtFile.after(dtZip)) {
//					System.out.println("----------------------------");
//					System.out.println(file.getAbsolutePath());
//					System.out.println(sdf.format(dtFile));
//					System.out.println(sdf.format(dtZip));
//
//                	int resposta = DialogoUtil.mostraDialogoConfirmacao(janelaPrincipal,
//                            "O arquivo " + file.getName() + " possui uma cópia no destino modificada mais recentemente.\n" +
//                            "Deseja substituir?", "Efetuar cópia de segurança", JOptionPane.YES_NO_OPTION);
//
//                    if (resposta == JOptionPane.NO_OPTION) {
//                        continue;
//                    }
//                }
//            }
//
//            OutputStream out = new FileOutputStream(file);
//            IOUtils.copy(zip, out);
//            out.close();
//            
//            if(!file.setLastModified(entry.getTime())) {
//            	System.out.println("Falha ao alterar a data do arquivo.");
//            }
//            
//            count++;
//        }
//        zip.close();
//
//		return count;
//	}
//    
//}