package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {
    private String name;
    private TimerService timerService;
    private int compteur;
    private boolean active;
    private final int valeurInitiale;

    public CompteARebours(String name, TimerService timerService, int valeurInitiale) {
        this.name = name;
        this.timerService = timerService;
        this.valeurInitiale = valeurInitiale;
        this.compteur = valeurInitiale;
        this.active = true;
        
        // S'inscrire comme observateur
        this.timerService.addTimeChangeListener(this);
        
        System.out.println( name + " créé avec valeur initiale: " + compteur);
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        // Vérifier si le compte à rebours est actif et répond seulement aux changements de secondes
        if (!active || !TimerChangeListener.SECONDE_PROP.equals(prop)) {
            return;
        }

        // Décrémenter le compteur
        if (compteur > 0) {
            compteur--;
            System.out.println(name + " : " + compteur);
            
            // Se désinscrire automatiquement quand on arrive à 0
            if (compteur == 0) {
                System.out.println(name + " terminé! (0 atteint)");
                seDesinscrire(); // Point 2: Auto-désinscription
            }
        }
    }
    
    /**
     * Point 2: Fonctionnalité pour se désinscrire
     */
    public void seDesinscrire() {
        if (active) {
            this.timerService.removeTimeChangeListener(this);
            this.active = false;
            System.out.println(name + " désinscrit automatiquement");
        }
    }
    
    /**
     * Méthode pour redémarrer le compte à rebours
     */
    public void redemarrer() {
        if (!active) {
            this.compteur = valeurInitiale;
            this.timerService.addTimeChangeListener(this);
            this.active = true;
            System.out.println(name + " redémarré avec valeur: " + compteur);
        }
    }
    
    public void arreterManuellement() {
        seDesinscrire();
    }
    
    // Getters
    public int getCompteur() {
        return compteur;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public String getName() {
        return name;
    }
    
    public int getValeurInitiale() {
        return valeurInitiale;
    }
}