package src.model.sim;

public class Bull extends Simulation {

    @Override
    protected double simulate(int steps, char stepSize, double pricePerStock, double percentChange) {
        int period = (stepSize == 'D') ? 365 : (stepSize == 'M') ? 12 : 1;
        percentChange = Math.abs(percentChange);
        return Math.round((pricePerStock + (pricePerStock * (percentChange / 100.0) * (1.0 / period))) * 100.0) / 100.0;
    }
}
