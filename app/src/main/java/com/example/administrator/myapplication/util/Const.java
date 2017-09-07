package com.example.administrator.myapplication.util;

/**
 * Created by Administrator on 2017/8/28.
 */

public class Const {
    /**
     偏好设置的名字
     */
    public static String LOGIN_USER_FILE_NAME = "user-info";

    private static String BASE_URL = "http://47.93.3.163:8080/mixinan";

    //根据页数、条数，查找blog列表
    public static String GET_BLOGS_BY_PAGE = BASE_URL +"/blog/getblogsbypage.do?pageno=";

    //根据 blogId,点赞+1
    //public static String LIKE = BASE_URL + "/blog/like.do?blogId=150";
    public static String LIKE = BASE_URL + "/blog/like.do?blogId=";


    //根据blogId获取 blog信息 以及 user信息
    public static String getWeibo = BASE_URL + "/blog/item.do?blogId=";

    //通过blogId删除blog
    //public static String deleteBlog = BASE_URL + "/blog/delete.do?blogId=30";
    public static String deleteBlog = BASE_URL + "/blog/delete.do?blogId=";

    //根据 页数、条数，查找weibo(包含blog、user信息)列表
    //public static String getWeiboByPage = BASE_URL + "/blog/getWeiboByPage.do?pageno=1&blogno=10";
    public static String getWeiboByPage = BASE_URL + "/blog/getWeiboByPage.do?pageno=";

    //获取最后一张图片
    public static String getLastPic = BASE_URL + "/pic/getLastPic.do";

    //通过userId找到user
    public static String getUserById = BASE_URL + "/user/getUserById.do?id=";


    //添加一条blog
//    public static String addBlog = BASE_URL + "/blog/addblog.do?userId=3&blogText=";
    public static String addBlog = BASE_URL + "/blog/addblog.do?userId=";

    //检查用户
    //public static String checkUser = BASE_URL + "/user/login.do?name=125&password=125";
    public static String checkUser = BASE_URL + "/user/login.do?name=";

    //注册用户
    //public static String registUser = BASE_URL + "/user/regist.do?name=125&password=125&gender=1";
      public static String registUser = BASE_URL + "/user/regist.do?name=";

    //添加图片
    String addPic = "http://47.93.3.163:8080/mixinan/pic/add.do?url=test&desc=hi";
    //获取所有图片
    String getPics = "http://47.93.3.163:8080/mixinan/pic/getAll.do";






    //根据blogId查找blog
    String getBlogByBlogId = "http://47.93.3.163:8080/mixinan/blog/getblog.do?id=3";
    //获取所有的blog
    String getAllBlogs = "http://47.93.3.163:8080/mixinan/blog/getallblogs.do";

    //获取blog页数
    public static String getBlogPages = BASE_URL+"/blog/getPages.do";

    //通过页数，获取当前页所有blog
    String getAllBlogsByPage = "http://47.93.3.163:8080/mixinan/blog/getblogsbypage.do?pageno=3";

    //搜索blog
    String searchBlogs = "http://47.93.3.163:8080/mixinan/blog/blogsearch.do?kw=no";
    //根据userId查找该用户所有blog
    String getBlogsByUserId = "http://47.93.3.163:8080/mixinan/blog/getByUserId.do?userId=3";
    //根据blogId 修改blog
    String updateBlog = "http://47.93.3.163:8080/mixinan/blog/update.do?blogId=35&blogText=sleep";


}
