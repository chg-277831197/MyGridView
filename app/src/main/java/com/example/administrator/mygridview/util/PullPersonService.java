package com.example.administrator.mygridview.util;

import android.util.Xml;

import com.example.administrator.mygridview.bean.Book;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PullPersonService {


    /**
     * ------------------------使用PULL解析XML-----------------------
     * @param inStream
     * @return
     * @throws Exception
     */
    public static List<Book> getData(InputStream inStream)
            throws Exception {
        Book data = null;
        List<Book> datas = null;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(inStream, "GBK");
        int event = pullParser.getEventType();// 觸發第一個事件
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    datas = new ArrayList<Book>();
                    break;
                case XmlPullParser.START_TAG:
                    if ("data".equals(pullParser.getName())) {
                        data = new Book();
                    }
                    if (data != null) {
                        if ("title".equals(pullParser.getName())) {
                            data.setTitle(pullParser.nextText());
                        }
                        if ("code".equals(pullParser.getName())) {
                            data.setCode(Integer.parseInt(pullParser.nextText()));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("data".equals(pullParser.getName())) {
                        datas.add(data);
                        data = null;
                    }
                    break;
            }
            event = pullParser.next();
        }
        return datas;
    }
}