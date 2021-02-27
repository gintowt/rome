package com.roma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class SettingsFragment extends Fragment {


    Button profile, password, delete, logOut, notification;
    ImageView avatar;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        profile = v.findViewById(R.id.profile);
        password = v.findViewById(R.id.password);
        delete = v.findViewById(R.id.delete_account);
        logOut = v.findViewById(R.id.logOut);
        notification = v.findViewById(R.id.notification);
        avatar = v.findViewById(R.id.avatar);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                alertDialog.setTitle("Log Out.");
                alertDialog.setMessage("Do you want to log out?");
                alertDialog.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent logout = new Intent(getActivity(), Start.class);
                        startActivity(logout);
                        Toast.makeText(getActivity(), "Log out successfully.", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialogB = alertDialog.create();
                alertDialogB.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                alertDialog.setTitle("Delete.");
                alertDialog.setMessage("Do you want to delete your account?");
                alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent logout = new Intent(getActivity(), Start.class);
                        startActivity(logout);
                        Toast.makeText(getActivity(), "Account deleted.", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialogB = alertDialog.create();
                alertDialogB.show();
            }
        });
        return v;
    }
}