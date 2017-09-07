package com.example.administrator.myapplication.util;

public class Api {

	//检查用户
	String checkUser = "http://47.93.3.163:8080/mixinan/user/login.do?name=123&password=123";
	//注册用户
	String registUser = "http://47.93.3.163:8080/mixinan/user/regist.do?name=125&password=125&gender=1";
	//通过userId找到user
	String getUserById = "http://47.93.3.163:8080/mixinan/user/getUserById.do?id=11";


	//根据blogId获取 blog信息 以及 user信息
	String getWeibo = "http://47.93.3.163:8080/mixinan/blog/item.do?blogId=10";

	//根据 页数、条数，查找blog列表
	String getBlogsByPage = "http://47.93.3.163:8080/mixinan/blog/getblogsbypage.do?pageno=1&blogno=16";

	//根据 页数、条数，查找weibo(包含blog、user信息)列表
	String getWeiboByPage = "http://47.93.3.163:8080/mixinan/blog/getWeiboByPage.do?pageno=1&blogno=16";

	//根据blogId查找blog
	String getBlogByBlogId = "http://47.93.3.163:8080/mixinan/blog/getblog.do?id=3";

	//获取所有的blog
	String getAllBlogs = "http://47.93.3.163:8080/mixinan/blog/getallblogs.do";

	//根据 每页条数，获取blog总页数（如果没输参数，则默认按10）
	String getPages = "http://47.93.3.163:8080/mixinan/blog/getPages.do?blogno=8";

	//添加一条blog
	String addBlog = "http://47.93.3.163:8080/mixinan/blog/addblog.do?userId=3&blogText=blogtext";
	//搜索blog
	String searchBlogs = "http://47.93.3.163:8080/mixinan/blog/blogsearch.do?kw=no";
	//通过blogId删除blog
	String deleteBlog = "http://47.93.3.163:8080/mixinan/blog/delete.do?blogId=30";
	//根据userId查找该用户所有blog
	String getBlogsByUserId = "http://47.93.3.163:8080/mixinan/blog/getByUserId.do?userId=3";
	//根据blogId 修改blog
	String updateBlog = "http://47.93.3.163:8080/mixinan/blog/update.do?blogId=35&blogText=sleep";


	//添加图片
	String addPic = "http://47.93.3.163:8080/mixinan/pic/add.do?url=test&desc=hi";
	//获取所有图片
	String getPics = "http://47.93.3.163:8080/mixinan/pic/getAll.do";
	//获取最后一张图片
	String getLastPic = "http://47.93.3.163:8080/mixinan/pic/getLastPic.do";
	
}
