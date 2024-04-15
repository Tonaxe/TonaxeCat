package com.mygdx.tonaxecat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Cat cat;
    private Vector2 lastTouchPosition;
    private boolean isCatTouched;

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("fondoCasa.png");

        Texture catTexture = new Texture("gato.png");
        cat = new Cat(catTexture);
        float catX = (Gdx.graphics.getWidth() - cat.getWidth()) / 2;
        float catY = 100;
        cat.setPosition(catX, catY);

        lastTouchPosition = new Vector2();
        isCatTouched = false;

        // Establecer esta clase como el manejador de entrada
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                lastTouchPosition.set(screenX, screenY);

                // Verificar si se hizo clic en el gato
                Rectangle catBounds = cat.getBoundingRectangle();
                isCatTouched = catBounds.contains(screenX, Gdx.graphics.getHeight() - screenY);

                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                // Si el gato no fue tocado inicialmente, no se mueve
                if (!isCatTouched) {
                    return false;
                }

                // Calcular la diferencia entre la posición actual y la anterior del dedo
                float deltaX = screenX - lastTouchPosition.x;
                float deltaY = screenY - lastTouchPosition.y;

                // Calcular la nueva posición del gato
                float newCatX = cat.getX() + deltaX;
                float newCatY = cat.getY() - deltaY; // Invertir la dirección vertical

                // Limitar la posición del gato para que no salga de la pantalla
                if (newCatX < 0) {
                    newCatX = 0;
                } else if (newCatX + cat.getWidth() > Gdx.graphics.getWidth()) {
                    newCatX = Gdx.graphics.getWidth() - cat.getWidth();
                }

                if (newCatY < 0) {
                    newCatY = 0;
                } else if (newCatY + cat.getHeight() > Gdx.graphics.getHeight()) {
                    newCatY = Gdx.graphics.getHeight() - cat.getHeight();
                }

                // Establecer la nueva posición del gato
                cat.setPosition(newCatX, newCatY);

                // Actualizar la posición del dedo
                lastTouchPosition.set(screenX, screenY);
                return true;
            }


            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                // Restablecer la bandera cuando se suelta el dedo
                isCatTouched = false;
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        batch.begin();
        cat.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        cat.getTexture().dispose();
    }
}
