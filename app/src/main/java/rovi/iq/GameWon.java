package rovi.iq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameWon extends Activity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);



    }




    //This is onclick listener for button
    //it will navigate from this activity to GeneralKnowledgeActivity
    public void PlayAgain(View view) {

        Intent intent = new Intent(GameWon.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(GameWon.this, HomeScreen.class);
        startActivity(intent);

        finish();
    }
}
