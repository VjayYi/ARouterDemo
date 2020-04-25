package my.test.arount;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

/**
 * Created by YiVjay
 * on 2020/4/25
 */
public class Arouter {
    private static Arouter arouter = new Arouter();

    private Map<String, Class<? extends Activity>> map;
    private Context context;

    private Arouter() {
        map = new HashMap<>();
    }

    public static Arouter getInstance() {
        return arouter;
    }

    public void init(Context context){
        this.context=context;
        List<String> classNames = getClassName("com.arouter.test");
        for (String  className: classNames) {
            try {
                Class<?> aClass = Class.forName(className);
                if(IRouter.class.isAssignableFrom(aClass)){
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.putActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void putActivity(String string, Class<? extends Activity> cls) {
        if (string != null && cls != null) {
            map.put(string, cls);
        }
    }

    public void jumpActivity(String key, Bundle bundle){
        Class<? extends Activity> aClass = map.get(key);
        if(aClass!=null){
            Intent intent=new Intent();
            intent.setClass(context,aClass);
            if(bundle!=null){
                intent.putExtra("value",bundle);
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
            context.startActivity(intent);
        }
    }

    private List<String> getClassName(String packageName){
        List<String> list=new ArrayList<>();
        String path;
        try {
            path=context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), 0).sourceDir;
            DexFile dexFile=new DexFile(path);
            Enumeration enumeration=dexFile.entries();
            while (enumeration.hasMoreElements()){
                String name= (String) enumeration.nextElement();
                if(name.contains(packageName)){
                    list.add(name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

}
