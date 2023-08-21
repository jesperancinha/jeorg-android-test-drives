package com.joai.ping2;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.joai.ping2.util.SystemUiHiderBase;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 */
public class PingFragment extends Fragment {

    private EditText editText = null;

    private TextView textResult = null;

    private TextView textComment = null;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public PingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PingFragment newInstance(String param1, String param2) {
        PingFragment fragment = new PingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View mainView = inflater.inflate(R.layout.fragment_ping_layout, container, false);
        editText = (EditText) mainView.findViewById(R.id.editDNS);
        Button btnCalculate = (Button) mainView.findViewById(R.id.btnCalculate);
        TextView textView = (TextView) mainView.findViewById(R.id.textView);
        textResult = (TextView) mainView.findViewById(R.id.txtResult);
        textComment = (TextView) mainView.findViewById(R.id.textComment);
        btnCalculate.setOnClickListener(view -> {


            long nowStamp = System.currentTimeMillis();
            String ipAddress = editText.getText().toString();

            try {
                InetAddress inet = InetAddress.getByName(ipAddress);
                textResult.setText(String.format("Adress:%s\nHostname:%s", inet.getHostAddress(), inet.getHostName()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
                textResult.setText(String.format("Host not found!\n%s", e.getMessage()));
            }
            long endStamp = System.currentTimeMillis();
            textComment.setText(String.format("Ping lasted %d miliseconds", (endStamp - nowStamp)));
        });

        // Inflate the layout for this fragment
        return mainView;
    }
}
