package com.ugg.gdxjam.model;

import java.util.Comparator;

import com.ugg.gdxjam.view.Renderable;

public class ZComparator implements Comparator<Renderable> {

	public ZComparator(){
			}
	
    @Override
    public int compare(Renderable r1, Renderable r2) {
        return (int)Math.signum(r1.zInd - r2.zInd);
    }
}
