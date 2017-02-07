package com.oceansoft.osc.mvp.presenter.impl;

import android.content.Context;

import com.oceansoft.osc.mvp.model.IMvpModel;
import com.oceansoft.osc.mvp.presenter.IMvpPresenter;
import com.oceansoft.osc.mvp.view.IMvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Jackie on 2017/1/29.
 * 同时绑定View层和Model层
 *
 * 弱引用
 * 用弱引用去持有对象，当这个对象一旦被释放（或者置为空），它就会自动释放
 */

public abstract class AbsMvpPresenter<M extends IMvpModel,V extends IMvpView>
        implements IMvpPresenter<V>{
    private V proxy;
    private M model;
    private WeakReference<Context> weakContxt;
    private WeakReference<V> weakView;

//    public M bindModel(){
//        return model;
//    }

    /**
     * 抽象该方法，子类必须实现，绑定Model
     * @return
     */
    public abstract M bindModel();

    public M getModel() {
        if (this.model==null){
            this.model=bindModel();
        }

        return model;
    }

    public Context getContext() {
        return weakContxt.get();
    }
    //构造器
    public AbsMvpPresenter(Context context){
        this.weakContxt=new WeakReference<Context>(context);
    }
    public V getView(){
        //返回代理对象
        return proxy;

    }


    /**
     * 用于检测View是否为空对象
     * @return
     */
    public boolean isAttachView(){
        if (this.weakView!=null&&this.weakView.get()!=null){
            return true;
        }
        return false;
    }

    @Override
    public void attachView(V view) {
       this.weakView=new WeakReference<V>(view);
        MvpViewInvocationHandler handler=new MvpViewInvocationHandler(this.weakView.get());
        //动态代理
        proxy= (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(),
                handler);

    }

    @Override
    public void detachView() {
        if (this.weakView!=null){
            this.weakView.clear();
            this.weakView=null;
        }
    }
    private class MvpViewInvocationHandler implements InvocationHandler{
        //目标对象
        private IMvpView iMvpView;
        public MvpViewInvocationHandler(IMvpView iMvpView){
            this.iMvpView=iMvpView;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //在这里使用动态代理去判断是否绑定了，就不用每个类里都进行判断（优化）
            if (isAttachView()){
                return method.invoke(iMvpView,args);
            }
            return null;
        }
    }



}
