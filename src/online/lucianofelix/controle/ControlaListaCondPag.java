package online.lucianofelix.controle;

import java.util.List;

import online.lucianofelix.beans.CondPagamento;

public class ControlaListaCondPag {

	private List<CondPagamento> iterableList;
	private int currentPosition;
	private int collectionSize;
	public ControlaListaCondPag(List<CondPagamento> iterableList) {
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
	protected CondPagamento getCollectionItemAt(int position) {
		return getIterableList().get(position);
	}
	// Recupera o primeiro item da minha coleção.
	public CondPagamento first() {
		return getCollectionItemAt(0);
	}
	// Recupera o último item da minha coleção.
	public CondPagamento last() {
		return getCollectionItemAt(this.collectionSize);
	}
	// Recupera o próximo item a partir da posição atual.
	public CondPagamento next() {
		return getCollectionItemAt(getNextPosition());
	}
	// Recupera o item anterior a partir da posição atual.
	public CondPagamento previous() {
		return getCollectionItemAt(getPreviousPosition());
	}
	// Recupera um item em qualquer posição.
	public CondPagamento getAt(int position) {
		return getCollectionItemAt(position);
	}
	public List<CondPagamento> getIterableList() {
		return iterableList;
	}
	public void setIterableList(List<CondPagamento> iterableList) {
		this.iterableList = iterableList;
	}
	public int getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
}
