package online.lucianofelix.util;
import java.io.File;
import java.util.zip.ZipFile;

import javax.swing.JFileChooser;
/**
 * 
 * @author Luciano de Oliveira Felix Silva
 * Esta classe tem o objetivo de trabalhar com as janelas de seleção da classe JFileChooser retornando o arquivo
 * selecionado através da escolha. Lembrando que um arquivo ainda aqui é um endereço do arquivo e não o arquivo 
 * propriamente. O arquivo será apontado para que as classes manipuladoras de arquivo, seja txt, xml, zip ou outro
 * tipo qualquer. Esta classe é uma interface entre a camada de Visão e Controle efetuando o diálogo com o usuário.
 *
 */


public class SelecionaArquivo {

	private JFileChooser  escolheLocal;
	private JFileChooser escolherArquivo;
	private String pasta = null; 
	private String nomArquivo = null;
	
	public String getPasta() {
		return pasta;
	}

	public void setPasta(String pasta) {
		this.pasta = pasta;
	}

	
	public String getNomArquivo() {
		return nomArquivo;
	}

	public void setNomArquivo(String nomArquivo) {
		this.nomArquivo = nomArquivo;
	}
	
	
	// TODO +++++Seleciona o arquivo que deseja abrir manipulando as caixas de diálogo retornado o arquivo selecionado++++++++++++
	
	public File escolheArquivoAbrir(File arquivo){
			
			escolherArquivo = new JFileChooser();
			escolherArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
			escolherArquivo.showOpenDialog(escolherArquivo);
			arquivo = escolherArquivo.getSelectedFile();
			
			return arquivo;
			
	}
	
		

//-----------------------------Este método retorna verdadeiro para a seleção de aprovado o nome do arquivo setando
//								as variáveis pasta e nomArquivo com os respectivos endereços 
//
	
	boolean escolheLocalSalvar(SelecionaArquivo local){
		
		escolheLocal =  new JFileChooser();
		
		int opcao = escolheLocal.showSaveDialog(null);
		
		if (opcao == JFileChooser.APPROVE_OPTION) {
			pasta = escolheLocal.getSelectedFile().getParent();
			nomArquivo = escolheLocal.getSelectedFile().getName();
			return true;
			
		}
				
		return false;
	}
	
}
