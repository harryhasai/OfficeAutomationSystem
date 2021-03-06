package com.hengkai.officeautomationsystem.function.contacts;

import com.hengkai.officeautomationsystem.base.presenter.BasePresenter;
import com.hengkai.officeautomationsystem.final_constant.NetworkTagFinal;
import com.hengkai.officeautomationsystem.network.entity.ContactsEntity;
import com.hengkai.officeautomationsystem.utils.ToastUtil;
import com.hengkai.officeautomationsystem.utils.rx.RxApiManager;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Harry on 2018/5/4.
 */
public class ContactsActivityPresenter extends BasePresenter<ContactsActivity> {

    private final ContactsActivityModel model;

    public ContactsActivityPresenter() {
        model = new ContactsActivityModel();
    }

    public void getContactsList() {
        model.getContactsList(new Observer<ContactsEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxApiManager.get().add(NetworkTagFinal.CONTACTS_ACTIVITY_GET_CONTACTS_LIST, d);
            }

            @Override
            public void onNext(ContactsEntity contactsEntity) {
                if (contactsEntity.CODE == 1) {
                    List<ContactsEntity.DIRECTORIESBean> list = contactsEntity.DIRECTORIES;
                    if (list != null && list.size() != 0) {
                        view.prepareData(list);
                    } else {
                        view.noData();
                    }
                } else if (contactsEntity.CODE == -1) {
                    ToastUtil.showToast("操作失败: " + contactsEntity.MES);
                } else if (contactsEntity.CODE == 0) {//TOKEN失效
                    view.showLoginDialog(view);
                }
            }

            @Override
            public void onError(Throwable e) {
                view.noData();
                ToastUtil.showToast("请求网络失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
