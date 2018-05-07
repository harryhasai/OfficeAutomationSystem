package com.hengkai.officeautomationsystem.function.management_of_goods;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hengkai.officeautomationsystem.R;
import com.hengkai.officeautomationsystem.network.entity.GoodsEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harry on 2018/4/28.
 */
public class ManagementOfGoodsAdapter extends RecyclerView.Adapter<ManagementOfGoodsAdapter.ViewHolder> {

    private List<GoodsEntity.GoodsBean> goodsList;

    public ManagementOfGoodsAdapter(List<GoodsEntity.GoodsBean> goodsList) {
        super();
        this.goodsList = goodsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_management_of_goods_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoodsEntity.GoodsBean bean = goodsList.get(position);
        holder.tvName.setText(bean.getName());
        holder.tvCount.setText(String.format("库存数量：%d%s", bean.getNum(), bean.getGoodsTypeName()));
        holder.tvType.setText(String.format("类别：%s", bean.getGoodsTypeName()));
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_type)
        TextView tvType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
