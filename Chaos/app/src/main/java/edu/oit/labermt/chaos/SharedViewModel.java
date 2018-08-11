package edu.oit.labermt.chaos;

import android.arch.lifecycle.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    public static class UIData {
        private int vertices = 3;
        private float percent = .5f;
        private int iterations = 50;
        private int start = 2;
        private int period = 1;


        public int getVertices()
        {
            return vertices;
        }
        public void setVertices(final int value)
        {
            vertices = value;
        }
        public float getPercent()
        {
            return percent;
        }
        public void setPercent(final float value)
        {
            percent = value;
        }
        public int getIterations()
        {
            return iterations;
        }
        public void setIterations(final int value)
        {
            iterations = value;
        }

        public int getStart()
        {
            return start;
        }
        public void setStart(final int value)
        {
            start = value;
        }

        public int getPeriod()
        {
            return period;
        }
        public void setPeriod(final int value)
        {
            period = value;
        }
    }
    public MutableLiveData<UIData> uiDataLiveData_ = new MutableLiveData<>();
}