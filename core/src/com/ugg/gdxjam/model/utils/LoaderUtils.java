package com.ugg.gdxjam.model.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ObjectMap;
import com.ugg.gdxjam.GDXJam;
import com.ugg.gdxjam.model.Logger;
import com.ugg.gdxjam.model.enums.ParticleType;

public class LoaderUtils implements AssetErrorListener {

    //Asset manager
    private AssetManager assetManager;

    //Render
    private Skin skin = null;

    //Atlas
    private TextureAtlas atlas = null;

    //Resoluciones
    public Resolution[] resolutions = {new Resolution(240, 320, ""),
            new Resolution(480, 700, "0.416"),
            new Resolution(720, 1150, "0.66"),
            new Resolution(1080, 1850, "1")};

    private final String gameAtlas = "data/game.atlas";
    private final String guiAtlas = "data/gui.atlas";


    //Regiones
    private final ObjectMap<String, AtlasRegion> regions;

    //Bundles
    private I18NBundle bundle;

    public final ResolutionFileResolver resolver;

    private static final String ROOT_DIR = "data/particles/";

    private Array<ParticleEffect> effectTemplates = new Array<ParticleEffect>();
    private Array<ParticleEffectPool> effectPools = new Array<ParticleEffectPool>();

    public LoaderUtils() {
        resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
        assetManager = new AssetManager(resolver);
        assetManager.setErrorListener(this);
        Texture.setAssetManager(assetManager);
        assetManager.setLoader(I18NBundle.class, new I18NBundleLoader(resolver));

        GUIConfig.initializeValues(resolutions);

        this.atlas = null;
        this.regions = new ObjectMap<String, AtlasRegion>();

    }

    public void init() {

        /*************** ASSET MANAGER ********************/
        assetManager.load("data/gui.json", Skin.class, new SkinLoader.SkinParameter(guiAtlas));
        assetManager.load(gameAtlas, TextureAtlas.class);
        assetManager.load("data/mental-space", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(java.util.Locale.getDefault()));

        /**************** Load Sounds and music *******************/
        SoundManager.getInstance().loadBasicSounds();
        MusicManager.getInstance().init(this);
        MusicManager.getInstance().loadBasics();

        /**************** Finish ******************/
        this.finishLoading();

        this.loadParticles();
    }

    public void loadParticles () {
        for (int i = 0; i < ParticleType.values().length; i++) {
            ParticleEffect template = new ParticleEffect();
            template.load(Gdx.files.internal(ROOT_DIR + ParticleType.values()[i].file), this.getGameAtlas());
            //template.scaleEffect(0.02f);
            effectTemplates.add(template);

            ParticleEffectPool pool = new ParticleEffectPool(template, 4, 20);
            effectPools.add(pool);
        }
    }

    public ParticleEffectPool.PooledEffect obtainEffect(ParticleType type){
        return effectPools.get(type.ordinal()).obtain();
    }

    public void unloadAll() {
        SoundManager.getInstance().dispose();
        assetManager.dispose();
    }

    public LoaderUtils unload(String fileName) {
        this.assetManager.unload(fileName);
        return this;
    }

    public void finishLoading() {
        assetManager.finishLoading();
    }

    public LoaderUtils load(String path, Class classToLoad) {
        assetManager.load(path, classToLoad);
        return this;
    }

    public <T> T get(String fileName, Class<T> type) {
        return this.assetManager.get(fileName, type);
    }

    public boolean isLoaded(String fileName) {
        return assetManager.isLoaded(fileName);
    }

    public TextureAtlas getGUIAtlas() {
        return assetManager.get(guiAtlas, TextureAtlas.class);
    }

    public TextureAtlas getGameAtlas() {
        if (this.atlas == null)
            this.atlas = assetManager.get(gameAtlas, TextureAtlas.class);
        return atlas;
    }

    public Skin getSkin() {
        if (skin == null)
            skin = assetManager.get("data/gui.json", Skin.class);
        return skin;
    }

    public AtlasRegion getRegion(String key) {
        if (this.regions.get("key") == null) {
            AtlasRegion reg = this.getGameAtlas().findRegion(key);
            if(reg == null)
                Logger.getInstance().log("Es null: " + key);
            this.regions.put(key, reg);
            return reg;
        } else {
            return this.regions.get(key);
        }
    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        try {
            throw throwable;
        } catch (Throwable e) {
            Logger.getInstance().error(e.getMessage());
            e.printStackTrace();
        }
    }

    public I18NBundle getBundle() {
        if (this.bundle == null)
            this.bundle = assetManager.get("data/mental-space", I18NBundle.class);
        return this.bundle;
    }

}



