package model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestClientModel extends TestCase {
	StubModel model;

	@Before
	public void setUp() throws Exception {
		model = new StubModel();

	}

	@Test
	public void testRequestFire() {
		model.requestFire();
		assertEquals(model.requestFire, true);
	}

	@Test
	public void testRequestStartGame() {
		model.requestStartGame();
		assertEquals(model.requestStartGame, true);
	}

	@Test
	public void testRequestSetTotalTime() {
		model.requestSetTotalTime(1000);
		assertEquals(model.requestSetTotalTime, true);
	}

	@Test
	public void testRequestSetPlayerNumber() {
		model.requestSetPlayerNumber(6);
		assertEquals(model.requestSetPlayerNumber, true);
	}

	@Test
	public void testRequestSetLocation() {
		model.requestSetLocation(0, 0);
		assertEquals(model.requestSetLocation, true);
	}

	@Test
	public void testRequestEstablishRoom() {
		assertTrue(model.requestEstablishRoom(80));
	}

	@Test
	public void testRequestEnterRoom() {
		assertTrue(model.requestEnterRoom("123.0.0.1", 80));
	}
}
