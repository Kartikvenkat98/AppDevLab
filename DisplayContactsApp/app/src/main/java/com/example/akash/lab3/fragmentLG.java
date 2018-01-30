package com.example.akash.lab3;

import android.content.Context;
import android.support.annotation.Nullable;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;


import static android.provider.ContactsContract.Contacts.DISPLAY_NAME;
import static android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragmentLG.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragmentLG#newInstance} factory method to
 * create an instance of this fragment.
 *
 */


public class fragmentLG extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public fragmentLG() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentLG.
     */
    // TODO: Rename and change types and number of parameters

    static String start=null;
    static String end=null;
    static TextView t1=null;
    public static fragmentLG newInstance(String param1, String param2) {
        fragmentLG fragment = new fragmentLG();
        Bundle args = new Bundle();
        start=param1;
        end=param2;
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        Uri CONTACT_URI= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String SELECTION=null;
        if(end.charAt(0)!='Z') {
             SELECTION = "DISPLAY_NAME >= '" + start + "' and DISPLAY_NAME<= '" + end + "'";
        }
        else{
            SELECTION = "DISPLAY_NAME >= '" + start+"'";
        }
        CursorLoader cl=new CursorLoader(getActivity(),CONTACT_URI,null,SELECTION,null,"DISPLAY_NAME asc");
        Log.d("ADf",SELECTION);
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        StringBuilder sb=new StringBuilder();
        data.moveToFirst();
        while(!data.isAfterLast())
        {

                sb.append("\n" + data.getString(data.getColumnIndex(DISPLAY_NAME)));
                sb.append(":" + data.getString(data.getColumnIndex(NUMBER)));
                Log.d("hi:", "sb.toString");


            data.moveToNext();

        }
        t1.setText(sb.toString());
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Log.d("hi:","onCreateFRAG");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         getLoaderManager().initLoader(0,null,this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v1= inflater.inflate(R.layout.fragment_fragment_lg, container, false);
        t1 =v1.findViewById(R.id.cc);
        //t1.setText(start);



        return v1;


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


    @Override
    public void onStop() {
        super.onStop();

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
