package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.resources.MusicPlayer;


public class AndroidLauncher extends AndroidApplication implements gameCallback {
	public boolean test = false;
	public float pos = 0;
	Bundle extras;
	LosuGame game;
	AndroidLauncher al;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		al = this;
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		// create an instance of MyGame, and set the callback
		game = new LosuGame(al);
		// Since AndroidLauncher implements MyGame.MyGameCallback, we can just pass 'this' to the callback setter.
		//RyzeGame2 game = new RyzeGame2();
		game.setMyGameCallback(this);


		initialize(game, config);
	}

	@Override
	public void onPause(){
		super.onPause();
	}

	@Override
	public void onStartActivityA(MusicPlayer player) {
		String strReact = "Reaction";
		Intent intent = new Intent(this, PickChamp.class);
		intent.putExtra("Res",strReact);
		intent.putExtra("Position",player.getPosition());
		startActivity(intent);
	}

	@Override
	public void onStartActivityB(){
		//Intent intent = new Intent(this, ActivityB.class);
		//startActivity(intent);
		//String strRange = "Range";
		//intent.putExtra("Res",strRange);
	}

	@Override
	public void onStartSomeActivity(int someParameter, String someOtherParameter){
		//Intent intent = new Intent(this, ActivityA.class);

		 //do whatever you want with the supplied parameters.
		//if (someParameter == 42) {
			//intent.putExtra(MY_EXTRA, someOtherParameter);
		//}
		//startActivity(intent);
	}

	@Override
	public float getPos(){
		return pos;
	}

}
