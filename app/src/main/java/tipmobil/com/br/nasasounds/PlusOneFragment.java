package tipmobil.com.br.nasasounds;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link PlusOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlusOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PlusOneFragment extends Fragment {

    // The URL to +1.  Must be a valid URL.
    //private final String PLUS_ONE_URL = "http://developer.android.com";
    private final String PLUS_ONE_URL = "https://market.android.com/details?id=tipmobil.com.br.nasasounds";

    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;

    private PlusOneButton mPlusOneButton;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PlusOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlusOneFragment newInstance() {
        PlusOneFragment fragment = new PlusOneFragment();
        return fragment;
    }
    public PlusOneFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);

        //Find the +1 button
        mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
        mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
