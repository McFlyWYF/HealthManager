package com.eric.cookbook.ui.mine;

import android.app.Activity;
import android.content.Intent;
import android.widget.FrameLayout;

import com.eric.cookbook.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;


public class AboutHelper{

    private Activity activity;
    private static int theme = R.style.AppThemeLight;

    private AboutHelper(Activity activity) {
        this.activity = activity;
    }

    public static AboutHelper with(Activity activity){
        return new AboutHelper(activity);
    }

    public AboutHelper init(){
        activity.setTheme(theme);
        return this;
    }

    public void loadAbout() {
        final FrameLayout flHolder = (FrameLayout) activity.findViewById(R.id.about);
        AboutBuilder builder = AboutBuilder.with(activity)
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .setPhoto(R.drawable.head)
                .setCover(R.mipmap.profile_cover)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("小飞侠")
                .setSubTitle("Mobile Developer")
                .setLinksColumnsCount(4)
                .setBrief("God helps those who help themselves")
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("wangyufei1006")
                .addBitbucketLink("user")
                .addFacebookLink("user")
                .addTwitterLink("user")
                .addInstagramLink("user")
                .addGooglePlusLink("user")
                .addYoutubeChannelLink("user")
                .addDribbbleLink("user")
                .addEmailLink("hcqeric@126.com")
                .addSkypeLink("user")
                .addGoogleLink("user")
                .addAndroidLink("user")
                .addWebsiteLink("site")
                .addFiveStarsAction()
                .addMoreFromMeAction("user")
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .addUpdateAction()
                .setActionsColumnsCount(2)
                .addFeedbackAction("hcqeric@126.com")
                .addIntroduceAction((Intent) null)
                .addHelpAction((Intent) null)
                .addChangeLogAction((Intent) null)
                .addRemoveAdsAction((Intent) null)
                .addDonateAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);
        AboutView view = builder.build();
        flHolder.addView(view);
    }

}
