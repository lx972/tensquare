package cn.lx.tensquare.article.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/****
 * @Author:lx
 * @Description:Item构建
 * @Date 2020/9/5 10:33
 *****/
@ApiModel(description = "Item",value = "Item")
@TableName(value="tb_item")
public class Item implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@TableId(value = "id",type = IdType.INPUT)
	private String id;//ID

	@ApiModelProperty(value = "专栏名称",required = false)
	@TableField(value = "name")
	private String name;//专栏名称

	@ApiModelProperty(value = "专栏简介",required = false)
	@TableField(value = "summary")
	private String summary;//专栏简介

	@ApiModelProperty(value = "用户ID",required = false)
	@TableField(value = "userid")
	private String userid;//用户ID

	@ApiModelProperty(value = "申请日期",required = false)
	@TableField(value = "createtime")
	private Date createtime;//申请日期

	@ApiModelProperty(value = "审核日期",required = false)
	@TableField(value = "checktime")
	private Date checktime;//审核日期

	@ApiModelProperty(value = "状态",required = false)
	@TableField(value = "state")
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
