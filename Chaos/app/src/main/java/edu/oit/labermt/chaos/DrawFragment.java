package edu.oit.labermt.chaos;

import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DrawFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DrawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawFragment extends Fragment {

    private static final String TAG = "DrawFragment";
    private TextView textView_;
    // TODO: cleanup commented out stuff


    private OnFragmentInteractionListener mListener;


    public DrawFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrawFragment newInstance(String param1, String param2) {
        DrawFragment fragment = new DrawFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();

        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout= inflater.inflate(R.layout.fragment_draw, container, false);
        textView_ = layout.findViewById(R.id.textViewDraw);
        final Drawing drawing = layout.findViewById(R.id.draw);
        SharedViewModel sharedViewModel_;
        sharedViewModel_ = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        sharedViewModel_.uiDataLiveData_.observe(getActivity(), new Observer<SharedViewModel.UIData>() {
            @Override
            public void onChanged(@Nullable SharedViewModel.UIData value) {
                if (value != null) {
                    final int s_vertices = value.getVertices();
                    final int s_iter = value.getIterations();
                    final int s_start = value.getStart();
                    final int s_period = value.getPeriod();
                    final float s_percent = value.getPercent();
                    final int s_color = value.getColor();
                    final String text = "Iterations: " + String.valueOf(s_iter) + " Percent: " + String.valueOf(s_percent) + " Vertices: " + String.valueOf(s_vertices)
                            + " Start: " + String.valueOf(s_start) + " Period: " + String.valueOf(s_period) + " Color: " + String.valueOf(s_color);
                    textView_.setText(text);
                    drawing.vertices = s_vertices;
                    drawing.iterations = s_iter;
                    drawing.start = s_start;
                    drawing.period = s_period;
                    drawing.percent = s_percent;
                    drawing.paint_.setColor(s_color);
                }
            }
        });

        return layout;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
