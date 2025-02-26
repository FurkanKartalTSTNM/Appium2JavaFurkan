package tests.model;

public enum Folder {
    REPORTS("reports/"),
    SCREENSHOTS("screenshots/");

    private String folderName;

    Folder(String folderName) {this.folderName = folderName;}

    public String getFolderName() {return folderName;}
}
