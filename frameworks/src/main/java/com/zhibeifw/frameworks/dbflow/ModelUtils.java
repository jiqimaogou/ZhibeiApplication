package com.zhibeifw.frameworks.dbflow;

import android.database.Cursor;

import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.cache.BaseCacheableModel;

import java.util.List;

/**
 * Created by Administrator on 2015/5/30 0030.
 */
public class ModelUtils {
    public static <ModelClass extends Model> ModelClass convertToModel(Cursor cursor, Class<ModelClass> modelClass) {
        ModelClass retModel = null;
        if (BaseCacheableModel.class.isAssignableFrom(modelClass)) {
            retModel = (ModelClass) SqlUtils.convertToCacheableModel(false,
                                                                     (Class<? extends BaseCacheableModel>) modelClass,
                                                                     cursor);
        } else {
            retModel = SqlUtils.convertToModel(false, modelClass, cursor);
        }
        return retModel;
    }

    public static <ModelClass extends Model> List<ModelClass> convertToList(Cursor cursor,
            Class<ModelClass> modelClass) {
        List<ModelClass> list = null;
        if (BaseCacheableModel.class.isAssignableFrom(modelClass)) {
            /*list = (List<ModelClass>) SqlUtils.convertToCacheableList((Class<? extends BaseCacheableModel>) modelClass,
                                                                      cursor);*/
        } else {
            list = SqlUtils.convertToList(modelClass, cursor);
        }

        return list;
    }
}
