package online.lucianofelix.adapter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import online.lucianofelix.beans.CotacaoAtivo;
import online.lucianofelix.dao.DAOCotacaoAtivo;
import online.lucianofelix.indicadoresAT.MediaMovelSimples;
import online.lucianofelix.util.SerieTemporal;

public class ConstrutorGrafico extends JPanel {

	private static JFreeChart chart;
	private JLabel lblGrafico;
	private static ChartPanel chartPanel;
	private static JPanel painelGrafico;
	static double valDouble;
	static DefaultCategoryDataset defCatDataset;
	static DefaultPieDataset pieDataset;
	DefaultCategoryDataset datasetb;
	static List<CotacaoAtivo> arrayCot;
	static DAOCotacaoAtivo daoCot;

	public ConstrutorGrafico() {
		super();

		arrayCot = new ArrayList<CotacaoAtivo>();
		daoCot = new DAOCotacaoAtivo();
		valDouble = 0;

	}

	public JPanel graficoBarrasDespCentroCusto() {
		painelGrafico = new JPanel();
		datasetb = new DefaultCategoryDataset();
		datasetb.addValue(8, "TI", "TI");
		datasetb.addValue(15, "Teste", "Teste");
		datasetb.addValue(18, "Casa", "Casa");
		chart = ChartFactory.createBarChart("Despesas X Centro de Custo", "$",
				"Centro de Custo", datasetb);
		chart.setBackgroundPaint(Color.BLACK);
		chart.setBorderPaint(Color.BLACK);
		chart.getTitle().setVisible(false);
		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.BLACK);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(420, 500));
		painelGrafico.add(chartPanel);
		return painelGrafico;
	}

	public JPanel graficoPizzaRecDesp(float receita, float despesa) {
		painelGrafico = new JPanel();
		pieDataset = new DefaultPieDataset();
		pieDataset.setValue("Despesas", despesa);
		pieDataset.setValue("Receitas", receita);
		chart = ChartFactory.createPieChart("Receitas X Despesas", pieDataset);
		chart.setBackgroundPaint(Color.BLACK);
		chart.setBorderPaint(Color.BLACK);
		chart.getTitle().setVisible(false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelLinksVisible(true);
		plot.setNoDataMessage(
				"Não existem dados para serem exibidos no gráfico");

		// plot.setStartAngle(90);
		plot.setDirection(Rotation.CLOCKWISE);

		plot.setBackgroundPaint(Color.DARK_GRAY);
		// plot.setInteriorGap(0.20);

		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(420, 500));
		painelGrafico.add(chartPanel);
		return painelGrafico;
	}

	public JPanel graficoLinhaPrecFech(String idNeg) {
		painelGrafico = new JPanel(new GridLayout());
		defCatDataset = new DefaultCategoryDataset();
		arrayCot = daoCot.conCotAtvOrdDtAscend(idNeg);
		for (int i = 0; i < arrayCot.size(); i++) {
			valDouble = arrayCot.get(i).getPreFec();
			defCatDataset.addValue(valDouble, "Pre Fec",
					String.valueOf(arrayCot.get(i).getDataCotacao()));
		}

		chart = ChartFactory.createLineChart("Gráfico de Preços", "", "",
				defCatDataset, PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundPaint(Color.BLACK);
		chart.getTitle().setVisible(false);
		// chart.getSubtitle(0).setVisible(false);
		// System.out.println("chart.subtitlescount: " +
		// chart.getSubtitleCount());
		// cria um elemento plot para buscar o plot do grÃ¡fico e operar
		// mudanÃ§as
		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// Muda a cor do fundo do grÃ¡fico
		plot.setBackgroundPaint(Color.BLACK);
		// muda a cor das linhas do grid do grÃ¡fico
		plot.setRangeGridlinePaint(Color.WHITE);
		// cria um elemento de faixa para buscar a faixa do plot do grafico e
		// operar mudanÃ§as
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		// deixa so a faixa de valores que sÃ£o inerentes ao grÃ¡fico(exclui o
		// zero)
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(false);

		// Cria um elemento para renderizar e busca o item que sera renderizado
		CategoryItemRenderer categoryitemrenderer = plot.getRenderer();
		// muda a cor da linha do grafico
		categoryitemrenderer.setSeriesPaint(0, Color.CYAN);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		renderer.setSeriesShape(0, renderer.DEFAULT_SHAPE);
		// tornar a linha do grafico visivel (o inteiro e o numero da linha)
		// renderer.setSeriesLinesVisible(0, true);

		// mudar linhas - pontilhada
		renderer.setSeriesStroke(0,
				new BasicStroke(2.0f, BasicStroke.JOIN_ROUND,
						BasicStroke.JOIN_ROUND, 2.0f, new float[]{10.0f, 6.0f},
						1.0f));

		// renderer.setSeriesStroke(
		// 0, new BasicStroke(
		// 2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
		// 1.0f, new float[] {6.0f, 6.0f}, 0.0f
		// )
		// );
		// renderer.setSeriesStroke(
		// 0, new BasicStroke(
		// 2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
		// 1.0f, new float[] {2.0f, 6.0f}, 0.0f
		// )
		// );
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(503, 323));
		painelGrafico.add(chartPanel);
		return painelGrafico;

		// OutputStream arquivo;
		// try {
		// arquivo = new FileOutputStream("D:\\grafico.png");
		// ChartUtilities.writeChartAsJPEG(arquivo, chart, 720, 680);
		// arquivo.close();
		//
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static JPanel graficoPreFec(String idNeg) {
		painelGrafico = new JPanel();
		defCatDataset = new DefaultCategoryDataset();
		arrayCot = daoCot.conCotAtvOrdDtAscend(idNeg);
		for (int i = 0; i < arrayCot.size(); i++) {
			valDouble = arrayCot.get(i).getPreFec();
			defCatDataset.addValue(valDouble, "Pre Fec",
					String.valueOf(arrayCot.get(i).getDataCotacao()));
		}
		chart = ChartFactory.createLineChart("Grafico de Preï¿½os", "Dia",
				"Valor", defCatDataset, PlotOrientation.VERTICAL, true, true,
				false);
		chartPanel = new ChartPanel(chart);
		painelGrafico.add(chartPanel);
		return painelGrafico;
	}

	public JLabel ativoParaGraficoPreFec(String idNeg) {
		arrayCot = daoCot.conCotAtvOrdDtAscend(idNeg);
		for (int i = 0; i < arrayCot.size(); i++) {
			valDouble = arrayCot.get(i).getPreFec();
			defCatDataset.addValue(valDouble, "Pre Fec",
					String.valueOf(arrayCot.get(i).getDataCotacao().getDay()));
		}
		chart = ChartFactory.createLineChart("Grafico de Preï¿½os", "Dia",
				"Valor", defCatDataset, PlotOrientation.VERTICAL, true, true,
				false);
		chart.setBackgroundPaint(getBackground());
		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.BLACK);
		plot.setWeight(1000);
		CategoryItemRenderer categoryitemrenderer = plot.getRenderer();
		categoryitemrenderer.setSeriesPaint(0, Color.cyan);
		OutputStream arquivo;
		try {
			arquivo = new FileOutputStream("D:\\grafico.png");
			ChartUtilities.writeChartAsJPEG(arquivo, chart, 720, 680);
			arquivo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblGrafico = new JLabel(new ImageIcon("D:\\grafico.png"));
		return lblGrafico;
	}

	public JLabel ativoParaGraficoMediaMovSimples(String idNeg, int dias) {

		ArrayList<Float> arrayMediaM = new ArrayList<Float>();
		ArrayList<Float> arrayValores = new ArrayList<Float>();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<CotacaoAtivo> arrayCot = new ArrayList<CotacaoAtivo>();
		MediaMovelSimples mediaM = new MediaMovelSimples();
		DAOCotacaoAtivo daoCot = new DAOCotacaoAtivo();
		SerieTemporal serie = new SerieTemporal();
		double valDouble = 0;
		arrayCot = daoCot.conCotAtvOrdDtAscend(idNeg);
		arrayValores = serie.GeraSerieTemporalPreFec(arrayCot);
		arrayMediaM = (ArrayList<Float>) mediaM.calcula(dias, arrayValores);
		System.out.println(arrayMediaM.size());
		for (int i = 0; i < arrayMediaM.size(); i++) {
			valDouble = arrayMediaM.get(i);
			System.out.println(valDouble);
			dataset.addValue(valDouble, "MM de " + dias,
					String.valueOf(arrayCot.get(i).getDataCotacao().getDay()));
		}
		chart = ChartFactory.createLineChart(
				"Media Mï¿½vel de " + dias + " dias", "Dia", "Valor", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		OutputStream arquivo;
		try {
			arquivo = new FileOutputStream("D:\\grafico.png");
			ChartUtilities.writeChartAsJPEG(arquivo, chart, 720, 680);
			arquivo.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblGrafico = new JLabel(new ImageIcon("D:\\grafico.png"));
		return lblGrafico;
	}

	private class GeradorDeGrafico {
		// atributos: serie, comeco, fim, grafico e dados
		// (todos gerados com ctrl + 1, conforme vocï¿½ criar o construtor)

		private SerieTemporal serie;
		private int comeco;
		private int fim;
		private DefaultCategoryDataset dados;
		private Object grafico;

		public GeradorDeGrafico(SerieTemporal serie, int comeco, int fim) {
			this.serie = serie;
			this.comeco = comeco;
			this.fim = fim;
			this.dados = new DefaultCategoryDataset();
			this.grafico = ChartFactory.createLineChart("Indicadores", "Dias",
					"Valores", dados, PlotOrientation.VERTICAL, true, true,
					false);
		}

		public void salva(OutputStream out) throws IOException {
			ChartUtilities.writeChartAsJPEG(out, (JFreeChart) grafico, 500,
					350);
		}
	}

}
