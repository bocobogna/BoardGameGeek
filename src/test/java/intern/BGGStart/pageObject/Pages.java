package intern.BGGStart.pageObject;

import intern.BGGStart.api.Api;
import intern.BGGStart.pageObject.fragments.BrowseMenuDropDown;
import intern.BGGStart.pageObject.fragments.MainHeader;
import intern.BGGStart.pageObject.fragments.SignInModal;
import intern.BGGStart.pageObject.fragments.UserMenuDropDown;

public class Pages {

    public AllGamesPage allGamesPage = new AllGamesPage();
    public CollectionPage collectionPage = new CollectionPage();
    public EditUserDetailsPage editUserDetailsPage = new EditUserDetailsPage();
    public GamePage gamePage = new GamePage();
    public HomePage homePage = new HomePage();
    public UserHomePage userHomePage = new UserHomePage();
    public UserProfilePage userProfilePage = new UserProfilePage();

    public MainHeader mainHeader = new MainHeader();
    public SignInModal loginForm = new SignInModal();
    public BrowseMenuDropDown browseMenuDropDown = new BrowseMenuDropDown();
    public UserMenuDropDown userMenuDropDown = new UserMenuDropDown();

    public Api api = new Api();

}
