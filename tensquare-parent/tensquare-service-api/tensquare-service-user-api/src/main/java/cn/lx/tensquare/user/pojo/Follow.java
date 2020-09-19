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
 * @Description:Follow构建
 * @Date 2020/9/5 10:33
 *****/
@ApiModel(description = "Follow",value = "Follow")
@TableName(value="tb_follow")
public class Follow implements Serializable{

	@ApiModelProperty(value = "用户ID",required = false)
	@TableId(value = "userid",type = IdType.INPUT)
	private String userid;//用户ID

	@ApiModelProperty(value = "被关注用户ID",required = false)
	@TableField(value = "targetuser")
	private String targetuser;//被关注用户ID



	//get方法
	public String getUserid() {
		return userid;
	}

	//set方法
	public void setUserid(String userid) {
		this.userid = userid;
	}
	//get方法
	public String getTargetuser() {
		return targetuser;
	}

	//set方法
	public void setTargetuser(String targetuser) {
		this.targetuser = targetuser;
	}


}
