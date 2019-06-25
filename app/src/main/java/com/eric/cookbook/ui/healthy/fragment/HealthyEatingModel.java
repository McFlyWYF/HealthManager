package com.eric.cookbook.ui.healthy.fragment;

import com.eric.cookbook.bean.HealthyEatingBean;
import com.eric.cookbook.network.OkHttpCallback;
import com.eric.cookbook.utils.UrlUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/15.
 */

public class HealthyEatingModel implements HealthyEatingContracts.IHealthyEatingModel {

    @Override
    public void loadHealthyEatingData(int curPage, int ysjkType, final OkHttpCallback<HealthyEatingBean> callback) {
        final String healthUrl = UrlUtil.generateUrl(ysjkType, curPage);

        Observable.create(new Observable.OnSubscribe<Document>() {
            @Override
            public void call(Subscriber<? super Document> subscriber) {
                try {
                    Document document = Jsoup.connect(healthUrl).get();
                    subscriber.onNext(document);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).flatMap(new Func1<Document, Observable<Element>>() {
            @Override
            public Observable<Element> call(Document document) {
                Element ele_health_list = document.getElementsByClass("list-conBox-ul").get(0);
                return Observable.from(ele_health_list.getElementsByTag("li"));
            }
        }).map(new Func1<Element, HealthyEatingBean>() {
            @Override
            public HealthyEatingBean call(Element element) {
                HealthyEatingBean healthyEatingBean = new HealthyEatingBean();

//                连接和图片
                Element ele_img_src = element.getElementsByTag("a").get(0);
                Element ele_img = ele_img_src.getElementsByTag("img").get(0);
                String healthImg = ele_img.attr("src");
                String healthLink = ele_img_src.attr("href");
                healthyEatingBean.setHealthImgSrc(healthImg);
                healthyEatingBean.setHealthLink(healthLink);

//                title和content
                Element ele_div_right = element.getElementsByClass("right").first();
                Element ele_title = ele_div_right.getElementsByClass("title").get(0);
                Element ele_content = ele_div_right.getElementsByClass("info").get(0);
                String healthTitle = ele_title.text();
                String healthContent = ele_content.text();
                healthyEatingBean.setHealthTitle(healthTitle);
                healthyEatingBean.setHealthContent(healthContent);
                return healthyEatingBean;
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<HealthyEatingBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HealthyEatingBean healthyEatingBean) {
                callback.onSuccess(healthyEatingBean);
            }
        });
    }
}
