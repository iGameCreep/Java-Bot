package fr.gamecreep.bot.commands;

public enum Commands {

    gnou("gnou","Incroyable blague 👀", "Alors c'est l'histoire d'un gnou qui se balade dans la savane et qui croise un autre groupe de gnou." + "\nL'autre groupe de gnou le voyant tout seul lui a donc demandé : " + "\nEh viens avec gnou :water_buffalo:", true),
    slashcommand("slashcommand","Slash Command", "This is a Slash Command !", false);

    private String name;
    private String message;
    private String description;
    private boolean ephemeral;

    Commands(String name, String description, String message, boolean ephemeral) {
        this.name = name;
        this.message = message;
        this.description = description;
        this.ephemeral = ephemeral;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getMessage() {
        return message;
    }

    public boolean getEphemeral() {
        return ephemeral;
    }

}
