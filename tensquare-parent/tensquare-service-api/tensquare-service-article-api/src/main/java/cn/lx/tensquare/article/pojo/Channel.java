package cn.lx.tensquare.article.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/****
 * @Author:lx
 * @Description:Channel构建
 * @Date 2020/9/5 10:33
 *****/
@ApiModel(description = "Channel",value = "Channel")
@TableName(value="tb_channel")
public class Channel implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@TableId(value = "id",type = IdType.INPUT)
	private String id;//ID

	@ApiModelProperty(value = "频道名称",required = false)
	@TableField(value = "name")
	private String name;//频道名称

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
	public String getState() {
		return state;
	}

	//set方法
	public void setState(String state) {
		this.state = state;
	}


}
