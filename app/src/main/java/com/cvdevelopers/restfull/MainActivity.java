package com.cvdevelopers.restfull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cvdevelopers.restfull.api.RestManager;
import com.cvdevelopers.restfull.models.Response;
import com.cvdevelopers.restfull.services.PingService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

//annotation to make it generate the respective annotated class.
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    //Annotation that finds the view with that id and attaches it to the label
    @ViewById(R.id.hello_world_text)
    TextView helloWorldLabel;

    //annotation that injects the instance of pingservice,
    @Bean
    PingService pingService;

    //annotation that is executed after all the views are accessible
    @AfterViews
    protected void afterViews() {
        pingTheServer();
    }

    //annotation to run this method in the background
    @Background
    protected void pingTheServer() {
        Response response = pingService.pingServer();
        Log.i(TAG, response.getResponse());
        updateLabelWithResponse(response.getResponse());
    }


    //annotation to run this method in the main thread
    @UiThread
    protected void updateLabelWithResponse (String response){
        helloWorldLabel.setText(response);
    }

}
