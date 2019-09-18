package online.lucianofelix.controle;

import java.util.List;

import online.lucianofelix.beans.Ativo;


	public class ControlaListaAtivos  {
		
		private List<Ativo> iterableList;
	    private int currentPosition;
	    private int collectionSize;
		
	    public ControlaListaAtivos(List <Ativo> iterableList) {
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
	    protected Ativo getCollectionItemAt(int position) {
	        return getIterableList().get(position);
	    }

	    // Recupera o primeiro item da minha cole��o.
	    public Ativo first() {
	        return getCollectionItemAt(0);
	    }

	    // Recupera o �ltimo item da minha cole��o.
	    public Ativo last() {
	        return getCollectionItemAt(this.collectionSize);
	    }

	    // Recupera o pr�ximo item a partir da posi��o atual.
	    public Ativo next() {
	        return getCollectionItemAt(getNextPosition());
	    }

	    // Recupera o item anterior a partir da posi��o atual.
	    public Ativo previous() {
	        return getCollectionItemAt(getPreviousPosition());
	    }

	    // Recupera um item em qualquer posi��o.
	    public Ativo getAt(int position) {
	        return getCollectionItemAt(position);
			
		}
	    public List<Ativo> getIterableList() {
			return iterableList;
		}

		public void setIterableList(List<Ativo> iterableList) {
			this.iterableList = iterableList;
		}

		public int getCurrentPosition() {
			return currentPosition;
		}

		public void setCurrentPosition(int currentPosition) {
			this.currentPosition = currentPosition;
		}
	}




