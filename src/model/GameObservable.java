package model;

import view.GameObserver;

public interface GameObservable {
    public void gameSubscribe(GameObserver o);

    public void gameUnsubscribe(GameObserver o);

    public void notifyGameUpdate();
}
