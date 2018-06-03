package main.domainAction;

import java.util.Random;

public class ActionImpl implements Action<Integer> {


    private int maxNumber;

    public ActionImpl(int maxNumber){
        this.maxNumber = maxNumber;
    }


    public int getMaxValue(){
        return maxNumber;
    }



    @Override
    public Integer getAction() {
        return new Integer(this.maxNumber);
    }



    @Override
    public String toString(){
        return "The number of the action: " +(this.maxNumber);
    }

}
