package com.odm.oa.model.request;

public class ProjectPagination  extends PaginationBase{
	private Long projectId;
	private Long positionId;
	private String name;
	public Long getprojectId() {
		return projectId;
	}
	public void setprojectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
