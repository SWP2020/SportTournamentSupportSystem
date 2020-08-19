package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Node<E> {
	
	Integer id;
	Integer degree;
	E data;
	
	Node<E> left;
	Node<E> right;
	
	@JsonBackReference
	Node<E> parent;
	
	
	
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getLeft() {
		return left;
	}

	public void setLeft(Node<E> left) {
		this.left = left;
	}

	public Node<E> getRight() {
		return right;
	}

	public void setRight(Node<E> right) {
		this.right = right;
	}

	public Node<E> getParent() {
		return parent;
	}

	public void setParent(Node<E> parent) {
		this.parent = parent;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	
	
}
