package com.odm.oa.entity.ex;

import com.odm.oa.entity.Goods;

public class GoodsExxg extends Goods {
	private String goodtypes;
	private String saletypes;
	public GoodsExxg(String goodtypes, String saletypes) {
		super();
		this.goodtypes = goodtypes;
		this.saletypes = saletypes;
	}
	public GoodsExxg() {
		super();
	}
	
	public String getGoodtypes() {
		return goodtypes;
	}
	public void setGoodtypes(String goodtypes) {
		this.goodtypes = goodtypes;
	}
	public String getSaletypes() {
		return saletypes;
	}
	public void setSaletypes(String saletypes) {
		this.saletypes = saletypes;
	}
	

}
