package com.hengkai.officeautomationsystem.function.visit_record.select_visit_project;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.hengkai.officeautomationsystem.R;
import com.hengkai.officeautomationsystem.base.BaseActivity;
import com.hengkai.officeautomationsystem.final_constant.CommonFinal;
import com.hengkai.officeautomationsystem.final_constant.NetworkTagFinal;
import com.hengkai.officeautomationsystem.function.new_project.NewProjectActivity;
import com.hengkai.officeautomationsystem.network.entity.VisitRecordDetailGetVisitUnitEntity;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/6/15.
 */
public class SelectVisitProjectActivity extends BaseActivity<SelectVisitProjectPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_operation)
    TextView tvOperation;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;

    private List<VisitRecordDetailGetVisitUnitEntity.DATABean> mList;
    private SelectVisitProjectAdapter adapter;
    /**
     * 当前单位下客户的ID
     */
    private int currentCustomerID;
    /**
     * 当前单位ID
     */
    private int currentUnitID;
    private String customerName;
    private String unitName;

    /**
     * 是否为拜访跟进
     */
    private boolean openByVisit;

    @Override
    protected int setupView() {
        return R.layout.activity_select_visit_project;
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏, 参数2: 颜色, 参数3: 透明度(0-255, 0表示透明, 255不透明)
        StatusBarUtil.setColor(this, getResources().getColor(R.color.app_theme_color), 0);
        ButterKnife.bind(this);
        initTitleBar();

        mList = new ArrayList<>();

        setupRecyclerView();

        // 获取配置
        Intent intent = getIntent();

        if(intent.hasExtra(CommonFinal.EXTRA_KEY_OPEN_BY_VISIT)) {
            openByVisit = intent.getBooleanExtra(CommonFinal.EXTRA_KEY_OPEN_BY_VISIT, false);
        }

        currentCustomerID = intent.getIntExtra("currentCustomerID", 0);
        customerName = intent.getStringExtra("customerName");
        unitName = intent.getStringExtra("unitName");
        currentUnitID = intent.getIntExtra("unitID", 0);

        mPresenter.getVisitProjectList(currentUnitID);
    }

    private void initTitleBar() {
        tvTitle.setText("跟进项目");
        tvOperation.setVisibility(View.VISIBLE);
        tvOperation.setText("新增");
    }

    @OnClick({R.id.iv_back, R.id.tv_operation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_operation:
                Intent intent = new Intent(this, NewProjectActivity.class);
                if (currentCustomerID != 0 && currentUnitID != 0) {
                    intent.putExtra("currentCustomerID", currentCustomerID);
                    intent.putExtra("unitID", currentUnitID);
                    intent.putExtra("customerName", customerName);
                    intent.putExtra("unitName", unitName);
                    intent.putExtra(CommonFinal.EXTRA_KEY_OPEN_BY_VISIT, openByVisit);
                }
                startActivityForResult(intent,
                        CommonFinal.SELECT_VISIT_PROJECT_REQUEST_CODE);
                break;
        }
    }

    /**
     * 配置列表相关
     */
    private void setupRecyclerView() {
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectVisitProjectAdapter(mList);
        swipeTarget.setAdapter(adapter);
        swipeTarget.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter.setOnItemClickListener(new SelectVisitProjectAdapter.OnItemClickListener() {
            @Override
            public void onClick(int ID, String name) {
                Intent intent = new Intent();
                intent.putExtra("projectID", ID);
                intent.putExtra("projectName", name);
                setResult(CommonFinal.SELECT_VISIT_PROJECT_RESULT_CODE, intent);
                finish();
            }
        });
    }

    @Override
    protected ArrayList<String> cancelNetWork() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add(NetworkTagFinal.VISIT_RECORD_DETAIL_ACTIVITY_GET_VISIT_UNIT_PROJECT_LIST);
        return tags;
    }

    @Override
    protected SelectVisitProjectPresenter bindPresenter() {
        return new SelectVisitProjectPresenter();
    }

    public void getVisitProjectList(List<VisitRecordDetailGetVisitUnitEntity.DATABean> list) {
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CommonFinal.SELECT_VISIT_PROJECT_REQUEST_CODE && resultCode == CommonFinal.COMMON_RESULT_CODE) {
            mPresenter.getVisitProjectList(currentUnitID);
        } else if(requestCode == CommonFinal.SELECT_VISIT_PROJECT_REQUEST_CODE && resultCode == CommonFinal.SELECT_VISIT_PROJECT_RESULT_CODE) {
            Intent intent = new Intent();
            intent.putExtra("projectID", data.getIntExtra("projectID",0));
            intent.putExtra("projectName", data.getStringExtra("projectName"));
            setResult(CommonFinal.SELECT_VISIT_PROJECT_RESULT_CODE, intent);
            finish();
        }
    }

    @Override
    protected void reloadData() {
        mPresenter.getVisitProjectList(currentUnitID);
    }
}
