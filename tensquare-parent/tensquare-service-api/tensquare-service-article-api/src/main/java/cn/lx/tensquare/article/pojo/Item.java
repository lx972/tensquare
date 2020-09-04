package cn.lx.tensquare.article.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:shenkunlin
 * @Description:Item构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Item",value = "Item")
@Table(name="tb_item")
public class Item implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@Id
    @Column(name = "id")
	private String id;//ID

	@ApiModelProperty(value = "专栏名称",required = false)
    @Column(name = "name")
	private String name;//专栏名称

	@ApiModelProperty(value = "专栏简介",required = false)
    @Column(name = "summary")
	private String summary;//专栏简介

	@ApiModelProperty(value = "用户ID",required = false)
    @Column(name = "userid")
	private String userid;//用户ID

	@ApiModelProperty(value = "申请日期",required = false)
    @Column(name = "createtime")
	private Date createtime;//申请日期

	@ApiModelProperty(value = "审核日期",required = false)
    @Column(name = "checktime")
	private Date checktime;//审核日期

	@ApiModelProperty(value = "状态",required = false)
    @Column(name = "state")
	private String state;//状态



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
	}
	//get方法
	public String getSummary() {
		return summary;
	}

	//set方法
	public void setSummary(String summary) {
		this.summary = summary;
	}
	//get方法
	public String getUserid() {
		return userid;
	}

	//set方法
	public void setUserid(String userid) {
		this.userid = userid;
	}
	//get方法
	public Date getCreatetime() {
		return createtime;
	}

	//set方法
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	//get方法
	public Date getChecktime() {
		return checktime;
	}

	//set方法
	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}
	//get方法
	public String getState() {
		return state;
	}

	//set方法
	public void setState(String state) {
		this.state = state;
	}


}
