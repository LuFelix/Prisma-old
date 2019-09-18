package online.lucianofelix.beans;

public class ItensIndicadores extends Indicadores {

	private int periodo;

	public ItensIndicadores setItemIndi(Indicadores indi) {
		setNomeIndi(indi.getNomeIndi());
		setCodiFuse(indi.getCodiFuse());
		return null;
	}

	@Override
	public int getPeriodo() {
		return periodo;
	}

	@Override
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

}
