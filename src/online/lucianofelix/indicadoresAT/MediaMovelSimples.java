package online.lucianofelix.indicadoresAT;

import java.util.ArrayList;
import java.util.List;

import online.lucianofelix.beans.Indicadores;

public class MediaMovelSimples extends Indicadores {

	List<Float> arrayMedia;

	public List<Float> calcula(int periodo, ArrayList<Float> valores) {
		arrayMedia = new ArrayList<>();
		float media = 0;
		int fim = valores.size() - periodo + 1;
		for (int i = 0; i < fim; i++) {
			float soma = 0;
			for (int j = i; j < periodo + i; j++) {
				soma = soma + valores.get(j);
				// System.out.println("Valor: "+valores.get(j));
			}
			media = soma / periodo;
			// System.out.println("Média:=====>> "+media+" ");
			arrayMedia.add(media);
		}
		return arrayMedia;
	}

}
