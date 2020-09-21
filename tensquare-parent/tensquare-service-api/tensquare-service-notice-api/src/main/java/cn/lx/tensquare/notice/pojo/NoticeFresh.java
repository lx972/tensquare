package cn.lx.tensquare.notice.pojo;
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
 * @Description:NoticeFresh构建
 * @Date 2020/9/5 10:33
 *****/
@ApiModel(description = "NoticeFresh",value = "NoticeFresh")
@TableName(value="tb_notice_fresh")
public class NoticeFresh implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@TableId(value = "id",type = IdType.INPUT)
	private String id;//

	@ApiModelProperty(value = "用户id",required = false)
	@TableField(value = "userId")
	private String userId;//用户id

	@ApiModelProperty(value = "通知id",required = false)
	@TableField(value = "noticeId")
	private String noticeId;//通知id



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getUserId() {
		return userId;
	}

	//set方法
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//get方法
	public String getNoticeId() {
		return noticeId;
	}

	//set方法
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}


}
