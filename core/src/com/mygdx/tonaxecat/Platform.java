package com.mygdx.tonaxecat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Platform extends Sprite {

    public Platform(Texture texture, float x, float y) {
        super(texture);
        setPosition(x, y);
    }
}
