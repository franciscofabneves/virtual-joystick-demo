package io.github.controlwear.joystickdemo;

import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import io.github.controlwear.virtual.joystick.android.JoystickView;

/**
 * Created by germano on 18/10/17.
 */

public class VirtualJoystick extends Fragment {
    public static final String TITLE = "Virtual Joystick";

    public static VirtualJoystick newInstance() {

        return new VirtualJoystick();
    }

    EditText edit_text;
    Button rtn;
    Switch switchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.virtual_joystick, container, false);

        switchButton = (Switch) view.findViewById(R.id.simpleSwitch);
        rtn = (Button) view.findViewById(R.id.btnAddTitle);
        edit_text = (EditText)view.findViewById(R.id.IpAddress);

        final JoystickView joystickLeft = (JoystickView) view.findViewById(R.id.joystickView_left);
        final JoystickView joystickRight = (JoystickView) view.findViewById(R.id.joystickView_right);

        final String[] IP = new String[1];
        rtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        IP[0] = edit_text.getText().toString();
                        //Toast msg = Toast.makeText(getBaseContext(), IP[0],Toast.LENGTH_LONG).show();
                    }
                });

        switchButton.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
                    @Override
                    public void onMove(int angle, int strength) {
                        if (isChecked) {
                            UdpClientThread.sendMessage("LA:" + String.format("%03d", angle) +
                                    " LS:" + String.format("%03d", strength) + " ;", IP[0]);

                        }
                    }
                });

                joystickRight.setOnMoveListener(new JoystickView.OnMoveListener() {
                    @Override
                    public void onMove(int angle, int strength) {
                        if (isChecked) {
                            UdpClientThread.sendMessage("RA:" + String.format("%03d", angle) +
                                    " RS:" + String.format("%03d", strength) + " ;", IP[0]);

                        }
                    }
                });
            }
        });
        return view;

    }
}
