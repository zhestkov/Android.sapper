package com.example.task;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.task.Mechanics.Cell;

class FieldAdapter extends BaseAdapter {
	private Context mContext;
	Mechanics FieldModel;
	
	//конструктор
	
	public FieldAdapter(Context context, int cols, int rows, int mines) {
		mContext = context;
		FieldModel = new Mechanics(rows, cols, mines);	
	}
	
	// количество элементов в GridView
	public int getCount() {
		return FieldModel.getCount();
	}
	
	// объект, хранящийся под номером position
	// GridView работает как одномерный массив WTF?!?!
	
	public Object getItem(int position) {
		return FieldModel.getCell(position);
	}
	// ID элемента на месте position
	public long getItemId(int position) {
		return position;
	}
	
	// прорисовка картинки для данного position
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView view;
		
		if (convertView == null) {
			view = new ImageView(mContext);
			view.setLayoutParams(new GridView.LayoutParams(40, 40));
			view.setAdjustViewBounds(false);
			//типа масштабирования
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			// отступы
			view.setPadding(0, 0, 0, 0);
		}
		else
			view = (ImageView) convertView;
		
		Cell k = (Cell) getItem(position);
		switch (k.CellState) {
		case Mechanics.CELL_CLOSE:
			view.setImageResource(R.drawable.close);
			break;
		case Mechanics.CELL_OPEN:
			switch(k.MineState) {
			case Mechanics.MINE_YES: // мина в клетке
				view.setImageResource(R.drawable.m);
				break;
			case Mechanics.MINE_NO: // нет ни одной мины вокруг
				view.setImageResource(R.drawable.ground);
				break;
			case Mechanics.MINE_1:
				view.setImageResource(R.drawable.m1);
				break;
			case Mechanics.MINE_2:
				view.setImageResource(R.drawable.m2);
				break;
			case Mechanics.MINE_3:
				view.setImageResource(R.drawable.m3);
				break;
			case Mechanics.MINE_4:
				view.setImageResource(R.drawable.m4);
				break;
			case Mechanics.MINE_5:
				view.setImageResource(R.drawable.m5);
				break;
			case Mechanics.MINE_6:
				view.setImageResource(R.drawable.m6);
				break;
			case Mechanics.MINE_7:
				view.setImageResource(R.drawable.m7);
				break;
			case Mechanics.MINE_8:
				view.setImageResource(R.drawable.m8);
				break;
			}
			break;
		case Mechanics.CELL_CHECK: // флаг
			view.setImageResource(R.drawable.f);
			break;
		case 3: // неверно установленный флаг
			view.setImageResource(R.drawable.wf);
			break;
		}
		return view;	
	}
	
}



public class Field extends Activity {
	   public static final String EXT_COLS = "cols";
	   public static final String EXT_ROWS = "rows";
	    public static final String EXT_MINES = "mines";
	    public static Activity activity;
	    private GridView FieldGrid;
	    private FieldAdapter mAdapter;
	    
	    public void onCreate(Bundle savedInstanceState) {
	    	activity = this;
	    	super.onCreate(savedInstanceState);
	    	// убираем заголовок окна
	    	//requestWindowFeature(Window.FEATURE_NO_TITLE);
	    	setContentView(R.layout.field);
	    	
	    	// получаем параметры поля
	    	Bundle extras = getIntent().getExtras();
	    	int cols = 5; // extras.getInt(EXT_COLS);
	    	int rows = 4; // extras.getInt(EXT_ROWS);
	    	int mines = 2; // extras.getInt(EXT_MINES);
	    	
	    	mAdapter = new FieldAdapter(this, cols, rows, mines);
	    		    	 	
	    	TextView message = (TextView) findViewById(R.id.data);
	    	message.setText("Rows: " + rows + "\nColumns: " + cols + "\nMines: " + mines);
	    	FieldGrid = (GridView) findViewById(R.id.field_grid);
	    	FieldGrid.setAdapter(mAdapter);
	    	FieldGrid.setNumColumns(cols);
	    	
	    	Button btn = (Button) findViewById (R.id.restartButton);
	    	
	    	// restart button
	    	btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mAdapter.FieldModel.reNew();
					FieldGrid.setAdapter(mAdapter);				
				}
				
	    	});
	    	
	    	// обработка нажатия
	    	
	    	FieldGrid.setOnItemClickListener(new OnItemClickListener() {
	    		public void onItemClick(AdapterView parent, View v, int position, long id) {
	    			mAdapter.FieldModel.TapCell(position);
	    			FieldGrid.setAdapter(mAdapter);
	    		}
	    	});
	    	
	    	// обработка длительного нажатия
	    	FieldGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	    		public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
	    			mAdapter.FieldModel.LongTapCell(position);
	    			FieldGrid.setAdapter(mAdapter);
	    			return true;
	    		}
			});
	    }
	    
}
	    


