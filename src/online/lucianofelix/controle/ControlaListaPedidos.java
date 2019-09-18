package online.lucianofelix.controle;

import java.util.List;

import online.lucianofelix.beans.Pedido;

public class ControlaListaPedidos {

	private List<Pedido> iterableList;
	private int currentPosition;
	private int collectionSize;

	public ControlaListaPedidos(List<Pedido> iterableList) {
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

	// Anda um item para tr�s.
	public int getPreviousPosition() {
		setCurrentPosition(getCurrentPosition() - 1);
		return getCurrentPosition();
	}

	// Pega um item em uma determinada posição.
	protected Pedido getCollectionItemAt(int position) {
		return getIterableList().get(position);
	}

	// Recupera o primeiro item da coleção.
	public Pedido first() {
		return getCollectionItemAt(0);
	}

	// Recupera o �ltimo item da minha cole��o.
	public Pedido last() {
		return getCollectionItemAt(this.collectionSize);
	}

	// Recupera o pr�ximo item a partir da posi��o atual.
	public Pedido next() {
		return getCollectionItemAt(getNextPosition());
	}

	// Recupera o item anterior a partir da posi��o atual.
	public Pedido previous() {
		return getCollectionItemAt(getPreviousPosition());
	}

	// Recupera um item em qualquer posi��o.
	public Pedido getAt(int position) {
		return getCollectionItemAt(position);

	}
	public List<Pedido> getIterableList() {
		return iterableList;
	}

	public void setIterableList(List<Pedido> iterableList) {
		this.iterableList = iterableList;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
}
