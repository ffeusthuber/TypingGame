package domain;

public class StringSelectorStub implements StringSelector {
    private final String toSelect;

    public StringSelectorStub(String toSelect) {
        this.toSelect = toSelect;
    }

    @Override
    public String random() {
        return toSelect;
    }
}
