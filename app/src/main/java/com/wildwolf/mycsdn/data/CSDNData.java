package com.wildwolf.mycsdn.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class CSDNData implements Parcelable {

    private String name;
    private String imgUrl;
    private String subtype;
    private String articleCount;
    private String readCount;
    private String href;
    private int width;
    private int height;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
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

    public CSDNData(){}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imgUrl);
        dest.writeString(this.subtype);
        dest.writeString(this.articleCount);
        dest.writeString(this.readCount);
        dest.writeString(this.href);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    protected CSDNData(Parcel in) {
        this.name = in.readString();
        this.imgUrl = in.readString();
        this.subtype = in.readString();
        this.articleCount = in.readString();
        this.readCount = in.readString();
        this.href = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Creator<CSDNData> CREATOR = new Creator<CSDNData>() {
        @Override
        public CSDNData createFromParcel(Parcel source) {
            return new CSDNData(source);
        }

        @Override
        public CSDNData[] newArray(int size) {
            return new CSDNData[size];
        }
    };
}
