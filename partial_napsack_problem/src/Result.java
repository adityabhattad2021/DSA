import java.util.ArrayList;

public class Result {
    private ArrayList<double[]> selectedArray;
    private double fraction;

    public Result(ArrayList<double[]> selectedArry,double fraction){
        this.selectedArray=selectedArry;
        this.fraction=fraction;
    }

    public ArrayList<double[]> getSelectedArray() {
        return selectedArray;
    }

    public double getFraction(){
        return fraction;
    }
}
