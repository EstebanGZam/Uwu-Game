package com.example.integrativeTask.control;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music {

	private final MediaPlayer mediaPlayer;

	public Music(String filePath, boolean loop) {
		this.mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
		if (loop) mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}

	public void play() {
		mediaPlayer.play();
	}

}