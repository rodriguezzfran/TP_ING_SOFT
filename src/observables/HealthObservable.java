package observables;

import java.util.ArrayList;
import java.util.List;

public class HealthObservable implements  Observable{

    private List<Observer> observers = new ArrayList<>();
    private int Health;

    public HealthObservable(int Health){
        this.Health = Health;
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
            o.updateState(this.Health);
        }
    }

    public void setHealth(int change){
        this.Health += change;
        System.out.println("Current Health : " + this.Health);
        notifyAllObservers();
    }
}
