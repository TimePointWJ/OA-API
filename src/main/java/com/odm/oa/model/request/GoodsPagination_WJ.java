package com.odm.oa.model.request;

public class GoodsPagination_WJ extends PaginationBase{
	private String goodsType;
	private String saleFlg;
	private String goodsName;
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getSaleFlg() {
		return saleFlg;
	}
	public void setSaleFlg(String saleFlg) {
		this.saleFlg = saleFlg;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
}
