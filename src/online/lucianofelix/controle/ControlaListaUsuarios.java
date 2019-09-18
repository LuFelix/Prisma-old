package online.lucianofelix.controle;

import java.util.List;

import online.lucianofelix.beans.Pessoa;
import online.lucianofelix.beans.Produto;

public class ControlaListaUsuarios  {
	
	private List<Pessoa> iterableList;
    private int currentPosition;
    private int collectionSize;
	
    public ControlaListaUsuarios(List <Pessoa> iterableList) {
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

    // Pega um item em uma determinada posi��o.
    protected Pessoa getCollectionItemAt(int position) {
        return getIterableList().get(position);
    }

    // Recupera o primeiro item da minha cole��o.
    public Pessoa first() {
        return getCollectionItemAt(0);
    }

    // Recupera o �ltimo item da minha cole��o.
    public Pessoa last() {
        return getCollectionItemAt(this.collectionSize);
    }

    // Recupera o pr�ximo item a partir da posi��o atual.
    public Pessoa next() {
        return getCollectionItemAt(getNextPosition());
    }

    // Recupera o item anterior a partir da posi��o atual.
    public Pessoa previous() {
        return getCollectionItemAt(getPreviousPosition());
    }

    // Recupera um item em qualquer posi��o.
    public Pessoa getAt(int position) {
        return getCollectionItemAt(position);
		
	}
    public List<Pessoa> getIterableList() {
		return iterableList;
	}

	public void setIterableList(List<Pessoa> iterableList) {
		this.iterableList = iterableList;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
}

