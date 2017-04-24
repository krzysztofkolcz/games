package com.platformer01_ex01.bricks;

public class Pos{
  private int x;
  private int y;

  public Pos(int x,int y){
    this.x = x;
    this.y = y;
  }

  public void setX(int x){
    this.x = x;
  }

  public int getX(){
    return this.x;
  }

  public void setY(int y){
    this.y = y;
  }

  public int getY(){
    return this.y;
  }

  /* public boolean equals(Pos that){ */
  /*   return this.getX() == that.getX() && this.getY() == that.getY(); */
  /* } */

	@Override public boolean equals(Object other) {
			boolean result = false;
			if (other instanceof Pos) {
					Pos that = (Pos) other;
					result = (this.getX() == that.getX() && this.getY() == that.getY());
			}
			return result;
	}
}
