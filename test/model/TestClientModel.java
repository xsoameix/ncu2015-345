package model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestClientModel extends TestCase {
	ClientModel clientModel;
	StubModel model;

	@Before
	public void setUp() throws Exception {
		clientModel = new ClientModel();
		model = new StubModel();
		clientModel.setModel(model);
	}

	@Test
	public void testRequestFire() {
		clientModel.requestFire();
		assertEquals(model.requestFire, true);
	}

	@Test
	public void testRequestStartGame() {
		clientModel.requestStartGame();
		assertEquals(model.requestStartGame, true);
	}

	@Test
	public void testRequestSetTotalTime() {
		clientModel.requestSetTotalTime(1000);
		assertEquals(model.requestSetTotalTime, true);
	}

	@Test
	public void testRequestSetPlayerNumber() {
		clientModel.requestSetPlayerNumber(6);
		assertEquals(model.requestSetPlayerNumber, true);
	}

	@Test
	public void testRequestSetLocation() {
		clientModel.requestSetLocation(0, 0);
		assertEquals(model.requestSetLocation, true);
	}

	@Test
	public void testRequestEstablishRoom() {
		assertTrue(clientModel.requestEstablishRoom(80));
	}

	@Test
	public void testRequestEnterRoom() {
		assertTrue(clientModel.requestEnterRoom("123.0.0.1", 80));
	}

	@Test
	public void testSet() {
		Byte[] packet = {};
		clientModel.set(packet);
		assertEquals(model.set,true);
	}
}
