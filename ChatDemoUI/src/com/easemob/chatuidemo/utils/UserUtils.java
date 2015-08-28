package com.easemob.chatuidemo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.easemob.chatuidemo.HXApplication;
import com.easemob.chatuidemo.R;
import com.easemob.chatuidemo.domain.User;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;

public class UserUtils {

    /**
     * 根据username获取相应user，由于demo没有真实的用户数据，这里给的模拟的数据；
     *
     * @param username
     * @return
     */
    public static User getUserInfo(String username) {
        User user = HXApplication.getInstance().getContactList().get(username);
        if (user == null) {
            user = new User(username);
        }

        if (user != null) {
            //demo没有这些数据，临时填充
            //            user.setNick(username);
            //            user.setAvatar("http://downloads.easemob.com/downloads/57.png");
        }
        return user;
    }

    /**
     * 设置用户头像
     *
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView) {
        imageView.setBackgroundDrawable(null);
        User user = getUserInfo(username);
        com.squareup.picasso.Transformation transformation = new RoundedTransformationBuilder().oval(true).build();
        if (user != null) {
            Picasso.with(context)
                    .load(user.getAvatar())
                    .transform(transformation)
                    .placeholder(R.drawable.default_avatar)
                    .fit()
                    .into(imageView);
        } else {
            Picasso.with(context).load(R.drawable.default_avatar).transform(transformation).into(imageView);
        }
    }
}
