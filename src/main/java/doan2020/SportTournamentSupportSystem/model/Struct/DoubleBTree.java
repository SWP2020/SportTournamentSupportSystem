package doan2020.SportTournamentSupportSystem.model.Struct;

import java.io.Serializable;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

public class DoubleBTree<E> extends BTree<E> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public DoubleBTree() {
		super();
	}

	public DoubleBTree(Node<E> root) {
		super(root);
	}




	public Node<E> getByIdAndDegree(int index, int degree) {
		
		if (index < 1) {
			return null;
		}
		
		if (degree < 1) {
			return null;
		}
		
		if (index == 1 && degree == 1) {
			return this.root;
		}
		
		Node<E> parent;
		if (degree % 2 == 0) {
			parent = getByIdAndDegree(index, degree - 1);
			if (parent == null)
				return null;
			return parent.getRight();
		} else {
			parent = getByIdAndDegree(index / 2, degree - 1);
			if (parent == null)
				return null;
			
			if (index % 2 == 0) {
				return parent.getLeft();
			} else {
				return parent.getRight();
			}
		}
		
		
		
		
	}

}
