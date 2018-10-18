package com.odm.oa.model.request;


public class AgentPagination extends PaginationBase {
	
	// 代理商名称
    private String name;
    
    // 代理人类型
    private int agentType;
    
    // 代理商法人
    private String legalPerson;
    
    //移动电话
    private String mobilePhone;
    
    // 联系人
    private String linkman;
    
    // 电子邮箱
    private String mail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAgentType() {
		return agentType;
	}

	public void setAgentType(int agentType) {
		this.agentType = agentType;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
    
    
}
