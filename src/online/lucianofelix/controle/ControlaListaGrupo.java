package online.lucianofelix.controle;

import java.util.List;

import online.lucianofelix.beans.GrupoSubgrupo;

public class ControlaListaGrupo {

	private List<GrupoSubgrupo> iterableList;
	private int currentPosition;
	private int collectionSize;

	public ControlaListaGrupo(List<GrupoSubgrupo> iterableList) {
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

	// Anda um item para trÃ¡s.
	public int getPreviousPosition() {
		setCurrentPosition(getCurrentPosition() - 1);
		return getCurrentPosition();
	}

	// Pega um item em uma determinada posição.
	protected GrupoSubgrupo getCollectionItemAt(int position) {
		return getIterableList().get(position);
	}

	// Recupera o primeiro item da coleção.
	public GrupoSubgrupo first() {
		return getCollectionItemAt(0);
	}

	// Recupera o último item da coleção.
	public GrupoSubgrupo last() {
		return getCollectionItemAt(this.collectionSize);
	}

	// Recupera o próximo item a partir da posição atual.
	public GrupoSubgrupo next() {
		return getCollectionItemAt(getNextPosition());
	}

	// Recupera o item anterior a partir da posição atual.
	public GrupoSubgrupo previous() {
		return getCollectionItemAt(getPreviousPosition());
	}

	// Recupera um item em qualquer posição.
	public GrupoSubgrupo getAt(int position) {
		return getCollectionItemAt(position);

	}

	public List<GrupoSubgrupo> getIterableList() {
		return iterableList;
	}

	public void setIterableList(List<GrupoSubgrupo> iterableList) {
		this.iterableList = iterableList;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

}
