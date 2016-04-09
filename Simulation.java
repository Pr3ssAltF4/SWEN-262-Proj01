package src.model.sim;


import src.model.Equity;

import java.util.ArrayList;

/*
Need to use Stratage pattern
 */
public abstract class Simulation {

    /*
    To be implemented in the class extentions
     */
    protected abstract double simulate(int steps, char stepSize, double pricePerStock, double percentChange);

    public ArrayList<Equity> simulateMarket(ArrayList<Equity> equities, int steps, char stepSize, double percentChange) {
        ArrayList<Equity> simulated = equities;
        for (Equity e : equities) {
            Equity temp = e;
            temp.setPricePerStock(simulate(steps, stepSize, e.getPricePerStock(), percentChange));
            simulated.add(temp);
        }
        return simulated;
    }
}
