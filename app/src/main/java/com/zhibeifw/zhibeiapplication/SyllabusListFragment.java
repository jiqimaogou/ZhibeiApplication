package com.zhibeifw.zhibeiapplication;

import android.os.Bundle;

import com.joanzapata.android.BaseAdapterHelper;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.zhibeifw.frameworks.app.ActionBarPullToRefreshListFragment;
import com.zhibeifw.frameworks.app.DaggerApplication;
import com.zhibeifw.frameworks.widget.FlowQuickAdapter;
import com.zhibeifw.zhibeiapplication.model.Syllabus;

import javax.inject.Inject;

/**
 * Created by Administrator on 2015/3/23 0023.
 */
public class SyllabusListFragment extends ActionBarPullToRefreshListFragment {

    @Inject
    ZhibeiService zhibeiservice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DaggerApplication) getActivity().getApplication()).inject(this);
        setListAdapter(new FlowQuickAdapter<Syllabus>(getActivity(), R.layout.material_big_image_card_layout, new Select().from(Syllabus.class).where().queryCursorList()) {
            @Override
            protected void convert(BaseAdapterHelper helper, Syllabus syllabus) {
                /* boolean isRetweet = status.isRetweet();
                   if (isRetweet) status = status.getRetweetedStatus();

                   helper.setText(tweetText, status.getText())
                   .setVisible(tweetRT, isRetweet)
                   .setText(tweetName, status.getUser().getName())
                   .setText(tweetDate, dateFormat.format(status.getCreatedAt()))
                   .setImageUrl(tweetAvatar, status.getUser().getProfileImageURL())
                   .linkify(tweetText); */
                   helper.setText(R.id.titleTextView, syllabus.getSyllabus());
            }
        });
    }
}
