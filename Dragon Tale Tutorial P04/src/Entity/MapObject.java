package Entity;

import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Tile;

import java.awt.Rectangle;

public abstract class MapObject {
	
	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	// position and vector
	protected double x; /* z tego co rozumiem, jest to pozycja względem lewegó górnego rogu okna aplikacji */
	protected double y; /* jw. */
	protected double dx;
	protected double dy;
	
	// dimensions
	protected int width; /* chyba szerokość i wysokość sprita z postacią - np. 30px */
	protected int height;
	
	// collision box
	protected int cwidth; /* szerokość i wysokość postaci - np. 20px */
	protected int cheight;
	
	// collision
	protected int currRow; /* aktualny wiersz i kolumna w której jest postać? */
	protected int currCol;
	protected double xdest; /* następna pozycja x w której znajdzie się player - w następnym cyklu */
	protected double ydest; 
	protected double xtemp; /* tymczasowa pozycja - do czego? */
	protected double ytemp;
	protected boolean topLeft; /* czy prawy górny róg dotyka do miejsca mapy typu blocked - czyli, że nie powinien się poruszać dalej */
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	
	// movement - w którą stronę się porusza
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	// movement attributes
	protected double moveSpeed; /* przyspieszenie, lub coś w tym stylu */
	protected double maxSpeed; /* maksymalna szybkość playera */
	protected double stopSpeed; /* opóźnienie (odwrotność przyspieszenia) dla zatrzymywania */
	protected double fallSpeed; /* grawitacja - czyli przyspieszenie opadania */
	protected double maxFallSpeed; /* maksymalna prędkość opadania */
	protected double jumpStart; /* jak wysoko moze skakać player? */
	protected double stopJumpSpeed; /* nie czaje */
	
	// constructor
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize(); 
	}
	
  /* czy dwa obiekty zachodzą na siebie? */
	public boolean intersects(MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
  /* zwraca obiekt Rectangle */
	public Rectangle getRectangle() {
		return new Rectangle(
				(int)x - cwidth,
				(int)y - cheight,
				cwidth,
				cheight
		);
	}
	
	public void calculateCorners(double x, double y) {
		
		int leftTile = (int)(x - cwidth / 2) / tileSize; /* w której kolumnie znajduje się lewa krawędź postaci - kolumna szerokości tailSize */
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize; /* w której kolumnie znajduje się prawa krawędź postaci - kolumna szerokości tailSize */
		int topTile = (int)(y - cheight / 2) / tileSize; /* w którym wierszu znajduje się górna krawędź postaci - wiersze wysokości tileSize */
		int bottomTile = (int)(y + cheight / 2 - 1) / tileSize; /* w którym wierszu znajduje się dolna krawędź postaci */

    if(topTile < 0 || bottomTile >= tileMap.getNumRows() || leftTile < 0 || rightTile >= tileMap.getNumCols()) {
        topLeft = topRight = bottomLeft = bottomRight = false;
        return;
    }
		
		int tl = tileMap.getType(topTile, leftTile); /* typ kafelka dla rogu top left - BLOCKED albo REGULAR */
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		topLeft = tl == Tile.BLOCKED;/* czy górny lewy róg trafił na kafelka BLOCKED */
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
		
	}
	
	public void checkTileMapCollision() {
		
		currCol = (int)x / tileSize; /* aktualny kafelek w poziomie */
		currRow = (int)y / tileSize; /* aktualny kafelek w pionie */
		
		xdest = x + dx; /* następna pozycja w poziomie */
		ydest = y + dy; /* następna pozycja w poziomie */
		
		xtemp = x; /* aktualna pozycja */
		ytemp = y; /* aktualna pozycja */
		
		calculateCorners(x, ydest);
		if(dy < 0) {
      /* lecimy do góry */
			if(topLeft || topRight) { /* walimy w górny kafelek BLOCKED */
				dy = 0;
				ytemp = currRow * tileSize + cheight / 2; /* obliczenie pozycji y */
			}
			else {
				ytemp += dy;
			}
		}
		if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				ytemp = (currRow + 1) * tileSize - cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		
		calculateCorners(xdest, y);
		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
				xtemp = currCol * tileSize + cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
				xtemp = (currCol + 1) * tileSize - cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		
		if(!falling) {
			calculateCorners(x, ydest + 1);
			if(!bottomLeft && !bottomRight) {
				falling = true;
			}
		}
		
	}
	
	public int getx() { return (int)x; }
	public int gety() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition() {
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setJumping(boolean b) { jumping = b; }
	
	public boolean notOnScreen() {
		return x + xmap + width < 0 ||
			x + xmap - width > GamePanel.WIDTH ||
			y + ymap + height < 0 ||
			y + ymap - height > GamePanel.HEIGHT;
	}
	
}
















