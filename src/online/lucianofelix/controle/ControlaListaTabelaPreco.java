package online.lucianofelix.controle;

import java.util.List;

import online.lucianofelix.beans.TabelaPreco;

public class ControlaListaTabelaPreco {
	private List<TabelaPreco> iterableList;
	private int currentPosition;
	private int collectionSize;

	public ControlaListaTabelaPreco(List<TabelaPreco> iterableList) {
		super();
		this.iterableList = iterableList;
		this.currentPosition = currentPosition;
		this.collectionSize = collectionSize;
	}

	// Anda um item para a frente.
	public int getNextPosition() {
		setCurrentPosition(getCurrentPosition() + 1);
		return getCurrentPosition();
	}

	// Anda um ítem para trás.
	public int getPreviousPosition() {
		setCurrentPosition(getCurrentPosition() - 1);
		return getCurrentPosition();
	}

	// Pega um item em uma determinada posição.
	protected TabelaPreco getCollectionItemAt(int position) {
		return getIterableList().get(position);
	}

	// Recupera o primeiro item da minha coleção.
	public TabelaPreco first() {
		return getCollectionItemAt(0);
	}

	// Recupera o Ãºltimo item da minha coleção.
	public TabelaPreco last() {
		return getCollectionItemAt(this.collectionSize);
	}

	// Recupera o prÃ³ximo item a partir da posição atual.
	public TabelaPreco next() {
		return getCollectionItemAt(getNextPosition());
	}

	// Recupera o item anterior a partir da posição atual.
	public TabelaPreco previous() {
		return getCollectionItemAt(getPreviousPosition());
	}

	// Recupera um item em qualquer posição.
	public TabelaPreco getAt(int position) {
		return getCollectionItemAt(position);

	}

	public List<TabelaPreco> getIterableList() {
		return iterableList;
	}

	public void setIterableList(List<TabelaPreco> iterableList) {
		this.iterableList = iterableList;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
}
