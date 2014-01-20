/***********/
/** Sean Leonard					 */
/** C05633915						 */
/**------------------------------------------------------*/
/** The Circle class is the clas used to draw the DraftPieces on the Board.	 */

package draftspkg;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends DraftsPiece{

    // center point
    private int x, y;
    private int radius;
    private Color colour;

    public Circle(int x, int y, int radius, Color colour) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.colour = colour;
    }

    // draw the circle
    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillOval(x, y, 2 * radius, 2 * radius);
    }

}

