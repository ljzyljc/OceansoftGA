package com.oceansoft.osc.moudle.consult.view;

import com.oceansoft.osc.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osc.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/2/3.
 */

public interface IConsultView extends IMvpView{

    void loadListView(ConsultMatter consultMatter);

    void loadListViewFail(String errorMessage);


}
