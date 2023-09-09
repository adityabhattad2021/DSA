public class AlgorithmResult<R> {
    private R result;
    private double timeInMilli;

    public AlgorithmResult(R result, double timeInMilli) {
        this.result = result;
        this.timeInMilli = timeInMilli;
    }

    public R getResult() {
        return result;
    }

    public double getTimeInMilli() {
        return timeInMilli;
    }
}
