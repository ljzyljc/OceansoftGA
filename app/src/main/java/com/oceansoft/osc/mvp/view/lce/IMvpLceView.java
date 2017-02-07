package com.oceansoft.osc.mvp.view.lce;

import com.oceansoft.osc.mvp.view.IMvpView;

/**
 * Created by Jackie on 2017/1/29.
 * Loading-content-error
 */

public interface IMvpLceView<M> extends IMvpView {
    //例如：加载
    //返回结果数据
    //显示View
    //加载错误

    /**
     * true代表你用的是下拉刷新组件，如果用的是下拉刷新组件，就没有必要用loading页
     * @param isPullToRefresh
     */
    public void showLoading(boolean isPullToRefresh);

    //显示视图
    public void showContent();

    //处理异常接口方法
    public void showError();

    //绑定数据
    public void bindData(M data);

    //加载数据
    public void loadData(boolean pullToRefresh);
}
