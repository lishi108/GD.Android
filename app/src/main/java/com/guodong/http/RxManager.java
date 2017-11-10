package com.guodong.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by LSQ108 on 2017/10/27.
 */

public class RxManager {
    private static volatile RxManager rxManager;
    private Map<String,CompositeDisposable> map;
    private RxManager(){
        if(map == null)
            map = new HashMap<>();
    }
    public static RxManager getInstance(){
        if(rxManager == null){
            synchronized (RxManager.class){
                rxManager = new RxManager();
            }
        }
        return rxManager;
    }
    public void add(String key, Disposable disposable){
        Set<String> keySet = map.keySet();
        if(keySet.contains(key)){
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.add(disposable);
        }else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            map.put(key,compositeDisposable);
        }
    }
    public void clear(String key){
        Set<String> keySet = map.keySet();
        if(keySet.contains(key)){
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.clear();
            map.remove(key);
        }
    }
}
