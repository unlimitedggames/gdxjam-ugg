package com.ugg.gdxjam.model.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.ugg.gdxjam.model.Mappers;
import com.ugg.gdxjam.model.components.AngularComponent;
import com.ugg.gdxjam.model.components.PositionComponent;
import com.ugg.gdxjam.model.components.VelocityComponent;

public class AngularMovementSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<VelocityComponent> vm;
    private ComponentMapper<AngularComponent>  am;
    

    public AngularMovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class, AngularComponent.class).get());
        pm = Mappers.position;
        vm = Mappers.velocity;
        am= Mappers.angulo;
    }

    public void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        VelocityComponent velocity = vm.get(entity);
        AngularComponent angulo = am.get(entity);

        /*position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;*/
        
        //Calculamos constante
        float constante = 1 * deltaTime;
        
        //Se calcula el ángulo
        angulo.angulo += (float) (MathUtils.PI * constante);
        
        /*float vX, vY;
        vX = -angulo.radio * constante * MathUtils.sin(angulo.angulo);
        vY = angulo.radio * constante * MathUtils.cos(angulo.angulo);
        velocity.x = (vX + 1);
        velocity.y = (vY + 1);*/
        
        position.x += angulo.radio * MathUtils.cos(angulo.angulo);
        position.y += angulo.radio * MathUtils.sin(angulo.angulo);
        /*
         * 
         * public Posicion calcularNuevaPosicion(Posicion posicion, Vector velocidad) {            
                int xFinal, yFinal;
                int xInicial = posicion.getEnX();
                int yInicial = posicion.getEnY();
                double vX, vY;
                double wt = radio * delta;

                angulo = angulo + delta * Math.PI;
                Modifico el vector velocidad 
                vX = -radio * wt * Math.sin(angulo);
                vY = radio * wt * Math.cos(angulo);
                velocidad.setComponenteY((int) Math.round(vY + 1));
                
                xFinal = xInicial + (int)(Math.round(vX) + velocidad.getComponenteX() * 0.4);
                yFinal = yInicial + (int)(Math.round(vY) + velocidad.getComponenteY() * 0.4);
                return new Posicion(xFinal, yFinal);    
        }

         * 
         * 
         * 
         * 
         * 
         * */
        
    }

}
