package main.domainAction;

import java.util.Random;

public class ActionImpl implements Action<String> {


    private String maxNumber;

    public ActionImpl(String maxNumber){
        this.maxNumber = maxNumber;
    }


    @Override
    public String getAction() {
        return maxNumber;
    }



    @Override
    public String toString(){
        return "The number of the action: " +(this.maxNumber);
    }

}
