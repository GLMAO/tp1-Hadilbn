package org.emp.gl.clients ; 

import org.emp.gl.timer.service.TimerChangeListener ;
import org.emp.gl.timer.service.TimerService; 


public class Horloge implements TimerChangeListener{

    String name; 
    TimerService timerService ;
    boolean active;
    int compteur;
    


    public Horloge (String name,TimerService timerService,int valeurInitiale) {
        this.name = name ; 
        this.timerService = timerService; 
        active=true;
        this.compteur = valeurInitiale;
        // S'inscrire comme observer du TimerService
        this.timerService.addTimeChangeListener(this);

        System.out.println ("Horloge "+name+" initialized!") ;
    }

    public void afficherHeure () {
        if (timerService != null)
            System.out.println (name + " affiche " + 
                                String.format("%02d:%02d:%02d",
                                timerService.getHeures() +":"+
                                timerService.getMinutes()+":"+
                                timerService.getSecondes())) ;
    }
    
    public void arreter() {
        if (timerService != null) {
            this.timerService.removeTimeChangeListener(this);
            System.out.println(name + " désinscrite");
        }
    }
     @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (!active) return;
        
        // Décrémenter seulement quand les secondes changent
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            if (compteur > 0) {
                compteur--;
                System.out.println(name + " : " + compteur);
                
                // Se désinscrire quand on arrive à 0
                if (compteur == 0) {
                    System.out.println( name + " terminé!");
                    arreter();
                }
            }
        }
    }

}
