package com.hengkai.officeautomationsystem.function.goods_supplier;

import com.hengkai.officeautomationsystem.base.presenter.BasePresenter;
import com.hengkai.officeautomationsystem.final_constant.NetworkTagFinal;
import com.hengkai.officeautomationsystem.function.management_of_goods.AddGoodsActivity;
import com.hengkai.officeautomationsystem.function.management_of_goods.AddGoodsModel;
import com.hengkai.officeautomationsystem.network.entity.AddGoodsEntity;
import com.hengkai.officeautomationsystem.network.entity.AddGoodsSupplierEntity;
import com.hengkai.officeautomationsystem.network.entity.CommonReceiveMessageEntity;
import com.hengkai.officeautomationsystem.network.entity.EditGoodsEntity;
import com.hengkai.officeautomationsystem.network.entity.EditGoodsSupplierEntity;
import com.hengkai.officeautomationsystem.utils.ToastUtil;
import com.hengkai.officeautomationsystem.utils.rx.RxApiManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AddGoodsSupplierPresenter extends BasePresenter<AddGoodsSupplierActivity> {

    private final AddGoodsSupplierModel model;

    public AddGoodsSupplierPresenter() {
        model = new AddGoodsSupplierModel();
    }

    public void addGoodsSupplier(String name, int supperType, String city, String address,
                                 String postalCode, String contacts, String phone, String fax,String mailBox,
                                 String qq, String weChat, String source, int state, String description, String remark) {
        view.showDialog();
        model.addGoodsSupplier(new Observer<CommonReceiveMessageEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.ADD_GOODS_SUPPLIER_ACTIVITY_DO_ADD, d);
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
        }, name, supperType, city, address, postalCode, contacts, phone, fax,mailBox, qq, weChat, source, state, description, remark);
    }

    public void updateGoodsSupplier(int id, String name, int supperType, String city, String address,
                                    String postalCode, String contacts, String phone, String fax,String mailBox,
                                    String qq, String weChat, String source, int state, String description, String remark) {
        view.showDialog();
        model.updateGoodsSupplier(new Observer<CommonReceiveMessageEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.UPDATE_GOODS_SUPPLIER_ACTIVITY_GET_PARAMS, d);
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
        }, id, name, supperType, city, address, postalCode, contacts, phone, fax,mailBox, qq, weChat, source, state, description, remark);
    }

    public void getAddSupplierParams() {
        view.showDialog();
        model.getAddSupplierParams(new Observer<AddGoodsSupplierEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.ADD_GOODS_SUPPLIER_ACTIVITY_GET_PARAMS, d);
            }

            @Override
            public void onNext(AddGoodsSupplierEntity addEntity) {
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

    public void getUpdateSupplierParams(int id) {
        view.showDialog();
        model.getUpdateSupplierParams(new Observer<EditGoodsSupplierEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.UPDATE_GOODS_SUPPLIER_ACTIVITY_DO_UPDATE, d);
            }

            @Override
            public void onNext(EditGoodsSupplierEntity paramsEntity) {
                view.dismissDialog();
                if (paramsEntity.CODE == 1) {
                    view.getEditParamsSuccess(paramsEntity);
                } else if (paramsEntity.CODE == 0) {//TOKEN失效
                    ToastUtil.showToast("登录失效，请重新登录");
                    view.showLoginDialog(view);
                } else {
                    ToastUtil.showToast(paramsEntity.MES);
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
