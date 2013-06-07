package entities;

import infrastructure.Parse;
import infrastructure.PathUtil;

import java.io.IOException;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class DynamicPathEntity extends PathEntity {

	public DynamicPathEntity(Vec2[] verts) {
		super(verts);
	}

	public DynamicPathEntity(Vec2[] lp, Vec2[] local, float x, float y,
			float width, float height) {
		super(lp, local, x, y, width, height);
	}

	@Override
	protected BodyDef createBD() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(xPos, yPos);
		bodyDef.fixedRotation = true;
		return bodyDef;
	}

	@Override
	protected FixtureDef createFD() {
		FixtureDef fix = new FixtureDef();
		fix.shape = ps;
		fix.density = 0.6f;
		fix.friction = 2.3f;
		fix.restitution = 0f;
		return fix;
	}

	public static DynamicPathEntity parse(String[] frags) {
		return new DynamicPathEntity(PathUtil.parseVec2(frags[3]),
				PathUtil.parseVec2(frags[4]), Float.parseFloat(frags[1]),
				Float.parseFloat(frags[2]), Float.parseFloat(frags[5]),
				Float.parseFloat(frags[6]));
	}

	public String toString() {
		return super.toString();
	}

}
