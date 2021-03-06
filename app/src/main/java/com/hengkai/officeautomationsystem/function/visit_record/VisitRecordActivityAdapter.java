package com.hengkai.officeautomationsystem.function.visit_record;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hengkai.officeautomationsystem.R;
import com.hengkai.officeautomationsystem.network.entity.VisitRecordEntity;
import com.hengkai.officeautomationsystem.utils.DateFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harry on 2018/5/7.
 */
public class VisitRecordActivityAdapter extends RecyclerView.Adapter<VisitRecordActivityAdapter.ViewHolder> {

    private List<VisitRecordEntity.DATABean> mList;
    private Context context;

    public VisitRecordActivityAdapter(List<VisitRecordEntity.DATABean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_visit_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final VisitRecordEntity.DATABean bean = mList.get(position);
        switch (bean.state) {
            case 0://未审批
                holder.tvStateType.setText("待审批");
                break;
            case 1://通过
                holder.tvStateType.setText("通过");
                break;
            case 2://未通过
                holder.tvStateType.setText("未通过");
                break;
            case 3://已撤销
                holder.tvStateType.setText("已撤销");
                break;
            case 4://已保存
                holder.tvStateType.setText("已保存");
                break;
        }
        switch (bean.type) {
            case "0":   //跟进
                holder.tvType.setText("跟进");
                break;
            case "1":   //拜访
                holder.tvType.setText("拜访");
                break;
            case "2":   //招待
                holder.tvType.setText("招待");
                break;
            case "3":   //方案
                holder.tvType.setText("方案");
                break;
            case "4":   //合同
                holder.tvType.setText("合同");
                break;
            case "5":   //标书
                holder.tvType.setText("标书");
                break;
            case "6":   //日常工作
                holder.tvType.setText("日常工作");
                break;
        }
        if (!TextUtils.isEmpty(bean.companyName)) {
            holder.tvUnitName.setText(bean.companyName);
        } else {
            holder.tvUnitName.setText(String.format("%s的%s", bean.userName, holder.tvType.getText().toString()));
        }
        holder.tvDepartment.setText(bean.department);
        holder.tvContactsName.setText(bean.contactsName);
        String startTime;
        if (bean.startTime == 0) {
            startTime = "还未开始";
        } else {
            startTime = DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_1, bean.startTime);
        }
        if (bean.endTime == 0) {
            holder.tvTime.setText(String.format("开始时间%s", startTime));
        } else {
            String endTime = DateFormatUtils.getFormatedDateTime(DateFormatUtils.PATTERN_1, bean.endTime);
            holder.tvTime.setText(String.format("%s至%s", startTime, endTime));
        }
        holder.tvUserName.setText(bean.userName);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(bean);
                }
            }
        });

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener != null) {
                    mListener.onLongClick(bean, holder.getAdapterPosition());
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_unit_name)
        TextView tvUnitName;
        @BindView(R.id.tv_state_type)
        TextView tvStateType;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_department)
        TextView tvDepartment;
        @BindView(R.id.tv_contacts_name)
        TextView tvContactsName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;

        LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (LinearLayout) itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onClick(VisitRecordEntity.DATABean bean);

        void onLongClick(VisitRecordEntity.DATABean bean, int position);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void updateData(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }
}
