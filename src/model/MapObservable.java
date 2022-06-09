package model;

import view.MapObserver;

public interface MapObservable {
    public void mapSubscribe(MapObserver o);

    public void mapUnsubscribe(MapObserver o);

    public void notifyMapUpdate();
}
