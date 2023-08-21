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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;


/**
 *
 */
public class TraceRouteFragment extends Fragment {

    private EditText editText = null;

    private TextView textResult = null;

    private TextView textComment = null;

    public TraceRouteFragment() {
        // Required empty public constructor
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


        View mainView = inflater.inflate(R.layout.fragment_trace_route_layout, container, false);


        editText = (EditText) mainView.findViewById(R.id.editDNS);
        Button btnCalculate = (Button) mainView.findViewById(R.id.btnCalculate);
        textResult = (TextView) mainView.findViewById(R.id.txtResult);
        textComment = (TextView) mainView.findViewById(R.id.textComment);
        btnCalculate.setOnClickListener(view -> {
            long nowStamp = System.currentTimeMillis();
            String ipAddress = editText.getText().toString();

            try {
                InetAddress[] inets = InetAddress.getAllByName(ipAddress);
                final StringBuilder sb = new StringBuilder();
                sb.append(String.format("Hostname:%s", ipAddress));
                for (InetAddress inet : inets) {
                    sb.append(String.format("\n%s", inet.getHostAddress()));
                }

                textResult.setText(sb.toString());

            } catch (UnknownHostException e) {
                e.printStackTrace();
                textResult.setText(String.format("Host not found!\n%s", e.getMessage()));
            }
            long endStamp = System.currentTimeMillis();
            textComment.setText(String.format(Locale.getDefault(), "Ping lasted %d miliseconds", (endStamp - nowStamp)));
        });

        return mainView;
    }
}
