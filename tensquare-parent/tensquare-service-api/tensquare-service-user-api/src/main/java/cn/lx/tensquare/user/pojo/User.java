package cn.lx.tensquare.user.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:lx
 * @Description:User构建
 * @Date 2020/9/5 10:33
 *****/
@ApiModel(description = "User",value = "User")
@TableName(value="tb_user")
public class User implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@TableId(value = "id",type = IdType.INPUT)
	private String id;//ID

	@ApiModelProperty(value = "手机号码",required = false)
	@TableField(value = "mobile")
	private String mobile;//手机号码

	@ApiModelProperty(value = "密码",required = false)
	@TableField(value = "password")
	private String password;//密码

	@ApiModelProperty(value = "昵称",required = false)
	@TableField(value = "nickname")
	private String nickname;//昵称

	@ApiModelProperty(value = "性别",required = false)
	@TableField(value = "sex")
	private String sex;//性别

	@ApiModelProperty(value = "出生年月日",required = false)
	@TableField(value = "birthday")
	private Date birthday;//出生年月日

	@ApiModelProperty(value = "头像",required = false)
	@TableField(value = "avatar")
	private String avatar;//头像

	@ApiModelProperty(value = "E-Mail",required = false)
	@TableField(value = "email")
	private String email;//E-Mail

	@ApiModelProperty(value = "注册日期",required = false)
	@TableField(value = "regdate")
	private Date regdate;//注册日期

	@ApiModelProperty(value = "修改日期",required = false)
	@TableField(value = "updatedate")
	private Date updatedate;//修改日期

	@ApiModelProperty(value = "最后登陆日期",required = false)
	@TableField(value = "lastdate")
	private Date lastdate;//最后登陆日期

	@ApiModelProperty(value = "在线时长（分钟）",required = false)
	@TableField(value = "online")
	private Long online;//在线时长（分钟）

	@ApiModelProperty(value = "兴趣",required = false)
	@TableField(value = "interest")
	private String interest;//兴趣

	@ApiModelProperty(value = "个性",required = false)
	@TableField(value = "personality")
	private String personality;//个性

	@ApiModelProperty(value = "粉丝数",required = false)
	@TableField(value = "fanscount")
	private Integer fanscount;//粉丝数

	@ApiModelProperty(value = "关注数",required = false)
	@TableField(value = "followcount")
	private Integer followcount;//关注数



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getMobile() {
		return mobile;
	}

	//set方法
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getNickname() {
		return nickname;
	}

	//set方法
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	//get方法
	public String getSex() {
		return sex;
	}

	//set方法
	public void setSex(String sex) {
		this.sex = sex;
	}
	//get方法
	public Date getBirthday() {
		return birthday;
	}

	//set方法
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	//get方法
	public String getAvatar() {
		return avatar;
	}

	//set方法
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	//get方法
	public String getEmail() {
		return email;
	}

	//set方法
	public void setEmail(String email) {
		this.email = email;
	}
	//get方法
	public Date getRegdate() {
		return regdate;
	}

	//set方法
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	//get方法
	public Date getUpdatedate() {
		return updatedate;
	}

	//set方法
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	//get方法
	public Date getLastdate() {
		return lastdate;
	}

	//set方法
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	//get方法
	public Long getOnline() {
		return online;
	}

	//set方法
	public void setOnline(Long online) {
		this.online = online;
	}
	//get方法
	public String getInterest() {
		return interest;
	}

	//set方法
	public void setInterest(String interest) {
		this.interest = interest;
	}
	//get方法
	public String getPersonality() {
		return personality;
	}

	//set方法
	public void setPersonality(String personality) {
		this.personality = personality;
	}
	//get方法
	public Integer getFanscount() {
		return fanscount;
	}

	//set方法
	public void setFanscount(Integer fanscount) {
		this.fanscount = fanscount;
	}
	//get方法
	public Integer getFollowcount() {
		return followcount;
	}

	//set方法
	public void setFollowcount(Integer followcount) {
		this.followcount = followcount;
	}


}
