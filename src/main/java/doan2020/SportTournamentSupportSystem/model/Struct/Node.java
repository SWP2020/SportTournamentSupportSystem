package doan2020.SportTournamentSupportSystem.model.Struct;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Node<E> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer degree;
	private E data;
	
	private Node<E> left;
	private Node<E> right;
	
	@JsonBackReference
	private Node<E> parent;
	
	
	
	
	
	

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
