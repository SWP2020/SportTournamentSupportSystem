package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.io.Serializable;
import java.util.ArrayList;

public class BTree<E> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected Integer totalNode;
	
	protected Node<E> root;

	
	
	
	
	
	
	
	
	public BTree(Node<E> root) {
		this.root = root;
	}
	
	public BTree() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalNode() {
		if (this.totalNode == null){
			this.totalNode = this.calTotalNode();
		}
		return totalNode;
	}
	
	private Integer calTotalNode() {
		
		return countNode(root);
	}
	
	public Integer countNode(Node<E> node) {
		int count = 0;
		if (node == null)
			return 0;
		count += countNode(node.getLeft());
		count += countNode(node.getRight());
		count ++;
		return count;
	}

	public void setTotalNode(Integer totalNode) {
		this.totalNode = totalNode;
	}

	public Node<E> getRoot() {
		return root;
	}

	public void setRoot(Node<E> root) {
		this.root = root;
	}
	
	public Node<E> getById(Integer index) {
		
		if (index < 1) {
			return null;
		}
		if (index == 1) {
			return this.root;
		}
		
		Node<E> parent = getById(index / 2);
		
		if (parent == null)
			return null;
		
		if (index % 2 == 0) {
			return parent.getLeft();
		} else {
			return parent.getRight();
		}
	}
	
	public void setById(Integer index, E data) {
		if (index < 1) {
			return;
		}
		
		if (index == 1) {
			this.root.setData(data);
		}
		
		ArrayList<Integer> bits = new ArrayList<Integer>();
		int bitNum = 0;
		
		while (index > 0) {
			
			bits.add(index % 2);
			index /= 2;
			bitNum ++;
		}
		
		Node<E> poiter = this.root;
		
		for (int i = bitNum - 2; i >=0; i--) {
			
			if (bits.get(i).intValue() == 0)
				poiter = poiter.left;
			else 
				poiter = poiter.right;
		}
		
		poiter.setData(data);
		
	}
	
}
