package abpl.billingapp;

import android.app.Application;
import android.content.Context;

public class AppContext {
    private static AppContext INSTANCE = null;
    private Application mApplication;
    private String mCVV;
    private String APENABLE;
    private Context mHomeContext;

    public static AppContext getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new AppContext();
        }
        return INSTANCE;
    }

    public void setContext(Application application) {
        this.mApplication = application;
    }

    public Application getContext() {
        return this.mApplication;
    }

    public Context getHomeContext() {
        return mHomeContext;
    }

    public void setHomeContext(Context mHomeContext) {
        this.mHomeContext = mHomeContext;
    }

    public void setCVV(String cvv) {
        this.mCVV = cvv;
    }

    public String getCVV() {
        return this.mCVV;
    }

    public void setAPENABLE(String ap) {
        this.APENABLE = ap;
    }

    public String getAPENABLE() {
        return this.APENABLE;
    }

}
