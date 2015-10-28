package Block;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Zheng-Yuan on 10/24/2015.
 */
public class Block {

    /**
     * Prairie, Grass, Mountain, Forest, Water
     */
    private int type;

    private static BufferedImage[] image;
    private static final int MAX_TYPE = 5;
    private static final String BLOCKS_FILE_NAME = "/map.png";
    private static final int BLOCK_IMAGE_SIZE = 16;

    static{
        image = new BufferedImage[MAX_TYPE];
        try{
            BufferedImage temp = ImageIO.read(Block.class.getResource(BLOCKS_FILE_NAME));
            int numberOfRow = temp.getHeight() / BLOCK_IMAGE_SIZE;
            int numberOfCol = temp.getWidth() / BLOCK_IMAGE_SIZE;
            for(int row = 0;row<numberOfRow;row++) {
                for(int col = 0;col<numberOfCol;col++){
                    int id = row * numberOfCol + col;
                    image[id] = temp.getSubimage(col * BLOCK_IMAGE_SIZE, row * BLOCK_IMAGE_SIZE, BLOCK_IMAGE_SIZE, BLOCK_IMAGE_SIZE);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Block(int type){
        this.type = type;
    }

    public void draw(Graphics2D g, int x, int y, int dx, int dy) {
        g.drawImage(image[type], x, y, dx, dy, null);
    }

}
