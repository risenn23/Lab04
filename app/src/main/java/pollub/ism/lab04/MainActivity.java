package pollub.ism.lab04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    char[][] gameState = new char[3][3];
    char[] bundleState = new char[9];
    boolean player1 = true;
    Character player1Symbol = 'X';
    Character player2Symbol = 'O';

    private Button button_0_0,button_0_1,button_0_2,button_1_0,button_1_1,button_1_2,button_2_0,button_2_1,button_2_2;
    private Dictionary<String,Boolean> buttonStatuses = new Hashtable<String, Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // wypelniam tablice pustymi znakami
//        Arrays.stream(gameState).forEach(x -> Arrays.fill(x, ' '));
        button_0_0 = findViewById(R.id.button_0_0);
        button_0_1 = findViewById(R.id.button_0_1);
        button_0_2 = findViewById(R.id.button_0_2);
        button_1_0 = findViewById(R.id.button_1_0);
        button_1_1 = findViewById(R.id.button_1_1);
        button_1_2 = findViewById(R.id.button_1_2);
        button_2_0 = findViewById(R.id.button_2_0);
        button_2_1 = findViewById(R.id.button_2_1);
        button_2_2 = findViewById(R.id.button_2_2);


        buttonStatuses.put("button_0_0",true);
        buttonStatuses.put("button_0_1",true);
        buttonStatuses.put("button_0_2",true);
        buttonStatuses.put("button_1_0",true);
        buttonStatuses.put("button_1_1",true);
        buttonStatuses.put("button_1_2",true);
        buttonStatuses.put("button_2_0",true);
        buttonStatuses.put("button_2_1",true);
        buttonStatuses.put("button_2_2",true);



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putChar("button_0_0",gameState[0][0]);
        outState.putChar("button_0_1",gameState[0][1]);
        outState.putChar("button_0_2",gameState[0][2]);
        outState.putChar("button_1_0",gameState[1][0]);
        outState.putChar("button_1_1",gameState[1][1]);
        outState.putChar("button_1_2",gameState[1][2]);
        outState.putChar("button_2_0",gameState[2][0]);
        outState.putChar("button_2_1",gameState[2][1]);
        outState.putChar("button_2_2",gameState[2][2]);

        outState.putBoolean("button_0_0_status",button_0_0.isEnabled());
        outState.putBoolean("button_0_1_status",button_0_1.isEnabled());
        outState.putBoolean("button_0_2_status",button_0_2.isEnabled());
        outState.putBoolean("button_1_0_status",button_1_0.isEnabled());
        outState.putBoolean("button_1_1_status",button_1_1.isEnabled());
        outState.putBoolean("button_1_2_status",button_1_2.isEnabled());
        outState.putBoolean("button_2_0_status",button_2_0.isEnabled());
        outState.putBoolean("button_2_1_status",button_2_1.isEnabled());
        outState.putBoolean("button_2_2_status",button_2_2.isEnabled());

        outState.putBoolean("player1",player1);





    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        player1 = savedInstanceState.getBoolean("player1");

        button_0_0.setText(String.valueOf(savedInstanceState.getChar("button_0_0")));
        button_0_1.setText(String.valueOf(savedInstanceState.getChar("button_0_1")));
        button_0_2.setText(String.valueOf(savedInstanceState.getChar("button_0_2")));
        button_1_0.setText(String.valueOf(savedInstanceState.getChar("button_1_0")));
        button_1_1.setText(String.valueOf(savedInstanceState.getChar("button_1_1")));
        button_1_2.setText(String.valueOf(savedInstanceState.getChar("button_1_2")));
        button_2_0.setText(String.valueOf(savedInstanceState.getChar("button_2_0")));
        button_2_1.setText(String.valueOf(savedInstanceState.getChar("button_2_1")));
        button_2_2.setText(String.valueOf(savedInstanceState.getChar("button_2_2")));

        gameState[0][0] = savedInstanceState.getChar("button_0_0");
        gameState[0][1] = savedInstanceState.getChar("button_0_1");
        gameState[0][2] = savedInstanceState.getChar("button_0_2");
        gameState[1][0] = savedInstanceState.getChar("button_1_0");
        gameState[1][1] = savedInstanceState.getChar("button_1_1");
        gameState[1][2] = savedInstanceState.getChar("button_1_2");
        gameState[2][0] = savedInstanceState.getChar("button_2_0");
        gameState[2][1] = savedInstanceState.getChar("button_2_1");
        gameState[2][2] = savedInstanceState.getChar("button_2_2");


        button_0_0.setEnabled(savedInstanceState.getBoolean("button_0_0_status"));
        button_0_1.setEnabled(savedInstanceState.getBoolean("button_0_1_status"));
        button_0_2.setEnabled(savedInstanceState.getBoolean("button_0_2_status"));
        button_1_0.setEnabled(savedInstanceState.getBoolean("button_1_0_status"));
        button_1_1.setEnabled(savedInstanceState.getBoolean("button_1_1_status"));
        button_1_2.setEnabled(savedInstanceState.getBoolean("button_1_2_status"));
        button_2_0.setEnabled(savedInstanceState.getBoolean("button_2_0_status"));
        button_2_1.setEnabled(savedInstanceState.getBoolean("button_2_1_status"));
        button_2_2.setEnabled(savedInstanceState.getBoolean("button_2_2_status"));



    }

    public void btnClick(View view) {
        int clickedRow, clickedColumn;
        String clickedBtn = view.getResources().getResourceEntryName(view.getId());
        String[] clickedPosition = clickedBtn.split("_");
        clickedRow = Integer.parseInt(clickedPosition[1]);
        clickedColumn = Integer.parseInt(clickedPosition[2]);

        if (player1) {
            gameState[clickedRow][clickedColumn] = player1Symbol;
            player1 = false;
        } else {
            gameState[clickedRow][clickedColumn] = player2Symbol;
            player1 = true;
        }

        Button button = (Button) findViewById(view.getId());
        button.setText(Character.toString(gameState[clickedRow][clickedColumn]));
        button.setEnabled(false);
        System.out.println(clickedBtn);
        buttonStatuses.put(clickedBtn,false);
        System.out.println(Arrays.deepToString(gameState));

        CheckIfWin();
    }

    private void CheckIfWin() {
        boolean win = false;
        //sprawdzenie kolumn i wierszy: pionowo poziomo
        for (int i = 0; i < 3; i++) {
            if (gameState[i][0] == 'X' && gameState[i][1] == 'X' && gameState[i][2] == 'X') {
                System.out.println("win x");
                win = true;
            } else if (gameState[i][0] == 'O' && gameState[i][1] == 'O' && gameState[i][2] == 'O') {
                System.out.println("win O");
                win = true;
            } else if (gameState[0][i] == 'X' && gameState[1][i] == 'X' && gameState[2][i] == 'X') {
                System.out.println("win X");
                win = true;
            } else if (gameState[0][i] == 'O' && gameState[1][i] == 'O' && gameState[2][i] == 'O') {
                System.out.println("win O");
                win = true;
            }
        }

        // sprawdzenie diagonali
        if (gameState[0][0] == 'X' && gameState[1][1] == 'X' && gameState[2][2] == 'X') {
            System.out.println("win X");
            win = true;
        } else if (gameState[0][0] == 'O' && gameState[1][1] == 'O' && gameState[2][2] == 'O') {
            System.out.println("win O");
            win = true;
        } else if (gameState[2][0] == 'X' && gameState[1][1] == 'X' && gameState[0][2] == 'X') {
            System.out.println("win X");
            win = true;
        } else if (gameState[2][0] == 'O' && gameState[1][1] == 'O' && gameState[0][2] == 'O') {
            System.out.println("win O");
            win = true;
        }

        if(win == true) {
            button_0_0.setEnabled(false);
            button_0_1.setEnabled(false);
            button_0_2.setEnabled(false);
            button_1_0.setEnabled(false);
            button_1_1.setEnabled(false);
            button_1_2.setEnabled(false);
            button_2_0.setEnabled(false);
            button_2_1.setEnabled(false);
            button_2_2.setEnabled(false);
            Toast.makeText(this,"wygrana", Toast.LENGTH_LONG).show();
        }


    }
}