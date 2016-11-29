package com.wildwolf.mycsdn.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class CSDNLibData implements Parcelable {

    private String name;
    private String imgUrl;
    private String iconUrl;
    private String href;
    private String articleCount;
    private String readCount;
    private int width;
    private int height;

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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imgUrl);
        dest.writeString(this.iconUrl);
        dest.writeString(this.href);
        dest.writeString(this.articleCount);
        dest.writeString(this.readCount);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    public CSDNLibData() {
    }

    protected CSDNLibData(Parcel in) {
        this.name = in.readString();
        this.imgUrl = in.readString();
        this.iconUrl = in.readString();
        this.href = in.readString();
        this.articleCount = in.readString();
        this.readCount = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Creator<CSDNLibData> CREATOR = new Creator<CSDNLibData>() {
        @Override
        public CSDNLibData createFromParcel(Parcel source) {
            return new CSDNLibData(source);
        }

        @Override
        public CSDNLibData[] newArray(int size) {
            return new CSDNLibData[size];
        }
    };
}
