package com.example.task;

import java.util.Random;

public class Mechanics {
	
	public static final int CELL_CLOSE = 0; // €чейка закрыта
    public static final int CELL_OPEN = 1; // €чейка открыта
    public static final int CELL_CHECK = 2; // €чейка помечена
    
    // константы определени€ параметров €чейки
    public static final int MINE_NO = 0; // ћины нет
    public static final int MINE_YES = -1; // ћина в €чейке
    public static final int MINE_1 = 1; // ћин в соседних €чейках
    public static final int MINE_2 = 2; // ћин в соседних €чейках
    public static final int MINE_3 = 3; // ћин в соседних €чейках
    public static final int MINE_4 = 4; // ћин в соседних €чейках
    public static final int MINE_5 = 5; // ћин в соседних €чейках
    public static final int MINE_6 = 6; // ћин в соседних €чейках
    public static final int MINE_7 = 7; // ћин в соседних €чейках
    public static final int MINE_8 = 8; // ћин в соседних €чейках
    public static final int WRONG_FLAG= 9; //Ќеправильно установленный флаг
    
    
    public class Cell {
    	int CellState;
    	int MineState;
    	// constructor
    	public Cell() {
    		this.CellState = CELL_CLOSE;
    		this.MineState = MINE_NO;
    	}
    	// установка состо€ний клетки
    	public void setCellState(int cState) {
    		this.CellState = cState;
    	}
    	public void setMineState(int mState) {
    		this.MineState = mState;
    	}
    	
    	// получение состо€ний клетки
    	public int getCellState() {
    		return this.CellState;
    	}
    	public int getMineState() {
    		return this.MineState;
    	}
    }
    
    // создаем поле
    Cell[][] CellField;
    
    private static int cols; // столбы
    private static int rows; // строки
    private static int mines;  // мины
    
    public Mechanics(int rows, int cols, int mines) {
    	this.cols = cols;
    	this.rows = rows;
    	this.mines = mines;
    	CellField = new Cell[rows][cols];
    	initField(mines); // посев пол€
    }
    private void initField(int minesNumber) {
    	for (int i = 0; i < rows; i++)
    		for (int j = 0; j < cols; j++)
    			CellField[i][j] = new Cell();
    	
    	Random random = new Random(System.currentTimeMillis());
    	for (int i = 0; i < minesNumber; i++) {
    		int cc;
    		int cr;
    		do {
    			cc = random.nextInt(cols);
    			cr = random.nextInt(rows);
    		} while (isNotMineSet(cr, cc) != true);
    		CellField[cr][cc].setMineState(MINE_YES);
    	}
    	
    	// расстановка мин в соседних клетках
    	for (int i = 0; i < rows; i++)
    		for (int j = 0; j < cols; j++) {
    			if (CellField[i][j].MineState != MINE_YES) { // нет мины в точке [i,j]
    				for (int k = 0; i < rows; i++) {
    					if (isMineSet(i+k, j-1) == true)
    						CellField[i][j].MineState ++ ;
    					if ( (isMineSet(i+k, j) == true) && (i+k != i) )
    						CellField[i][j].MineState ++ ;
    					if (isMineSet(i+k, j+1) == true)
    						CellField[i][j].MineState ++ ;
    				}
    			}
    		}
    }
    void reNew() {
    	for (int i = 0; i < rows; i++)
    		for (int j = 0; j < cols; j++) {
    			CellField[i][j].CellState = CELL_CLOSE;
    			CellField[i][j].MineState = MINE_NO;
    		}
    	Random rnd = new Random(System.currentTimeMillis());
    	for (int i = 0; i < mines; i++) {
    		int cc;
    		int cr;
    		do {
    			cc = rnd.nextInt(cols);
    			cr = rnd.nextInt(rows);
    		} while (isNotMineSet(cr, cc) != true);
    		CellField[cr][cc].setMineState(MINE_YES);
    	}
    	
    	// расстановка мин в соседних клетках
    	for (int i = 0; i < rows; i++)
    		for (int j = 0; j < cols; j++) {
    			if (CellField[i][j].MineState != MINE_YES) { // нет мины в точке [i,j]
    				for (int k = 0; i < rows; i++) {
    					if (isMineSet(i+k, j-1) == true)
    						CellField[i][j].MineState ++ ;
    					if ( (isMineSet(i+k, j) == true) && (i+k != i) )
    						CellField[i][j].MineState ++ ;
    					if (isMineSet(i+k, j+1) == true)
    						CellField[i][j].MineState ++ ;
    				}
    			}
    		}
    }
    
    public int getCount() {
    	return cols * rows;
    }
    // не установлена мина в клетке?
    public boolean isNotMineSet(int Row, int Col) {
    	if (Row < 0 || Row >= rows || Col < 0 || Col >= cols)
    		return false;
    	if (CellField[Row][Col].getMineState() == MINE_NO)
    		return true;
    	else
    		return false;
    }
    
    // устрановлена мина в клетке?
    
    public boolean isMineSet(int Row, int Col) {
    	if (Row < 0 || Row >= rows || Col < 0 || Col >= cols)
    		return false;
    	if (CellField[Row][Col].getMineState() == MINE_YES)
    		return true;
    	else
    		return false;
    }
    
    // помечена клетка?
    
    public boolean isCellCheck(int Row, int Col) {
    	if (Row < 0 || Row >= rows || Col < 0 || Col >= cols)
    		return false;
    	if (CellField[Row][Col].getCellState() == CELL_CHECK)
    		return true;
    	else
    		return false;
    }
    
    // посмотреть клетку
    
    public Cell getCell(int position) {
    	int r = position / rows;
    	int c = position % cols;
    	return CellField[r][c];
    }
    
    // обработка одиночного(короткого) нажати€ на €чейку
    
    public void TapCell(int position) {
    	//int r = position / rows;
    	//int c = position % rows;
    	int r = position / cols;
    	int c = position % cols;
    	switch(CellField[r][c].CellState) {
    	case CELL_CLOSE: // €чейка закрыта
    		CellField[r][c].CellState = CELL_OPEN;
    		switch(CellField[r][c].MineState) {
    		case MINE_NO: // мин в округе нет
    			clearAround(position);
    			break;
    		case MINE_YES: // в €чейке мина
    			Boom(position);
    			break;
    		}
    		break;
    		
    	case CELL_OPEN: // €чейка открыта
    		break;
    	case 2: // €чейка помечена
    		break;
    	}
    }
    
    // функци€ открыти€ €чейки
    public boolean openCell(int Row, int Col) {
    	if (Row < 0 || Row >= rows || Col < 0 || Col >= cols )
    		return false;
    	CellField[Row][Col].CellState = CELL_OPEN;
    	return true;
    }
    
    // если €чейка пуста - открыть вокруг €чейки без мин
    public void clearAround(int position) {
    	openCell(position / cols, position % cols);
    	
    	for (int i = position / cols; i < rows; i++)
    		for (int j = 0; j < cols; j++) {
    			if ( (CellField[i][j].MineState == MINE_NO) && (CellField[i][j].CellState == CELL_OPEN) ) {
    				for (int k = -1; k < 2; k++) {
    					if ( (isMineSet(i+k, j-1) == false) && (isCellCheck(i+k, j-1) != true))
    						openCell(i+k, j-1);
    					if ( (isMineSet(i+k, j) == false) && (isCellCheck(i+k, j) != true))
    						openCell(i+k, j);
    					if ( (isMineSet(i+k, j+1) == false) && (isCellCheck(i+k, j+1) != true))
    						openCell(i+k, j+1);
    				}
    			}
    		}
    }
    
    // взрыв мины
    
    public void Boom(int position) {
    	for (int i = 0; i < rows; i++)
    		for (int j = 0; j < cols; j++) {
    			switch(CellField[i][j].CellState) {
    			case CELL_CLOSE:
    				CellField[i][j].CellState = CELL_OPEN;
    				break;
    			case CELL_CHECK:
    				// если неверно стоит флаг
    				//то замен€ем на перечеркнутый флаг
    				if (CellField[i][j].MineState != MINE_YES)
    					CellField[i][j].CellState = 3;
    				break;
    			}
    		}
    }
    
    // обработка долгого нажати€ на €чейку
    
    public void LongTapCell(int position) {
    	int r = position / cols;
    	int c = position % cols;
    	switch (CellField[r][c].CellState) {
    	case CELL_CLOSE: // €чейка закрыта
    		CellField[r][c].CellState = CELL_CHECK;
    		break;
    	case CELL_OPEN:
    		break;
    	case CELL_CHECK:
    		CellField[r][c].CellState = CELL_CLOSE;
    		break;
    	}
    }
    
}
    					