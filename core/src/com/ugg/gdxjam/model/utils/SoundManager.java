package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.ugg.gdxjam.model.LRUCache;
import com.ugg.gdxjam.model.LRUCache.CacheEntryRemovedListener;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.enums.GameSound;


public class SoundManager extends MultimediaManager
        implements
        CacheEntryRemovedListener<GameSound, Sound>,
        Disposable {

    private static SoundManager _instance;
    static private Application app;

    private boolean soundEnabled = true;

    /**
     * The sound cache.
     */
    private final LRUCache<GameSound, Sound> soundCache;

    /**
     * Creates the sound manager.
     */
    protected SoundManager() {
        soundCache = new LRUCache<GameSound, Sound>(20);
        soundCache.setEntryRemovedListener(this);
    }

    public static SoundManager getInstance() {
        if (app == null || app != Gdx.app) {
            app = Gdx.app;
            _instance = new SoundManager();
        }
        return _instance;
    }

    public void loadBasicSounds() {
        float volumenanteior = this.volume;
        this.volume = 0f;
        this.play(GameSound.Hit);
        this.play(GameSound.HitBig);
        this.play(GameSound.Shoot);
        this.play(GameSound.ShootBig);
        this.play(GameSound.Explosion);
        this.play(GameSound.BigExplosion);
        this.volume = volumenanteior;
    }

    public void play(
            GameSound sound) {
        play(sound, false, 1f);
    }

    /**
     * Plays the specified sound.
     */
    public void play(
            GameSound sound, boolean loop, float soundVolume) {
        // check if the sound is enabled
        if (!soundEnabled) return;

        try {
            // try and get the sound from the cache
            Sound soundToPlay = soundCache.get(sound);
            if (soundToPlay == null) {
                FileHandle soundFile = Gdx.files.internal(sound.getFileName());
                soundToPlay = Gdx.audio.newSound(soundFile);
                soundCache.add(sound, soundToPlay);
            }

            // play the sound
            soundToPlay.stop();
            if (loop)
                soundToPlay.loop(soundVolume * volume);
            else
                soundToPlay.play(soundVolume * volume);
        } catch (Exception ex) {
            Logger.getInstance().error("Couldn't play sound: " + sound.name());
        }
    }

// EntryRemovedListener implementation

    @Override
    public void notifyEntryRemoved(
            GameSound key,
            Sound value) {
        value.dispose();
    }

    /**
     * Disposes the sound manager.
     */
    @Override
    public void dispose() {
        for (Sound sound : soundCache.retrieveAll()) {
            sound.stop();
            sound.dispose();
        }
        this.app = null;
    }
}

