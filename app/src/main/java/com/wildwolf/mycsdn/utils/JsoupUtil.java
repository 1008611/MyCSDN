package com.wildwolf.mycsdn.utils;

import android.util.Log;


import com.wildwolf.mycsdn.constant.Api;
import com.wildwolf.mycsdn.data.BlogData;
import com.wildwolf.mycsdn.data.CSDNData;
import com.wildwolf.mycsdn.data.CSDNLibData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${wild00wolf} on 2016/11/21.
 */
public class JsoupUtil {

    public static List<BlogData> parseTest2(String s) {
        Document document = Jsoup.parse(s);
//        Elements elements = document.select("div.pic");
        Elements elements = document.getElementsByClass("article_item");

        List<BlogData> list = new ArrayList<>();
        BlogData data;
        for (Element element : elements) {
            data = new BlogData();
            data.setTitle(element.select("h1").text());

            String href = element.select("a").attr("href");
            href = href.substring(href.indexOf("/"));
            data.setId(href);

            data.setSubtype(element.select("div.article_manage").text());
            data.setUrl(Api.URL_GET_BLOG + element.select("a").attr("href"));
            list.add(data);
        }

        return list;

    }

    public static List<BlogData> parseOtherBlog(String s) {
        Document document = Jsoup.parse(s);
        Elements elements = document.getElementsByClass("clearfix");

        List<BlogData> list = new ArrayList<>();
        BlogData data;
        for (Element element : elements) {
            data = new BlogData();
            data.setTitle(element.select("dd").select("h3.list_c_t").select("a").text());
//            data.setSubtype(element.select("dd").select("p.list_c_c").text().substring(0,15));
            String year = element.select("dt").select("div.date").select("span").text();
            String month = element.select("dt").select("div.date").select("em").text();
            String day = element.select("dt").select("div.date").select("div.date_b").text();

            data.setSubtype(year + "." + NumUtil.ChineseChangeToNumber(month) + "." + day);

            String href = element.select("dd").select("h3.list_c_t").select("a").attr("href");
            href = href.substring(href.indexOf("/"));
            data.setId(href);
            data.setUrl(Api.URL_GET_BLOG + element.select("dd").select("h3.list_c_t").select("a").attr("href"));

            list.add(data);
        }

        return list;
    }

    public static List<CSDNData> parseCSDN(String s) {
        Document document = Jsoup.parse(s);
//        Elements elements = document.select("div.pic");
        Elements elements = document.getElementsByClass("experts_list");

        List<CSDNData> list = new ArrayList<>();
        CSDNData data;
        for (Element element : elements) {
            data = new CSDNData();

            data.setName(element.select("dd").select("a.expert_name").text());
            String href = element.select("dt").select("a").attr("href");

            href = href.substring(href.lastIndexOf("/") + 1);
            data.setHref(href);

            data.setImgUrl(element.select("dt").select("img").attr("src"));
            data.setSubtype(element.select("div.address").text());

            data.setArticleCount(element.select("div.fl").select("b").text());
            data.setReadCount(element.select("div.fr").select("b").text());

            list.add(data);
        }

        return list;
    }

    public static List<CSDNLibData> parseCsdnLib(String s) {
        Document document = Jsoup.parse(s);

        Elements elements = document.getElementsByClass("whitebk");

        Log.e("TAG-e", elements.toString());

        List<CSDNLibData> list = new ArrayList<>();
        CSDNLibData data;
        for (Element element : elements) {
            data = new CSDNLibData();
            data.setHref(element.select("div.divtop").select("a.topphoto").attr("href"));
            data.setImgUrl(element.select("div.divtop").select("div.bannerimg").select("img").attr("src"));  //
            data.setIconUrl(element.select("div.divtop").select("a.topphoto").select("img").attr("src"));  //
            data.setName(element.select("div.divbottoms").select("a.title").text());
            list.add(data);
        }


        return list;
    }
//    public static List<CSDNLibData> parseCsdnLib2(String s) {
//        Document document = Jsoup.parse(s);
//
//        Elements elements2 = document.getElementsByClass("divbottoms");
//        CSDNLibData data ;
//        List<CSDNLibData> list = new ArrayList<>();
//        for (Element element : elements2) {
//            data = new CSDNLibData();
//            data.setName(element.select("a.title").text());
//            list.add(data);
//        }
//
//        return list;
//    }


}


