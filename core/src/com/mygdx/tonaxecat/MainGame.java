package com.mygdx.tonaxecat;

import com.badlogic.gdx.Game;

public class MainGame extends Game {

    @Override
    public void create() {
        // Cuando se inicia el juego, se establece la pantalla principal como GameScreen
        setScreen(new GameScreen());
    }
}
