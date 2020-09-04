package cn.lx.tensquare.article.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:shenkunlin
 * @Description:Channel构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Channel",value = "Channel")
@Table(name="tb_channel")
public class Channel implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@Id
    @Column(name = "id")
	private String id;//ID

	@ApiModelProperty(value = "频道名称",required = false)
    @Column(name = "name")
	private String name;//频道名称

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
	public String getState() {
		return state;
	}

	//set方法
	public void setState(String state) {
		this.state = state;
	}


}
