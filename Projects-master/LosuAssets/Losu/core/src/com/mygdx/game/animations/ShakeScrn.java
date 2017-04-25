package com.mygdx.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Paradox on 3/16/2017.
 */

public class ShakeScrn {
    private float elapsed;
    private float duration;
    private float intensity;


    /**
     * Start the screen shaking with a given power and duration
     * @param intensity How much intensity should the shaking use.
     * @param duration Time in milliseconds the screen should shake.
     */
    public ShakeScrn(float intensity, float duration){
        this.elapsed = 0;
        this.duration = duration / 1000f;
        this.intensity = intensity;
    }

    /**
     * Updates the shake and the camera.
     * This must be called prior to camera.update()
     */
    public void update(float delta, OrthographicCamera camera) {

        // Only shake when required.
        //if (elapsed < duration) {

            // Calculate the amount of shake based on how long it has been shaking already
            float currentPower = intensity * camera.zoom * ((duration - elapsed) / duration);
            float x = (random.nextFloat() - 0.5f) * 2 * currentPower;
            float y = (random.nextFloat() - 0.5f) * 2 * currentPower;
            camera.translate(-x, -y);

            // Increase the elapsed time by the delta provided.
            elapsed += delta;

            System.out.println(elapsed + "  " + intensity + "  " + x + "  " + y);
        //}
    }
}
