package online.lucianofelix.controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTable;

import online.lucianofelix.beans.TipoSistema;
import online.lucianofelix.dao.DAOTiposSistema;
import online.lucianofelix.tableModels.commom.TableModelTipoSistema;
import online.lucianofelix.util.CellRenderer;
import online.lucianofelix.visao.FrameInicial;
import online.lucianofelix.visao.PainelConta;
import online.lucianofelix.visao.PainelGrupo;

public class ControlaTipoSistema {
	private DAOTiposSistema daoTipo;
	private TableModelTipoSistema mdlTbl;
	private JTable tbl01;
	private TipoSistema tipoS;

	public ControlaTipoSistema() {
		daoTipo = new DAOTiposSistema();
	};

	public List<TipoSistema> listTipo() {
		return daoTipo.pesquisaString("");
	};

	public JTable tblTipoS(String str) {
		tbl01 = new JTable();
		tbl01.add(new CellRenderer());
		mdlTbl = new TableModelTipoSistema(daoTipo.pesquisaString(""));
		tbl01.setModel(mdlTbl);
		tbl01.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent tecla) {
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					tipoS = mdlTbl.getTipoS(tbl01.getSelectedRow());
					PainelGrupo.carregarCampos(tipoS);
					FrameInicial.atualizaTela();
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				}
			}

			@Override
			public void keyPressed(KeyEvent tecla) {
				int posicao = tbl01.getSelectedRow();
				if (tecla.getExtendedKeyCode() == 40
						|| tecla.getExtendedKeyCode() == 38) {
					tipoS = mdlTbl.getTipoS(tbl01.getSelectedRow());
					PainelGrupo.carregarCampos(tipoS);
				} else if (tecla.getExtendedKeyCode() == 27) {// esc
					FrameInicial.getTxtfPesquisa().grabFocus();
				} else if (tecla.getExtendedKeyCode() == 10) {
					tipoS = mdlTbl.getTipoS(tbl01.getSelectedRow());
					PainelGrupo.carregarCampos(tipoS);
					// funcaoSobrescrever();
					FrameInicial.getTabela().changeSelection(--posicao, 0,
							false, false);
					PainelConta.getTxtFNomeConta().grabFocus();
				}
			}
		});
		tbl01.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				tipoS = mdlTbl.getTipoS(tbl01.getSelectedRow());
				PainelGrupo.carregarCampos(tipoS);
				FrameInicial.atualizaTela();
			}
		});

		tbl01.setShowHorizontalLines(true);
		ajusta_tamanho_coluna();
		return tbl01;
	}
	private void ajusta_tamanho_coluna() {
		// tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl01.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbl01.getColumnModel().getColumn(1).setPreferredWidth(220);

	}
	public void iniciar() {
		FrameInicial.setTabela(tblTipoS(""));
		FrameInicial.getTabela().setRowSelectionInterval(0, 0);
		tipoS = mdlTbl.getTipoS(FrameInicial.getTabela().getSelectedRow());
		FrameInicial.setPainelVisualiza(new PainelGrupo(tipoS));
		FrameInicial.atualizaTela();

	};

}
