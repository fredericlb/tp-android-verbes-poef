package fr.epsi.verbes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        String partieId = getIntent().getStringExtra("partieId");
        final TextView score = (TextView) findViewById(R.id.scoreDisplay);
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8000/partie/" + partieId + "/score";

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                score.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("Verbes/FetchScore", error.networkResponse.toString());
            }
        });

        queue.add(req);
    }

    public void onHomeButtonClicked(View v) {
        finish();
    }
}
