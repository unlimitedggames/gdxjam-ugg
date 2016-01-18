
package com.ugg.gdxjam.model;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PhysicsContactListener implements ContactListener {


	@Override
	public void beginContact (Contact contact) {
		GameEntity entityA = (GameEntity)contact.getFixtureA().getBody().getUserData();
		GameEntity entityB = (GameEntity)contact.getFixtureB().getBody().getUserData();

		entityA.addCollision(entityB);
		entityB.addCollision(entityA);


		//Logger.getInstance().log("Hit!");

		/*if (Components.PROJECTILE.has(entityA)) processProjectile(entityA, entityB);
		if (Components.PROJECTILE.has(entityB)) processProjectile(entityB, entityA);

		if (Components.SQUAD.has(entityA)) processTargetTracker(entityA, entityB, false);
		if (Components.SQUAD.has(entityB)) processTargetTracker(entityB, entityA, false);*/

	}

	@Override
	public void endContact (Contact contact) {
		GameEntity entityA = (GameEntity)contact.getFixtureA().getBody().getUserData();
		GameEntity entityB = (GameEntity)contact.getFixtureB().getBody().getUserData();

		entityA.removeCollision(entityB);
		entityB.removeCollision(entityA);

		/*if (Components.SQUAD.has(entityA)) processTargetTracker(entityA, entityB, true);
		if (Components.SQUAD.has(entityB)) processTargetTracker(entityB, entityA, true);*/
	}

	public void processTargetTracker (Entity squad, Entity target, boolean contactEnd) {
		/*SquadComponent squadComp = Components.SQUAD.get(squad);
		if (contactEnd)
			squadComp.untrack(squad, target);
		else
			squadComp.track(squad, target);*/
	}

	public void processProjectile (Entity projectile, Entity target) {
		/*if (Components.HEALTH.has(target)) {
			if (!Constants.friendlyFire) {
				Faction projectileFaction = Components.FACTION.get(projectile).getFaction();
				Faction targetFaction = Components.FACTION.get(target).getFaction();
				if (projectileFaction == targetFaction) return;
			}

			ProjectileComponent projectileComp = Components.PROJECTILE.get(projectile);
			HealthComponent healthComp = Components.HEALTH.get(target);

			healthComp.value -= projectileComp.getDamage();
		}

		EntityUtils.removeEntity(projectile);*/
	}

	@Override
	public void preSolve (Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve (Contact contact, ContactImpulse impulse) {

	}

}
