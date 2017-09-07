package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.myapplication.MyApp;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.WeiboAdapter;
import com.example.administrator.myapplication.entity.Blog;
import com.example.administrator.myapplication.entity.WeiboData;
import com.example.administrator.myapplication.entity.WeiboResult;
import com.example.administrator.myapplication.module.BlogModule;
import com.example.administrator.myapplication.util.Const;
import com.example.administrator.myapplication.util.Util;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BlogFragment extends Fragment {
    private ListView lv;
    private WeiboAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private String url;
    private int currentPage;
    private int pages;
    private List<WeiboData> weiboDatas;
    private boolean haveMore;

    public BlogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blog, container, false);
        url = Const.getWeiboByPage;
        currentPage = 1;

        //微博页数
        pages = MyApp.getBlogPages();

        haveMore=true;
        initView(v);
        getData(currentPage);
        initListener();

        registerForContextMenu(lv);

        return v;
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                //微博页数
                BlogModule.getPages();
                pages = MyApp.getBlogPages();
                System.out.println("mixinan  blogFragment(pages)="+pages);
                haveMore=true;
                getData(currentPage);
            }
        });


        getActivity().findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.smoothScrollToPosition(0);
            }
        });


        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0){
                    //滚动到最后一行,在这里可以处理ListView上拉加载更多
                    if (haveMore){
                        currentPage++;
                        getData(currentPage);
                    }else{
                        //ToastUtil.showToast(getActivity(),"没有更多了");
                    }

                }
            }
        });
    }


    /**
     * 根据页数，获取weibo集合
     * @param pageno
     */
    private void getData(final int pageno) {
        refreshLayout.setRefreshing(true);

        //根据 页数、条数，查找weibo(包含blog、user信息)列表
        //public static String getWeiboByPage = BASE_URL + "/blog/getWeiboByPage.do?pageno=1&blogno=10";
        //public static String getWeiboByPage = BASE_URL + "/blog/getWeiboByPage.do?pageno=";

        StringRequest request = new StringRequest(url+pageno+"&blogno=10", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    List<WeiboData> data = new ArrayList<WeiboData>();
                    Gson gson = new Gson();
                    WeiboResult result = gson.fromJson(response,WeiboResult.class);
                    data = (List<WeiboData>) result.getData();
                    if (pageno < pages && data.size()==10) {
                        haveMore=true;
                    }else{
                        haveMore = false;
                    }
                    show(data,pageno);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("mixinan", error.toString());
            }
        });

        MyApp.getRequestQueue().add(request);
    }




    /**
     * 显示数据
     * @param data
     * @param pageno
     */
    private void show(List<WeiboData> data, int pageno) {
        if (pageno==1){
            weiboDatas.clear();
            weiboDatas.addAll(data);
            adapter = new WeiboAdapter(weiboDatas,getActivity());
            lv.setAdapter(adapter);
        }else{
            weiboDatas.addAll(data);
            adapter.notifyDataSetChanged();
        }
        refreshLayout.setRefreshing(false);
    }





    private void initView(View v) {
        lv = (ListView) v.findViewById(R.id.lv_blog);
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.unclick,R.color.click);
        weiboDatas = new ArrayList<WeiboData>();
    }



    @Override
    public void onResume() {
        super.onResume();
        currentPage = 1;
        getData(currentPage);
    }





    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(getActivity());
        inflater.inflate(R.menu.menu_blog_context, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;  //长按的位置，从0开始

        Blog blog = weiboDatas.get(position).getBlog();

        switch (item.getItemId()){
            case R.id.menu_blog_copy:
                //复制到剪贴板
                Util.copyToClipboard(getActivity(), Util.UTF8Decoder(blog.getMi_blog_text()));
                break;
            case R.id.menu_blog_share:
                //分享blog内容
                Util.shareBlog(getActivity(),Util.UTF8Decoder(blog.getMi_blog_text()),"分享微博到:");
                break;
            case R.id.menu_blog_delete:
                //删除位置为position的blog
                Util.showDeleteBlogDialog(getActivity(),weiboDatas.get(position),adapter);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
