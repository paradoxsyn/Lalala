package com.mygdx.game.states;

/**
 * Created by Paradox on 2/4/2017.
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.LosuGame;

import java.util.Stack;

public class StateManager {

    private Stack<State> states;
    LosuGame game;

    public StateManager(LosuGame game) {

        states = new Stack<State>();
        this.game=game;

    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(State state) {
        pop();
        push(state);
    }

    public void translate(State one, State two){
        pop();
        push(one);
        pop();
        push(two);
    }

    public void update(float dt) {
        if(!states.isEmpty()){
            states.peek().update(dt);
        }
    }

    public void render(float dt) {
        if(!states.isEmpty()){
            states.peek().render();
        }
    }

}
