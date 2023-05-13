package com.example.ti3.Control;

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

    public void reproducir() {
        mediaPlayer.play();
    }

    public void detener() {
        mediaPlayer.stop();
    }

    public void pausar() {
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

    public void setVolumen(double volumen) {
        mediaPlayer.setVolume(volumen);
    }

    public double getVolumen() {
        return mediaPlayer.getVolume();
    }

    public boolean isReproduciendo() {
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    public boolean isPausado() {
        return mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED;
    }

    public boolean isDetenido() {
        return mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED;
    }
}