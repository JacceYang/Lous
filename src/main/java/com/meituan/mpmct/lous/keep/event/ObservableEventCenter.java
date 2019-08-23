package com.meituan.mpmct.lous.keep.event;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author:Yangchao16
 * @Description:
 * @Data:Initialized in 1:48 PM 2019/8/23
 **/
public class ObservableEventCenter extends Observable {

    private static ObservableEventCenter observable = new ObservableEventCenter();

    public static void changed() {
        observable.setChanged();
    }

    public static void publishEvent(Object object) {
        observable.notifyObservers(object);
    }

    public static void addListener(Observer observer) {
        observable.addObserver(observer);
    }

}
