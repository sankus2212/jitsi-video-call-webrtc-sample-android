package com.jitsi_videocalling_master;

import android.os.Bundle;
import android.widget.Toast;

import com.facebook.react.bridge.UiThreadUtil;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetView;
import org.jitsi.meet.sdk.JitsiMeetViewListener;
import org.jitsi.meet.sdk.invite.AddPeopleController;
import org.jitsi.meet.sdk.invite.InviteController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MainActivity extends JitsiMeetActivity implements JitsiMeetViewListener{


    @Override
    protected JitsiMeetView initializeView() {
        JitsiMeetView view = super.initializeView();
        if (BuildConfig.DEBUG && view != null) {
            view.setListener(this);
            // inviteController
            final InviteController inviteController = view.getInviteController();
            inviteController.setListener(this::onInviteControllerBeginAddPeople);
            inviteController.setAddPeopleEnabled(false);
            inviteController.setDialOutEnabled(inviteController.isAddPeopleEnabled());
        }
        try {
            URL url = new URL("meet.jit.si/test_room");//change your room name here
            view.setDefaultURL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setWelcomePageEnabled(true);

        super.onCreate(savedInstanceState);

    }


    private void onInviteControllerBeginAddPeople(
        AddPeopleController addPeopleController) {
        UiThreadUtil.assertOnUiThread();
        addPeopleController.endAddPeople();
    }


    @Override
    public void onConferenceFailed(Map<String, Object> map) {

    }

    @Override
    public void onConferenceJoined(Map<String, Object> map) {

    }

    @Override
    public void onConferenceLeft(Map<String, Object> map) {
        Toast.makeText(this, "Call Ended", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onConferenceWillJoin(Map<String, Object> map) {

    }

    @Override
    public void onConferenceWillLeave(Map<String, Object> map) {

    }

    @Override
    public void onLoadConfigError(Map<String, Object> map) {

    }
}
