package com.mygdx.tonaxecat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private Texture backgroundTexture;

    @Override
    public void show() {
        batch = new SpriteBatch();
        // Cargar la textura del fondo
        backgroundTexture = new Texture("fondoCasa.png");
    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        Gdx.gl.glClearColor(0.3f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibujar el fondo
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Aquí se renderizará el resto del juego
    }

    @Override
    public void dispose() {
        // Liberar los recursos utilizados
        batch.dispose();
        backgroundTexture.dispose();
    }
}
