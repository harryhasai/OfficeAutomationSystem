package com.hengkai.officeautomationsystem.network.service;

import com.hengkai.officeautomationsystem.network.entity.CommonReceiveMessageEntity;
import com.hengkai.officeautomationsystem.network.entity.GoodsEntity;
import com.hengkai.officeautomationsystem.network.entity.GoodsInDetailEntity;
import com.hengkai.officeautomationsystem.network.entity.GoodsInEntity;
import com.hengkai.officeautomationsystem.network.entity.GoodsParamsEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface GoodsInService {

    @FormUrlEncoded
    @POST
    Observable<GoodsInEntity> getGoodsInList(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonReceiveMessageEntity> submitGoodsIn(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<GoodsParamsEntity> getParams(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<GoodsInDetailEntity> getGoodsInDetail(@Url String url, @FieldMap Map<String, String> params);
}
