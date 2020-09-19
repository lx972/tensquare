package cn.lx.tensquare.user.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.lang.String;
/****
 * @Author:lx
 * @Description:Admin构建
 * @Date 2020/9/5 10:33
 *****/
@ApiModel(description = "Admin",value = "Admin")
@TableName(value="tb_admin")
public class Admin implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@TableId(value = "id",type = IdType.INPUT)
	private String id;//ID

	@ApiModelProperty(value = "登陆名称",required = false)
	@TableField(value = "loginname")
	private String loginname;//登陆名称

	@ApiModelProperty(value = "密码",required = false)
	@TableField(value = "password")
	private String password;//密码

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
	public String getLoginname() {
		return loginname;
	}

	//set方法
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	//get方法
	public String getPassword() {
		return password;
	}

	//set方法
	public void setPassword(String password) {
		this.password = password;
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
