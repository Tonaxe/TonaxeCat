package com.mygdx.tonaxecat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Cat extends Sprite {

    private Rectangle bounds;

    public Cat(Texture texture) {
        super(texture);
        bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getBounds() {
        return bounds;
    }
}

