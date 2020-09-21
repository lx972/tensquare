package cn.lx.tensquare.notice.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;


/****
 * @Author:lx
 * @Description:Notice构建
 * @Date 2020/9/5 10:33
 *****/
@ApiModel(description = "Notice",value = "Notice")
@TableName(value="tb_notice")
public class Notice implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@TableId(value = "id",type = IdType.INPUT)
	private String id;//ID

	@ApiModelProperty(value = "接收消息用户的ID",required = false)
	@TableField(value = "receiverId")
	private String receiverId;//接收消息用户的ID

	@ApiModelProperty(value = "进行操作用户的ID",required = false)
	@TableField(value = "operatorId")
	private String operatorId;//进行操作用户的ID

	@ApiModelProperty(value = "进行操作的用户昵称",required = false)
	@TableField(exist = false)
	private String operatorName;//进行操作的用户昵称

	@ApiModelProperty(value = "操作类型（评论，点赞等）",required = false)
	@TableField(value = "action")
	private String action;//操作类型（评论，点赞等）

	@ApiModelProperty(value = "被操作的对象，例如文章，评论等",required = false)
	@TableField(value = "targetType")
	private String targetType;//被操作的对象，例如文章，评论等

	@ApiModelProperty(value = "对象名称或简介",required = false)
	@TableField(exist = false)
	private String targetName;//对象名称或简介

	@ApiModelProperty(value = "被操作对象的id，例如文章的id，评论的id",required = false)
	@TableField(value = "targetId")
	private String targetId;//被操作对象的id，例如文章的id，评论的id

	@ApiModelProperty(value = "发表日期",required = false)
	@TableField(value = "createtime")
	private Date createtime;//发表日期

	@ApiModelProperty(value = "通知类型",required = false)
	@TableField(value = "type")
	private String type;//通知类型

	@ApiModelProperty(value = "",required = false)
	@TableField(value = "state")
	private String state;//



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getReceiverId() {
		return receiverId;
	}

	//set方法
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	//get方法
	public String getOperatorId() {
		return operatorId;
	}

	//set方法
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	//get方法
	public String getAction() {
		return action;
	}

	//set方法
	public void setAction(String action) {
		this.action = action;
	}
	//get方法
	public String getTargetType() {
		return targetType;
	}

	//set方法
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	//get方法
	public String getTargetId() {
		return targetId;
	}

	//set方法
	public void setTargetId(String targetId) {
		this.targetId = targetId;
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
	public String getType() {
		return type;
	}

	//set方法
	public void setType(String type) {
		this.type = type;
	}
	//get方法
	public String getState() {
		return state;
	}

	//set方法
	public void setState(String state) {
		this.state = state;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
}
