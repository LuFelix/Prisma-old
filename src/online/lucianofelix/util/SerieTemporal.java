package online.lucianofelix.util;

import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.CotacaoAtivo;

public class SerieTemporal {
	ArrayList<Float> arrayPreco;

	public ArrayList<Float> GeraSerieTemporalPreFec(List<CotacaoAtivo> arrayCot) {
		arrayPreco = new ArrayList<>();
		for (int i = 0; i < arrayCot.size(); i++) {
			arrayPreco.add(arrayCot.get(i).getPreFec());
		}
		return arrayPreco;
	}

	public ArrayList<Float> GeraSerieTemporalPreAbe(List<CotacaoAtivo> arrayCot) {
		for (int i = 0; i < arrayCot.size(); i++) {
			arrayPreco.add(arrayCot.get(i).getPreAbe());

		}
		return arrayPreco;
	}
}