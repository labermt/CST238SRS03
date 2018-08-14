package edu.oit.labermt.chaos;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //SeekBar PercentSeekBar;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //DataComm mCallback;
/*
    public interface DataComm{
        public void setMyPercent(float x);
        public float getMyPercent();
    }
*/
    //private SharedViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //model = ViewModelProvider(getActivity()).get(SharedViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        final SharedViewModel sharedViewModel_;
        sharedViewModel_ = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        final SeekBar PercentSeekBar = view.findViewById(R.id.seekBar);
        final TextView PercentSeekBarValue = (TextView)view.findViewById(R.id.PercentTextView);
        final EditText IterEnter = view.findViewById(R.id.editTextIter);
        final EditText VertEnter = view.findViewById(R.id.editTextVert);
        final EditText StartEnter = view.findViewById(R.id.editTextStart);
        final EditText PeriodEnter = view.findViewById(R.id.editTextPeriod);
        final ColorPickerView colorPickerView = view.findViewById(R.id.colorPickerView);

        final SharedViewModel.UIData uiData = new SharedViewModel.UIData();


        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                uiData.setColor(colorEnvelope.getColor());
                sharedViewModel_.uiDataLiveData_.postValue(uiData);
            }
        });

        PercentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChangedValue = .5f;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = (float)progress/10;
                PercentSeekBarValue.setText(String.valueOf(progressChangedValue));
                uiData.setPercent(progressChangedValue);
                sharedViewModel_.uiDataLiveData_.postValue(uiData);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        IterEnter.addTextChangedListener( new TextWatcher()
        {
            int iterationsChanged = 50;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (IterEnter.getText().toString().length() <= 0) {
                    IterEnter.setError("You must enter a number");
                } else {
                    IterEnter.setError(null);
                    String s_value = IterEnter.getText().toString();
                    uiData.setIterations(Integer.parseInt(s_value));
                    sharedViewModel_.uiDataLiveData_.postValue(uiData);

                }
            }
        });

        VertEnter.addTextChangedListener( new TextWatcher()
        {
            int vertChanged = 3;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (VertEnter.getText().toString().length() <= 0) {
                    VertEnter.setError("You must enter a number");
                } else {
                    VertEnter.setError(null);
                    String s_value = VertEnter.getText().toString();
                    if (Integer.parseInt(s_value) < 3)
                        VertEnter.setError("You must enter a number greater than 3");
                    else {
                        VertEnter.setError(null);
                        uiData.setVertices(Integer.parseInt(s_value));
                        sharedViewModel_.uiDataLiveData_.postValue(uiData);
                    }
                }
            }
        });

        StartEnter.addTextChangedListener( new TextWatcher()
        {
            //int vertChanged = 3;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StartEnter.getText().toString().length() <= 0) {
                    StartEnter.setError("You must enter a number");
                } else {
                    StartEnter.setError(null);
                    String s_value = StartEnter.getText().toString();
                    StartEnter.setError(null);
                    uiData.setStart(Integer.parseInt(s_value));
                    sharedViewModel_.uiDataLiveData_.postValue(uiData);

                }
            }
        });

        PeriodEnter.addTextChangedListener( new TextWatcher()
        {
            //int vertChanged = 3;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PeriodEnter.getText().toString().length() <= 0) {
                    PeriodEnter.setError("You must enter a number");
                } else {
                    PeriodEnter.setError(null);
                    String s_value = PeriodEnter.getText().toString();
                    PeriodEnter.setError(null);
                    uiData.setPeriod(Integer.parseInt(s_value));
                    sharedViewModel_.uiDataLiveData_.postValue(uiData);

                }
            }
        });

        sharedViewModel_.uiDataLiveData_.observe(getActivity(), new Observer<SharedViewModel.UIData>() {
            @Override
            public void onChanged(@Nullable SharedViewModel.UIData value) {
                if (value != null) {
                    // mSeekBar.setProgress(value);
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        if(context instanceof DataComm){
            mCallback = (DataComm) context;
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
