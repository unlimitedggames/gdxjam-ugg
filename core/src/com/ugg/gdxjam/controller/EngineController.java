package com.ugg.gdxjam.controller;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pools;
import com.ugg.gdxjam.GDXJam;
import com.ugg.gdxjam.model.GameEntity;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.*;
import com.ugg.gdxjam.model.enums.*;
import com.ugg.gdxjam.model.factories.*;
import com.ugg.gdxjam.model.listeners.GameEntityListener;
import com.ugg.gdxjam.model.systems.*;
import com.ugg.gdxjam.model.utils.LoaderUtils;
import com.ugg.gdxjam.view.DisabledBlendingGroup;
import com.ugg.gdxjam.view.RenderableRegion;

public class EngineController implements Disposable {

    /**************************************/
    /********** ASHLEY OBJECTS ************/
    /**************************************/

    final PooledEngine oEngine;
	final MovementSystem oMovementSystem;
	final RenderableUpdateSystem oRenderUpdateSystem;
    final AngularMovementSystem oAngularSystem;
    final RenderSystem oRenderingSystem;
    final ShowActorSystem oShowActorSystem;
    final WeaponSystem oWeaponSystem;

    //To keep trace of systems
    final Array<EntitySystem> systems;

    /**************************************/
    /********** STAGE OBJECTS ************/
    /**************************************/

	final Stage playStage;

    /**************************************/
    /************** FACTORIES *************/
    /**************************************/

	final TroopFactory troopFactory;
	final BulletFactory bulletFactory;
    final ScenarioFactory scenarioFactory;
    final RewardFactory rewardFactory;
    final ParticleFactory particleFactory;

    /**************************************/
    /************* CONTROLLERS ************/
    /**************************************/

    final PhysicsController physicsCtrl;


	public EngineController(Stage playStage, Stage guiStage, PhysicsController physicsCtrl, InputController inputCtrl, CameraController cameraPlay, LoaderUtils loader){
		this.playStage = playStage;
        this.physicsCtrl = physicsCtrl;
        DisabledBlendingGroup disabledBlendingGroup = new DisabledBlendingGroup();
		oEngine = new PooledEngine();
        RenderDataType.init(loader);
        this.systems = new Array<EntitySystem>();
        this.troopFactory = new TroopFactory(this, physicsCtrl);
		this.bulletFactory = new BulletFactory(this, physicsCtrl);
        this.scenarioFactory = new ScenarioFactory(this, physicsCtrl);
        this.rewardFactory = new RewardFactory(this, physicsCtrl);
        this.particleFactory = new ParticleFactory(this, physicsCtrl, loader);
		oRenderUpdateSystem = new RenderableUpdateSystem();
		oMovementSystem = new MovementSystem();
		oAngularSystem = new AngularMovementSystem();
		oRenderingSystem = new RenderSystem(disabledBlendingGroup, playStage, guiStage, loader);
		oShowActorSystem = new ShowActorSystem();
		oWeaponSystem = new WeaponSystem();
		PhysicsSystem oPhysicsSystem = new PhysicsSystem(physicsCtrl);
		DebugSystem oDebugSystem = new DebugSystem(guiStage, playStage, loader, physicsCtrl, cameraPlay);
		CollisionSystem oCollisionSystem = new CollisionSystem();
        KillSystem oKillSystem = new KillSystem(this, physicsCtrl);
        TimeLapseSystem oTimeLapseSystem = new TimeLapseSystem();
        SteeringSystem oSteeringSystem = new SteeringSystem();
        ProcessInputSystem oProcessInputSystem = new ProcessInputSystem(inputCtrl);
        LockOnSystem oLockOnSystem = new LockOnSystem();
        FSMSystem oFSMSystem = new FSMSystem();
        BuildingSystem oBuildingSystem = new BuildingSystem();
        SensorSystem oSensorSystem = new SensorSystem();
        FuelSystem oFuelSystem = new FuelSystem();
        ParticleSystem oParticleSystem = new ParticleSystem();
		oDebugSystem.priority = 1;

        addSystem(oProcessInputSystem)
                .addSystem(oFSMSystem)
                .addSystem(oSensorSystem)
                .addSystem(oLockOnSystem)
                .addSystem(oPhysicsSystem)
                .addSystem(oFuelSystem)
                .addSystem(oCollisionSystem)
                .addSystem(oKillSystem)
                //.addSystem(oBuildingSystem)
                .addSystem(oTimeLapseSystem)
                .addSystem(oMovementSystem)
		        //.addSystem(oAngularSystem)
                .addSystem(oSteeringSystem)
                .addSystem(oWeaponSystem)
                .addSystem(oShowActorSystem)
		        .addSystem(oRenderUpdateSystem)
                .addSystem(oParticleSystem)
                .addSystem(oRenderingSystem);

        if(GDXJam.DEBUG) addSystem(oDebugSystem);

        oEngine.addEntityListener(new GameEntityListener(this));

        TroopDataType.init();

        TextureAtlas.AtlasRegion region = loader.getRegion("background");
        Color color = new Color(1f, 1f, 1f, 1f);
        float amountToChangeX = 0.0001f;
        float amountToChangeY = 0.0002f;

        for(int i = -600; i <= 2200f; i+=40) {
            for(int j = -600; j <= 2200f; j+=40) {
                RenderableRegion renderableRegion = new RenderableRegion(region);
                renderableRegion.setSize(40, 40);
                renderableRegion.setPosition(i, j);
                color.set(color.r - amountToChangeY, color.g, color.b - amountToChangeX, 1f);
                renderableRegion.setColor(color);
                //renderableRegion.setColor(1f,  amountToChangeX, amountToChangeY);
                playStage.addActor(renderableRegion);
                renderableRegion.setZIndex(0);

            }
        }
	}

    private EngineController addSystem(EntitySystem system){
        oEngine.addSystem(system);
        this.systems.add(system);
        return this;
    }

    public Entity newEntity(){
        return this.oEngine.createEntity();
    }

    public EngineController addEntity(Entity entity){
        this.oEngine.addEntity(entity);
        return this;
    }

    public EngineController addShowComponent(Entity entity){
        ShowActorComponent san = createComponent(ShowActorComponent.class);
        san.stage = this.playStage;
        entity.add(san);
        return this;
    }

    public <T extends Component> T createComponent (Class<T> componentType) {
        return oEngine.createComponent(componentType);
    }

	public void update(float delta){
		
		delta = Math.min(delta, GDXJam.TIME_STEP);
		oEngine.update(delta);
	}

    /********* Creation of entities **************/

    public GameEntity createAlly(TroopDataType troopType){
        return createAlly(troopType, null);
    }

    public GameEntity createAlly(TroopDataType troopType, GameEntity parent){
        return troopFactory.create(troopType, GameEntityType.Ally, parent);
    }

    public GameEntity createEnemy(TroopDataType troopType){
        return createEnemy(troopType, null);
    }

    public GameEntity createEnemy(TroopDataType troopType, GameEntity parent){
        return troopFactory.create(troopType, GameEntityType.Enemy, parent);
    }

    public GameEntity createScenario(ScenarioDataType dataType){
        return scenarioFactory.create(dataType, GameEntityType.Scenario, null);
    }

    public GameEntity createReward(RewardDataType dataType){
        return rewardFactory.create(dataType, GameEntityType.Reward, null);
    }

    public GameEntity createBullet(GameEntity parent, BulletDataType bulletType){
        GameEntityTypeComponent component = Mappers.gameEntityType.get(parent.entity);
        return bulletFactory.create(bulletType, component.type == GameEntityType.Ally ? GameEntityType.AllyBullet : GameEntityType.EnemyBullet, parent);
    }

    public GameEntity createParticle(ParticleType type){
        return createParticle(type, null);
    }

    public GameEntity createParticle(ParticleType type, GameEntity parent){
        return particleFactory.create(type, GameEntityType.Particle, parent);
    }

	public void removeEntity(GameEntity gameEntity){

        this.oEngine.removeEntity(gameEntity.entity);
	}

    public void removeGameEntity(GameEntity gameEntity){
        final Entity entity = gameEntity.entity;
        final PhysicsComponent phyC = Mappers.physics.get(entity);
        final RenderableComponent renderableC = Mappers.renderable.get(entity);
        final LifeBarComponent lifeBarComponent = Mappers.lifeBar.get(entity);
        if(renderableC != null){
            renderableC.renderable.remove();
        }
        if(lifeBarComponent != null){
            lifeBarComponent.lifeBar.remove();
        }
        if(phyC != null){
            final Body body = phyC.body;
            physicsCtrl.destroyBody(body);
        }
        Pools.free(gameEntity);
    }

    public EngineController toggleSystems(boolean start){
        for(EntitySystem system : systems){
            system.setProcessing(start);
        }
        return this;
    }

    public void killAllEnemies(){
        for(Entity entity : this.oEngine.getEntities()){
            GameEntityTypeComponent typeC = Mappers.gameEntityType.get(entity);
            if(typeC != null) {
                GameEntityType type = typeC.type;
                if (type == GameEntityType.Enemy || type == GameEntityType.EnemyBullet) {
                    PhysicsComponent physicsC = Mappers.physics.get(entity);
                    if(physicsC != null && physicsC.body != null){
                        physicsC.body.setActive(false);
                    }
                    WeaponsComponent weaponC = Mappers.weapon.get(entity);
                    if(weaponC != null){
                        weaponC.enabled = false;
                    }
                }
            }
        }
    }

    @Override
    public void dispose() {
        oEngine.clearPools();
    }
}
