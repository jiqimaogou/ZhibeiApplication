package com.zhibeifw.zhibeiapplication;

// import android.database.Observable;

import com.zhibeifw.zhibeiapplication.model.Syllabus;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2015/3/23 0023.
 */
public interface ZhibeiService {
  @GET("/syllabuses")
  Observable<List<Syllabus>> listSyllabuses();
}
