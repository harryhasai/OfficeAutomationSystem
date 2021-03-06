package com.hengkai.officeautomationsystem.function.new_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hengkai.officeautomationsystem.R;
import com.hengkai.officeautomationsystem.network.entity.NewProjectGetTypeEntity;

import java.util.List;

/**
 * Created by Harry on 2018/5/8.
 * 底部弹窗的适配器
 */
public class TypeBottomDialogAdapter extends RecyclerView.Adapter<TypeBottomDialogAdapter.ViewHolder> {

    private List<NewProjectGetTypeEntity.DATEBean> mList;
    private String name;
    private Context context;

    public TypeBottomDialogAdapter(List<NewProjectGetTypeEntity.DATEBean> list, String name) {
        mList = list;
        if (!TextUtils.isEmpty(name)) {
            this.name = name;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_visit_record_detail_bottom_dialog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NewProjectGetTypeEntity.DATEBean bean = mList.get(position);
        holder.tvName.setText(bean.value);
        if (bean.value.equals(name)) {
            holder.tvName.setTextColor(context.getResources().getColor(R.color.blue2));
        }
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        FrameLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            container = (FrameLayout) itemView;
        }
    }

    public interface OnItemClickListener {
        void onClick(NewProjectGetTypeEntity.DATEBean bean);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
