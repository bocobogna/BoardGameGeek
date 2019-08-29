package intern.BGGStart.enums;

public enum UserMenuOption {
    ACCOUNT("Account"),
    COLLECTION("Collection"),
    PROFILE("Profile"),
    SIGN_OUT("Sign Out");

    private String option;

    UserMenuOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
