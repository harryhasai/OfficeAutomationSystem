package com.hengkai.officeautomationsystem.network.service;

import com.hengkai.officeautomationsystem.network.entity.GoodsDetailEntity;
import com.hengkai.officeautomationsystem.network.entity.GoodsEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface HomeService {

    @FormUrlEncoded
    @POST
    Observable<GoodsEntity> getMsgList(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<GoodsDetailEntity> getApproveList(@Url String url, @FieldMap Map<String, String> params);
}
