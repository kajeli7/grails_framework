package fr.fullstack.project

class Annonce {
    String title;
    String description;
    Double price;
    Boolean active = Boolean.FALSE;
    Date dateCreated;
    Date lastUpdated;
    List illustrations;

    static hasMany = [illustrations: Illustration];

    static belongsTo = [author: User]
    static constraints = {
        title nullable: false, blank: false, size: 5..255
        description nullable: false, blank: false
        price min:0d
        active nullable: false
        illustrations nullable: true
    }

    static mapping = {
        description type: 'text'
    }
}
