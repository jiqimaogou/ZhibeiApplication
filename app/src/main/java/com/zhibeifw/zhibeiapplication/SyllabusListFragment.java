package com.zhibeifw.zhibeiapplication;

import android.os.Bundle;
import android.util.Log;

import com.joanzapata.android.BaseAdapterHelper;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.zhibeifw.frameworks.app.ActionBarPullToRefreshListFragment;
import com.zhibeifw.frameworks.app.DaggerApplication;
import com.zhibeifw.frameworks.widget.FlowQuickAdapter;
import com.zhibeifw.zhibeiapplication.model.Syllabus;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Administrator on 2015/3/23 0023.
 */
public class SyllabusListFragment extends ActionBarPullToRefreshListFragment {

    public static final String TAG = SyllabusListFragment.class.getSimpleName();

    @Inject
    ZhibeiService zhibeiservice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DaggerApplication) getActivity().getApplication()).inject(this);
        setListAdapter(new FlowQuickAdapter<Syllabus>(getActivity(), R.layout.material_basic_buttons_card, new Select().from(Syllabus.class).where().queryCursorList()) {
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
        zhibeiservice.listSyllabuses()
            .doOnNext(new Action1<List<Syllabus>>() {
                @Override
                public void call(List<Syllabus> syllabuses) {

                    final int count = syllabuses.size();
                    for (int i = 0; i < count; i++) {
                        final Syllabus syllabus = syllabuses.get(i);
                        syllabus.save(false);

                    }
                }
            })
            .doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Log.e(TAG, throwable.getMessage());
                }
            })
            .finallyDo(new Action0() {
                @Override
                public void call() {

                }
            })
            .subscribe();
    }
}
