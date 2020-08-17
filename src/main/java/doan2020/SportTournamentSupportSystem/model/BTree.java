package doan2020.SportTournamentSupportSystem.model;

import doan2020.SportTournamentSupportSystem.model.LogicEntity.Node;

public class BTree<E>{
	
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
		return totalNode;
	}

	public void setTotalNode(Integer totalNode) {
		this.totalNode = totalNode;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	
	
	
}
