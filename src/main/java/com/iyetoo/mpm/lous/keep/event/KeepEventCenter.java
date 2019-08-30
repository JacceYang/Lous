package com.iyetoo.mpm.lous.keep.event;

import java.util.Observable;
import java.util.Observer;

/**
 * {@link KeepEventCenter} is a event publish center for {@link com.iyetoo.mpm.lous.keep.annotation.EnableKeep}.
 * Also you can register a component in this Envent center by use {@link KeepEventCenter#addListener(Observer)}
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 1:48 PM 2019/8/23
 **/
public class KeepEventCenter extends Observable {

    private static KeepEventCenter observable = new KeepEventCenter();

    /**
     * mark the observable have changed so in the next event loop the the observers can be notified.
     */
    public static void changed() {
        observable.setChanged();
    }

    /**
     *  publish a event at your application environment.
     * @param object
     */
    public static void publishEvent(Object object) {
        observable.notifyObservers(object);
    }

    /**
     * register a observer to Keep Event Center
     * @param observer
     */
    public static void addListener(Observer observer) {
        observable.addObserver(observer);
    }

}
