package com.hengkai.officeautomationsystem.function.goods_in;

import com.hengkai.officeautomationsystem.base.presenter.BasePresenter;
import com.hengkai.officeautomationsystem.final_constant.NetworkTagFinal;
import com.hengkai.officeautomationsystem.network.entity.CommonReceiveMessageEntity;
import com.hengkai.officeautomationsystem.network.entity.GoodsParamsEntity;
import com.hengkai.officeautomationsystem.utils.ToastUtil;
import com.hengkai.officeautomationsystem.utils.rx.RxApiManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GoodsInPresenter extends BasePresenter<GoodsInActivity> {

    private final GoodsInModel model;

    public GoodsInPresenter() {
        model = new GoodsInModel();
    }

    public void saveGoodsIn(double price, String reason, String details) {
        view.showDialog();
        model.saveGoodsIn(new Observer<CommonReceiveMessageEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.GOODS_IN_ACTIVITY_SAVE_GOODS_IN, d);
            }

            @Override
            public void onNext(CommonReceiveMessageEntity msgEntity) {
                if (msgEntity.CODE == 1) {
                    view.dismissDialog();
                    view.submitSuccess();
                } else if (msgEntity.CODE == 3) {
                    view.dismissDialog();
                    ToastUtil.showToast("系统异常，申请提交失败");
                } else if (msgEntity.CODE == 0) {//TOKEN失效
                    view.dismissDialog();
                    ToastUtil.showToast("登录失效，请重新登录");
                    view.showLoginDialog(view);
                } else {
                    view.dismissDialog();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.dismissDialog();
                ToastUtil.showToast("请求网络失败");
            }

            @Override
            public void onComplete() {

            }
        }, price, reason, details);
    }

    public void getParams() {
        view.showDialog();
        model.getParams(new Observer<GoodsParamsEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.GOODS_IN_ACTIVITY_GET_GOODS_PARAMS, d);
            }

            @Override
            public void onNext(GoodsParamsEntity goodsParamsEntity) {
                if (goodsParamsEntity.getCODE() == 1) {
                    view.dismissDialog();
                    view.getParamsSuccess(goodsParamsEntity);
                } else if (goodsParamsEntity.getCODE() == 3) {
                    view.dismissDialog();
                    ToastUtil.showToast("系统异常，获取参数失败");
                    view.finish();
                } else if (goodsParamsEntity.getCODE() == 0) {//TOKEN失效
                    view.dismissDialog();
                    ToastUtil.showToast("登录失效，请重新登录");
                    view.showLoginDialog(view);
                } else if (goodsParamsEntity.getCODE() == -2) {
                    ToastUtil.showToast("系统没有设置参数，请联系管理员");
                    view.dismissDialog();
                    view.finish();
                } else {
                    view.dismissDialog();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.dismissDialog();
                ToastUtil.showToast("请求网络失败");
                view.finish();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
