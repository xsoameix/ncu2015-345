package net;

public class FakeSession {

    public FakeServerModel model;

    public FakeSession(FakeServerModel model) {
        this.model = model;
    }

    public void onData(byte body[]) {
        model.set(body);
    }
}
