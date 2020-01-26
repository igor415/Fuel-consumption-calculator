package com.varivoda.igor.kalkulatorpotronjegoriva;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinner;
    String[] paths = {"€", "kn", "km", "rsd"};
    Double broj_litara, kilometri, cijena_litre, rezultat;
    private EditText broj_litaraE, kilometriE, cijena_litreE;
    private TextView naslovrez;
    private Button izracunaj;
    private AdView mAdView;
    private LinearLayout container;
    private PublisherAdView mPublisherAdView;


    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    public void popup(Double reza) {
        /*****/
        izracunaj.setEnabled(true);
        /******/
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.novi_dialog, null);

        // Set the custom layout as alert dialog view
        String x = String.format("%.2f", reza);
        builder.setView(dialogView);
        naslovrez = dialogView.findViewById(R.id.naslovrez);
        naslovrez.setText("Potrebno vam je: " + x + " litara goriva.\n");
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void popup2(Double reza, Double pare) {
        /*****/
        izracunaj.setEnabled(true);
        /******/
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.novi_dialog, null);

        // Set the custom layout as alert dialog view
        String x = String.format("%.2f", reza);
        String y = String.format("%.2f", pare);
        builder.setView(dialogView);
        naslovrez = dialogView.findViewById(R.id.naslovrez);
        naslovrez.setText("Potrebno vam je : " + x + " litara goriva.\nPut će vas koštati : " + y + " " + spinner.getSelectedItem().toString());
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        spinner = view.findViewById(R.id.spinner1);
        broj_litaraE = view.findViewById(R.id.editText11);
        kilometriE = view.findViewById(R.id.editText22);
        cijena_litreE = view.findViewById(R.id.editText33);
        izracunaj = view.findViewById(R.id.button);
        izracunaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*****/
                izracunaj.setEnabled(false);
                /******/
                if (broj_litaraE.getText().toString().length() > 0 && kilometriE.getText().toString().length() > 0) {
                    if (cijena_litreE.getText().toString().length() > 0) {
                        if (spinner.getSelectedItem().toString().equals("€")) {
                            String x = broj_litaraE.getText().toString();
                            broj_litara = Double.parseDouble(x);
                            kilometri = Double.parseDouble(kilometriE.getText().toString());
                            rezultat = kilometri / 100 * broj_litara;
                            cijena_litre = Double.parseDouble(cijena_litreE.getText().toString());
                            final Double xx = rezultat * cijena_litre;
                            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
                            izracunaj.startAnimation(animation);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    izracunaj.getAnimation().cancel();
                                    izracunaj.clearAnimation();
                                    popup2(rezultat, xx);
                                }
                            }, 500);


                        }
                        if (spinner.getSelectedItem().toString().equals("kn")) {
                            String x = broj_litaraE.getText().toString();
                            broj_litara = Double.parseDouble(x);
                            kilometri = Double.parseDouble(kilometriE.getText().toString());
                            rezultat = kilometri / 100 * broj_litara;
                            cijena_litre = Double.parseDouble(cijena_litreE.getText().toString());
                            final Double xx = rezultat * cijena_litre;
                            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
                            izracunaj.startAnimation(animation);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    izracunaj.getAnimation().cancel();
                                    izracunaj.clearAnimation();
                                    popup2(rezultat, xx);
                                }
                            }, 500);


                        }
                        if (spinner.getSelectedItem().toString().equals("km")) {
                            String x = broj_litaraE.getText().toString();
                            broj_litara = Double.parseDouble(x);
                            kilometri = Double.parseDouble(kilometriE.getText().toString());
                            rezultat = kilometri / 100 * broj_litara;
                            cijena_litre = Double.parseDouble(cijena_litreE.getText().toString());
                            final Double xx = rezultat * cijena_litre;
                            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
                            izracunaj.startAnimation(animation);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    izracunaj.getAnimation().cancel();
                                    izracunaj.clearAnimation();
                                    popup2(rezultat, xx);
                                }
                            }, 500);


                        }
                        if (spinner.getSelectedItem().toString().equals("rsd")) {
                            String x = broj_litaraE.getText().toString();
                            broj_litara = Double.parseDouble(x);
                            kilometri = Double.parseDouble(kilometriE.getText().toString());
                            rezultat = kilometri / 100 * broj_litara;
                            cijena_litre = Double.parseDouble(cijena_litreE.getText().toString());
                            final Double xx = rezultat * cijena_litre;
                            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
                            izracunaj.startAnimation(animation);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    izracunaj.getAnimation().cancel();
                                    izracunaj.clearAnimation();
                                    popup2(rezultat, xx);
                                }
                            }, 500);


                        }

                    } else {
                        String x = broj_litaraE.getText().toString();
                        broj_litara = Double.parseDouble(x);
                        kilometri = Double.parseDouble(kilometriE.getText().toString());
                        rezultat = kilometri / 100 * broj_litara;
                        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
                        izracunaj.startAnimation(animation);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                izracunaj.getAnimation().cancel();
                                izracunaj.clearAnimation();
                                popup(rezultat);
                            }
                        }, 500);

                    }
                } else {
                    /*****/
                    izracunaj.setEnabled(true);
                    /******/
                    String s1 = broj_litaraE.getText().toString();
                    String s2 = kilometriE.getText().toString();
                    if (TextUtils.isEmpty(s1) && TextUtils.isEmpty(s2)) {
                        broj_litaraE.setError(getResources().getString(R.string.error_msg));
                        kilometriE.setError(getResources().getString(R.string.error_msg));
                    } else if (TextUtils.isEmpty(s2)) {
                        kilometriE.setError(getResources().getString(R.string.error_msg));
                    } else if (TextUtils.isEmpty(s1)) {
                        broj_litaraE.setError(getResources().getString(R.string.error_msg));
                    }
                }


            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.custom_spin, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.my_spinner_text_color), PorterDuff.Mode.SRC_ATOP);


        mPublisherAdView = view.findViewById(R.id.publisherAdView);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        mPublisherAdView.loadAd(adRequest);

        return view;
    }


}
