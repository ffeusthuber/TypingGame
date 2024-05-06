package domain;

public class SpawnPointSelectorStub implements SpawnPointSelector {

    private final Position toSelect;

    public SpawnPointSelectorStub(Position toSelect){
        this.toSelect = toSelect;
    }
    @Override
    public Position random() {
        return toSelect;
    }

}
