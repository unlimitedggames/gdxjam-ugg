package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;
import com.ugg.gdxjam.model.enums.GameMusic;

public class MusicManager extends MultimediaManager
        implements
        Disposable {

    private static MusicManager _instance;
    static private Application app;

    private boolean musicEnabled = true;

    /**
     * Holds the music currently being played, if any.
     */
    private Music musicBeingPlayed;
    private GameMusic currentGameMusic;
    private LoaderUtils loader;

    /**
     * Creates the music manager.
     */
    public static MusicManager getInstance() {
        if (app == null || app != Gdx.app) {
            app = Gdx.app;
            _instance = new MusicManager();
        }
        return _instance;
    }

    public void init(LoaderUtils loader) {
        this.loader = loader;
    }

    public void setCurrentMusic(GameMusic music) {
        this.currentGameMusic = music;
    }

    public void unloadBasics() {
        this.unloadMusic(GameMusic.Main);
        this.unloadMusic(GameMusic.Boss);
    }

    public void loadBasics() {
        this.load(GameMusic.Main);
        this.load(GameMusic.Boss);
        loader.finishLoading();
    }

    public void load(GameMusic music) {
        if (!loader.isLoaded(music.getFileName()))
            loader.load(music.getFileName(), Music.class).finishLoading();
    }

    public void loadAndPlay(GameMusic music) {
        load(music);
        setCurrentMusic(music);
        play();
    }

    /**
     * Plays the given music (starts the streaming).
     * <p/>
     * If there is already a music being played it is stopped automatically.
     */
    public void play() {
        // check if the music is enabled
        if (!musicEnabled) return;

        // stop any music being played
        Music musicToPlay = loader.get(currentGameMusic.getFileName(), Music.class);
        if(musicToPlay != null && musicBeingPlayed != musicToPlay) {
            stop();
            musicBeingPlayed = loader.get(currentGameMusic.getFileName(), Music.class);
            musicBeingPlayed.setVolume(volume);
            musicBeingPlayed.setLooping(true);
            musicBeingPlayed.play();
        }
    }

    public void pause() {
        if (musicBeingPlayed != null)
            musicBeingPlayed.pause();
    }

    public void resume() {
        if (musicEnabled && musicBeingPlayed != null)
            musicBeingPlayed.play();
    }

    /**
     * Stops and disposes the current music being played, if any.
     */
    public void stop() {
        if (musicBeingPlayed != null) {
            musicBeingPlayed.stop();
        }
    }

    public void unloadMusic(GameMusic music) {
        if (loader.isLoaded(music.getFileName())) {
            if (music == currentGameMusic && musicBeingPlayed != null)
                musicBeingPlayed.stop();
            loader.unload(music.getFileName());
        }
        if (music == currentGameMusic) {

            currentGameMusic = null;
            musicBeingPlayed = null;
        }
    }

    public void unloadMusic() {
        if (currentGameMusic != null && musicBeingPlayed != null) {
            loader.unload(currentGameMusic.getFileName());
            currentGameMusic = null;
            musicBeingPlayed = null;
        }
    }


    /**
     * Sets the music volume which must be inside the range [0,1].
     */
    @Override
    public void setVolume(
            float volume) {
        super.setVolume(volume);

        // if there is a music being played, change its volume
        if (musicBeingPlayed != null) {
            musicBeingPlayed.setVolume(volume);
        }
    }

    /**
     * Enables or disabled the music.
     */
    public void setEnabled(
            boolean enabled) {
        this.musicEnabled = enabled;

        // if the music is being deactivated, stop any music being played
        if (!enabled) {
            pause();
        } else {
            resume();
        }
    }

    /**
     * Disposes the music manager.
     */
    @Override
    public void dispose() {
        stop();
        musicBeingPlayed = null;
    }
}

