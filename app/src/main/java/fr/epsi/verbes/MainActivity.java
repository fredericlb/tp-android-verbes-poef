package fr.epsi.verbes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

  String playerId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView welcomeLine = (TextView) findViewById(R.id.welcomeLine);
    welcomeLine.setVisibility(View.INVISIBLE);
    Button btn = (Button) findViewById(R.id.btnDisconnect);
    btn.setEnabled(false);

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel("test", "Test",
              NotificationManager.IMPORTANCE_DEFAULT);
      getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }

    SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
    if (sp.contains("joueurId")) {
      String joueurId = sp.getString("joueurId", null);
      String nom = sp.getString("nom", null);
      String prenom = sp.getString("prenom", null);
      this.updateUserInfo(prenom, nom);
      this.playerId = joueurId;
      btn.setEnabled(true);
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    Intent intent = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test");
    builder
            .setContentTitle("Reviens !")
            .setContentText("Reviens dans l'application")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent);

    NotificationManagerCompat manager = NotificationManagerCompat.from(this);
    manager.notify(100, builder.build());

  }


  public void onDisconnectPressed(View v) {
    SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
    sp.edit()
            .remove("joueurId")
            .remove("prenom")
            .remove("nom")
            .apply();
    TextView welcomeLine = (TextView) findViewById(R.id.welcomeLine);
    welcomeLine.setVisibility(View.INVISIBLE);

    Button btn = (Button) findViewById(R.id.btnDisconnect);
    btn.setEnabled(false);
  }

  public void onInscriptionBtnClicked(View v) {
    Intent intent = new Intent(this, InscriptionActivity.class);
    startActivityForResult(intent, 1);
  }

  public void onConnexionBtnClicked(View v) {
    Intent intent = new Intent(this, ConnexionActivity.class);
    startActivityForResult(intent, 2);
  }

  public void onRevisionBtnClicked(View v) {
    Intent intent = new Intent(this, Revisions.class);
    startActivity(intent);
  }

  public void onPartieBtnClicked(View v) {

    String url = "http://10.0.2.2:8000/partie/" + this.playerId;
    final Intent intent = new Intent(this, QuestionActivity.class);
    StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        Log.w("verbsLog/partie", response);
        intent.putExtra("partieId", response);

        startActivity(intent);
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.w("verbsLog/partie", error.networkResponse.toString());
      }
    });

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(req);

  }

  protected void updateUserInfo(String prenom, String nom) {
    TextView welcomeLine = (TextView) findViewById(R.id.welcomeLine);
    welcomeLine.setVisibility(View.VISIBLE);
    welcomeLine.setText("Bienvenue " + prenom + " " + nom);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1 || requestCode == 2) {
      String nom = data.getStringExtra("nom");
      String prenom = data.getStringExtra("prenom");
      String joueurId = data.getStringExtra("joueurId");
      this.playerId = joueurId;
      Log.w("verbesLog", nom);
      Log.w("verbesLog", prenom);
      Log.w("verbesLog/joueurId", joueurId);
      this.updateUserInfo(prenom, nom);
      Button btn = (Button) findViewById(R.id.btnDisconnect);
      btn.setEnabled(true);
    }
  }
}
