package view.play.game.field.object;

import model.game.field.dynamic.Obstacle;
import view.play.game.field.FieldObjectView;

public class ObstacleView extends FieldObjectView{
	private Obstacle obstacle;

	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
}
