package com.guodong.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Description:
 * Created by Administrator on 2017/11/9.
 */

public class SharedPreferencesUtils {
    private static SharedPreferences sp;

    public static SharedPreferences getInstance() {
        if(sp == null) throw new RuntimeException("SharedPreference must be init by init(context,fileName).");
        return sp;
    }

    public static void init(Context context, String sharedName) {
        if(sp == null){
            synchronized (SharedPreferencesUtils.class){
                sp = getSharedPreferences(context,sharedName);
            }
        }
    }

    private static SharedPreferences getSharedPreferences(Context context,String sharedName){
        if (context == null || TextUtils.isEmpty(sharedName)) {
            throw new IllegalStateException("The Prefs class is not initialized correctly.");
        }
        return context.getSharedPreferences(sharedName, Context.MODE_PRIVATE);
    }

    public static<T> void put(@NonNull  String key, @NonNull T object) {
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public static <T> T get(@NonNull String key, T defaultObject) {
        Object object;
        if (defaultObject instanceof String) {
            object = sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            object =  sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            object =  sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            object =  sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            object =  sp.getLong(key, (Long) defaultObject);
        }else{
            throw new UnsupportedOperationException("Type not supported: " + defaultObject.getClass()
                    .getSimpleName());
        }
        return (T) object;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(@NonNull String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     */
    public static void clear() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(@NonNull String key) {
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAllData() {
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}

