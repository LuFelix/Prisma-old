package online.lucianofelix.zebra;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import online.lucianofelix.beans.AtivoYahoo;
import online.lucianofelix.beans.CotacaoYahoo;
import online.lucianofelix.dao.DAOAtvYahoo;
import online.lucianofelix.dao.DAOCotacaoYahoo;
import online.lucianofelix.visao.AbaFuse;

/**
 * This program demonstrates the use of a progress bar to monitor the progress of a thread.
 * @version 1.04 2007-08-01
 * @author Cay Horstmann
 */
//public class ProgressBarTest
//{
//   public static void main(String[] args)
//   {
//      EventQueue.invokeLater(new Runnable()
//         {
//            public void run()
//            {
//               JFrame frame = new ProgressBarFrame();
//               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//               frame.setVisible(true);
//            }
//         });
//   }
//}

/**
 * A frame that contains a button to launch a simulated activity, a progress
 * bar, and a text area for the activity output.
 */
public class ProgressBarFrame extends JFrame {

	private JButton startButton;
	private JProgressBar progressBar;
	private JCheckBox checkBox;
	private JTextArea textArea;
	private JScrollPane scrStatus;

	public ProgressBarFrame() {

		DAOAtvYahoo daoAtvYahoo = new DAOAtvYahoo();
		final List<AtivoYahoo> listAtvYahoo = daoAtvYahoo.listarOrdIdNeg();
		setTitle("Carregar Cotações");
		setSize(350, 150);
		setLocationRelativeTo(null);

		// this text area holds the activity output
		textArea = new JTextArea();
		textArea.setBounds(5, 5, 340, 105);
		scrStatus = new JScrollPane(textArea);
		scrStatus.setBounds(5, 5, 340, 105);

		// set up panel with button and progress bar
		JPanel panel = new JPanel();
		panel.setLayout(null);
		startButton = new JButton("Start");
		startButton.setBounds(5, 5, 20, 20);

		progressBar = new JProgressBar(0, listAtvYahoo.size());
		progressBar.setStringPainted(true);
		progressBar.setBounds(5, 115, 340, 30);

		// panel.add(startButton);
		panel.add(progressBar);
		panel.add(scrStatus);
		// checkBox = new JCheckBox("indeterminate");
		// checkBox.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent event)
		// {
		// progressBar.setIndeterminate(checkBox.isSelected());
		// progressBar.setStringPainted(!progressBar.isIndeterminate());
		// }
		// });
		//
		// panel.add(checkBox);

		add(panel);
		setVisible(true);

		activity = new SimulatedActivity(listAtvYahoo.size());
		activity.execute();
		// set up the button action
		//
		// startButton.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent event)
		// {
		// startButton.setEnabled(false);
		// activity = new SimulatedActivity(listAtvYahoo.size());
		// activity.execute();
		// }
		// });
	}

	private SimulatedActivity activity;

	class SimulatedActivity extends SwingWorker<Void, Integer> {
		private List<AtivoYahoo> listAtvYahoo;
		/**
		 * Constructs the simulated activity that increments a counter from 0 to
		 * a given target.
		 * 
		 * @param t
		 *            the target value of the counter.
		 */
		private DAOCotacaoYahoo daoCotYahoo;
		private DAOAtvYahoo daoAtvYahoo;
		private int tamLista;
		private List<CotacaoYahoo> listCotacoes;

		public SimulatedActivity(int t) {
			daoAtvYahoo = new DAOAtvYahoo();
			daoCotYahoo = new DAOCotacaoYahoo();
			listAtvYahoo = daoAtvYahoo.listarOrdIdNeg();
			System.out.println("Tamanho lista de Ativos: " + listAtvYahoo.size());
			tamLista = listAtvYahoo.size() - 1;
			current = 0;
			target = tamLista;
		}

		@Override
		protected Void doInBackground() throws Exception {

			try {
				while (current < target) {
					listAtvYahoo.get(current);
					listCotacoes = daoCotYahoo.conCotAtvOrdDtAscend(listAtvYahoo.get(current).getIdYahoo());
					listAtvYahoo.get(current).setListCot(listCotacoes);
					System.out.println("Cotações carregadas para: " + listAtvYahoo.get(current).getIdYahoo());
					Thread.sleep(1);

					current++;
					publish(current);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void process(List<Integer> chunks) {
			for (Integer chunk : chunks) {
				textArea.append(chunk + " da lista. Carregou para=>> " + listAtvYahoo.get(current).getIdYahoo() + "\n");
				progressBar.setValue(chunk);
			}
		}

		@Override
		protected void done() {
			// AbaMinerador.setListAtvSis(listAtvYahoo);
			AbaFuse.minerarQtdNegMercVista();
			startButton.setEnabled(true);
		}

		private int current;
		private int target;
	}
}