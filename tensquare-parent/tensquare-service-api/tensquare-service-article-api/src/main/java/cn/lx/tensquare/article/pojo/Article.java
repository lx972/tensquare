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
 * @Description:Article构建
 * @Date 2020/9/5 10:33
 *****/
@ApiModel(description = "Article",value = "Article")
@TableName(value="tb_article")
public class Article implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@TableId(value = "id",type = IdType.INPUT)
	private String id;//ID

	@ApiModelProperty(value = "专栏ID",required = false)
	@TableField(value = "columnid")
	private String columnid;//专栏ID

	@ApiModelProperty(value = "用户ID",required = false)
	@TableField(value = "userid")
	private String userid;//用户ID

	@ApiModelProperty(value = "标题",required = false)
	@TableField(value = "title")
	private String title;//标题

	@ApiModelProperty(value = "文章正文",required = false)
	@TableField(value = "content")
	private String content;//文章正文

	@ApiModelProperty(value = "文章封面",required = false)
	@TableField(value = "image")
	private String image;//文章封面

	@ApiModelProperty(value = "发表日期",required = false)
	@TableField(value = "createtime")
	private Date createtime;//发表日期

	@ApiModelProperty(value = "修改日期",required = false)
	@TableField(value = "updatetime")
	private Date updatetime;//修改日期

	@ApiModelProperty(value = "是否公开",required = false)
	@TableField(value = "ispublic")
	private String ispublic;//是否公开

	@ApiModelProperty(value = "是否置顶",required = false)
	@TableField(value = "istop")
	private String istop;//是否置顶

	@ApiModelProperty(value = "浏览量",required = false)
	@TableField(value = "visits")
	private Integer visits;//浏览量

	@ApiModelProperty(value = "点赞数",required = false)
	@TableField(value = "thumbup")
	private Integer thumbup;//点赞数

	@ApiModelProperty(value = "评论数",required = false)
	@TableField(value = "comment")
	private Integer comment;//评论数

	@ApiModelProperty(value = "审核状态",required = false)
	@TableField(value = "state")
	private String state;//审核状态

	@ApiModelProperty(value = "所属频道",required = false)
	@TableField(value = "channelid")
	private String channelid;//所属频道

	@ApiModelProperty(value = "URL",required = false)
	@TableField(value = "url")
	private String url;//URL

	@ApiModelProperty(value = "类型",required = false)
	@TableField(value = "type")
	private String type;//类型



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getColumnid() {
		return columnid;
	}

	//set方法
	public void setColumnid(String columnid) {
		this.columnid = columnid;
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
	public String getTitle() {
		return title;
	}

	//set方法
	public void setTitle(String title) {
		this.title = title;
	}
	//get方法
	public String getContent() {
		return content;
	}

	//set方法
	public void setContent(String content) {
		this.content = content;
	}
	//get方法
	public String getImage() {
		return image;
	}

	//set方法
	public void setImage(String image) {
		this.image = image;
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
	public Date getUpdatetime() {
		return updatetime;
	}

	//set方法
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	//get方法
	public String getIspublic() {
		return ispublic;
	}

	//set方法
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
	//get方法
	public String getIstop() {
		return istop;
	}

	//set方法
	public void setIstop(String istop) {
		this.istop = istop;
	}
	//get方法
	public Integer getVisits() {
		return visits;
	}

	//set方法
	public void setVisits(Integer visits) {
		this.visits = visits;
	}
	//get方法
	public Integer getThumbup() {
		return thumbup;
	}

	//set方法
	public void setThumbup(Integer thumbup) {
		this.thumbup = thumbup;
	}
	//get方法
	public Integer getComment() {
		return comment;
	}

	//set方法
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	//get方法
	public String getState() {
		return state;
	}

	//set方法
	public void setState(String state) {
		this.state = state;
	}
	//get方法
	public String getChannelid() {
		return channelid;
	}

	//set方法
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	//get方法
	public String getUrl() {
		return url;
	}

	//set方法
	public void setUrl(String url) {
		this.url = url;
	}
	//get方法
	public String getType() {
		return type;
	}

	//set方法
	public void setType(String type) {
		this.type = type;
	}


}
