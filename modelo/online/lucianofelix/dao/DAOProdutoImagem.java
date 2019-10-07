package online.lucianofelix.dao;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import online.lucianofelix.beans.Produto;
import online.lucianofelix.beans.ProdutoImagem;
import online.lucianofelix.util.Conexao;

public class DAOProdutoImagem {

	private PreparedStatement prepStm;
	private Conexao c;
	private List<ProdutoImagem> listImagem;

	public DAOProdutoImagem() {
		super();
		c = new Conexao(ConfigS.getBdPg(), "siacecf");
	}

	public void removerImagem(ProdutoImagem prodImagem) {
		String sql = "delete from produtos_imagens where codi_produto = ? and seq_produto_imagem = ?;";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			prepStm.setString(1, prodImagem.getCodiProduto());
			prepStm.setInt(2, prodImagem.getSeqImagemProduto());
			prepStm.execute();
			c.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
			c.desconectar();
		}
	}
	/**
	 * Salva uma imagem para o produto
	 * 
	 * @param produto
	 * @param arquivo
	 * 
	 */
	public void cadastrarImagem(Produto produto, File arquivo) {
		String sql = "insert into produtos_imagens (codi_produto, imagem, nome_imagem) values (?,?,?);";
		c.conectar();
		try {
			prepStm = c.getCon().prepareStatement(sql);
			byte[] imagemArray = new byte[(int) arquivo.length()];
			DataInputStream imagemStream = new DataInputStream(
					new FileInputStream(arquivo));
			imagemStream.readFully(imagemArray);
			imagemStream.close();
			prepStm.setString(1, produto.getCodi_prod_1());
			prepStm.setBytes(2, imagemArray);
			prepStm.setString(3, arquivo.getName());
			prepStm.executeUpdate();
			c.desconectar();
		} catch (Exception e) {
			e.printStackTrace();
			c.desconectar();
		}

	}

	public List<ProdutoImagem> carregarImagens(Produto produto) {
		String sql = "SELECT seq_produto_imagem, imagem, nome_imagem, codi_produto,desc_imagem "
				+ "FROM produtos_imagens WHERE codi_produto ='"
				+ produto.getCodi_prod_1() + "';";
		Conexao c = new Conexao(ConfigS.getBdPg(), "siacecf");
		listImagem = new ArrayList<ProdutoImagem>();
		c.conectar();
		try {
			Statement stm = c.getCon().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stm.executeQuery(sql);
			ProdutoImagem imgProd;
			while (rs.next()) {
				imgProd = new ProdutoImagem();
				imgProd.setSeqImagemProduto(rs.getInt("seq_produto_imagem"));
				imgProd.setImagem(rs.getBytes("imagem"));
				imgProd.setImgIcon(new ImageIcon(rs.getBytes("imagem")));
				imgProd.setNomeImagem(rs.getString("nome_imagem"));
				imgProd.setCodiProduto(rs.getString("codi_produto"));
				listImagem.add(imgProd);
			}
			c.desconectar();
			return listImagem;
		} catch (Exception e) {
			c.desconectar();
			e.printStackTrace();
			return null;
		}

	}

}
