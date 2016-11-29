package com.wildwolf.mycsdn.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${wild00wolf} on 2016/11/22.
 */
public class BlogData  implements Parcelable {
    private String title;
    private String url;
    private String id;
    private int width;
    private int height;
    private String subtype;

    public BlogData(){}

    protected BlogData(Parcel in) {
        title = in.readString();
        url = in.readString();
        id = in.readString();
        width = in.readInt();
        height = in.readInt();
        subtype = in.readString();
    }


    public static final Creator<BlogData> CREATOR = new Creator<BlogData>() {
        @Override
        public BlogData createFromParcel(Parcel in) {
            return new BlogData(in);
        }

        @Override
        public BlogData[] newArray(int size) {
            return new BlogData[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String detailUrl) {
        this.id = detailUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(url);
        parcel.writeString(id);
        parcel.writeInt(width);
        parcel.writeInt(height);
        parcel.writeString(subtype);
    }
}

