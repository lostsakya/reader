package com.sismics.reader;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender.Method;
import org.acra.sender.HttpSender.Type;
import org.json.JSONObject;

import android.app.Application;

import com.androidquery.callback.BitmapAjaxCallback;
import com.sismics.reader.model.application.ApplicationContext;
import com.sismics.reader.util.PreferenceUtil;

/**
 * Main application.
 * 
 * @author bgamard
 */
@ReportsCrashes(formKey = "",
        httpMethod = Method.PUT,
        reportType = Type.JSON,
        formUri = "http://acralyzer.sismics.com/reader-report",
        formUriBasicAuthLogin = "reporter",
        formUriBasicAuthPassword = "jOS9ezJR",
        mode = ReportingInteractionMode.TOAST,
        forceCloseDialogAfterToast = true,
        resToastText = R.string.crash_toast_text)
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        ACRA.init(this);

        // Fetching /user/info from cache
        JSONObject json = PreferenceUtil.getCachedJson(getApplicationContext(), PreferenceUtil.PREF_CACHED_USER_INFO_JSON);
        ApplicationContext.getInstance().setUserInfo(getApplicationContext(), json);

        // TODO Changing the category of a subscription
        // TODO Renaming subscriptions
        // TODO Open the drawer when the ViewPager is at the end and all articles are loaded
        // TODO Sync the drawer's ListView when the user starts draging the drawer (not at the end)
        // TODO Admin interface if the user is admin

        super.onCreate();
    }
    
    @Override
    public void onLowMemory() {
        BitmapAjaxCallback.clearCache();
    }
}
