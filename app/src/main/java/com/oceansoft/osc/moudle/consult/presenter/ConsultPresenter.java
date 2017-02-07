package com.oceansoft.osc.moudle.consult.presenter;

import android.content.Context;
import android.util.Log;

import com.oceansoft.osc.moudle.base.presenter.AbsBasePresenter;
import com.oceansoft.osc.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osc.moudle.consult.model.ConsultModel;
import com.oceansoft.osc.moudle.consult.view.IConsultView;

/**
 * Created by TempCw on 2017/2/3.
 */

public class ConsultPresenter extends AbsBasePresenter<ConsultModel,IConsultView> {
    public ConsultPresenter(Context context) {
        super(context);
    }

    @Override
    public ConsultModel bindModel() {
        return new ConsultModel(getContext());
    }
    public void getConsultList(int pageNum){
        getModel().getMatterList(pageNum, new ConsultModel.OnLoadLister() {
            @Override
            public void onSuccess(ConsultMatter consultMatter) {
                getView().load(consultMatter);
                Log.e("tag",consultMatter.getData().getList().get(0).getGuid()+":");
                Log.i("jc","  getView().load(consultMatter);cg");
            }

            @Override
            public void onFailor(String errorMessage) {
                getView().loadFail();
                Log.i("jc",errorMessage+"加载失败");
            }
        });

    }

    @Override
    public void Onunsubscribe() {
        getModel().unsubscribe();
    }
}
