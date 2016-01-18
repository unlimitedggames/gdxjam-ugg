package com.ugg.gdxjam.model.enums;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.ObjectMap;
import com.ugg.gdxjam.controller.PhysicsController;
import com.ugg.gdxjam.model.data.BodyData;

public enum BodyDataType {

    NormalPlayerBody,
	NormalShipBody,
	NormalBulletBody,
	WatcherBulletBody,
    MotherShip,
	EnemyMine,
	Meteorite;

    static ObjectMap<BodyDataType, BodyData> bodiesData;
    
    public static void init(PhysicsController physicsCtrl){
    	bodiesData = new ObjectMap<BodyDataType, BodyData>();

    	FixtureDef def = new FixtureDef();
		BodyDef bodyDef = new BodyDef();
    	CircleShape crcShape = new CircleShape();
    	crcShape.setRadius(1.6f);
    	def.shape = crcShape;
    	def.isSensor = true;
		bodyDef.bullet = false;
		bodyDef.type = BodyType.DynamicBody;
    	BodyData bodyData = new BodyData(bodyDef, physicsCtrl, def);
    	bodiesData.put(BodyDataType.NormalShipBody, bodyData);

        def = new FixtureDef();
        bodyDef = new BodyDef();
        crcShape = new CircleShape();
        crcShape.setRadius(1.5f);
        def.shape = crcShape;
        def.isSensor = true;
        bodyDef.bullet = false;
        bodyDef.type = BodyType.DynamicBody;
        bodyData = new BodyData(bodyDef, physicsCtrl, def);
        bodiesData.put(BodyDataType.NormalPlayerBody, bodyData);

    	def = new FixtureDef();
    	crcShape = new CircleShape();
		bodyDef = new BodyDef();
    	crcShape.setRadius(0.5f);
    	def.shape = crcShape;
    	def.isSensor = true;
		bodyDef.bullet = true;
		bodyDef.type = BodyType.DynamicBody;
    	bodyData = new BodyData(bodyDef, physicsCtrl, def);
    	bodiesData.put(BodyDataType.NormalBulletBody, bodyData);

		def = new FixtureDef();
		crcShape = new CircleShape();
		bodyDef = new BodyDef();
		crcShape.setRadius(1f);
		def.shape = crcShape;
		def.isSensor = true;
		bodyDef.bullet = true;
		bodyDef.type = BodyType.DynamicBody;
		bodyData = new BodyData(bodyDef, physicsCtrl, def);
		bodiesData.put(BodyDataType.WatcherBulletBody, bodyData);

        def = new FixtureDef();
        crcShape = new CircleShape();
        bodyDef = new BodyDef();
        crcShape.setRadius(19f);
        def.shape = crcShape;
        def.isSensor = true;
        bodyDef.bullet = false;
        bodyDef.type = BodyType.DynamicBody;
        bodyData = new BodyData(bodyDef, physicsCtrl, def);
        bodiesData.put(BodyDataType.MotherShip, bodyData);

		def = new FixtureDef();
		crcShape = new CircleShape();
		bodyDef = new BodyDef();
		crcShape.setRadius(3.5f);
		def.shape = crcShape;
		def.isSensor = true;
		bodyDef.bullet = false;
		bodyDef.type = BodyType.DynamicBody;
		bodyData = new BodyData(bodyDef, physicsCtrl, def);
		bodiesData.put(BodyDataType.EnemyMine, bodyData);

		def = new FixtureDef();
		crcShape = new CircleShape();
		bodyDef = new BodyDef();
		crcShape.setRadius(2.3f);
		def.shape = crcShape;
		def.isSensor = true;
		bodyDef.bullet = false;
		bodyDef.fixedRotation = false;
		bodyDef.type = BodyType.DynamicBody;
		bodyData = new BodyData(bodyDef, physicsCtrl, def);
		bodiesData.put(BodyDataType.Meteorite, bodyData);
    }
    
    public static BodyData get(BodyDataType body){
    	return bodiesData.get(body);
    }
	
	
}
