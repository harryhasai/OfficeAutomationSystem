package com.hengkai.officeautomationsystem.network.service;

import com.hengkai.officeautomationsystem.network.entity.CommentVisitEntity;
import com.hengkai.officeautomationsystem.network.entity.CommonReceiveMessageEntity;
import com.hengkai.officeautomationsystem.network.entity.NoticeDetailEntity;
import com.hengkai.officeautomationsystem.network.entity.NoticeEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface NoticeService {

    @FormUrlEncoded
    @POST
    Observable<NoticeEntity> getNoticeList(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<NoticeDetailEntity> getNoticeDetail(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommentVisitEntity> getNoticeComments(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Observable<CommonReceiveMessageEntity> comment(@Url String url, @FieldMap Map<String, String> params);

}
