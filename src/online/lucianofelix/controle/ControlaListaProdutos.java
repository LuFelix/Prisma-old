package online.lucianofelix.controle;

import java.util.List;

import online.lucianofelix.beans.Produto;

public class ControlaListaProdutos  {
	
	private List<Produto> iterableList;
    private int currentPosition;
    private int collectionSize;
	
    public ControlaListaProdutos(List <Produto> iterableList) {
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
    protected Produto getCollectionItemAt(int position) {
        return getIterableList().get(position);
    }

    // Recupera o primeiro item da minha coleção.
    public Produto first() {
        return getCollectionItemAt(0);
    }

    // Recupera o último item da minha coleção.
    public Produto last() {
        return getCollectionItemAt(this.collectionSize);
    }

    // Recupera o próximo item a partir da posição atual.
    public Produto next() {
        return getCollectionItemAt(getNextPosition());
    }

    // Recupera o item anterior a partir da posição atual.
    public Produto previous() {
        return getCollectionItemAt(getPreviousPosition());
    }

    // Recupera um item em qualquer posição.
    public Produto getAt(int position) {
        return getCollectionItemAt(position);
		
	}
    public List<Produto> getIterableList() {
		return iterableList;
	}

	public void setIterableList(List<Produto> iterableList) {
		this.iterableList = iterableList;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
}

