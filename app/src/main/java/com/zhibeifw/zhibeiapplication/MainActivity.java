package com.zhibeifw.zhibeiapplication;

import android.support.v4.app.Fragment;

import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.zhibeifw.frameworks.app.drawer.FragmentDrawerActivity;
import com.zhibeifw.zhibeiapplication.syllabus.OnlineRoomListFragment;
import com.zhibeifw.zhibeiapplication.test.ViewPagerTestFragment;

public class MainActivity extends FragmentDrawerActivity {

    @Override
    protected void addDrawerItems(DrawerBuilder drawerBuilder) {
        drawerBuilder.addDrawerItems(new PrimaryDrawerItem().withName("在线供佛").withTag(Fragment.class),
                                     new PrimaryDrawerItem().withName("祈福·祈愿").withTag(ViewPagerTestFragment.class),
                                     new PrimaryDrawerItem().withName("讨论园区").withTag(Fragment.class),
                                     new DividerDrawerItem(),
                                     new SecondaryDrawerItem().withName("智悲讲堂").withTag(OnlineRoomListFragment.class),
                                     new SecondaryDrawerItem().withName("佛心慧语").withTag(Fragment.class),
                                     new SecondaryDrawerItem().withName("佛教小知识").withTag(Fragment.class),
                                     new DividerDrawerItem(),
                                     new SecondaryDrawerItem().withName("寺院钟声").withTag(Fragment.class),
                                     new SecondaryDrawerItem().withName("佛学故事").withTag(Fragment.class),
                                     new DividerDrawerItem(),
                                     new SecondaryDrawerItem().withName("计数器").withTag(Fragment.class));

    }
}
