package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class PanePDV extends JPanel {
	private JPanel pnlRoot;

	private JScrollPane scrCupom;
	private JPanel pnlButtons;
	private JPanel pnlLeft;
	private JPanel pnlRight;
	private JSplitPane pnlSplit;
	private JTextArea txtAItens;
	private JButton btn01;
	private JButton btn02;
	private JButton btn03;
	private JButton btn04;

	public PanePDV() {

		super();
		initComponents2();

	}

	private void initComponents() {

		pnlSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		pnlLeft = new JPanel(new FlowLayout());
		pnlRight = new JPanel(new FlowLayout());

		pnlButtons = new JPanel(new GridLayout(1, 4));
		btn01 = new JButton("btn01");
		btn02 = new JButton("btn02");
		btn03 = new JButton("btn03");
		btn04 = new JButton("btn04");

		pnlButtons.add(btn01);
		pnlButtons.add(btn02);
		pnlButtons.add(btn03);
		pnlButtons.add(btn04);
		pnlLeft.add(pnlButtons);

		txtAItens = new JTextArea(20, 50);
		pnlRight.add(txtAItens);
		pnlSplit.setDividerLocation(100);
		pnlSplit.add(pnlLeft);
		pnlSplit.add(pnlRight);

		pnlRoot = new JPanel(new FlowLayout());

		pnlRoot.add(pnlSplit);

	};
	private void initComponents2() {

		pnlButtons = new JPanel(new GridLayout(1, 4));
		btn01 = new JButton("btn01");
		btn02 = new JButton("btn02");
		btn03 = new JButton("btn03");
		btn04 = new JButton("btn04");

		pnlButtons.add(btn01);
		pnlButtons.add(btn02);
		pnlButtons.add(btn03);
		pnlButtons.add(btn04);

		txtAItens = new JTextArea(20, 50);

		pnlRoot = new JPanel(new FlowLayout());

		pnlRoot.add(pnlButtons);
		setVisible(true);

	};

}
