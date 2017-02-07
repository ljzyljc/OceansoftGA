package com.oceansoft.osc.moudle.consult.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.oceansoft.osc.R;
import com.oceansoft.osc.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osc.utils.ToastUtil;


import java.util.List;

/**
 * Created by TempCw on 2017/1/15.
 * 咨询中心界面 adapter
 */

public class ConsultAdapter extends BaseRecyclerAdapter<ConsultAdapter.ViewHolder> {

    private List<ConsultMatter.DataBean.ListBean> mList;
    private Context context;

    public ConsultAdapter(List<ConsultMatter.DataBean.ListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType,boolean isItem) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consult_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position,boolean isItem) {
        holder.txt_question_content.setText(mList.get(position).getContext());
//        Log.i("jc",mList.get(position).getContext()+"0000");
        holder.view_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(context,"点击的问题");
            }
        });
    }

    @Override
    public int getAdapterItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private  TextView txt_question_content;
        private TextView txt_question_time;
        private TextView txt_title;

        private RelativeLayout view_question;

        public ViewHolder(View itemView) {
           super(itemView);
             txt_question_content = (TextView) itemView.findViewById(R.id.txt_question_content);
            txt_question_time= (TextView) itemView.findViewById(R.id.txt_question_time);
            txt_title= (TextView) itemView.findViewById(R.id.txt_title);
            view_question= (RelativeLayout) itemView.findViewById(R.id.view_question);


//           TextView txt_question_content = (TextView) ViewHolder.get(view, R.id.txt_question_content);
//           TextView txt_question_time = (TextView) ViewHolder.get(view, R.id.txt_question_time);
//           TextView txt_title = ViewHolder.get(view, R.id.txt_title);
//           final View view_loading = ViewHolder.get(view, R.id.view_loading);
//           final View view_question = ViewHolder.get(view, R.id.view_question);
//           final TextView txt_answer_content = (TextView) ViewHolder.get(view, R.id.txt_answer_content);
//           final TextView txt_answer_time = (TextView) ViewHolder.get(view, R.id.txt_answer_time);
//           final View view_answer = ViewHolder.get(view, R.id.view_answer);
//           final TextView txt_no_response = (TextView) ViewHolder.get(view, R.id.txt_no_response);
       }
   }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
