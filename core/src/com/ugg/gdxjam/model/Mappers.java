package com.ugg.gdxjam.model;

import com.badlogic.ashley.core.ComponentMapper;
import com.ugg.gdxjam.model.components.*;

public class Mappers {
    //Render
	public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<SizeComponent> size = ComponentMapper.getFor(SizeComponent.class);
    public static final ComponentMapper<ScaleComponent> scale = ComponentMapper.getFor(ScaleComponent.class);
    public static final ComponentMapper<RotationComponent> rotation = ComponentMapper.getFor(RotationComponent.class);
    public static final ComponentMapper<OriginComponent> origin = ComponentMapper.getFor(OriginComponent.class);
    public static final ComponentMapper<ColorComponent> color = ComponentMapper.getFor(ColorComponent.class);
    public static final ComponentMapper<ShowActorComponent> showActor = ComponentMapper.getFor(ShowActorComponent.class);
    public static final ComponentMapper<RenderableComponent> renderable = ComponentMapper.getFor(RenderableComponent.class);
    public static final ComponentMapper<ParticleTypesComponent> particleType = ComponentMapper.getFor(ParticleTypesComponent.class);
    public static final ComponentMapper<ParticleComponent> particle = ComponentMapper.getFor(ParticleComponent.class);
    
    //Movement
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<AngularComponent> angulo = ComponentMapper.getFor(AngularComponent.class);
    public static final ComponentMapper<InitialPositionComponent> initialPosition = ComponentMapper.getFor(InitialPositionComponent.class);
    
    //Physics
    public static final ComponentMapper<PhysicsComponent> physics = ComponentMapper.getFor(PhysicsComponent.class);
    public static final ComponentMapper<CollisionComponent> collision = ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<SensorComponent> sensor = ComponentMapper.getFor(SensorComponent.class);
    
    //Game
    public static final ComponentMapper<GameEntityTypeComponent> gameEntityType = ComponentMapper.getFor(GameEntityTypeComponent.class);
    public static final ComponentMapper<LifeComponent> life = ComponentMapper.getFor(LifeComponent.class);
    public static final ComponentMapper<LifeBarComponent> lifeBar = ComponentMapper.getFor(LifeBarComponent.class);
    public static final ComponentMapper<DamageComponent> damage = ComponentMapper.getFor(DamageComponent.class);
    public static final ComponentMapper<WeaponsComponent> weapon = ComponentMapper.getFor(WeaponsComponent.class);
    public static final ComponentMapper<KillComponent> kill = ComponentMapper.getFor(KillComponent.class);
    public static final ComponentMapper<RemovedComponent> removed = ComponentMapper.getFor(RemovedComponent.class);
    public static final ComponentMapper<InvincibleComponent> invicible = ComponentMapper.getFor(InvincibleComponent.class);
    public static final ComponentMapper<LifeDurationComponent> lifeDuration = ComponentMapper.getFor(LifeDurationComponent.class);
    public static final ComponentMapper<IntervalComponent> interval = ComponentMapper.getFor(IntervalComponent.class);
    public static final ComponentMapper<LifeDecrementComponent> lifeDecrement = ComponentMapper.getFor(LifeDecrementComponent.class);
    public static final ComponentMapper<LockOnComponent> lockOn = ComponentMapper.getFor(LockOnComponent.class);
    public static final ComponentMapper<PlayerComponent> player = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<BuildingComponent> building = ComponentMapper.getFor(BuildingComponent.class);
    public static final ComponentMapper<ParentComponent> parent = ComponentMapper.getFor(ParentComponent.class);
    public static final ComponentMapper<FuelComponent> fuel = ComponentMapper.getFor(FuelComponent.class);
    public static final ComponentMapper<RewardComponent> reward = ComponentMapper.getFor(RewardComponent.class);
    public static final ComponentMapper<RewardTypeComponent> rewardType = ComponentMapper.getFor(RewardTypeComponent.class);

    //Multimedia
    public static final ComponentMapper<SoundComponent> sound = ComponentMapper.getFor(SoundComponent.class);
    public static final ComponentMapper<ChangeMusicComponent> changeMusic = ComponentMapper.getFor(ChangeMusicComponent.class);

    //Input
    public static final ComponentMapper<InputCommandComponent> inputCommand = ComponentMapper.getFor(InputCommandComponent.class);

    //AI
    public static final ComponentMapper<FSMComponent> fsm = ComponentMapper.getFor(FSMComponent.class);
    public static final ComponentMapper<SteeringBehaviorComponent> steeringBehavior = ComponentMapper.getFor(SteeringBehaviorComponent.class);
    public static final ComponentMapper<SteerableComponent> steerable = ComponentMapper.getFor(SteerableComponent.class);
}
