package com.hengkai.officeautomationsystem.function.management_of_goods;

import com.hengkai.officeautomationsystem.base.presenter.BasePresenter;
import com.hengkai.officeautomationsystem.final_constant.NetworkTagFinal;
import com.hengkai.officeautomationsystem.network.entity.AddGoodsEntity;
import com.hengkai.officeautomationsystem.network.entity.CommonReceiveMessageEntity;
import com.hengkai.officeautomationsystem.network.entity.EditGoodsEntity;
import com.hengkai.officeautomationsystem.network.entity.GoodsParamsEntity;
import com.hengkai.officeautomationsystem.utils.ToastUtil;
import com.hengkai.officeautomationsystem.utils.rx.RxApiManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AddGoodsPresenter extends BasePresenter<AddGoodsActivity> {

    private final AddGoodsModel model;

    public AddGoodsPresenter() {
        model = new AddGoodsModel();
    }

    public void addGoods(String name, int type, int supplier, int unit, String band, String spec, double cost, String remark, int num, double total) {
        view.showDialog();
        model.addGoods(new Observer<CommonReceiveMessageEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.ADD_GOODS_ACTIVITY_DO_ADD, d);
            }

            @Override
            public void onNext(CommonReceiveMessageEntity msgEntity) {
                view.dismissDialog();
                if (msgEntity.CODE == 1) {
                    view.addSuccess();
                } else if (msgEntity.CODE == 0) {//TOKEN失效
                    ToastUtil.showToast("登录失效，请重新登录");
                    view.showLoginDialog(view);
                } else {
                    ToastUtil.showToast(msgEntity.MES);
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
        }, name, type, supplier, unit, band, spec, cost, remark, num, total);
    }

    public void updateGoods(int id,String name, int type, int supplier, int unit, String band, String spec, double cost, String remark, int num, double total) {
        view.showDialog();
        model.updateGoods(new Observer<CommonReceiveMessageEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.UPDATE_GOODS_ACTIVITY_DO_UPDATE, d);
            }

            @Override
            public void onNext(CommonReceiveMessageEntity msgEntity) {
                view.dismissDialog();
                if (msgEntity.CODE == 1) {
                    view.editSuccess();
                } else if (msgEntity.CODE == 0) {//TOKEN失效
                    ToastUtil.showToast("登录失效，请重新登录");
                    view.showLoginDialog(view);
                } else {
                    ToastUtil.showToast(msgEntity.MES);
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
        }, id,name, type, supplier, unit, band, spec, cost, remark, num, total);
    }

    public void getAddParams() {
        view.showDialog();
        model.getAddParams(new Observer<AddGoodsEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.ADD_GOODS_ACTIVITY_GET_PARAMS, d);
            }

            @Override
            public void onNext(AddGoodsEntity addEntity) {
                view.dismissDialog();
                if (addEntity.CODE == 1) {
                    view.getAddParamsSuccess(addEntity);
                } else if (addEntity.CODE == 0) {//TOKEN失效
                    ToastUtil.showToast("登录失效，请重新登录");
                    view.showLoginDialog(view);
                } else {
                    ToastUtil.showToast(addEntity.MES);
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

    public void getEditParams(int id) {
        view.showDialog();
        model.getUpdateParams(new Observer<EditGoodsEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.UPDATE_GOODS_ACTIVITY_GET_PARAMS, d);
            }

            @Override
            public void onNext(EditGoodsEntity goodsParamsEntity) {
                view.dismissDialog();
                if (goodsParamsEntity.CODE == 1) {
                    view.getEditParamsSuccess(goodsParamsEntity);
                } else if (goodsParamsEntity.CODE == 0) {//TOKEN失效
                    ToastUtil.showToast("登录失效，请重新登录");
                    view.showLoginDialog(view);
                } else {
                    ToastUtil.showToast(goodsParamsEntity.MES);
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
        }, id);
    }
}
