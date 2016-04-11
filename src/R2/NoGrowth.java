package R2;

public class NoGrowth extends Simulation {

    @Override
    protected double simulate(int steps, char stepSize, double pricePerStock, double percentChange) {
        return pricePerStock;
    }
}
