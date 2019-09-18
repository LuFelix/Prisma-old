package online.lucianofelix.controle;

import java.util.List;

import online.lucianofelix.beans.Fuse;

public class ControlaListaFuse {

	private List<Fuse> iterableList;
	private int currentPosition;
	private int collectionSize;

	public ControlaListaFuse(List<Fuse> iterableList) {
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
	protected Fuse getCollectionItemAt(int position) {
		return getIterableList().get(position);
	}

	// Recupera o primeiro item da coleção.
	public Fuse first() {
		return getCollectionItemAt(0);
	}

	// Recupera o �ltimo item da minha cole��o.
	public Fuse last() {
		return getCollectionItemAt(this.collectionSize);
	}

	// Recupera o pr�ximo item a partir da posi��o atual.
	public Fuse next() {
		return getCollectionItemAt(getNextPosition());
	}

	// Recupera o item anterior a partir da posi��o atual.
	public Fuse previous() {
		return getCollectionItemAt(getPreviousPosition());
	}

	// Recupera um item em qualquer posi��o.
	public Fuse getAt(int position) {
		return getCollectionItemAt(position);

	}

	public List<Fuse> getIterableList() {
		return iterableList;
	}

	public void setIterableList(List<Fuse> iterableList) {
		this.iterableList = iterableList;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
}
