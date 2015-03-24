package com.zhibeifw.zhibeiapplication;

import com.zhibeifw.zhibeiapplication.model.Syllabus;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by Administrator on 2015/3/23 0023.
 */
public interface ZhibeiService {
  @GET("/zhibei_feed/get_syllabus_list")
  List<Syllabus> listSyllabuses();
}
