package edu.oit.labermt.chaos;

import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private float Percent;

    public float getPercent(){
        return Percent;
    }
}
