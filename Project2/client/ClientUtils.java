package Project2.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class ClientUtils {

    public static int calcHeightForText(JPanel container, String str, int width) {
        FontMetrics metrics = container.getGraphics().getFontMetrics(container.getFont());
        int hgt = metrics.getHeight();
        int adv = metrics.stringWidth(str);
        final int PIXEL_PADDING = 6;
        Dimension size = new Dimension(adv, hgt + PIXEL_PADDING);
        final float PADDING_PERCENT = 1.1f;
        // calculate modifier to line wrapping so we can display the wrapped message
        int mult = (int) Math.round(size.width / (width * PADDING_PERCENT));
        // System.out.println(mult);
        mult++;
        return size.height * mult;
    }

    public static void clearBackground(JComponent comp) {
        comp.setOpaque(false);
        comp.setBorder(BorderFactory.createEmptyBorder());
        comp.setBackground(new Color(0, 0, 0, 0));
    }
    public static int countOccurrences(String str, String subStr) {
        // Split the string by the substring and get the length of the resulting array
        // Subtract 1 because split() returns one more item than the number of
        // occurrences
        return str.split(subStr, -1).length - 1;
    }
}