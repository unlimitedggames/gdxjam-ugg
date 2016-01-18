package com.ugg.gdxjam.model.enums;
import com.ugg.gdxjam.model.data.BulletData;
import com.ugg.gdxjam.model.data.VelocityData;

public enum VelocityDataType {

	BulletNormal (new VelocityData(1f, 1f, 50f, VelocityType.Linear)),
	BulletImpulse (new VelocityData(1f, 1f, 0.5f, VelocityType.Impulse)),
	SlowBullet (new VelocityData(1f, 1f, 5f, VelocityType.Linear)),
	SlowBulletImpulse (new VelocityData(1f, 1f, 0.37f, VelocityType.Impulse));

	private final VelocityData data;

	private VelocityDataType(
			VelocityData data)
	{
		this.data = data;
	}

	public VelocityData get()
	{
		return data;
	}
	
	
}
