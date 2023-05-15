package com.example.integrativeTask.control;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music {

	private MediaPlayer mediaPlayer;
	private boolean loop;

	public Music(String filePath, boolean loop) {
		this.mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
		this.loop = loop;
		if (loop) {
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		}
	}

	public void play() {
		mediaPlayer.play();
	}

	public void stop() {
		mediaPlayer.stop();
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
		if (loop) {
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		} else {
			mediaPlayer.setCycleCount(1);
		}
	}

	public void setVolume(double volume) {
		mediaPlayer.setVolume(volume);
	}

	public double getVolume() {
		return mediaPlayer.getVolume();
	}

	public boolean isPlaying() {
		return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
	}

	public boolean isPaused() {
		return mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED;
	}

	public boolean isStopped() {
		return mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED;
	}
}