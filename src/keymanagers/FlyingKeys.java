package keymanagers;

import java.io.IOException;

import infrastructure.App;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import entities.Player;

public class FlyingKeys extends DefaultKeys {
	public EventHandler<KeyEvent> keyPress = new EventHandler<KeyEvent>() {
		@Override
		public synchronized void handle(KeyEvent key) {
			final KeyEvent t = key;
			buffer.add(t.getCode());
			t.consume();
			if (t.getCode() == KeyCode.P) {
				App.game.getCurrentMap().toggleTime();
			}
			if (t.getCode() == KeyCode.D) {
				if (App.game.getPlayer() != null) {
					if (((Player) App.game.getPlayer()).getSide() == Player.Side.LEFT) {
						App.game.getPlayer().setVisible(false);
						((Player) App.game.getPlayer())
								.setSide(Player.Side.RIGHT);
						((Player) App.game.getPlayer()).changeNode();
						App.game.getPlayer().setVisible(true);
					}
				}
			}
			if (t.getCode() == KeyCode.A) {
				if (App.game.getPlayer() != null) {
					if (((Player) App.game.getPlayer()).getSide() == Player.Side.RIGHT) {
						App.game.getPlayer().setVisible(false);
						((Player) App.game.getPlayer())
								.setSide(Player.Side.LEFT);
						((Player) App.game.getPlayer()).changeNode();
						App.game.getPlayer().setVisible(true);
					}
				}
			}

			if (t.getCode() == KeyCode.N) {
				if (App.game.getIsAtDoor()) {
					int index = App.game.getMaps().indexOf(
							App.game.getCurrentMap());
					if (index + 1 < App.game.getMaps().size())
						App.game.changeMap(App.game.getMaps().get(index + 1));
				}
			}
			if (t.getCode() == KeyCode.R)
				try {
					App.game.getCurrentMap().reset();
					System.out.println("R Hit");
				} catch (IOException e) {
					e.printStackTrace();
				}

		}
	};
	public EventHandler<KeyEvent> keyRelease = new EventHandler<KeyEvent>() {
		@Override
		public synchronized void handle(KeyEvent key) {
			Body body = App.game.getPlayer().getBody();
			final KeyEvent t = key;
			buffer.remove(t.getCode());
			if (t.getCode() == KeyCode.A && body.getLinearVelocity().x != 0) {
				Vec2 velocity = new Vec2(0, body.getLinearVelocity().y);
				body.setLinearVelocity(velocity);
				((Player)App.game.getPlayer()).setMoving(false);
			}
			if (t.getCode() == KeyCode.D && body.getLinearVelocity().x != 0) {
				Vec2 velocity = new Vec2(0, body.getLinearVelocity().y);
				body.setLinearVelocity(velocity);
				((Player)App.game.getPlayer()).setMoving(false);
			}
			t.consume();
		}

	};

	public Runnable keyThread = new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					Body body = App.game.getPlayer().getBody();
					if (buffer.contains(KeyCode.W)) {
						Vec2 velocity = new Vec2(body.getLinearVelocity().x, 20.0f);
						body.setLinearVelocity(velocity);
					}
					if (buffer.contains(KeyCode.A) && buffer.contains(KeyCode.D)) {
						Vec2 velocity = new Vec2(0, body.getLinearVelocity().y);
						body.setLinearVelocity(velocity);
					} else if (buffer.contains(KeyCode.A)) {
						Vec2 velocity = new Vec2(-20.0f, body.getLinearVelocity().y);
						body.setLinearVelocity(velocity);
					} else if (buffer.contains(KeyCode.D)) {
						Vec2 velocity = new Vec2(20.0f, body.getLinearVelocity().y);
						body.setLinearVelocity(velocity);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						System.out.println("KeyManager stopped");
					}
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					try {
						Thread.sleep(10);
						System.out.println("NullPointerCaught");
					} catch (InterruptedException k) {
						System.out.println("KeyManager stopped");
					}
				}
			}
		}

	};

}
