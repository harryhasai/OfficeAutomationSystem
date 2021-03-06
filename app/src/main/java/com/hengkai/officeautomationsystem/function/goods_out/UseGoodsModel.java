package com.hengkai.officeautomationsystem.function.goods_out;

import com.hengkai.officeautomationsystem.final_constant.CommonFinal;
import com.hengkai.officeautomationsystem.final_constant.URLFinal;
import com.hengkai.officeautomationsystem.final_constant.UserInfo;
import com.hengkai.officeautomationsystem.network.service.GoodsService;
import com.hengkai.officeautomationsystem.network.service.UseGoodsService;
import com.hengkai.officeautomationsystem.utils.RetrofitHelper;
import com.hengkai.officeautomationsystem.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 *
 */
public class UseGoodsModel {

    private final UseGoodsService service;

    public UseGoodsModel() {
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        service = retrofit.create(UseGoodsService.class);
    }

    /**
     * 保存领用申请
     * @param observer
     * @param price 价格
     * @param reason 事由
     * @param details 物品详情
     * @param projectId 项目ID（可为空）
     */
    public void saveGoods(Observer observer, double price, String reason, String details, int projectId) {
        Map<String, String> params = new HashMap<>();

        params.put("TOKEN", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        params.put("USERID", SPUtils.getString(UserInfo.USER_ID.name(), ""));
        if (projectId > 0) {
            params.put("PRODUCTID", projectId + ""); // 项目ID
        }
        params.put("TOTALMONEY", price + ""); // 总价
        params.put("PURPOSE", reason + ""); // 领用原因
        params.put("OUTGOODSVO", details + ""); // 领用详情
        params.put("pageSize", CommonFinal.PAGE_SIZE + "");

        service.submitUse(URLFinal.USE_GOODS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取页面所需参数
     * @param observer
     */
    public void getParams(Observer observer) {
        Map<String, String> params = new HashMap<>();

        params.put("TOKEN", SPUtils.getString(UserInfo.TOKEN.name(), ""));
        // params.put("USERID", SPUtils.getInt(UserInfo.USER_ID.name(), 0) + "");

        service.getParams(URLFinal.USE_GOODS_PARAMS, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
