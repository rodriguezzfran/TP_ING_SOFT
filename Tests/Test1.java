import org.junit.Assert;
import org.junit.Test;
import main.GamePanel;

import org.junit.Assert.*;

public class Test1 {
    @Test
    public void testMovimiento() {
        Assert.assertEquals((GamePanel.getGame().getPlaying().getPlayer().getDown() ||
                        GamePanel.getGame().getPlaying().getPlayer().getRight() ||
                        GamePanel.getGame().getPlaying().getPlayer().getLeft()||
                        GamePanel.getGame().getPlaying().getPlayer().getUp()),false);
    }
}