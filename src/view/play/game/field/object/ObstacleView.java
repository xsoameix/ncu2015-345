package view.play.game.field.object;

import java.awt.Color;

import javax.swing.BorderFactory;

import model.game.field.dynamic.Obstacle;
import model.game.field.map.MapBlock;
import view.play.game.field.FieldObjectView;

public class ObstacleView extends FieldObjectView{

	public ObstacleView(Obstacle obstacle) {
		super(obstacle);
		setLocation(obstacle.getLocation());
		setSize(MapBlock.getSize());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
//
//	public Obstacle getObstacle() {
//		return obstacle;
//	}
//
//	public void setObstacle(Obstacle obstacle) {
//		this.obstacle = obstacle;
//	}
}
