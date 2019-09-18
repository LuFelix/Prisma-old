package online.lucianofelix.util;

import java.util.List;

public abstract class ListaObjetos {
	
	    private List<Object> iterableList;
	    private int currentPosition;
	    private int collectionSize;
	    // gets e sets omitidos.

	    public ListaObjetos(List<Object> iterableList) {
	        this.iterableList = iterableList;
	        this.collectionSize = iterableList.size();
	        this.currentPosition = 0;
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
	    protected Object getCollectionItemAt(int position) {
	        return getIterableList().get(position);
	    }

	    // Recupera o primeiro item da minha coleção.
	    public Object first() {
	        return getCollectionItemAt(0);
	    }

	    // Recupera o último item da minha coleção.
	    public Object last() {
	        return getCollectionItemAt(this.collectionSize);
	    }

	    // Recupera o próximo item a partir da posição atual.
	    public Object next() {
	        return getCollectionItemAt(getNextPosition());
	    }

	    // Recupera o item anterior a partir da posição atual.
	    public Object previous() {
	        return getCollectionItemAt(getPreviousPosition());
	    }

	    // Recupera um item em qualquer posição.
	    public Object getAt(int position) {
	        return getCollectionItemAt(position);
	   
	    }
	    public List<Object> getIterableList() {
			return iterableList;
		}

		public void setIterableList(List<Object> iterableList) {
			this.iterableList = iterableList;
		}

		public int getCurrentPosition() {
			return currentPosition;
		}

		public void setCurrentPosition(int currentPosition) {
			this.currentPosition = currentPosition;
		}
		
}
	/*
	 * 
		Um exemplo básico de Uso:
	 

		List<TipoDoObjeto> listaDoTipoDoObjeto = new ArrayList<>();
		
		No DAO preenche a lista
	
		while (rs.next()) {
		    
		    TipoDoObjeto t = new TipoDoObjeto();
		    t.setId(rs.getInt("id"));
		    t.setNome(rs.getString("nome"));
		    continua setando as propriedades do objeto de acordo com o campo na tabela
		    e adicionando 
		    listaDoTipoDoObjeto.add(t)

	// Chama o método que retorna uma coleção do TipodeObjeto.
	
	List<TipoDoObjeto> listaDoTipoDoObjeto = getColecaoDeTipoDoObjetodoBancoDeDados(); //Carrega a lista(Array) na clase de controle

	// Cria uma coleção IterableList tipada com a classe doTipoDoObjeto, onde o parâmetro é a lista de objetos criada do banco de dados.
	
	IterableList<TipoDoObjeto> tipoDoObjetoIterableList = new IterableList<TipoDoObjeto>(listaDoTipoDoObjeto);

	//Pego o primeiro sócio.
	TipoDoObjeto first = tipoDoObjetoIterableList.first();

	//Pego o último sócio.
	TipoDoObjeto last = tipoDoObjetoIterableList.last();

	//Pego o próximo sócio.
	TipoDoObjeto next = tipoDoObjetoIterableList.next();
	
	//Pego o sócio anterior.
	TipoDoObjeto previous = sociosIterableList.previous();

	//Pego um sócio qualquer sócio.
	TipoDoObjeto any = tipoDoObjetoIterableList.getCollectionItemAt(5);

*/