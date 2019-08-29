package intern.BGGStart.enums;

public enum BrowseMenuOption {
    ALL_BOARDGAMES("All Boardgames");

    private String option;

    BrowseMenuOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
