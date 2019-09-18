package online.lucianofelix.controle;

import java.util.List;

import online.lucianofelix.beans.Operacao;

public class ControlaListaOperacoes {

	private List<Operacao> iterableList;
	private int currentPosition;
	private int collectionSize;

	public ControlaListaOperacoes(List<Operacao> iterableList) {
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

	// Anda um item para trás.
	public int getPreviousPosition() {
		setCurrentPosition(getCurrentPosition() - 1);
		return getCurrentPosition();
	}

	// Pega um item em uma determinada posição.
	protected Operacao getCollectionItemAt(int position) {
		return getIterableList().get(position);
	}

	// Recupera o primeiro item da minha coleção.
	public Operacao first() {
		return getCollectionItemAt(0);
	}

	// Recupera o último item da minha coleção.
	public Operacao last() {
		return getCollectionItemAt(this.collectionSize);
	}

	// Recupera o próximo item a partir da posição atual.
	public Operacao next() {
		return getCollectionItemAt(getNextPosition());
	}

	// Recupera o item anterior a partir da posição atual.
	public Operacao previous() {
		return getCollectionItemAt(getPreviousPosition());
	}

	// Recupera um item em qualquer posição.
	public Operacao getAt(int position) {
		return getCollectionItemAt(position);

	}
	public List<Operacao> getIterableList() {
		return iterableList;
	}

	public void setIterableList(List<Operacao> iterableList) {
		this.iterableList = iterableList;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
}
