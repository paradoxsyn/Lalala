package dj.rabithole.losu;

import android.support.annotation.FloatRange;

/**
 * Created by Rabithole on 1/17/2017.
 */

public class Font {

    private int color;
    private String typeface;
    private float size;

}

class Layer {
    float x;
    float y;
    float scale;
    @FloatRange(from = 0.0F, to = 360.0F)
    float rotationInDegrees;
}

class TextLayer extends Layer {

    private String text;
    private Font font;
}
