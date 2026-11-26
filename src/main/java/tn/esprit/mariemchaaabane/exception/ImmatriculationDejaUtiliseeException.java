package tn.esprit.mariemchaaabane.exception;

public class ImmatriculationDejaUtiliseeException extends RuntimeException {
    public ImmatriculationDejaUtiliseeException(String immatriculation) {
        super("L'immatriculation " + immatriculation + " est déjà utilisée");
    }
}