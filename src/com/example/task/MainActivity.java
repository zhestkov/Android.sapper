package com.example.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener  {
	
	// границы допустимых значений числа строк
	private static final int ROWS_MIN = 2;
	private static final int ROWS_MAX = 15;

	
	// границы допустимых значений числа столбцов
	private static final int COLUMNS_MIN = 2;
	private static final int COLUMNS_MAX = 10;
	
	//результа проверки правильного ввода значений
	private static final int ALERT_NONE = 0; // все верно
	private static final int ALERT_COLUMNS = 1; // некорректное число столбцов
	private static final int ALERT_ROWS = 2; // некорретное число строк
	private static final int ALERT_MINES = 3; // некорректнон число мин
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Button mButton;
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button playButton = (Button) findViewById(R.id.playButton);
		playButton.setOnClickListener(this);
		
		EditText rowEditor = (EditText) findViewById(R.id.editRows);
		EditText colEditor = (EditText) findViewById(R.id.editColumns);
		EditText minesEditor = (EditText) findViewById(R.id.editMines);
		
		rowEditor.setText("10");
		colEditor.setText("8");
		minesEditor.setText("12");
		
	}
	
	private int checkInput(int cols, int rows, int mines) {
		if (cols < COLUMNS_MIN | cols > COLUMNS_MAX)
			return ALERT_COLUMNS;
		if (rows < ROWS_MIN | rows > ROWS_MAX)
			return ALERT_ROWS;
		if (mines < 0 | mines > rows * cols)
			return ALERT_MINES;
		return ALERT_NONE;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		/* EditText rowEditor = (EditText) findViewById(R.id.editRows);
		EditText colEditor = (EditText) findViewById(R.id.editColumns);
		EditText minesEditor = (EditText) findViewById(R.id.editMines);
		
		int cols = Integer.parseInt(rowEditor.getText().toString());
		int rows = Integer.parseInt(colEditor.getText().toString());
		int mines = Integer.parseInt(minesEditor.getText().toString());
		
		int alert_code = checkInput(cols, rows, mines);
		if (alert_code != ALERT_NONE) {
			showDialog(alert_code);
			return;	
		}
		*/
		Intent intent = new Intent(this, Field.class);
		//intent.putExtra(Field.EXT_COLS, cols);
		//intent.putExtra(Field.EXT_ROWS, rows);
		//intent.putExtra(Field.EXT_MINES, mines);
		
		startActivity(intent);
		finish();
		
	}

}
