package my.test.assemblywithoutapt;

import android.app.Application;
import android.content.Context;

import my.test.arount.Arouter;

/**
 * Created by YiVjay
 * on 2020/4/25
 */
public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Arouter.getInstance().init(this);
    }
}
