package com.mygdx.game;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.Losu;

public class AndroidLauncher extends AndroidApplication implements LosuGame.MyGameCallback {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;

		// create an instance of MyGame, and set the callback
		LosuGame game = new LosuGame();
		// Since AndroidLauncher implements MyGame.MyGameCallback, we can just pass 'this' to the callback setter.
		game.setMyGameCallback(this);

		initialize(game, config);
	}

	@Override
	public void onStartActivityA() {
		String strReact = "Reaction";
		Intent intent = new Intent(this, PickChamp.class);
		intent.putExtra("Res",strReact);
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

}
