package com.zhibeifw.zhibeiapplication.syllabus;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.zhibeifw.frameworks.dbflow.Model;
import com.zhibeifw.zhibeiapplication.model.AppDatabase;

/**
 * Created by Administrator on 2015/5/30 0030.
 */
@Table(databaseName = AppDatabase.NAME)
public class OnlineRoom extends BaseModel implements Parcelable, Model {
    @Column
    @PrimaryKey
    @Expose
    long id;

    @Column
    @Expose
    String title;

    @Column
    @Expose
    String room_number;

    @Column
    @Expose
    String category;

    @Column
    @Expose
    String content;

    @Column
    @Expose
    int status;

    @Column
    @Expose
    String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.room_number);
        dest.writeString(this.category);
        dest.writeString(this.content);
        dest.writeInt(this.status);
        dest.writeString(this.url);
    }

    public OnlineRoom() {
    }

    protected OnlineRoom(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.room_number = in.readString();
        this.category = in.readString();
        this.content = in.readString();
        this.status = in.readInt();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<OnlineRoom> CREATOR = new Parcelable.Creator<OnlineRoom>() {
        public OnlineRoom createFromParcel(Parcel source) {
            return new OnlineRoom(source);
        }

        public OnlineRoom[] newArray(int size) {
            return new OnlineRoom[size];
        }
    };
}
