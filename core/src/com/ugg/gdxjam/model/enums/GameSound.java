package com.ugg.gdxjam.model.enums;

public enum GameSound {
    Shoot("data/sounds/shoot.wav"),
    ShootBig("data/sounds/shoot-big.wav"),
    Hit("data/sounds/hit.wav"),
    HitBig("data/sounds/hit-big.wav"),
    Explosion("data/sounds/explosion.ogg"),
    BigExplosion("data/sounds/explosion-big.ogg"),
    PowerUp1("data/sounds/power-up.wav"),
    PowerUp2("data/sounds/power-up2.wav");



     private final String fileName;

     GameSound(
            String fileName) {
        this.fileName = fileName;
     }

     public String getFileName() {
        return fileName;
     }
}
