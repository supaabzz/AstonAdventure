package Entities;

import Screens.LevelOne;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Player implements Movement {

    private Vector2 position, velocity;

    LevelOne gameScreen;

    Animation<TextureRegion> animation;

    TextureAtlas textureAtlas;

    public Player(float x, float y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        textureAtlas = new TextureAtlas("Sprites/Characters/characters.atlas");
    }



    /*public void movementLeft(float dt) {
        leftAnimation.update(dt);
        position.x = LevelOne.SPEED * Gdx.graphics.getDeltaTime();
    }

    public void update(float dt) {
        stillAnimation.update(dt);
        velocity.add(0, 0);
        velocity.scl(dt);
    }

    public TextureRegion getTexture() {
        return stillAnimation.getFrame();
    }*/


}