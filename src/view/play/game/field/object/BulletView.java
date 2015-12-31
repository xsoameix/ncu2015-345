package view.play.game.field.object;

import model.game.field.dynamic.Bullet;
import view.play.game.field.FieldObjectView;

public class BulletView extends FieldObjectView{
	private Bullet bullet;

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
}
