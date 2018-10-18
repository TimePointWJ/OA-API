package com.odm.oa.model.authManager;

import java.util.ArrayList;
import java.util.List;

public class MenuTree{
	private String id;//节点标志
	private String label;//节点名称
	private boolean open;// 是否打开节点
	private boolean checked;//是否被选中
	private String parentId;//父级节点Id
	private boolean visible;//是否可见
	private boolean searched;//是否是搜索值
	private List<MenuTree> children = new ArrayList<>();// 子节点集合
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isSearched() {
		return searched;
	}
	public void setSearched(boolean searched) {
		this.searched = searched;
	}
	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

}
