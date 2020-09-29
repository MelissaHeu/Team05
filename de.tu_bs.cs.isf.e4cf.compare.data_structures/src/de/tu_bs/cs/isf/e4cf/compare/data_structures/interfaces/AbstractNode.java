package de.tu_bs.cs.isf.e4cf.compare.data_structures.interfaces;

import java.util.ArrayList;
import java.util.List;

import de.tu_bs.cs.isf.e4cf.compare.data_structures.enums.VariabilityClass;
import de.tu_bs.cs.isf.e4cf.compare.data_structures.impl.AttributeImpl;

public abstract class AbstractNode implements Node {
	private static final long serialVersionUID = 5776489857546412690L;
	private String nodeType;
	private List<Node> children;
	private Node parent;
	private List<Attribute> attributes;


	private VariabilityClass varClass;
	
	public AbstractNode() {
		initializeNode();
	}
	
	/**
	 * This method initializes all required objects.
	 */
	private void initializeNode() {
		setChildren(new ArrayList<Node>());
		setAttributes(new ArrayList<Attribute>());
	}
	
	@Override
	public boolean isRoot() {
		if(parent == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isLeaf() {
		if(children.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	@Override
	public void addAttribute(String key, String value) {
		Attribute attribute = null;
		for(Attribute attr : getAttributes()) {
			if(attr.getAttributeKey().equals(key)) {
				attribute = attr;
				break;
			}
		}
		if(attribute == null) {
			getAttributes().add(new AttributeImpl(key,value));
		}

	}
	

	@Override
	public Attribute getAttributesForKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getNumberOfChildren() {
		int size = 1;
		
		if(children.isEmpty()) {
			return size;
		} else {
			for(Node child : children) {
				size += child.getNumberOfChildren();
			}
			return size;
		}
	}
	
	@Override
	public List<Node> getChildrenOfType(String nodeType) {
		List<Node> childrenList = new ArrayList<Node>();
		for(Node child : getChildren()) {
			if(child.getNodeType().equals(nodeType)) {
				childrenList.add(child);
			}
		}
		return childrenList;
	}
	
	/******************************************************
	 * GETTER AND SETTER 
	 ******************************************************/
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	
	@Override
	public List<Node> getChildren() {
		return children;
	}
	
	@Override
	public void addChild(Node child) {
		child.setParent(this);
		this.children.add(child);
	}
	
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	@Override
	public VariabilityClass getVariabilityClass() {
		return varClass;
	}

	@Override
	public void setVariabilityClass(VariabilityClass varClass) {
		this.varClass = varClass;
	}
	
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String toString() {
		String nodeName = "NodeType: "+getNodeType() +" \n";
		for(Attribute attr : getAttributes()) {
			nodeName += "Attrribute Key: "+ attr.getAttributeKey() + "\n";
			for(String value : attr.getAttributeValues()) {
				nodeName += "        "+value +"\n";
			}
		}
		return nodeName;
	}
}
