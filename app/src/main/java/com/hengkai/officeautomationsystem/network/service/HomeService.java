package com.hengkai.officeautomationsystem.network.service;

import com.hengkai.officeautomationsystem.network.entity.MessageEntity;
import com.hengkai.officeautomationsystem.network.entity.NoticeEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface HomeService {

    @FormUrlEncoded
    @POST
    Observable<MessageEntity> getMsgList(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<MessageEntity> getApproveList(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<NoticeEntity> getNoticeList(@Url String url, @FieldMap Map<String, String> params);
}
