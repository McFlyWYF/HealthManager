package com.eric.cookbook.ui.healthdetail;

import android.util.Log;

import com.eric.cookbook.network.Networks;
import com.eric.cookbook.network.OkHttpCallback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;



public class HealthDetailModel implements HealthDetailContract.IHealthDetailModel{

    @Override
    public void loadDetailData(String health_id, final OkHttpCallback<String> callback) {
        Networks.getHealthApi()
                .getHealthContent(health_id)
                .map(new Func1<String, Element>() {
                    @Override
                    public Element call(String s) {
                        Document document = Jsoup.parse(s);
                        Element content_ele = document.getElementsByClass("detail-art-main").get(0);
                        content_ele.select(".art-title").remove();
                        content_ele.select(".add-tags").remove();
                        Elements subTitles = content_ele.getElementsByClass("item-nub");
                        for (Element subTitle : subTitles){
                            subTitle.remove();
                        }

                        Elements introTitles = content_ele.getElementsByClass("introTit");
                        for(int i = 0; i < introTitles.size(); i++){
                            introTitles.get(i).prependText((i+1) + ".");
                        }

                        Elements images = content_ele.getElementsByTag("img");

                        for(Element image : images){
                            if(image.attr("src") != null){
                                image.attr("style", "width:100%;height:auto;display: block");
                            }
                        }
                        return content_ele;
                    }
                })
                .map(new Func1<Element, String>() {
                    @Override
                    public String call(Element element) {
                        return element.toString();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("HTML",  "HTML ==== " + s);
                        callback.onSuccess(s);
                    }
                });
    }
}
