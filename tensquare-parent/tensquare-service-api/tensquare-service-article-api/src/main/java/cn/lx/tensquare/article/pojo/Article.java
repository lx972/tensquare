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
 * @Description:Article构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Article",value = "Article")
@Table(name="tb_article")
public class Article implements Serializable{

	@ApiModelProperty(value = "ID",required = false)
	@Id
    @Column(name = "id")
	private String id;//ID

	@ApiModelProperty(value = "专栏ID",required = false)
    @Column(name = "columnid")
	private String columnid;//专栏ID

	@ApiModelProperty(value = "用户ID",required = false)
    @Column(name = "userid")
	private String userid;//用户ID

	@ApiModelProperty(value = "标题",required = false)
    @Column(name = "title")
	private String title;//标题

	@ApiModelProperty(value = "文章正文",required = false)
    @Column(name = "content")
	private String content;//文章正文

	@ApiModelProperty(value = "文章封面",required = false)
    @Column(name = "image")
	private String image;//文章封面

	@ApiModelProperty(value = "发表日期",required = false)
    @Column(name = "createtime")
	private Date createtime;//发表日期

	@ApiModelProperty(value = "修改日期",required = false)
    @Column(name = "updatetime")
	private Date updatetime;//修改日期

	@ApiModelProperty(value = "是否公开",required = false)
    @Column(name = "ispublic")
	private String ispublic;//是否公开

	@ApiModelProperty(value = "是否置顶",required = false)
    @Column(name = "istop")
	private String istop;//是否置顶

	@ApiModelProperty(value = "浏览量",required = false)
    @Column(name = "visits")
	private Integer visits;//浏览量

	@ApiModelProperty(value = "点赞数",required = false)
    @Column(name = "thumbup")
	private Integer thumbup;//点赞数

	@ApiModelProperty(value = "评论数",required = false)
    @Column(name = "comment")
	private Integer comment;//评论数

	@ApiModelProperty(value = "审核状态",required = false)
    @Column(name = "state")
	private String state;//审核状态

	@ApiModelProperty(value = "所属频道",required = false)
    @Column(name = "channelid")
	private String channelid;//所属频道

	@ApiModelProperty(value = "URL",required = false)
    @Column(name = "url")
	private String url;//URL

	@ApiModelProperty(value = "类型",required = false)
    @Column(name = "type")
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
