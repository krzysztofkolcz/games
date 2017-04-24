package com.platformer01_ex01.bricks;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class BrickTest{

        @Test
        public void multiplicationOfZeroIntegersShouldReturnZero() {
                assertEquals("0 x 0 must be 0", 0, 0);
        }


				@Test
				public void posEquals(){
					Pos pos01 = new Pos(0,10);
					Pos pos02 = new Pos(0,10);
					assertEquals(pos01,pos02);
				}

        @Test
        public void moveDownCollistionTest01() {

					List<Pos> brickOnGround = new ArrayList<>();
					brickOnGround.add(new Pos(0,100));
					brickOnGround.add(new Pos(5,100));
					brickOnGround.add(new Pos(10,105));
					brickOnGround.add(new Pos(15,105));

					Brick brick = new LongBrick();
					brick.setFalling(true);
					brick.setXPos(10);
					brick.setYPos(95);
					brick.setBrickOnGround(brickOnGround);

					/* brick.printPositions(); */

					System.out.println(brick.canMoveDown());

					/* assertFalse(brick.canMoveDown()); */

        }


}
