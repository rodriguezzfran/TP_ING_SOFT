package observables;

import java.util.ArrayList;
import java.util.List;

public class HealthObservable implements  Observable{

    private List<Observer> observers = new ArrayList<>();
    private int health;

    public HealthObservable(int health){
        this.health = health;
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if(i>=0)
            this.observers.remove(o);
    }

    @Override
    public void notifyAllObservers() {
        for(Observer o : observers){
            o.updateState(this.health);
        }
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int change){
        this.health = change;
        notifyAllObservers();
    }
    public void changeHealth(int change){
        this.health += change;
        notifyAllObservers();
    }


}
