package grails_framework

import fr.fullstack.project.Annonce
import fr.fullstack.project.Illustration
import fr.fullstack.project.User
import grails.gorm.transactions.Transactional

class BootStrap {

    @Transactional
    void addData(Object servletContext) {
        ["Alice", "Bob", "Charlie"].each{
            String username ->
                def userInstance = new User(username: username);
                (1..5).each {
                    Integer index ->
                        def annonceInstance = new Annonce(title: "Title $username $index",
                                description: "Description de l'annonce $username $index",
                                price: 100 * index,
                                active: Boolean.TRUE);
                        (1..5).each{
                            annonceInstance.addToIllustrations(new Illustration(filename: "filename_$username-$index-$it"))
                        }
                        userInstance.addToAnnonces(annonceInstance);
                        userInstance.save(flush:true, failOnError: true);
                }
        }
    }

    def init = {
        servletContext -> addData(servletContext)

    }
    def destroy = {
    }
}
